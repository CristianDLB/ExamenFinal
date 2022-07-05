package com.example.examenfinal.Entidad;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "catalogoLibro")
public class Catalogos {
    public String caratula;
    public String titulo;
    public String resumen;
    public String latitud;
    public String longitud;
    @PrimaryKey(autoGenerate = true)
    public int id;

    public Catalogos() {
    }

    public Catalogos(String caratula, String titulo, String resumen, String latitud, String longitud, int id) {
        this.caratula = caratula;
        this.titulo = titulo;
        this.resumen = resumen;
        this.latitud = latitud;
        this.longitud = longitud;
        this.id = id;
    }
}
