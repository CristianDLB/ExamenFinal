package com.example.examenfinal.Servicio;

import com.example.examenfinal.Entidad.Catalogos;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Services {
    @GET("catalogo")
    Call<List<Catalogos>> GetCatalogo();

    @GET("catalogo/{id}")
    Call<Catalogos> DetalleCatalogo(@Path("id")int id);

    @POST("catalogo")
    Call<Catalogos> PostCatalogo(@Body Catalogos catalogo);

    @PUT("catalogo/{id}")
    Call<Catalogos> EditCatalogo(@Path("id")int id, @Body Catalogos catalogo);

    @DELETE("catalogo/{id}")
    Call<Catalogos> EliCatalogo(@Path("id")int id);

}
