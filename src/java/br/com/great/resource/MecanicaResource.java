/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.great.resource;


import br.com.great.controller.MecanicaController;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;

/**
 * REST Web Service Mecanica
 *
 * @author carleandro
 */
@Path("mecanica")
public class MecanicaResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of MecanicaResource
     */
    public MecanicaResource() {
    }

    /**
     * Retrieves representation of an instance of br.com.resource.MecanicaResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/xml")
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }
    
    /**
     * Método responsável por get em uma Mecanica de uma missao
     * @param jogo_id String
     * @return String
     */
    @GET
    @Path("/getMecanica")
    @Produces("application/json")
    public String getJogos(@QueryParam("mecanica_id") String jogo_id) {
         return new MecanicaController().getMecania(jogo_id).toString();
    }

    /**
     * PUT method for updating or creating an instance of MecanicaResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }
}
