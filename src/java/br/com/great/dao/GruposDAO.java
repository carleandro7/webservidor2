/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.great.dao;

import br.com.great.factory.ConnectionFactory;
import br.com.great.model.Grupo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
        
        /**
	 * 
	 * Método responsável por listar todos os grupos de um jogo do banco
	 *
         * @param jogo_id String
	 * @return ArrayList grupo
	 * @author Carleandro Noleto
	 * @since 10/12/2014
	 * @version 1.0
	 */
	public ArrayList<Grupo> getTodosGrupos(String jogo_id){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Grupo> grupos = null;
		Connection conexao = criarConexao();
		try {
                        grupos= new ArrayList<Grupo>();
                        pstmt = conexao.prepareStatement("select * from grupos where jogo_id = "+jogo_id+" order by nome");
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				Grupo grupo = new Grupo();
                                grupo.setId(rs.getInt("id"));
                                grupo.setNome(rs.getString("nome"));
                                grupo.setJogo_id(rs.getInt("jogo_id"));
				grupos.add(grupo);
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao listar todos os grupos: " + e.getMessage());
                } finally {
			fecharConexao(conexao, pstmt, rs);
		}
		return grupos;
	}

    public boolean getGrupoParticipando(int jogo_id, int grupo_id, int jogador_id) {
        	PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Grupo> grupos = null;
		Connection conexao = criarConexao();
		try {
                        pstmt = conexao.prepareStatement("select * from jogador_jogos where jogo_id = "+jogo_id+" AND jogador_id="+jogador_id );
			rs = pstmt.executeQuery();
			if(rs.next()){
			  return true;	
			}
		} catch (SQLException e) {
			System.out.println("Erro ao verificar participante em grupo: " + e.getMessage());
                } finally {
			fecharConexao(conexao, pstmt, rs);
		}
		return false;
    }
    
    public boolean setGrupoParticipando(int jogo_id, int grupo_id, int jogador_id) {
        	PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Grupo> grupos = null;
		Connection conexao = criarConexao();
		try {
                        pstmt = conexao.prepareStatement("INSERT INTO  jogador_jogos(`jogador_id`,`jogo_id` , `grupo_id`) VALUES (?, ?, ?)");
                            pstmt.setInt(1, jogador_id);
                            pstmt.setInt(2, jogo_id);
                            pstmt.setInt(3, grupo_id);
                            pstmt.executeUpdate();
                            return true;
		} catch (SQLException e) {
			System.out.println("Erro ao salvar participante em grupo: " + e.getMessage());
                } finally {
			fecharConexao(conexao, pstmt, rs);
		}
		return false;
    }
}
