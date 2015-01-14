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
public class TextosDAO extends ConnectionFactory{
    private static TextosDAO instance;
        /**
	 * 
	 * Método responsável por criar uma instancia da classe TextosDAO (Singleton)
	 *
	 * @return
	 * @author Carleandro Noleto
	 * @since 14/01/2015
	 * @version 1.0
	 */
	public static TextosDAO getInstance(){
		if(instance == null)
			instance = new TextosDAO();
		return instance;
	}
        
        public JSONObject getMecTexto(int mecanica_id){
               	PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conexao = criarConexao();
                JSONObject vtexto =null;
                try{
                        String sql = "SELECT * FROM  `vtextos` WHERE  `vtextos`.`mecanica_id` =  "+mecanica_id;
                        pstmt = conexao.prepareStatement(sql);
                        rs = pstmt.executeQuery();
			rs.next();
				vtexto = new JSONObject();
                                vtexto.put("id",rs.getInt("id"));
                                vtexto.put("nome",rs.getString("texto"));
                                vtexto.put("mecanicas_id",rs.getInt("mecanica_id"));
                                
		} catch (SQLException | JSONException e) {
			System.out.println("Erro ao listar dados de uma mecanica texto: " + e.getMessage());
                } finally {
			fecharConexao(conexao, pstmt, rs);
		}
		return vtexto;
            
        }
        
    
}
