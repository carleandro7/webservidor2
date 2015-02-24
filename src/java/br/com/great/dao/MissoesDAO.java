/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.great.dao;

import br.com.great.factory.ConnectionFactory;
import br.com.great.model.Missao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Classe responsavel realizar toda a interação com banco de dados relacionado com entidade missoes
 * @author carleandro
 */
public class MissoesDAO extends ConnectionFactory{
    
    private static MissoesDAO instance;
                /**
	 * 
	 * Método responsável por criar uma instancia da classe MissoesDAO (Singleton)
	 *
	 * @return static
	 * @author Carleandro Noleto
	 * @since 10/12/2014
	 * @version 1.0
	 */
	public static MissoesDAO getInstance(){
		if(instance == null)
			instance = new MissoesDAO();
		return instance;
	}
        
        	/**
	 * 
	 * Método responsável por listar todos os missoes dos grupos de um jogo do banco
	 *
         * @param jogo_id String
	 * @return JSONArray
	 * @author Carleandro Noleto
	 * @since 10/12/2014
	 * @version 1.0
	 */
	public JSONArray getTodos(String jogo_id){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JSONArray missoes = null;
		Connection conexao = criarConexao();
		try {
                        missoes= new JSONArray();
                         String sql = "SELECT  `missoes`.`id`, `missoes`.`nome`, `missoes`.`ordem`, `missoes`.`grupo_id`   FROM `grupos` " +
                                " LEFT JOIN `missoes`  ON (`missoes`.`grupo_id` = `grupos`.`id`) " +
                                " WHERE  `grupos`. `jogo_id` = "+jogo_id;
                        pstmt = conexao.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				JSONObject missao = new JSONObject();
                                missao.put("id",rs.getInt("id"));
                                missao.put("nome",rs.getString("nome"));
                                missao.put("ordem",rs.getInt("ordem"));
                                missao.put("grupo_id",rs.getInt("grupo_id"));
                                
				missoes.put(missao);
			}
                        
			
		} catch (SQLException | JSONException e) {
			System.out.println("Erro ao listar todas as missoes: " + e.getMessage());
                } finally {
			fecharConexao(conexao, pstmt, rs);
		}
		return missoes;
	}
        
         /**
	 * Método responsável por listar todos as missoes de um grupo
	 *
         * @param grupo_id String
	 * @return JSONArray
	 * @author Carleandro Noleto
	 * @since 10/12/2014
	 * @version 1.0
	 */
	public ArrayList<Missao> getMissoesGrupo(int grupo_id){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Missao> missoes = null;
		Connection conexao = criarConexao();
		try {
                        missoes= new ArrayList<Missao>();
                         String sql = "SELECT  *  FROM missoes  WHERE  grupo_id = "+grupo_id+"  ORDER BY ordem ";
                        pstmt = conexao.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				Missao missao = new Missao();
                                missao.setId(rs.getInt("id"));
                                missao.setNome(rs.getString("nome"));
                                missao.setOrdem(rs.getInt("ordem"));
                                missao.setGrupo_id(rs.getInt("grupo_id"));
                                missao.setLongitude(rs.getString("longitude"));
                                missao.setLatitude(rs.getString("latitude"));
				missoes.add(missao);
			}
                        
			
		} catch (SQLException e) {
			System.out.println("Erro ao listar todas as missoes: " + e.getMessage());
                } finally {
			fecharConexao(conexao, pstmt, rs);
		}
		return missoes;
	}

	/**
	 * 
	 * Método responsável por listar todas as missoes em uma distancia X para um jogador
	 *
         * @param grupo_id Id grupo das missoes
         * @param distancia Distancia do raio para pesquisar
         * @param latitude String
         * @param longitude String
         * @param missoes ArrayObject
         * @param sqlMissoes Id das missoes que nao devem esta na lista
	 * @return JSONArray Retorna id das missoes
	 * @author Carleandro Noleto
	 * @since 10/12/2014
	 * @version 1.0
	 */
	public JSONArray getMissoesRegiao(int distancia, int grupo_id, String latitude, String longitude, JSONArray missoes, String sqlMissoes){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
                String sql ="";
		Connection conexao = criarConexao();
		try {
                        if(distancia != 0){
                         sql = "select missoes.id from missoes where missoes.grupo_id ="+
                                 grupo_id+" AND (((3956 * 2 * ASIN(SQRT(POWER(SIN((abs("+latitude+")"
                                 + " - abs(missoes.latitude)) * pi()/180 / 2),2) + COS(abs("+latitude+") "
                                 + "* pi()/180 ) * COS(abs(missoes.latitude) * pi()/180) * POWER(SIN((abs("+longitude+") "
                                 + "- abs(missoes.longitude)) * pi()/180 / 2), 2)))) * 1.609344) < "+distancia+")"+sqlMissoes;
                        }else{ 
                            sql = "select missoes.id from missoes where missoes.grupo_id ="+
                                 grupo_id+" "+sqlMissoes;
                        }
                        pstmt = conexao.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				JSONObject missao = new JSONObject();
                                missao.put("id",rs.getInt("missoes.id"));
                                missao.put("prioridade",distancia);
				missoes.put(missao);
			}
		} catch (SQLException | JSONException e) {
			System.out.println("Erro ao listar todas as missoes em uma distancia: " + e.getMessage());
                } finally {
			fecharConexao(conexao, pstmt, rs);
		}
		return missoes;
	}
        /**
	 * 
	 * Método responsável por listar todas as missoes em uma distancia X para um jogador
         * @param missao_id Id missao
         * @param prioridade Primeiro das objetos
	 * @return JSONArray Lista de mecanicas com os arquivos
	 * @author Carleandro Noleto
	 * @since 10/12/2014
	 * @version 1.0
	 */
        public JSONArray getMissoes(int missao_id, int prioridade){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JSONArray missoes = null;
		Connection conexao = criarConexao();
		try {
                        missoes= new JSONArray();
                         String sql = "SELECT mecanicas.id, mecanicas.tipo,vfotos.id, vfotos.arqimage FROM missoes " +
                            "LEFT JOIN mecanicas on (missoes.id = mecanicas.missoes_id) " +
                             "LEFT JOIN vfotos on (vfotos.mecanica_id = mecanicas.id) " +
                             "WHERE (mecanicas.tipo = 'vfotos' OR mecanicas.tipo = 'vsons' OR mecanicas.tipo ='vvideos') AND missoes.id = "+missao_id;
                         
                        pstmt = conexao.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				JSONObject missao = new JSONObject();
                                missao.put("mecanica_id",rs.getInt("mecanicas.id"));
                                missao.put("tipo",rs.getString("mecanicas.tipo"));
                                missao.put("arquivo_id",rs.getInt("vfotos.id"));
                                missao.put("arquivo",rs.getString("vfotos.arqimage"));        
                                missao.put("prioridade",prioridade);
				missoes.put(missao);
			}
		} catch (SQLException | JSONException e) {
			System.out.println("Erro ao getMissoes " + e.getMessage());
                } finally {
			fecharConexao(conexao, pstmt, rs);
		}
		return missoes;
	}
}
