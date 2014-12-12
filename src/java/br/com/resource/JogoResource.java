/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.resource;

import br.com.controller.JogosController;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import org.json.JSONArray;


/**
 * REST Web Service
 *
 * @author carleandro
 */
@Path("jogo")
public class JogoResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of JogoResource
     */
    public JogoResource() {
    }

    /**
     * Retrieves representation of an instance of br.com.resource.JogoResource
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/listarTodos")
    @Produces("application/json")
    public String listaTodos() {
        //TODO return proper representation object
        
        return new JogosController().listarTodos().toString();
    }
    @GET
    @Path("/getJogos")
    @Produces("application/json")
    public String getJogos(@QueryParam("latitude") String latitude, @QueryParam("longitude") String longitude, @QueryParam("distancia") String distancia) {   
        return new JogosController().getJogos(latitude, longitude, distancia).toString();
    }
    
    @GET
    @Path("/getDadosInicias")
    @Produces("application/json")
    public String getDadosIniciais(@QueryParam("jogo_id") String jogo_id) {   
        
        return new JogosController().getDadosIniciais(jogo_id).toString();
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
