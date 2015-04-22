/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.great.dao;

import br.com.great.contexto.CapturarObjeto;
import br.com.great.contexto.Posicao;
import br.com.great.contexto.Texto;
import br.com.great.factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Classe responsavel realizar toda a interação com banco de dados relacionado com entidade Textos
 * @author carleandro
 */
public class TextosDAO extends ConnectionFactory{
    private static TextosDAO instance;
        /**
	 * 
	 * Método responsável por criar uma instancia da classe TextosDAO (Singleton)
	 *
	 * @return static
	 * @author Carleandro Noleto
	 * @since 14/01/2015
	 * @version 1.0
	 */
	public static TextosDAO getInstance(){
		if(instance == null)
			instance = new TextosDAO();
		return instance;
	}
        
        /**
	 * 
	 * Método responsável por get os dados da VTextos no banco de dados
	 *
         * @param mecanica_id int
	 * @return JSONObject Dados de VTextos
	 * @author Carleandro Noleto
	 * @since 14/01/2015
	 * @version 1.0
	 */
        public Texto getMecVTexto(int mecanica_id){
               	PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conexao = criarConexao();
                Texto vtexto =null;
                try{
                        String sql = "SELECT * FROM vtextos LEFT JOIN mecsimples ON "
                                + " (mecsimples.id = vtextos.mecsimples_id) WHERE vtextos.mecsimples_id = "+mecanica_id;
                        pstmt = conexao.prepareStatement(sql);
                        rs = pstmt.executeQuery();
			rs.next();
				vtexto = new Texto();
                                vtexto.setTexto_id(rs.getInt("vtextos.id"));
                                vtexto.setTexto(rs.getString("vtextos.texto"));
                                vtexto.setPosicao(new Posicao(rs.getDouble("mecsimples.latitude"), rs.getDouble("mecsimples.longitude")));
                                vtexto.setMecsimples_id(rs.getInt("vtextos.mecanica_id"));
                                
		} catch (SQLException e) {
			System.out.println("Erro ao listar dados de uma mecanica texto: " + e.getMessage());
                } finally {
			fecharConexao(conexao, pstmt, rs);
		}
		return vtexto;
            
        }
        
        
        /**
	 * 
	 * Método responsável por set os dados da CTextos no banco de dados
	 *
         * @param texto String
         * @param jogador_id String
         * @param latitude String
         * @param longitude String
         * @param ctexto_id String
	 * @return boolean Se atualiza True ou False
	 * @author Carleandro Noleto
	 * @since 19/01/2015
	 * @version 1.0
	 */
        public boolean setCTexto(String texto, String jogador_id, String latitude, String longitude, String ctexto_id){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conexao = criarConexao();
		try {                    
                        String sql = "UPDATE `ctextos` SET  `texto` =  '"+texto+"',"+
                                " `jogador_id` =  '"+jogador_id+"', `latitude` =  '"+latitude+
                            "',`longitude` =  '"+longitude+"' WHERE  `ctextos`.`id` ="+ctexto_id+";";
                        System.out.println(sql);
                        pstmt = conexao.prepareStatement(sql);
                        pstmt.execute(sql);
			return true;
		} catch (SQLException e) {
			System.out.println("Erro ao salvar settexto em grupo : " + e);
                }catch (Exception e){
                    System.out.println("Erro ao salvar arquivo settexto em grupo : " + e);
                }finally {
			fecharConexao(conexao, pstmt, rs);
		}
		return false;
	}
        
        /**
	 * 
	 * Método responsável por get os dados da CTextos no banco de dados
	 *
	 * @author Carleandro Noleto
         * @param mecanica_id int
         * @return JSONObject Dados de CTextos
	 * @since 19/01/2015
	 * @version 1.0
	 */
        public Texto getMecCTextos(int mecanica_id){
                PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conexao = criarConexao();
                Texto cTextos =null;
                try{
                        String sql = "SELECT * FROM ctextos LEFT JOIN mecsimples ON "
                                + " (mecsimples.id = ctextos.mecsimples_id) WHERE ctextos.mecsimples_id = "+mecanica_id;
                        pstmt = conexao.prepareStatement(sql);
                        rs = pstmt.executeQuery();
			if(rs.next()){
				cTextos = new Texto();
                                cTextos.setTexto_id(rs.getInt("ctextos.id"));
                                cTextos.setTexto(rs.getString("ctextos.texto"));
                                cTextos.setPosicao(new Posicao(rs.getDouble("mecsimples.latitude"), rs.getDouble("mecsimples.longitude")));
                                cTextos.setMecsimples_id(rs.getInt("ctextos.mecsimples_id"));
                        }
		} catch (SQLException e) {
			System.out.println("Erro ao listar dados de uma mecanica cTextos: " + e.getMessage());
                } finally {
			fecharConexao(conexao, pstmt, rs);
		}
		return cTextos;
            
    }
        
    
}
