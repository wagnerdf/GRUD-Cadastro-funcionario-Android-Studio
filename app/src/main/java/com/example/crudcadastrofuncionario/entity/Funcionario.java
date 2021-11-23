package com.example.crudcadastrofuncionario.entity;

public class Funcionario {

    private Integer id;
    private String nome;
    private String idade;
    private String sexo;
    private String email;
    private String profissao;

    public String toString(){
        return"[ID]: "+id+" [Nome]: "+nome+" [Idade]: "+idade+" [Sexo]: "+sexo+"[Email]: "+email+" [Profissão]: "+profissao;
    }

    public boolean isNovo() {

        if (this.id == null) {
            return true; /*Inserir novo*/
        }else if (this.id != null && this.id > 0) {
            return false; /*Atualizar*/
        }
        return id == null;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }


}
