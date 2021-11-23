package com.example.crudcadastrofuncionario.bdSqlite;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Conexao extends SQLiteOpenHelper {

    private static SQLiteDatabase instance;

    public static SQLiteDatabase getInstance(){
        return instance;
    }

    public Conexao(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        instance = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tabelaFuncionario = "";
        tabelaFuncionario += " create table funcionario (";
        tabelaFuncionario += " id integer primary key autoincrement, ";
        tabelaFuncionario += " nome varchar(255), ";
        tabelaFuncionario += " idade varchar(255), ";
        tabelaFuncionario += " sexo varchar(255), ";
        tabelaFuncionario += " email varchar(255), ";
        tabelaFuncionario += " profissao varchar(255) ";
        tabelaFuncionario += "); ";
        try{

            db.execSQL(tabelaFuncionario);

        }catch (SQLException e){

            Log.e("DB_LOG", "onCreate: "+e.getLocalizedMessage());

        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
