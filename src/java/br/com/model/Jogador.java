/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.model;

import java.io.Serializable;

/**
 *
 * @author carleandro
 */
public class Jogador{
    private int id;
    private String email;
    private String password;
    private String iddispositivo;

    /**
     *
     * @return String
     */
    public String getIddispositivo() {
        return iddispositivo;
    }

    /**
     *
     * @param iddispositivo String
     */
    public void setIddispositivo(String iddispositivo) {
        this.iddispositivo = iddispositivo;
    }

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
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password String
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
