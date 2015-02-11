package br.com.great.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 * 
 * Classe respons�vel por conter os metodos criar e fechar o banco de dados.
 *
 * @author Carleandro Noleto
 * @since 26/11/2014
 */
public class ConnectionFactory {

	// Caminho do banco de dados.
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost/pervasivebd";
	private static final String USUARIO = "carleandronoleto";
	private static final String SENHA = "GREat@201412";
	

	/**
	 * 
	 * M�todo respons�vel por criar uma conexao com o banco 
	 *
	 * @author Carleandro Noleto
         * @return  Connection Conexao com banco de dados
	 * @since 26/11/2014
	 */
	public Connection criarConexao(){
		
		Connection conexao = null;
		
		try {
			
			Class.forName(DRIVER);
			conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
		
		} catch (Exception e) {
			System.out.println("Erro ao criar conex�o com o banco: " + URL);
			e.printStackTrace();
		}
		return conexao;
	}
	
    /**
     *
     * @param conexao Connection
     * @param pstmt PreparedStatement
     * @param rs ResultSet
     */
    public void fecharConexao(Connection conexao, PreparedStatement pstmt, ResultSet rs){
		
		try {
			
			if(conexao != null){
				conexao.close();
			}
			if(pstmt != null){
				pstmt.close();
			}
			if(rs != null){
				rs.close();
			}
					
		} catch (Exception e) {
			System.out.println("Erro ao fechar conex�o com o banco: " + URL);
		}
	}
}

