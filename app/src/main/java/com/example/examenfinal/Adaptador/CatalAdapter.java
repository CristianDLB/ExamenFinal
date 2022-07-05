package com.example.examenfinal.Adaptador;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examenfinal.DetalleCatLibro;
import com.example.examenfinal.Entidad.Catalogos;
import com.example.examenfinal.Factor.RetoFactor;
import com.example.examenfinal.R;
import com.example.examenfinal.Servicio.Services;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CatalAdapter extends RecyclerView.Adapter<CatalAdapter.catlHolder> {

    List<Catalogos> catalogo;

    public CatalAdapter(List<Catalogos> catalogo) {
        this.catalogo = catalogo;
    }

    @NonNull
    @Override
    public catlHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.muestras_catalogos,parent,false);
        catlHolder holder = new catlHolder(vista);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull catlHolder holder, int position) {
        View view = holder.itemView;
        Catalogos catal = catalogo.get(position);
        TextView TvTitulo = view.findViewById(R.id.idTituloMos);
        ImageView TvImg = view.findViewById(R.id.idImgMos);

        TvTitulo.setText(catal.titulo);

        Picasso.get().load(catal.caratula).into(TvImg);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retro = RetoFactor.LinkReto();
                Services service = retro.create(Services.class);
                Call<Catalogos> call = service.DetalleCatalogo(catal.id);
                call.enqueue(new Callback<Catalogos>() {
                    @Override
                    public void onResponse(Call<Catalogos> call, Response<Catalogos> response) {
                        if (!response.isSuccessful()){
                            Log.e("AppCatalogo","ERROR APP");
                        }else {
                            Log.i("AppCatalogo","Voy a Detalles de Catalogos");
                            Log.i("AppCatalogo",new Gson().toJson(response.body()));
                            Intent intentAppAnime = new Intent(view.getContext(), DetalleCatLibro.class);

                            String catJSON =new Gson().toJson(catal);
                            intentAppAnime.putExtra("CATALOGOS", catJSON);
                            view.getContext().startActivity(intentAppAnime);
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

    @Override
    public int getItemCount() {
        return catalogo.size();
    }

    class catlHolder extends RecyclerView.ViewHolder{

        public catlHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
