package com.example.crudcadastrofuncionario;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.crudcadastrofuncionario.dao.FuncionarioDao;
import com.example.crudcadastrofuncionario.entity.Funcionario;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    int excluir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ListView listViewFunvionario = findViewById(R.id.listViewUsuario);

        ArrayAdapter<Funcionario> arrayAdapterFuncionario =
                new ArrayAdapter<>(getApplicationContext(),
                        android.R.layout.simple_list_item_1);

        FuncionarioDao funcionarioDao = new FuncionarioDao();
        List<Funcionario> funcionario = funcionarioDao.listar();
        arrayAdapterFuncionario.addAll(funcionario);
        listViewFunvionario.setAdapter(arrayAdapterFuncionario);

        Intent intetnt = new Intent(this, MainActivity.class);

        listViewFunvionario.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            final public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Funcionario funcionario = (Funcionario)  parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),"Carregando para editar "+id , Toast.LENGTH_LONG).show();

                Bundle parametros = new Bundle();


                parametros.putString("idFuncionario", String.valueOf((position)));
                parametros.putString("nomeFuncionario", funcionario.getNome());
                parametros.putString("idadeFuncionario", funcionario.getIdade());
                parametros.putString("sexoFuncionario", funcionario.getSexo());
                parametros.putString("emailFuncionario", funcionario.getEmail());
                parametros.putString("profissaoFuncionario", funcionario.getProfissao());


                intetnt.putExtras(parametros);


                startActivity(intetnt);
                finish();
            }
        });

            Intent refresh = new Intent(this, ListActivity.class);

            AlertDialog.Builder msgBox = new AlertDialog.Builder(this);
            msgBox.setTitle("Excluir...");
            msgBox.setIcon(android.R.drawable.ic_menu_delete);
            msgBox.setMessage("Deseja excluir este funcionário?");
        msgBox.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {

                FuncionarioDao funcionario = new FuncionarioDao();
                funcionario.excluir(excluir);

               startActivity(refresh);
               finish();
            }
        }).setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                Toast.makeText(ListActivity.this, "Exclusão cancelada.", Toast.LENGTH_SHORT).show();

            }

        });


        listViewFunvionario.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long id) {

                excluir = (int) id;
                msgBox.show();

                return true;
            }

        });
    }



}