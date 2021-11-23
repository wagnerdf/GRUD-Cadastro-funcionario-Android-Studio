package com.example.crudcadastrofuncionario.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.crudcadastrofuncionario.bdSqlite.Conexao;
import com.example.crudcadastrofuncionario.entity.Funcionario;

import java.util.ArrayList;
import java.util.List;

public class FuncionarioDao {

    public static ArrayList<Integer>arrayIds;

    public void salvar(Funcionario funcionario){

        if(funcionario.isNovo()){/*Gravar um novo*/

        ContentValues values = new ContentValues();
        values.put("nome", funcionario.getNome());
        values.put("idade", funcionario.getIdade());
        values.put("sexo", funcionario.getSexo());
        values.put("email", funcionario.getEmail());
        values.put("profissao", funcionario.getProfissao());

            try{
                SQLiteDatabase conexao = Conexao.getInstance();
                conexao.insert("funcionario",null, values);
            }catch (Exception e){
                Log.e("DB_LOG", "onCreate: "+e.getLocalizedMessage());
            }
        }else {/*Atualizar funcionario*/

            ContentValues values = new ContentValues();
            values.put("nome", funcionario.getNome());
            values.put("idade", funcionario.getIdade());
            values.put("sexo", funcionario.getSexo());
            values.put("email", funcionario.getEmail());
            values.put("profissao", funcionario.getProfissao());
            String[] idFuncionario = {String.valueOf(arrayIds.get(Integer.valueOf(funcionario.getId())))};

            try {

                SQLiteDatabase conexao = Conexao.getInstance();
                conexao.update("Funcionario",
                                values,
                                "id = ?",
                                idFuncionario);


            }catch (Exception e){
                Log.e("DB_LOG", "onCreate: "+e.getLocalizedMessage());
            }


        }


    }


    public List<Funcionario> listar(){

        arrayIds = new ArrayList<>();
        SQLiteDatabase conexao = Conexao.getInstance();
        Cursor c = conexao.query("funcionario", new String[] {"id","nome","idade","sexo","email","profissao"},
                null, null, null,null,null,null);
        List<Funcionario> funcionarios = new ArrayList<Funcionario>();
        if(c.moveToFirst()){
            do{
                arrayIds.add(c.getInt(0));
                Funcionario funcionario = new Funcionario();
                funcionario.setId(c.getInt(0));
                funcionario.setNome(c.getString(1 ));
                funcionario.setIdade(c.getString(2 ));
                funcionario.setSexo(c.getString(3));
                funcionario.setEmail(c.getString(4));
                funcionario.setProfissao(c.getString(5));

                funcionarios.add(funcionario);


            }while (c.moveToNext());
        }
        return funcionarios;
    }

    public void excluir(int id){
        try {
            SQLiteDatabase conexao = Conexao.getInstance();

            conexao.delete(
                        "Funcionario",
                        "id = ?",
                        new String[]{String.valueOf(arrayIds.get(id))}
            );
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
