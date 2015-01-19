/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.resource;

import br.com.controller.SonsController;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author carleandro
 */
@Path("Som")
public class SomResource {

    @Context
    private UriInfo context;

    private static final java.nio.file.Path BASE_DIR_CSOM = Paths.get("/var/www/html/pervasivebd/csom/");
    /**
     * Creates a new instance of SomResource
     */
    public SomResource() {
    }
    
    /**
     * Método responsável por set em uma mecanica csons
     * @param som String
     * @param jogador_id String
     * @param latitude String
     * @param longitude String
     * @param csons_id String
     * @return String
     * @author Carleandro Noleto
     * @since 12/01/2015
     * @version 1.0
     */
    @GET
    @Path("/setSomMeta")
    @Produces("application/json")
    public String setSomMeta(@QueryParam("som") String som, @QueryParam("jogador_id") String jogador_id,
            @QueryParam("latitude") String latitude, @QueryParam("longitude") String longitude, @QueryParam("csons_id") String csons_id) {
            return String.valueOf(new SonsController().setCSom(som, jogador_id, latitude, longitude, csons_id));
    }
    
     /**
     * Método responsável por criar um arquivo de som no servidor
     * @param in InputStream
     * @return String
     * @author Carleandro Noleto
     * @throws java.io.IOException Execeção ao salvar o arquivo em mp3
     * @since 12/01/2015
     * @version 1.0
     */
    @POST
    @Path("/setSom")
    @Consumes(MediaType.WILDCARD)
    public String postSetSom(InputStream in) throws IOException {
        String fileName = "" + System.currentTimeMillis();         
        fileName +=".mp3";
     
        try {
            // Copy the file to its location.
            Files.copy(in, BASE_DIR_CSOM.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception ex) {
            Logger.getLogger(GrupoResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fileName;   
    }
    
    /**
     * PUT method for updating or creating an instance of MecanicaResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes("application/json")
    public void putXml(String content) {
    }
}
