/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.great.resource;

import br.com.great.gerenciamento.ServidorJogo;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
import org.json.JSONArray;

/**
 * REST Web Service
 *
 * @author carleandro
 */
@Path("Servidor")
public class ServidorResource {

    @Context
    private UriInfo context;
    private static final java.nio.file.Path BASE_DIR_CFOTO = Paths.get("/var/www/html/pervasivebd/cfoto/");
    private static final java.nio.file.Path BASE_DIR_CVIDEO = Paths.get("/var/www/html/pervasivebd/cvideo/");
    private static final java.nio.file.Path BASE_DIR_CSOM = Paths.get("/var/www/html/pervasivebd/csom/");
     
    /**
     * Creates a new instance of ServidorResource_1
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
     * @return an HTTP response with content of the updated or created resource.
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
     * Método responsável por listar todos os pjogos
     * @param jogoJson
     * @return String Dados do jogo
     */
    @GET
    @Path("/getJogo")
    @Produces("application/json")
    public String getJogo(@QueryParam("json") JSONArray jogoJson) {
        //TODO return proper representation object
        return ServidorJogo.getInstance().acao(jogoJson).toString();
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
            System.err.println("Erro ao receber arquivo foto:"+ex.getMessage());
        }
        return fileName;   
    }
    /**
     * Método responsável por criar uma video no servidor
     * @param in inputStream
     * @return String
     * @author Carleandro Noleto
     * @throws java.io.IOException Exception ao salvar foto
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
            System.err.println("Erro ao receber arquivo video:"+ex.getMessage());
        }
        return fileName;   
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
             System.err.println("Erro ao receber arquivo som:"+ex.getMessage());
        }
        return fileName;   
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
