/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.great.dao;

import br.com.great.factory.ConnectionFactory;
import br.com.great.model.Mecanica;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Classe responsavel realizar toda a interação com banco de dados relacionado com entidade mecanica
 * @author carleandro
 */
public class MecanicasDAO extends ConnectionFactory{
    
    private static MecanicasDAO instance;
                /**
	 * 
	 * Método responsável por criar uma instancia da classe MecanicasDAO (Singleton)
	 *
	 * @return static
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
         * @param jogo_id String
	 * @return JSONArray
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
                         String sql = "SELECT `mecanicas`.`id`, `mecanicas`.`nome`, `mecanicas`.`tipo`, `mecanicas`.`ordem`, `mecanicas`.`tempo`, "
                                 + "`mecanicas`.`missoes_id`, `mecanicas`.`latitude`, `mecanicas`.`longitude` FROM `mecanicas` " +
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
                                mecanica.put("latitude",rs.getDouble("latitude"));
                                mecanica.put("longitude",rs.getDouble("longitude"));
                                mecanica.put("missoes_id",rs.getInt("missoes_id"));
                                
                                
                            switch (rs.getString("tipo")) {
                                case "vtextos":
                                    mecanica.put("mecanica", new TextosDAO().getMecVTexto(rs.getInt("id")));
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
                                case "ctextos":
                                    mecanica.put("mecanica", new TextosDAO().getMecCTextos(rs.getInt("id")));
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
        
        /**
	 * 
	 * Método responsável por get em uma Mecanica de uma missao de um jogo do banco de dados
	 *
         * @param mecanica_id String
	 * @return JSONArray
	 * @author Carleandro Noleto
	 * @since 10/12/2014
	 * @version 1.0
	 */
        public JSONArray getMecania(String mecanica_id){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JSONArray mecanicas = null;
		Connection conexao = criarConexao();
		try {
                        mecanicas= new JSONArray();
                         String sql = "SELECT * FROM mecanicas " +
                                       " WHERE  id = "+mecanica_id+"  ORDER BY ordem ";
                
                        pstmt = conexao.prepareStatement(sql);
			rs = pstmt.executeQuery();
                
			while(rs.next()){
				JSONObject mecanica = new JSONObject();
                                mecanica.put("mecanica_id",rs.getInt("id"));
                                mecanica.put("nome",rs.getString("nome"));
                                mecanica.put("tipo",rs.getString("tipo"));
                                mecanica.put("ordem",rs.getInt("ordem"));
                                mecanica.put("tempo",rs.getTime("tempo"));
                                mecanica.put("latitude",rs.getDouble("latitude"));
                                mecanica.put("longitude",rs.getDouble("longitude"));
                                mecanica.put("missoes_id",rs.getInt("missoes_id"));            
                            switch (rs.getString("tipo")) {
                                case "vtextos":
                                    mecanica.put("mecanica", new TextosDAO().getMecVTexto(rs.getInt("id")));
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
                                case "ctextos":
                                    mecanica.put("mecanica", new TextosDAO().getMecCTextos(rs.getInt("id")));
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
        
        /**
	 * 
	 * Método responsável por lista todas as mecanicas de uma missao
	 *
         * @param missao_id int
	 * @return JSONArray
	 * @author Carleandro Noleto
	 * @since 10/12/2014
	 * @version 1.0
	 */
        public ArrayList<Mecanica> getMecaniaMissao(int missao_id){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Mecanica> mecanicas = null;
		Connection conexao = criarConexao();
		try {
                        mecanicas= new ArrayList<Mecanica>();
                         String sql = "SELECT * FROM mecanicas  WHERE missoes_id = "+missao_id+"  ORDER BY ordem";
                
                        pstmt = conexao.prepareStatement(sql);
			rs = pstmt.executeQuery();
                
			while(rs.next()){
				Mecanica mecanica = new Mecanica();
                                mecanica.setId(rs.getInt("id"));
                                mecanica.setNome(rs.getString("nome"));
                                mecanica.setTipo(rs.getString("tipo"));
                                mecanica.setOrdem(rs.getInt("ordem"));
                                mecanica.setTempo(rs.getTime("tempo"));
                                mecanica.setLatitude(rs.getDouble("latitude"));
                                mecanica.setLongitude(rs.getDouble("longitude"));
                                mecanica.setMissoes_id(rs.getInt("missoes_id"));
                                mecanicas.add(mecanica);
			}
                        	
		} catch (SQLException e) {
			System.out.println("Erro ao listar dados de uma mecanica: " + e.getMessage());
                } finally {
			fecharConexao(conexao, pstmt, rs);
		}
		return mecanicas;
	}
        
}
