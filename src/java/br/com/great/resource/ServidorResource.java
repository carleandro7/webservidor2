/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.great.resource;

import br.com.great.gerenciamento.ServidorJogo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

/**
 * REST Web Service do Servidor
 *
 * @author carleandro
 */
@Path("servidor")
public class ServidorResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ServidorResource
     */
    public ServidorResource() {
    }

    /**
     * Retrieves representation of an instance of br.com.great.resource.ServidorResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of ServidorResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
    
    /**
     * Start o servidor dos jogos
     * @return String Informando se True ou False
     */
    @GET
    @Path("/startServidor")
    @Produces("application/json")
    public String startServidor() {           
        return Boolean.toString(ServidorJogo.getInstance().StartServidor());
    }
    
    /**
     * Stop no servidor dos jogos
     * @return String Informando se True ou False
     */
    @GET
    @Path("/stopServidor")
    @Produces("application/json")
    public String stopServidor() {           
        return Boolean.toString(ServidorJogo.getInstance().StopServidor());
    }
}
