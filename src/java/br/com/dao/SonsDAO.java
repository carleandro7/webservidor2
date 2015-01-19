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
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author carleandro
 */
public class SonsDAO extends ConnectionFactory{
        private static SonsDAO instance;
        /**
	 * 
	 * Método responsável por criar uma instancia da classe SonsDAO (Singleton)
	 *
	 * @return static
	 * @author Carleandro Noleto
	 * @since 14/01/2015
	 * @version 1.0
	 */
	public static SonsDAO getInstance(){
		if(instance == null)
			instance = new SonsDAO();
		return instance;
	}
        
        
        /**
	 * 
	 * Método responsável por set os dados da CSons no banco de dados
	 *
         * @param som String
         * @param jogador_id String
         * @param latitude String
         * @param longitude String
         * @param csons_id String
	 * @return boolean
	 * @author Carleandro Noleto
	 * @since 14/01/2015
	 * @version 1.0
	 */
        public boolean setCSom(String som, String jogador_id, String latitude, String longitude, String csons_id){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conexao = criarConexao();
		try {                    
                        String sql = "UPDATE `csons` SET  `som` =  '"+som+"',"+
                                " `jogador_id` =  '"+jogador_id+"', `latitude` =  '"+latitude+
                            "',`longitude` =  '"+longitude+"' WHERE  `csons`.`id` ="+csons_id+";";
                        System.out.println(sql);
                        pstmt = conexao.prepareStatement(sql);
                        pstmt.execute(sql);
                        return true;
		} catch (SQLException e) {
			System.out.println("Erro ao salvar som em grupo : " + e);
                }catch (Exception e){
                    System.out.println("Erro ao salvar arquivo som em grupo : " + e);
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
        public JSONObject getMecCSons(int mecanica_id){
                PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conexao = criarConexao();
                JSONObject cSons =null;
                try{
                        String sql = "SELECT * FROM  `csons` WHERE  `csons`.`mecanica_id` =  "+mecanica_id;
                        pstmt = conexao.prepareStatement(sql);
                        rs = pstmt.executeQuery();
			rs.next();
				cSons = new JSONObject();
                                cSons.put("id",rs.getInt("id"));
                                cSons.put("som",rs.getString("som"));
                                cSons.put("jogador_id",rs.getString("jogador_id"));
                                cSons.put("latitude",rs.getString("latitude"));
                                cSons.put("longitude",rs.getString("longitude"));
                                cSons.put("mecanicas_id",rs.getInt("mecanica_id"));
                                
		} catch (SQLException | JSONException e) {
			System.out.println("Erro ao listar dados de uma mecanica csons: " + e.getMessage());
                } finally {
			fecharConexao(conexao, pstmt, rs);
		}
		return cSons;
            
    }
    
}
