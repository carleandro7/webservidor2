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
public class IrLocaisDAO extends ConnectionFactory{
        private static IrLocaisDAO instance;
                /**
	 * 
	 * Método responsável por criar uma instancia da classe IrLocaisDAO (Singleton)
	 *
	 * @return static
	 * @author Carleandro Noleto
	 * @since 14/01/2015
	 * @version 1.0
	 */
	public static IrLocaisDAO getInstance(){
		if(instance == null)
			instance = new IrLocaisDAO();
		return instance;
	}
        
    /**
     *
     * @param mecanica_id Int
     * @return JSONObjet
     */
    public JSONObject getMecIrLocais(int mecanica_id){
                PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conexao = criarConexao();
                JSONObject irLocais =null;
                try{
                        String sql = "SELECT * FROM  `irlocais` WHERE  `irlocais`.`mecanica_id` =  "+mecanica_id;
                        pstmt = conexao.prepareStatement(sql);
                        rs = pstmt.executeQuery();
			rs.next();
				irLocais = new JSONObject();
                                irLocais.put("id",rs.getInt("id"));
                                irLocais.put("latitude",rs.getString("latitude"));
                                irLocais.put("longitude",rs.getString("longitude"));
                                irLocais.put("mecanicas_id",rs.getInt("mecanica_id"));
                                
		} catch (SQLException | JSONException e) {
			System.out.println("Erro ao listar todos os clientes: " + e.getMessage());
                } finally {
			fecharConexao(conexao, pstmt, rs);
		}
		return irLocais;
            
        }
    
}
