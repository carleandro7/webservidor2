/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.dao;

import br.com.factory.ConnectionFactory;
import br.com.model.Jogador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author carleandro
 */
public class JogadoresDAO extends ConnectionFactory {
    
    	private static JogadoresDAO instance;
	
	
	/**
	 * 
	 * Método responsável por criar uma instancia da classe JogadoresDAO (Singleton)
	 *
	 * @return
	 * @author Carleandro Noleto
	 * @since 27/12/2014
	 * @version 1.0
	 */
	public static JogadoresDAO getInstance(){
		if(instance == null)
			instance = new JogadoresDAO();
		return instance;
	}
	
	/**
	 * 
	 * Método responsável por listar todos os jogadores do banco
	 *
	 * @return
	 * @author Carleandro Noleto
	 * @since 27/12/2014
	 * @version 1.0
	 */
	public ArrayList<Jogador> listarTodos(){
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Jogador> jogadores = null;
		
		conexao = criarConexao();
		
		try {
                        jogadores = new ArrayList<Jogador>();
			pstmt = conexao.prepareStatement("select * from jogadores order by id");
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				Jogador jogador = new Jogador();
				
				jogador.setId(rs.getInt("id"));
				jogador.setEmail(rs.getString("email"));
				jogador.setPassword(rs.getString("password"));
				
				
				jogadores.add(jogador);
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao listar todos os jogadores: " + e);
		} finally {
			fecharConexao(conexao, pstmt, rs);
		}
		return jogadores;
	}
        /**
	 * 
	 * Método responsável por fazer o login do jogador
	 *
         * @param email
         * @param password
	 * @return Jogador
	 * @author Carleandro Noleto
	 * @since 27/11/2014
	 * @version 1.0
	 */
        public Jogador login(String email, String password){
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		conexao = criarConexao();
		Jogador jogador = new Jogador();
		try {
                        
			pstmt = conexao.prepareStatement("select * from jogadores where email ='"+email+"' AND password= '"+password+"'");
                        rs = pstmt.executeQuery();
                        if(rs.next()){
                            jogador.setId(rs.getInt("id"));
                            jogador.setEmail(rs.getString("email"));
                            jogador.setPassword(rs.getString("password"));
                            System.out.println(""+jogador.toString());
                        }
			
		} catch (SQLException e) {
			System.out.println("Erro ao realizar login: " + e);
		} finally {
			fecharConexao(conexao, pstmt, rs);
		}
		return jogador;
	}
        /**
	 * 
	 * Método responsável por get em todos os dados do jogador recebendo o id
	 *
         * @param id
	 * @return Jogadores
	 * @author Carleandro Noleto
	 * @since 27/11/2014
	 * @version 1.0
	 */
        public Jogador getJogador(String id){
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Jogador jogadores = new Jogador();
		
		conexao = criarConexao();
		
		try {
                        jogadores = new Jogador();
			pstmt = conexao.prepareStatement("select * from jogadores where id = "+id);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				Jogador jogador = new Jogador();
				
				jogador.setId(rs.getInt("id"));
				jogador.setEmail(rs.getString("email"));
				jogador.setPassword(rs.getString("password"));
				jogadores = jogador;
				
				
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao listar dados de um jogador: " + e);
		} finally {
			fecharConexao(conexao, pstmt, rs);
		}
		return jogadores;
	}
        /**
	 * 
	 * Método responsável cadastrar um jogador
	 *
         * @param email
         * @param password 
	 * @return String
	 * @author Carleandro Noleto
	 * @since 27/11/2014
	 * @version 1.0
	 */
        public String cadastrar(String email, String password) {
                    Connection conexao = null;
                    PreparedStatement pstmt = null;
                    ResultSet rs = null;
                    String mensagem="";

                    try{
                        conexao = criarConexao();
                        pstmt = conexao.prepareStatement("select * from jogadores where email='"+email+"'");
                        rs = pstmt.executeQuery();

                        if(rs.next()){//existi pelo menos um registro nessa lista
                            mensagem = "Esse email já esta cadastrador";
                        }else{
                            pstmt = conexao.prepareStatement("INSERT INTO  jogadores(`email` ,`password`) VALUES (?, ?)");
                            pstmt.setString(1, email);
                            pstmt.setString(2, password);
                            pstmt.executeUpdate();
                            mensagem="true";
                        }

                    } catch (SQLException e) {
                            System.out.println("Erro ao cadastrar jogador" + e);
                    } finally {
                            fecharConexao(conexao, pstmt, rs);
                    }
                    return mensagem;
        }
	
}
    
