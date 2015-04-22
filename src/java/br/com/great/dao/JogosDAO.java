/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.great.dao;

import br.com.great.contexto.Jogo;
import br.com.great.contexto.Posicao;
import br.com.great.factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



/**
 * Classe responsavel realizar toda a interação com banco de dados relacionado com entidade jogo
 * @author carleandro
 */
public class JogosDAO extends ConnectionFactory{
    
    private static JogosDAO instance;
                /**
	 * 
	 * Método responsável por criar uma instancia da classe JogosDAO (Singleton)
	 *
	 * @return static
	 * @author Carleandro Noleto
	 * @since 27/11/2014
	 * @version 1.0
	 */
	public static JogosDAO getInstance(){
		if(instance == null)
			instance = new JogosDAO();
		return instance;
	}
	
	
        /**
	 * Método responsável por listar todos os jogos perto de um local com distancia definida
         *
         * @param latitude String
         * @param longitude String
         * @param distancia String
	 * @return JSONArray lista de jogos
	 * @author Carleandro Noleto
	 * @since 27/11/2014
	 * @version 1.0
	 */
        public JSONArray getJogos(double latitude, double longitude, int distancia){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JSONArray jogos = null;
		Connection conexao = criarConexao();
		try {
                        jogos= new JSONArray();
                        //distancia em KM
                        String sql = 
                                "SELECT  ((3956 * 2 * ASIN(SQRT(POWER(SIN((abs("+latitude+") - abs(latitude)) *  "
                                + " pi()/180 / 2),2) + COS(abs("+latitude+") * pi()/180 ) * COS(abs(latitude) * pi()/180) "
                                + "* POWER(SIN((abs("+longitude+") - abs(longitude)) * 	pi()/180 / 2), 2)))) * 1.609344) as "
                                + "distancia, nome, id, icone FROM jogos  having distancia < "+distancia+" ORDER BY distancia limit 100";
                        pstmt = conexao.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				JSONObject jogo = new JSONObject();
                                jogo.put("id",rs.getInt("id"));
                                jogo.put("nome",rs.getString("nome"));
                                jogo.put("icone",rs.getString("icone"));
				jogos.put(jogo);
			}
			
		} catch (SQLException | JSONException e) {
			System.out.println("Erro ao listar todos os jogos em uma distancia em KM: " + e.getMessage());
                } finally {
			fecharConexao(conexao, pstmt, rs);
		}
		return jogos;
	}
        
    
    /**
     * Retorna um jogo com todos os seus dados
     * @return JSONArray lista de todos os jogos
     */
    public Jogo getJogo(int jogo_id) {
        PreparedStatement pstmt = null;
	ResultSet rs = null;
	Connection conexao = criarConexao();
	try {
            String sql = "select * from jogos where id="+jogo_id;
            pstmt = conexao.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                Jogo jogo = new Jogo();
                jogo.setId(rs.getInt("id"));
                jogo.setNome(rs.getString("nome"));
                jogo.setIcone(rs.getString("icone"));
                jogo.setPosicao(new Posicao(rs.getDouble("latitude"), rs.getDouble("longitude")));
                jogo.setNomeficticio(rs.getString("nomeficticio"));
                jogo.setStatus(rs.getInt("status"));
                return jogo;
            }
        } catch (SQLException e) {
            System.out.println("Erro no getJogo: " + e.getMessage());
        } finally {
            fecharConexao(conexao, pstmt, rs);
	}
        return null;
    }
        
}
