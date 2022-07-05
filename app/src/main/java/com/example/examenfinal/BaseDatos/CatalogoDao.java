package com.example.examenfinal.BaseDatos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.examenfinal.Entidad.Catalogos;

import java.util.List;

@Dao
public interface CatalogoDao {

    @Query("SELECT * FROM catalogoLibro")
    List<Catalogos> getTodo();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void Crear(Catalogos catalogo);
}
