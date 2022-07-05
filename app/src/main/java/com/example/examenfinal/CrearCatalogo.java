package com.example.examenfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.examenfinal.Entidad.Catalogos;
import com.example.examenfinal.Factor.RetoFactor;
import com.example.examenfinal.Servicio.Services;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class CrearCatalogo extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_catalogo);

        Button BtnCrearLbro = findViewById(R.id.BtnCreandoCa);
        EditText BtTitulo = findViewById(R.id.idTituloCre);
        EditText BtResumen = findViewById(R.id.idResumenCre);
        EditText BtLatitud = findViewById(R.id.idLatCre);
        EditText BtLongitud = findViewById(R.id.idLongCre);

        EditText BtCaratula = findViewById(R.id.idImgCre);

        BtnCrearLbro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retro = RetoFactor.LinkReto();
                Services service = retro.create(Services.class);
                Catalogos catalogoFull = new Catalogos();

                catalogoFull.titulo = String.valueOf(BtTitulo.getText());
                catalogoFull.resumen = String.valueOf(BtResumen.getText());
                catalogoFull.caratula = String.valueOf(BtCaratula.getText());

                catalogoFull.latitud = String.valueOf(BtLatitud.getText());
                catalogoFull.longitud = String.valueOf(BtLongitud.getText());

                Call<Catalogos> call = service.PostCatalogo(catalogoFull);
                call.enqueue(new Callback<Catalogos>() {
                    @Override
                    public void onResponse(Call<Catalogos> call, Response<Catalogos> response) {
                        if (response.isSuccessful()){
                            Log.i("AppCatalogo","Hubo conectividad");
                            Log.i("AppCatalogo",new Gson().toJson(response.body()));
                            Log.i("AppCatalogo","Libro  Registrada");
                            Toast.makeText(CrearCatalogo.this,"Libro Registrado",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Catalogos> call, Throwable t) {
                        Log.e("AppCatalogo","No Hubo conectividad");
                    }
                });


            }
        });

    }
}