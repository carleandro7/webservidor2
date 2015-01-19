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
public class GruposDAO extends ConnectionFactory{
    
    private static GruposDAO instance;
         /**
	 * 
	 * Método responsável por criar uma instancia da classe GruposDAO (Singleton)
	 *
	 * @return static
	 * @author Carleandro Noleto
	 * @since 10/12/2014
	 * @version 1.0
	 */
	public static GruposDAO getInstance(){
		if(instance == null)
			instance = new GruposDAO();
		return instance;
	}
        
        	/**
	 * 
	 * Método responsável por listar todos os grupos de um jogo do banco
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
		JSONArray grupos = null;
		Connection conexao = criarConexao();
		try {
                        grupos= new JSONArray();
                        pstmt = conexao.prepareStatement("select * from grupos where jogo_id = "+jogo_id+" order by nome");
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				JSONObject grupo = new JSONObject();
                                grupo.put("id",rs.getInt("id"));
                                grupo.put("nome",rs.getString("nome"));
                                
				grupos.put(grupo);
			}
			
		} catch (SQLException | JSONException e) {
			System.out.println("Erro ao listar todos os grupos: " + e.getMessage());
                } finally {
			fecharConexao(conexao, pstmt, rs);
		}
		return grupos;
	}
        
}
