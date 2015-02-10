/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.great.dao;

import br.com.great.factory.ConnectionFactory;
import br.com.great.model.Jogo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



/**
 *
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
	 * 
	 * Método responsável por listar todos os jogos do banco
	 *
	 * @return JSONArray
	 * @author Carleandro Noleto
	 * @since 27/11/2014
	 * @version 1.0
	 */
	public JSONArray listarTodos(){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JSONArray jogos = null;
		Connection conexao = criarConexao();
		try {
                        jogos= new JSONArray();
                        pstmt = conexao.prepareStatement("select * from jogos order by id");
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				JSONObject jogo = new JSONObject();
                                jogo.put("id",rs.getInt("id"));
                                jogo.put("nome",rs.getString("nome"));
                                jogo.put("icone",rs.getString("icone"));
                                jogo.put("longitude",rs.getString("longitude"));
                                jogo.put("latitude",rs.getString("latitude"));
				jogos.put(jogo);
			}
			
		} catch (SQLException | JSONException e) {
			System.out.println("Erro ao listar todos os jogos: " + e.getMessage());
                } finally {
			fecharConexao(conexao, pstmt, rs);
		}
		return jogos;
	}
        /**
	 * Método responsável por listar todos os jogos perto de um local com distancia definida
         *
         * @param latitude String
         * @param longitude String
         * @param distancia String
	 * @return JSONArray
	 * @author Carleandro Noleto
	 * @since 27/11/2014
	 * @version 1.0
	 */
        public JSONArray getJogos(String latitude, String longitude, String distancia){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JSONArray jogos = null;
		Connection conexao = criarConexao();
		try {
                        jogos= new JSONArray();
                        //distancia em KM
                        String sql = 
                                "SELECT  ((3956 * 2 * ASIN(SQRT(POWER(SIN((abs("+latitude+") - abs(dest.latitude)) *  "
                                + " pi()/180 / 2),2) + COS(abs("+latitude+") * pi()/180 ) * COS(abs(dest.latitude) * pi()/180) "
                                + "* POWER(SIN((abs("+longitude+") - abs(dest.longitude)) * 	pi()/180 / 2), 2)))) * 1.609344) as "
                                + "distancia, nome, id, icone FROM pjogos dest having distancia < "+distancia+" ORDER BY distancia limit 100";
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
     * @param idJogo String
     * @return JSONArray
     */
    public JSONArray getDadosIniciais(String idJogo){
		JSONArray jogos = null;
		try {
                        jogos= new JSONArray();
                        
                        jogos.put(new GruposDAO().getTodos(idJogo));                        
                        jogos.put(new MissoesDAO().getTodos(idJogo));
                        jogos.put(new MecanicasDAO().getTodos(idJogo));
                } catch (Exception e) {
			System.out.println("Erro ao listar todos os dados iniciais do jogo: " + e.getMessage());
                }
                
		return jogos;
    }
    
    public Jogo getpJogo(int jogo_id){
        Jogo jogo = new Jogo();
        PreparedStatement pstmt = null;
	ResultSet rs = null;
	Connection conexao = criarConexao();
	try {
            String sql = "select * from pjogos where id="+jogo_id;
            pstmt = conexao.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if(rs.next()){
                jogo.setId(rs.getInt("id"));
                jogo.setNome(rs.getString("nome"));
                jogo.setIcone(rs.getString("icone"));
		jogo.setLatitude(rs.getString("latitude"));
                jogo.setLongitude(rs.getString("longitude"));
                jogo.setUser_id(rs.getInt("user_id"));
                jogo.setOrdMecanicas(rs.getString("ordmecanicas"));
            }
        } catch (SQLException e) {
            System.out.println("Erro no getJogo: " + e.getMessage());
        } finally {
            fecharConexao(conexao, pstmt, rs);
	}
        return jogo;
    }
    public Jogo setNewJogo(int jogo_id, int jogador_id, String nome) {
        	PreparedStatement pstmt = null;
                Jogo jogo = new Jogo();
		ResultSet rs = null;
		Connection conexao = criarConexao();
		try {
                    jogo = getpJogo(jogo_id);
                    String sql = "INSERT INTO jogos (`nome`, `icone`, `latitude`, `longitude`, `ordmecanicas`, `nomeficticio`, `status`, `jogopai_id`, `user_id`) VALUES (?,?,?,?,?,?,?,?,?)";
                    pstmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    pstmt.setString(1, jogo.getNome());
                    pstmt.setString(2, jogo.getIcone());
                    pstmt.setString(3, jogo.getLatitude());
                    pstmt.setString(4, jogo.getLongitude());
                    pstmt.setString(5, jogo.getOrdMecanicas());
                    pstmt.setString(6, nome);
                    pstmt.setInt(7, 1);
                    pstmt.setInt(8, jogo_id);
                    pstmt.setInt(9, jogo.getUser_id());
                    pstmt.executeUpdate();
                    rs = pstmt.getGeneratedKeys();
                    rs.next();
                    jogo.setId((int) rs.getLong(1));
                    jogo.setJogopai_id(jogo_id);
                    jogo.setStatus(1);
                    jogo.setNomeficticio(nome);
                    return jogo;
		} catch (SQLException e) {
			System.out.println("Erro ao setNewJogo: " + e.getMessage());
                } finally {
			fecharConexao(conexao, pstmt, rs);
		}
	return jogo;
    }

    public ArrayList<Jogo> getJogosExecutando() {
        ArrayList<Jogo> jogos= new ArrayList<Jogo>();
        PreparedStatement pstmt = null;
	ResultSet rs = null;
	Connection conexao = criarConexao();
	try {
            String sql = "select * from jogos where status="+1;
            pstmt = conexao.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                Jogo jogo = new Jogo();
                jogo.setId(rs.getInt("id"));
                jogo.setNome(rs.getString("nome"));
                jogo.setIcone(rs.getString("icone"));
		jogo.setLatitude(rs.getString("latitude"));
                jogo.setLongitude(rs.getString("longitude"));
                jogo.setUser_id(rs.getInt("user_id"));
                jogo.setOrdMecanicas(rs.getString("ordmecanicas"));
                jogo.setNomeficticio(rs.getString("nomeficticio"));
                jogo.setStatus(rs.getInt("status"));
                jogo.setJogopai_id(rs.getInt("jogopai_id"));
                jogo.setUser_id(rs.getInt("user_id"));
                jogos.add(jogo);
            }
        } catch (SQLException e) {
            System.out.println("Erro no getJogosExecutando: " + e.getMessage());
        } finally {
            fecharConexao(conexao, pstmt, rs);
	}
        return jogos;
    }
        
}
