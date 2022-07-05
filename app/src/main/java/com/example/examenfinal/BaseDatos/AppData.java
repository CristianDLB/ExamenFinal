package com.example.examenfinal.BaseDatos;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.examenfinal.Entidad.Catalogos;

@Database(entities = {Catalogos.class}, version = 1)
public abstract class AppData extends RoomDatabase {

    public abstract CatalogoDao userDao();

    public static AppData getDatabase (Context context){
        return Room.databaseBuilder(context,AppData.class,"databaseDB.catalogosDB")
                .allowMainThreadQueries()
                .build();

    }

}
