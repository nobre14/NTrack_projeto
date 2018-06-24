package com.example.nobre.ntrack.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.nobre.ntrack.modelo.Moto;

import java.util.ArrayList;
import java.util.List;

public class MotoDAO extends SQLiteOpenHelper{


    public MotoDAO(Context context) {
        super(context, "NTrack", null, 8);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Moto(id INTEGER PRIMARY KEY, marca TEXT NOT NULL," +
                " modelo TEXT NOT NULL, ano INTEGER NOT NULL, cilindrada INTEGER NOT NULL);";

        String sqlMarcas = "CREATE TABLE Marcas(id INTEGER PRIMARY KEY, nomeMarca TEXT NOT NULL);";
        db.execSQL(sql);
        db.execSQL(sqlMarcas);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Moto;";
        String sqlMarcas = "DROP TABLE IF EXISTS Marcas;";
        db.execSQL(sql);
        db.execSQL(sqlMarcas);
        onCreate(db);
    }

    public void insert(Moto moto){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = pegaDadosMoto(moto);
        db.insert("Moto", null, dados);
    }

    private ContentValues pegaDadosMoto(Moto moto) {
        ContentValues dados = new ContentValues();
        dados.put("marca", moto.getMarca());
        dados.put("modelo", moto.getModelo());
        dados.put("cilindrada", moto.getCilndrada());
        dados.put("ano", moto.getAno());
        return dados;
    }

    public List<Moto> buscaMotos() {
        String sql = "SELECT * FROM Moto;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Moto> motos = new ArrayList<>();
        while (c.moveToNext()){
            Moto moto = new Moto();
            moto.setId(c.getLong(c.getColumnIndex("id")));
            moto.setMarca(c.getString(c.getColumnIndex("marca")));
            moto.setModelo(c.getString(c.getColumnIndex("modelo")));
            moto.setCilndrada(c.getInt(c.getColumnIndex("cilindrada")));
            moto.setAno(c.getInt(c.getColumnIndex("ano")));
            motos.add(moto);
        }
        c.close();
        return motos;
    }

    public void deleta(Moto moto){
        SQLiteDatabase db = getReadableDatabase();
        String[] parametros = {moto.getId().toString()};
        db.delete("Moto", "id = ?", parametros);
    }

    public void altera(Moto moto){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = pegaDadosMoto(moto);
        String[] parametros = {moto.getId().toString()};
        db.update("Moto", dados, "id = ?", parametros);
    }

    public List<String> buscarMarcas() {
        String sql = "SELECT * FROM Marcas;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<String> marcas = new ArrayList<>();
        String marca;
        while (c.moveToNext()){
            marca = c.getString(c.getColumnIndex("nomeMarca"));
            marcas.add(marca);
        }
        c.close();
        return marcas;
    }

    public void insertMarcas(String marca){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();
        dados.put("nomeMarca", marca);
        db.insert("Marcas", null, dados);
    }
}
