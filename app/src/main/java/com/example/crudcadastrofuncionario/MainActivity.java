package com.example.crudcadastrofuncionario;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


import androidx.navigation.ui.AppBarConfiguration;


import com.example.crudcadastrofuncionario.bdSqlite.Conexao;
import com.example.crudcadastrofuncionario.dao.FuncionarioDao;
import com.example.crudcadastrofuncionario.databinding.ActivityMainBinding;
import com.example.crudcadastrofuncionario.entity.Funcionario;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String limpar;
    String id;
    public EditText textNome, textIdade, textSexo, textEmail, textProfissao;


    public void salvar(View view){

        textNome = findViewById(R.id.textNome);
        textIdade = findViewById(R.id.textIdade);
        textSexo = findViewById(R.id.textSexo);
        textEmail = findViewById(R.id.textEmail);
        textProfissao = findViewById(R.id.textProfissao);

        Funcionario funcionario = new Funcionario();

        funcionario.setId(Integer.valueOf(id));
        funcionario.setNome(textNome.getText().toString());
        funcionario.setIdade(textIdade.getText().toString());
        funcionario.setSexo(textSexo.getText().toString());
        funcionario.setEmail(textEmail.getText().toString());
        funcionario.setProfissao(textProfissao.getText().toString());

        FuncionarioDao funcionarioDao = new FuncionarioDao();
        funcionarioDao.salvar(funcionario);

        limpar = "";
        id = null;
        textNome.setText(limpar);
        textIdade.setText(limpar);
        textSexo.setText(limpar);
        textEmail.setText(limpar);
        textProfissao.setText(limpar);

        Toast.makeText(getApplicationContext(),
                "Funcionário salvo", Toast.LENGTH_LONG).show();


    }

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        final Intent intent = new Intent (this, ListActivity.class);
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);

            }
        });

        new Conexao(getApplicationContext(),
                "db.funcionario",
                null,
                1);

        Intent intentRecebedora = getIntent();

        Bundle parametros = intentRecebedora.getExtras();

        if(parametros != null){

            id = parametros.getString("idFuncionario");
            String nome = parametros.getString("nomeFuncionario");
            String idade = parametros.getString("idadeFuncionario");
            String sexo = parametros.getString("sexoFuncionario");
            String email = parametros.getString("emailFuncionario");
            String profissao = parametros.getString("profissaoFuncionario");

            textNome = (EditText)findViewById(R.id.textNome);
            textIdade = (EditText)findViewById(R.id.textIdade);
            textSexo = (EditText)findViewById(R.id.textSexo);
            textEmail = (EditText)findViewById(R.id.textEmail);
            textProfissao = (EditText)findViewById(R.id.textProfissao);

            textNome.setText(nome);
            textIdade.setText(idade);
            textSexo.setText(sexo);
            textEmail.setText(email);
            textProfissao.setText(profissao);

            Toast.makeText(MainActivity.this, "Nome: "+nome +" Idade: "+idade, Toast.LENGTH_SHORT).show();


        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}