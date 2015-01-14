/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.dao;

import br.com.factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author carleandro
 */
public class MecanicasDAO extends ConnectionFactory{
    
    private static MecanicasDAO instance;
                /**
	 * 
	 * Método responsável por criar uma instancia da classe MecanicasDAO (Singleton)
	 *
	 * @return
	 * @author Carleandro Noleto
	 * @since 10/12/2014
	 * @version 1.0
	 */
	public static MecanicasDAO getInstance(){
		if(instance == null)
			instance = new MecanicasDAO();
		return instance;
	}
        
        	/**
	 * 
	 * Método responsável por listar todos as Mecanicas das missoes de um jogo do banco
	 *
         * @param jogo_id
	 * @return
	 * @author Carleandro Noleto
	 * @since 10/12/2014
	 * @version 1.0
	 */
	public JSONArray getTodos(String jogo_id){
                PreparedStatement pstmt = null;
		ResultSet rs = null;
		JSONArray mecanicas = null;
		Connection conexao = criarConexao();
		try {
                        mecanicas= new JSONArray();
                         String sql = "SELECT `mecanicas`.`id`, `mecanicas`.`nome`, `mecanicas`.`tipo`, `mecanicas`.`ordem`, `mecanicas`.`tempo`, `mecanicas`.`missoes_id` FROM `mecanicas` " +
                                       " LEFT JOIN `missoes` ON (`mecanicas`.`missoes_id` = `missoes`.`id`) " +
                                       " LEFT JOIN `grupos`  ON (`missoes`.`grupo_id` = `grupos`.`id`) " +
                                       " WHERE  `grupos`. `jogo_id` = "+jogo_id;
                
                        pstmt = conexao.prepareStatement(sql);
			rs = pstmt.executeQuery();
                
			while(rs.next()){
				JSONObject mecanica = new JSONObject();
                                mecanica.put("id",rs.getInt("id"));
                                mecanica.put("nome",rs.getString("nome"));
                                mecanica.put("tipo",rs.getString("tipo"));
                                mecanica.put("ordem",rs.getInt("ordem"));
                                mecanica.put("tempo",rs.getTime("tempo"));
                                mecanica.put("missoes_id",rs.getInt("missoes_id"));
                                
                                
                            switch (rs.getString("tipo")) {
                                case "vtextos":
                                    mecanica.put("mecanica", new TextosDAO().getMecTexto(rs.getInt("id")));
                                    break;
                                case "vfotos":
                                    mecanica.put("mecanica", new FotosDAO().getMecVFotos(rs.getInt("id")));
                                    break;
                                case "irlocais":
                                    mecanica.put("mecanica", new IrLocaisDAO().getMecIrLocais(rs.getInt("id")));
                                    break;
                                case "cfotos":
                                    mecanica.put("mecanica", new FotosDAO().getMecCFotos(rs.getInt("id")));
                                    break;
                                case "csons":
                                    mecanica.put("mecanica", new SonsDAO().getMecCSons(rs.getInt("id")));
                                    break;    
                                case "cvideos":
                                    mecanica.put("mecanica", new VideosDAO().getMecCVideos(rs.getInt("id")));
                                    break;    
                            }
                                mecanicas.put(mecanica);
			}
			
		} catch (SQLException | JSONException e) {
			System.out.println("Erro ao listar todas as mecanicas: " + e.getMessage());
                } finally {
			fecharConexao(conexao, pstmt, rs);
		}
		return mecanicas;
	}
        
        
        public JSONArray getMecania(String mecanica_id){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JSONArray mecanicas = null;
		Connection conexao = criarConexao();
		try {
                        mecanicas= new JSONArray();
                         String sql = "SELECT `mecanicas`.`id`, `mecanicas`.`nome`, `mecanicas`.`tipo`, `mecanicas`.`ordem`, `mecanicas`.`tempo`, `mecanicas`.`missoes_id` FROM `mecanicas` " +
                                       " WHERE  `mecanicas`. `id` = "+mecanica_id;
                
                        pstmt = conexao.prepareStatement(sql);
			rs = pstmt.executeQuery();
                
			while(rs.next()){
				JSONObject mecanica = new JSONObject();
                                mecanica.put("id",rs.getInt("id"));
                                mecanica.put("nome",rs.getString("nome"));
                                mecanica.put("tipo",rs.getString("tipo"));
                                mecanica.put("ordem",rs.getInt("ordem"));
                                mecanica.put("tempo",rs.getTime("tempo"));
                                mecanica.put("missoes_id",rs.getInt("missoes_id"));
                                
                                
                            switch (rs.getString("tipo")) {
                                case "vtextos":
                                    mecanica.put("mecanica", new TextosDAO().getMecTexto(rs.getInt("id")));
                                    break;
                                case "vfotos":
                                    mecanica.put("mecanica", new FotosDAO().getMecVFotos(rs.getInt("id")));
                                    break;
                                case "irlocais":
                                    mecanica.put("mecanica", new IrLocaisDAO().getMecIrLocais(rs.getInt("id")));
                                    break;
                                case "cfotos":
                                    mecanica.put("mecanica", new FotosDAO().getMecCFotos(rs.getInt("id")));
                                    break;
                                case "csons":
                                    mecanica.put("mecanica", new SonsDAO().getMecCSons(rs.getInt("id")));
                                    break; 
                                case "cvideos":
                                    mecanica.put("mecanica", new VideosDAO().getMecCVideos(rs.getInt("id")));
                                    break;    
                            }
                                mecanicas.put(mecanica);
			}
                        
			
		} catch (SQLException | JSONException e) {
			System.out.println("Erro ao listar dados de uma mecanica: " + e.getMessage());
                } finally {
			fecharConexao(conexao, pstmt, rs);
		}
		return mecanicas;
	}
        
}
