/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.great.resource;

import br.com.great.controller.JogadoresController;
import br.com.great.model.Jogador;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 * REST Web Service jogadro
 *
 * @author carleandro
 */
@Path("jogador")
public class JogadorResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ImageResource
     */
    public JogadorResource() {
    }

    /**
     *
     * Método responsável por fazer chamada ao controller listando todos os
     * jogadores
     *
     * @return ArrayList tipo Jogador
     * @author Carleandro Noleto
     * @since 27/11/2014
     * @version 1.0
     */
    @GET
    @Path("/listarTodos")
    @Produces("application/json")
    public ArrayList<Jogador> listarTodos() {
        return new JogadoresController().listarTodos();
    }

    /**
     * Método responsável por get em todos os dados do jogador
     * @param id String
     * @return Jogador
     */
    @GET
    @Path("/getJogador")
    @Produces("application/json")
    public Jogador getJogador(@QueryParam("id") String id) {
        return new JogadoresController().getJogador(Integer.valueOf(id));
    }

    /**
     * Método responsável por realizar o login do jogador no servidor
     *
     * @param email String
     * @param password String
     * @return String
     * @author Carleandro Noleto
     * @since 27/11/2014
     * @version 1.0
     */
    @GET
    @Path("login")
    @Produces("application/json")
    public Jogador login(@QueryParam("email") String email, @QueryParam("password") String password) {
        return new JogadoresController().login(email, password);
    }

    /**
     * Metodo responsavel por cadastrar jogador
     * @param email String
     * @param password String
     * @return String
     */
    @GET
    @Path("cadastrarJogador")
    @Produces("application/json")
    public String cadastrarJogador(@QueryParam("email") String email, @QueryParam("password") String password) {
        String mensagem = new JogadoresController().cadastrarJogador(email, password);
        boolean salvo;
        if (mensagem.equals("true")) {
            salvo = true;
            mensagem = "Cadastrado com sucesso!";
        } else {
            salvo = false;
        }
        return ("{\"salvo\":\"" + salvo + "\",\"mensagem\":\"" + mensagem + "\"}");
    }

    /**
     * Funcao de registrar o dispositivo do jogador
     * @param jogador_id String
     * @param device_id String
     * @return String
     */
    @GET
    @Path("registroDevice")
    @Produces("application/json")
    public String registroDevice(@QueryParam("jogador_id") String jogador_id, @QueryParam("device_id") String device_id) {
        return (new JogadoresController().registroDevice(jogador_id, device_id));
    }

}
