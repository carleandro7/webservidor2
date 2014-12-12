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
public class MissoesDAO extends ConnectionFactory{
    
    private static MissoesDAO instance;
                /**
	 * 
	 * Método responsável por criar uma instancia da classe MissoesDAO (Singleton)
	 *
	 * @return
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
         * @param jogo_id
	 * @return
	 * @author Carleandro Noleto
	 * @since 10/12/2014
	 * @version 1.0
	 */
	public JSONArray getTodos(String jogo_id){
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JSONArray missoes = null;
		conexao = criarConexao();
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
                        
			
		} catch (SQLException e) {
			System.out.println("Erro ao listar todos os clientes: " + e);
                } catch (JSONException e) {
			System.out.println("Erro ao listar todos os clientes: " + e);
		} finally {
			fecharConexao(conexao, pstmt, rs);
		}
		return missoes;
	}

	
}
