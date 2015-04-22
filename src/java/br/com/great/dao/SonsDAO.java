/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.great.dao;

import br.com.great.contexto.CapturarObjeto;
import br.com.great.contexto.Posicao;
import br.com.great.contexto.Som;
import br.com.great.factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Classe responsavel realizar toda a interação com banco de dados relacionado com entidade sons
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
	 * @return boolean Se atualizar true ou false
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
         * @return JSONObject Dados CSons
	 * @since 14/01/2015
	 * @version 1.0
	 */
        public Som getMecCSons(int mecanica_id){
                PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conexao = criarConexao();
                Som cSons =null;
                try{
                        String sql = "SELECT * FROM  csons LEFT JOIN mecsimples ON "
                                + " (mecsimples.id = csons.mecsimples_id) WHERE csons.mecsimples_id = "+mecanica_id;
                        pstmt = conexao.prepareStatement(sql);
                        rs = pstmt.executeQuery();
			if(rs.next()){
				cSons = new Som();
                                cSons.setSom_id(rs.getInt("csons.id"));
                                cSons.setArqSom(rs.getString("csons.som"));
                                cSons.setPosicao(new Posicao(rs.getDouble("mecsimples.latitude"), rs.getDouble("mecsimples.longitude")));
                                cSons.setMecsimples_id(rs.getInt("csons.mecsimples_id"));
                        }
		} catch (SQLException e) {
			System.out.println("Erro ao listar dados de uma mecanica csons: " + e.getMessage());
                } finally {
			fecharConexao(conexao, pstmt, rs);
		}
		return cSons;
            
    }
    
}
