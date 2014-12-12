/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.resource;

import br.com.controller.JogadoresController;
import br.com.model.Jogador;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author carleandro
 */
@Path("jogador")
public class JogadorResource {
 @Context
    private UriInfo context;

    public JogadorResource(){
        
    }
    /**
	 * 
	 * Método responsável por fazer chamada ao controller listando
	 *todos os jogadores
	 * @return ArrayList</Jogador> 
	 * @author Carleandro Noleto
	 * @since 27/11/2014
	 * @version 1.0
	 */
	@GET
	@Path("/listarTodos")
	@Produces("application/json")
	public ArrayList<Jogador> listarTodos(){
            
		return new JogadoresController().listarTodos();
	}
        @GET
	@Path("/getJogador")
	@Produces("application/json")
	public Jogador getJogador(@QueryParam("id") String id){   
		return new JogadoresController().getJogador(id);
	}
    /**
	 * 
	 * Método responsável receber uma string
	 *
	 * @return String
	 * @author Carleandro Noleto
	 * @since 27/11/2014
	 * @version 1.0
	 */
    @POST
    @Consumes("application/json")
    public String putJson(String content) {
        System.err.println("boa: "+content);
        return "nome carleandro1";
    }
    /**
	 * 
	 * Método responsável por receber uma string e um id de parametro
	 *
	 * @return String
	 * @author Carleandro Noleto
	 * @since 27/11/2014
	 * @version 1.0
	 */
    
    /**
     * Método responsável por realizar o login do jogador no servidor
     * @param email
     * @param password
     * @return String
     * @author Carleandro Noleto
     * @since 27/11/2014
     * @version 1.0
     */
    @GET
    @Path("login")
    @Produces("application/json")
    public Jogador login(@QueryParam("email") String email, @QueryParam("password") String password){

        System.out.println("ent "+email+" / "+password+" sai");
		return new JogadoresController().login(email,password);
    }
    
    @GET
    @Path("cadastrarJogador")
    @Produces("application/json")
    public String cadastrarJogador(@QueryParam("email") String email, @QueryParam("password") String password){
       String mensagem=new JogadoresController().cadastrarJogador(email,password);
       boolean salvo;
       if(mensagem.equals("true")){
           salvo = true;
           mensagem = "Cadastrado com sucesso!";
       }else{
           salvo = false;
       }
		return ("{\"salvo\":\""+salvo+"\",\"mensagem\":\""+mensagem+"\"}");
    }
    
    @GET
    @Path("enviarfoto")
    @Produces("application/json")
    public FileInputStream foto(){
        FileInputStream file = null;
        try{   
            file = new FileInputStream("/var/www/pervasiveed/app/webroot/img/logo1.png");
        }catch(FileNotFoundException ex) {
            Logger.getLogger(JogadorResource.class.getName()).log(Level.SEVERE, null, ex);
        }
           return file;
       }
    
}