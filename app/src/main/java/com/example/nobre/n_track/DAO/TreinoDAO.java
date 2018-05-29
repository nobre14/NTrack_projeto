package com.example.nobre.n_track.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.nobre.n_track.modelo.Treino;

public class TreinoDAO extends SQLiteOpenHelper{
    public TreinoDAO(Context context) {
        super(context, "NTrack", null, 1);
    }
    private Long id;
    private String data;
    private String autodromo;
    private String melhorTempo;
    private String setup;
    private String velocidadeMaxima;
    private int numVoltas;

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Treino(id INTEGER PRIMARY KEY," +
                "data TEXT NOT NULL," +
                "autodromo TEXT NOT NULL," +
                "melhorTempo TEXT NOT NULL," +
                "setup TEXT NOT NULL," +
                "velocidadeMaxima TEXT NOT NULL," +
                "numVoltas INTEGER NOT NULL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Treino;";
        db.execSQL(sql);
        onCreate(db);
    }

    public void insert(Treino treino){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = pegaDadosTreino(treino);
        db.insert("Moto", null, dados);
    }

    private ContentValues pegaDadosTreino(Treino treino) {
        ContentValues dados = new ContentValues();
        dados.put("data", treino.getData());
        dados.put("autodromo", treino.getAutodromo());
        dados.put("melhorTempo", treino.getMelhorTempo());
        dados.put("setup", treino.getSetup());
        dados.put("velocidadeMaxima", treino.getVelocidadeMaxima());
        dados.put("numVoltas", treino.getNumVoltas());
        return dados;
    }
}
