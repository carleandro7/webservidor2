/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.resource;

import br.com.controller.GruposController;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.media.Media;
import javax.ws.rs.Consumes;
import javax.ws.rs.Encoded;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;


/**
 * REST Web Service
 *
 * @author carleandro
 */
@Path("Grupo")
public class GrupoResource {

    @Context
    private UriInfo context;
    private static final java.nio.file.Path BASE_DIR = Paths.get(System.getProperty("user.home"), "Documentos", "Saved Images");
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
    
    @POST
    @Path("/setFoto")
    @Consumes(MediaType.WILDCARD)
    public String postFormData(InputStream in) throws IOException {
        String fileName = "" + System.currentTimeMillis();         
        fileName +=".jpg";
        try {
            // Copy the file to its location.
            Files.copy(in, BASE_DIR.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception ex) {
            Logger.getLogger(GrupoResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.err.println("Conectado:"+in.toString());
        //System.err.println(headers.getRequestHeader("Authorization").get(0)+" teste "+in.toString());
        // Return a 201 Created response with the appropriate Location header.
        return fileName;   
    }
    //setFotoMeta
    /**
     * Método responsável get todos os grupos de um jogo
     * @param image
     * @param jogador_id
     * @param latitude
     * @param longitude
     * @param cfotos_id
     * @return Boolena
     * @author Carleandro Noleto
     * @since 12/01/2015
     * @version 1.0
     */
    @GET
    @Path("/setFotoMeta")
    @Produces("application/json")
    public Boolean setFotoMeta(@QueryParam("image") String image, @QueryParam("jogador_id") String jogador_id,
            @QueryParam("latitude") String latitude, @QueryParam("longitude") String longitude, @QueryParam("cfotos_id") String cfotos_id) {
         return new GruposController().setCFoto(image, jogador_id, latitude, longitude, cfotos_id);
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
