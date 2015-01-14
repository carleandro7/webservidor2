/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.resource;

import br.com.controller.FotosController;
import br.com.controller.VideosController;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author carleandro
 */
@Path("Video")
public class VideoResource {

    @Context
    private UriInfo context;

    private static final java.nio.file.Path BASE_DIR_CVIDEO = Paths.get("/var/www/html/pervasivebd/cvideo/");
    /**
     * Creates a new instance of VideoResource
     */
    public VideoResource() {
    }

    
    /**
     * Método responsável por criar um arquivo de video no servidor
     * @param in
     * @return String
     * @author Carleandro Noleto
     * @throws java.io.IOException
     * @since 12/01/2015
     * @version 1.0
     */
    @POST
    @Path("/setVideo")
    @Consumes(MediaType.WILDCARD)
    public String postSetVideo(InputStream in) throws IOException {
        String fileName = "" + System.currentTimeMillis();         
        fileName +=".3gp";
        try {
            // Copy the file to its location.
            Files.copy(in, BASE_DIR_CVIDEO.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception ex) {
            Logger.getLogger(GrupoResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fileName;   
    }
    
    /**
     * Método responsável por set em uma mecanica cvideos
     * @param video
     * @param jogador_id
     * @param latitude
     * @param longitude
     * @param cfotos_id
     * @return String
     * @author Carleandro Noleto
     * @since 12/01/2015
     * @version 1.0
     */
    @GET
    @Path("/setVideoMeta")
    @Produces("application/json")
    public String setVideoMeta(@QueryParam("video") String video, @QueryParam("jogador_id") String jogador_id,
            @QueryParam("latitude") String latitude, @QueryParam("longitude") String longitude, @QueryParam("cvideos_id") String cvideos_id) {
            return String.valueOf(new VideosController().setCVideo(video, jogador_id, latitude, longitude, cvideos_id));
    }
    
    /**
     * PUT method for updating or creating an instance of MecanicaResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }
}
