/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.great.resource;

import br.com.great.controller.FotosController;
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
@Path("Image")
public class ImageResource {
    
    @Context
    private UriInfo context;
    
    private static final java.nio.file.Path BASE_DIR_CFOTO = Paths.get("/var/www/html/pervasivebd/cfoto/");

    /**
     * Creates a new instance of ImageResource
     */
    public ImageResource() {
    }
    /**
     * Método responsável por set em uma mecanica cfoto
     * @param image String
     * @param jogador_id String
     * @param latitude String
     * @param longitude String
     * @param cfotos_id String
     * @return String
     * @author Carleandro Noleto
     * @since 12/01/2015
     * @version 1.0
     */
    @GET
    @Path("/setFotoMeta")
    @Produces("application/json")
    public String setFotoMeta(@QueryParam("image") String image, @QueryParam("jogador_id") String jogador_id,
            @QueryParam("latitude") String latitude, @QueryParam("longitude") String longitude, @QueryParam("cfotos_id") String cfotos_id) {
            return String.valueOf(new FotosController().setCFoto(image, jogador_id, latitude, longitude, cfotos_id));
    }
    
    /**
     * Método responsável por criar uma imagem no servidor
     * @param in inputStream
     * @return String
     * @author Carleandro Noleto
     * @throws java.io.IOException Exception ao salvar foto
     * @since 12/01/2015
     * @version 1.0
     */
    @POST
    @Path("/setFoto")
    @Consumes(MediaType.WILDCARD)
    public String postSetFoto(InputStream in) throws IOException {
        String fileName = "" + System.currentTimeMillis();         
        fileName +=".jpg";
        try {
            // Copy the file to its location.
            Files.copy(in, BASE_DIR_CFOTO.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
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
    @Consumes("application/xml")
    public void putXml(String content) {
    }
}
