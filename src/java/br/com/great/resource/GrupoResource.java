/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.great.resource;

import br.com.great.controller.GruposController;
import br.com.great.gerenciamento.ServidorJogo;
import static br.com.great.util.Constants.GRUPO_INSERIRPARTICIPANTE;
import static br.com.great.util.Constants.JOGO_LISTAGRUPOS;
import br.com.great.util.Operacoes;

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
     * @param jogo_id String
     * @return String
     * @author Carleandro Noleto
     * @since 10/12/2014
     * @version 1.0
     */
    @GET
    @Path("/getGrupos")
    @Produces("application/json")
    public String getGrupos(@QueryParam("jogo_id") String jogo_id) {
         return ServidorJogo.getInstance().acao(JOGO_LISTAGRUPOS, Integer.valueOf(jogo_id), null).toString();
    }
    
    /**
     * Método responsável get todos os grupos de um jogo
     * @param jogo_id String
     * @return String
     * @author Carleandro Noleto
     * @since 10/12/2014
     * @version 1.0
     */
    @GET
    @Path("/setGrupoParticipando")
    @Produces("application/json")
    public String setGrupoParticipando(@QueryParam("jogo_id") String jogo_id, @QueryParam("grupo_id") String grupo_id,
            @QueryParam("jogador_id") String jogador_id) {
        String[][] key = {{"jogador_id"}};
        String[][] value = {{jogador_id}} ;
         return ServidorJogo.getInstance().acaoGrupo(GRUPO_INSERIRPARTICIPANTE,Integer.valueOf(grupo_id),Integer.valueOf(jogo_id), (new Operacoes().toJSONArray(key, value))).toString();
    }
    /**
     * PUT method for updating or creating an instance of JogoResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }

}
