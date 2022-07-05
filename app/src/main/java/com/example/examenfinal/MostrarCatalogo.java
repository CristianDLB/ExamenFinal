package com.example.examenfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.examenfinal.Adaptador.CatalAdapter;
import com.example.examenfinal.BaseDatos.AppData;
import com.example.examenfinal.BaseDatos.CatalogoDao;
import com.example.examenfinal.Entidad.Catalogos;
import com.example.examenfinal.Factor.RetoFactor;
import com.example.examenfinal.Servicio.Services;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MostrarCatalogo extends AppCompatActivity {

    AppData DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_catalogo);

        FloatingActionButton faB = findViewById(R.id.fab);
        faB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CrearCatalogo.class);
                startActivity(intent);
            }
        });

        Retrofit retro = RetoFactor.LinkReto();
        Services servicio = retro.create(Services.class);

        DB = AppData.getDatabase(getApplicationContext());

        Call<List<Catalogos>> call =servicio.GetCatalogo();
        call.enqueue(new Callback<List<Catalogos>>() {
            @Override
            public void onResponse(Call<List<Catalogos>> call, Response<List<Catalogos>> response) {
                if (!response.isSuccessful()){
                    Log.e("AppCatalogo","ERROR APP");
                }else {
                    Log.i("AppCatalogo","Me Conecte :)");
                    List<Catalogos> c = response.body();

                    GuardarEnBase(c);

                    CatalAdapter adapter = new CatalAdapter(c);

                    RecyclerView rv = findViewById(R.id.RecyclearP);
                    rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    rv.setHasFixedSize(true);
                    rv.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Catalogos>> call, Throwable t) {
                Log.e("AppCatalogo","No Hubo conectividad");
            }
        });

    }

    private void GuardarEnBase(List<Catalogos> catalogosX) {
        CatalogoDao dao = DB.userDao();
        for (Catalogos catal : catalogosX) {
            dao.Crear(catal);
        }
    }
}