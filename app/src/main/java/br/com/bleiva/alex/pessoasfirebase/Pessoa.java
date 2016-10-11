package br.com.bleiva.alex.pessoasfirebase;

import android.databinding.Bindable;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.lang.annotation.Annotation;

/**
 * Created by Alex on 06/10/2016.
 */

public class Pessoa implements Serializable {

    private String nome;
    private String email;
    private int idade;
    private String id;

    public Pessoa() {
    }

    public Pessoa(String nome, String email, int idade) {
        this.nome = nome;
        this.email = email;
        this.idade = idade;
    }

    @Exclude
    public String getId() {
        return id;
    }

    @Exclude
    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

}
