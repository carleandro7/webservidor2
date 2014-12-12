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
                Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JSONArray mecanicas = null;
		conexao = criarConexao();
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
                                
                                
                                if(rs.getString("tipo").equals("vtextos")){
                                    mecanica.put("mecanica", getMecTexto(rs.getInt("id")));
                                }else if(rs.getString("tipo").equals("vfotos")){
                                    mecanica.put("mecanica", getMecFotos(rs.getInt("id")));
                                }else if(rs.getString("tipo").equals("irlocais")){
                                    mecanica.put("mecanica", getMecIrLocais(rs.getInt("id")));
                                }
                                mecanicas.put(mecanica);
			}
                        
			
		} catch (SQLException e) {
			System.out.println("Erro ao listar todos os clientes: " + e);
                } catch (JSONException e) {
			System.out.println("Erro ao listar todos os clientes: " + e);
		} finally {
			fecharConexao(conexao, pstmt, rs);
		}
		return mecanicas;
	}
        
        
        private JSONObject getMecTexto(int mecanica_id){
                Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conexao = criarConexao();
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
                                
		} catch (SQLException e) {
			System.out.println("Erro ao listar todos os clientes: " + e);
                } catch (JSONException e) {
			System.out.println("Erro ao listar todos os clientes: " + e);
		} finally {
			fecharConexao(conexao, pstmt, rs);
		}
		return vtexto;
            
        }
        
	private JSONObject getMecFotos(int mecanica_id){
                Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conexao = criarConexao();
                JSONObject vfoto =null;
                try{
                        String sql = "SELECT * FROM  `vfotos` WHERE  `vfotos`.`mecanica_id` =  "+mecanica_id;
                        pstmt = conexao.prepareStatement(sql);
                        rs = pstmt.executeQuery();
			rs.next();
				vfoto = new JSONObject();
                                vfoto.put("id",rs.getInt("id"));
                                vfoto.put("arqimage",rs.getString("arqimage"));
                                vfoto.put("mecanicas_id",rs.getInt("mecanica_id"));
                                
		} catch (SQLException e) {
			System.out.println("Erro ao listar todos os clientes: " + e);
                } catch (JSONException e) {
			System.out.println("Erro ao listar todos os clientes: " + e);
		} finally {
			fecharConexao(conexao, pstmt, rs);
		}
		return vfoto;
            
        }
        
        private JSONObject getMecIrLocais(int mecanica_id){
                Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conexao = criarConexao();
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
                                
		} catch (SQLException e) {
			System.out.println("Erro ao listar todos os clientes: " + e);
                } catch (JSONException e) {
			System.out.println("Erro ao listar todos os clientes: " + e);
		} finally {
			fecharConexao(conexao, pstmt, rs);
		}
		return irLocais;
            
        }
}
