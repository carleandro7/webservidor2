/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.great.resource;

import br.com.great.controller.JogosController;
import br.com.great.gerenciamento.ServidorJogo;
import static br.com.great.util.Constants.JOGO_LISTAEXECUTANDO;
import static br.com.great.util.Constants.JOGO_NEWJOGO;
import br.com.great.util.Operacoes;
import java.util.Random;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import org.json.JSONArray;
import org.json.simple.JSONObject;


/**
 * REST Web Service jogo
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
     * Método responsável por listar todos os pjogos
     * @return String Dados do jogo
     */
    @GET
    @Path("/listarTodos")
    @Produces("application/json")
    public String listaTodos() {
        //TODO return proper representation object
        return new JogosController().listarTodos().toString();
    }

    /**
     * Método responsável por listar todos os pjogos em uma distancia X
     * @param latitude String
     * @param longitude String
     * @param distancia String
     * @return String Dados dos jogos
     */
    @GET
    @Path("/getJogos")
    @Produces("application/json")
    public String getJogos(@QueryParam("latitude") String latitude, @QueryParam("longitude") String longitude, @QueryParam("distancia") String distancia) {   
        return new JogosController().getJogos(latitude, longitude, distancia).toString();
    }
    /**
     * Funcao de lista todos os dados de determinado jogo
     * @param jogo_id String
     * @return String Todos os dados de um jogo
     */
    @GET
    @Path("/getDadosInicias")
    @Produces("application/json"+";charset=utf-8")
    public String getDadosIniciais(@QueryParam("jogo_id") String jogo_id) {   
        
        return new JogosController().getDadosIniciais(jogo_id).toString();
    }
    
    /**
     * Enviar mensagem para todos os participantes de um jogo
     * @param jogo_id id do jogo que está participando o jogador
     * @param mensagem mensagem para todos os participantes do grupo
     * @param jogador_id String
     * @return String Se enviado mensagem True ou false
     */
    @GET
    @Path("/setEnviarMensagem")
    @Produces("application/json")
    public String setEnviarMensagem(@QueryParam("jogo_id") String jogo_id, @QueryParam("mensagem") String mensagem,
        @QueryParam("jogador_id") String jogador_id) {           
        return Boolean.toString(new JogosController().enviarMensagem(jogo_id, mensagem, jogador_id));
    }

    /**
     * PUT method for updating or creating an instance of JogoResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
    
     /**
     * Executar um determinada acao
     * @param jogo_id id do jogo que está participando o jogador
     * @param objeto_id String
     * @param grupo_id String
     * @param mecanica_id String
     * @param jogador_id String
     * @return String
     */
    @GET
    @Path("/getExecucaoAcao")
    @Produces("application/json")
    public String getExecucaoAcao(@QueryParam("jogo_id") String jogo_id, @QueryParam("objeto_id") String objeto_id, @QueryParam("grupo_id") String grupo_id,
            @QueryParam("mecanica_id") String mecanica_id, @QueryParam("jogador_id") String jogador_id) {           
        return Integer.toString(new Random().nextInt(2));
    }
    
    /**
     * Metodo responsavel por lista todas as instancia de um jogoPai
     * @param jogo_id Id do jogo que está participando o jogador
     * @param jogador_id Id do jogador
     * @return String
     */
    @GET
    @Path("/getListaExecutando")
    @Produces("application/json")
    public String getListaExecutando(@QueryParam("jogo_id") String jogo_id, @QueryParam("jogador_id") String jogador_id) {           
        String[][] key = {{"jogador_id"}} ;
        String[][] value = {{jogador_id}} ;
        return ServidorJogo.getInstance().acao(JOGO_LISTAEXECUTANDO,Integer.valueOf(jogo_id) , new Operacoes().toJSONArray(key, value)).toString();
    }
    
    /**
     * Criar uma nova instancia do jogo no servidor
     * @param jogopai_id Id do pjogo
     * @param nomefecticio Nome ficticio para um jogo
     * @param jogador_id Id do jogador que esta criando o jogo
     * @return String
     */
    @GET
    @Path("/getNewJogo")
    @Produces("application/json")
    public String getNewJogo(@QueryParam("jogopai_id") String jogopai_id, @QueryParam("jogador_id") String jogador_id,
            @QueryParam("nomeficticio") String nomefecticio) {           
        String[][] key = {{"jogopai_id","jogador_id","nomeficticio"}} ;
        String[][] value = {{jogopai_id,jogador_id, nomefecticio}} ;
        return ServidorJogo.getInstance().acao(JOGO_NEWJOGO,0, new Operacoes().toJSONArray(key, value)).toString();
    }

}
