/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.great.resource;

import br.com.great.controller.TextosController;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;

/**
 * REST Web Service
 *
 * @author carleandro
 */
@Path("Texto")
public class TextoResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of TextoResource
     */
    public TextoResource() {
    }

    /**
     * Retrieves representation of an instance of br.com.resource.TextoResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }
    
    
    /**
     * Método responsável por set em uma mecanica csons
     * @param texto String
     * @param jogador_id String
     * @param latitude String
     * @param longitude String
     * @param ctexto_id String
     * @return String
     * @author Carleandro Noleto
     * @since 19/01/2015
     * @version 1.0
     */
    @GET
    @Path("/setTextoMeta")
    @Produces("application/json")
    public String setSomMeta(@QueryParam("texto") String texto, @QueryParam("jogador_id") String jogador_id,
            @QueryParam("latitude") String latitude, @QueryParam("longitude") String longitude, @QueryParam("ctextos_id") String ctexto_id) {
            return String.valueOf(new TextosController().setCTexto(texto, jogador_id, latitude, longitude, ctexto_id));
    }

    /**
     * PUT method for updating or creating an instance of TextoResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
