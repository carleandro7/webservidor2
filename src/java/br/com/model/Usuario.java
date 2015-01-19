/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.model;

/**
 *
 * @author carleandro
 */
public class Usuario {
    private int id;

    /**
     *
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id int
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return String
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email String
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return String
     */
    public String getSenha() {
        return senha;
    }

    /**
     *
     * @param senha String
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }
    private String email;
    private String senha;
}
