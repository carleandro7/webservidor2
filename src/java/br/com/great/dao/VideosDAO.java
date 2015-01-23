/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.great.dao;

import br.com.great.factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author carleandro
 */
public class VideosDAO extends ConnectionFactory{
        private static VideosDAO instance;
        /**
	 * 
	 * Método responsável por criar uma instancia da classe VideosDAO (Singleton)
	 *
	 * @return static
	 * @author Carleandro Noleto
	 * @since 14/01/2015
	 * @version 1.0
	 */
	public static VideosDAO getInstance(){
		if(instance == null)
			instance = new VideosDAO();
		return instance;
	}
        
        
        /**
	 * 
	 * Método responsável por set os dados da CSons no banco de dados
	 *
         * @param video String
         * @param jogador_id String
         * @param latitude String
         * @param longitude String
         * @param cvideos_id String
	 * @return boolean
	 * @author Carleandro Noleto
	 * @since 14/01/2015
	 * @version 1.0
	 */
        public boolean setCVideo(String video, String jogador_id, String latitude, String longitude, String cvideos_id){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conexao = criarConexao();
		try {                    
                        String sql = "UPDATE `cvideos` SET  `video` =  '"+video+"',"+
                                " `jogador_id` =  '"+jogador_id+"', `latitude` =  '"+latitude+
                            "',`longitude` =  '"+longitude+"' WHERE  `cvideos`.`id` ="+cvideos_id+";";
                        System.out.println(sql);
                        pstmt = conexao.prepareStatement(sql);
                        pstmt.execute(sql);
			return true;
		} catch (SQLException e) {
			System.out.println("Erro ao salvar video em grupo : " + e);
                }catch (Exception e){
                    System.out.println("Erro ao salvar arquivo video em grupo : " + e);
                }finally {
			fecharConexao(conexao, pstmt, rs);
		}
		return false;
	}
        
        /**
	 * 
	 * Método responsável por get os dados da CSons no banco de dados
	 *
	 * @author Carleandro Noleto
         * @param mecanica_id int
         * @return JSONObject
	 * @since 14/01/2015
	 * @version 1.0
	 */
        public JSONObject getMecCVideos(int mecanica_id){
                PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conexao = criarConexao();
                JSONObject cVideos =null;
                try{
                        String sql = "SELECT * FROM  `cvideos` WHERE  `cvideos`.`mecanica_id` =  "+mecanica_id;
                        pstmt = conexao.prepareStatement(sql);
                        rs = pstmt.executeQuery();
			rs.next();
				cVideos = new JSONObject();
                                cVideos.put("id",rs.getInt("id"));
                                cVideos.put("video",rs.getString("video"));
                                cVideos.put("jogador_id",rs.getString("jogador_id"));
                                cVideos.put("latitude",rs.getString("latitude"));
                                cVideos.put("longitude",rs.getString("longitude"));
                                cVideos.put("mecanicas_id",rs.getInt("mecanica_id"));
                                
		} catch (SQLException | JSONException e) {
			System.out.println("Erro ao listar dados de uma mecanica cvideos: " + e.getMessage());
                } finally {
			fecharConexao(conexao, pstmt, rs);
		}
		return cVideos;
            
    }
    
}

