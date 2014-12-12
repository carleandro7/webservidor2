/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.resource;

import br.com.controller.GruposController;
import javax.ws.rs.Consumes;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * REST Web Service
 *
 * @author carleandro
 */
@Path("Grupo")
public class GrupoResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GruposResource
     */
    public GrupoResource() {
    }

    /**
     * Método responsável get todos os grupos de um jogo
     * @param jogo_id
     * @return String
     * @author Carleandro Noleto
     * @since 10/12/2014
     * @version 1.0
     */
    @GET
    @Path("/getGrupos")
    @Produces("application/json")
    public String getJogos(@QueryParam("jogo_id") String jogo_id) {
         return new GruposController().getJogos(jogo_id).toString();
    }
    
    /**
     * PUT method for updating or creating an instance of JogoResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }

}