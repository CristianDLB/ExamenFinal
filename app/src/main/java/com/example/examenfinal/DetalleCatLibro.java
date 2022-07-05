package com.example.examenfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.examenfinal.BaseDatos.AppData;
import com.example.examenfinal.BaseDatos.CatalogoDao;
import com.example.examenfinal.Entidad.Catalogos;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DetalleCatLibro extends AppCompatActivity {

    AppData DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_cat_libro);

        String catJSON = getIntent().getStringExtra("CATALOGOS");
        Catalogos catalogo = new Gson().fromJson(catJSON,Catalogos.class);

        ImageView tvImgs = findViewById(R.id.ImgDetalle);
        TextView tvNombrs = findViewById(R.id.TituDetalle);
        TextView tvDescr= findViewById(R.id.ResuDetalle);

        Picasso.get().load(catalogo.caratula).into(tvImgs);
        tvNombrs.setText(catalogo.titulo);
        tvDescr.setText(catalogo.resumen);

        DB = AppData.getDatabase(getApplicationContext());

        Button btnCompar = findViewById(R.id.BtnVerComprar);
        btnCompar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(intent);
            }
        });

        Button btnFavorito = findViewById(R.id.BtnFavorito);
        btnFavorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CatalogoDao dao = DB.userDao();
                List<Catalogos> catalogosXd =dao.getTodo();
                Log.i("AppCatalogo",new Gson().toJson(catalogosXd));
                Toast.makeText(DetalleCatLibro.this,"Registrado BD",Toast.LENGTH_LONG).show();
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