/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.great.dao;

import br.com.great.factory.ConnectionFactory;
import br.com.great.model.Jogador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;

/**
 * Classe responsavel realizar toda a interação com banco de dados relacionado com entidade jogadores
 * @author carleandro
 */
public class JogadoresDAO extends ConnectionFactory {
    
    	private static JogadoresDAO instance;
	
	
	/**
	 * 
	 * Método responsável por criar uma instancia da classe JogadoresDAO (Singleton)
	 *
	 * @return Static
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
	 * @return ArrayList do tipo Jogador
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
         * @param email String
         * @param password String
	 * @return Jogador Dados do jogador
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
                            jogador.setIddispositivo(rs.getString("iddispositivo"));                            
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
	 * Método responsável por get em todos os dados do jogador
	 *
         * @param id String
	 * @return Jogadores Dados do jogador
	 * @author Carleandro Noleto
	 * @since 27/11/2014
	 * @version 1.0
	 */
        public Jogador getJogador(int id){
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Jogador jogador = new Jogador();
		conexao = criarConexao();
		try {
			pstmt = conexao.prepareStatement("select * from jogadores where id = "+id);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				jogador.setId(rs.getInt("id"));
				jogador.setEmail(rs.getString("email"));
				jogador.setPassword(rs.getString("password"));
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao listar dados de um jogador: " + e);
		} finally {
			fecharConexao(conexao, pstmt, rs);
		}
		return jogador;
	}
        /**
	 * Método responsável cadastrar um jogador
	 *
         * @param email String
         * @param password  String
	 * @return String Mensagem true ou false
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
        
        /**
	 * Método lista todos os deviceRegsId dos jogadores de um jogo
	 *
         * @param jogo_id  String
	 * @return ArrayList  Lista de Jogador
	 * @author Carleandro Noleto
	 * @since 23/01/2015
	 * @version 1.0
	 */
        public ArrayList<Jogador> getDeviceRegsIDJogo(int jogo_id){
            	Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Jogador> jogadores = null;		
		conexao = criarConexao();		
		try {
                        jogadores = new ArrayList<Jogador>();
			pstmt = conexao.prepareStatement("SELECT jogador_jogos.jogador_id, jogadores.iddispositivo, jogadores.email FROM jogador_jogos "
                                + "LEFT JOIN jogadores ON (jogadores.id = jogador_jogos.jogador_id) "
                                + "WHERE jogador_jogos.jogo_id ="+jogo_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				Jogador jogador = new Jogador();				
				jogador.setId(rs.getInt("jogador_jogos.jogador_id"));
				jogador.setIddispositivo(rs.getString("jogadores.iddispositivo"));				
                                jogador.setEmail(rs.getString("jogadores.email"));				
				jogadores.add(jogador);
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao listar todos deviceRegsID dos jogadores: " + e.getMessage());
		} finally {
			fecharConexao(conexao, pstmt, rs);
		}
		return jogadores;
	}
/**
	 * Método lista todos os deviceRegsId dos jogadores de um grupo
	 *
         * @param jogo_id  String
	 * @return ArrayList  Lista de Jogador
	 * @author Carleandro Noleto
	 * @since 23/01/2015
	 * @version 1.0
	 */
        public ArrayList<Jogador> getDeviceRegsID(int grupo_id){
            	Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Jogador> jogadores = null;		
		conexao = criarConexao();		
		try {
                        jogadores = new ArrayList<Jogador>();
			pstmt = conexao.prepareStatement("SELECT jogador_jogos.jogador_id, jogadores.iddispositivo, jogadores.email FROM jogador_jogos "
                                + "LEFT JOIN jogadores ON (jogadores.id = jogador_jogos.jogador_id) "
                                + "WHERE jogador_jogos.grupo_id ="+grupo_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				Jogador jogador = new Jogador();				
				jogador.setId(rs.getInt("jogador_jogos.jogador_id"));
				jogador.setIddispositivo(rs.getString("jogadores.iddispositivo"));				
                                jogador.setEmail(rs.getString("jogadores.email"));				
				jogadores.add(jogador);
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao listar todos deviceRegsID dos jogadores: " + e.getMessage());
		} finally {
			fecharConexao(conexao, pstmt, rs);
		}
		return jogadores;
	}
      /**
     * Metodo responsavel por verificar e alterar se o usuario estiver com outro
     * dispositivo
     * @param jogador_id id do jogador
     * @param device_id id que idencifica o dispositivo atraves de msg GCM
     * @return String Mensagem tru e false
     */
        public String registroDevice(String jogador_id, String device_id) {
        	Connection conexao = null;
                    PreparedStatement pstmt = null;
                    ResultSet rs = null;
                    String mensagem="false";
                    conexao = criarConexao();
		try {
                    pstmt = conexao.prepareStatement("UPDATE jogadores SET iddispositivo ='"+device_id+"' WHERE id = "+jogador_id);
                    pstmt.executeUpdate();
                    mensagem="true";
			
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar deviceRegsID dos jogadores: " + e.getMessage());
		} finally {
			fecharConexao(conexao, pstmt, rs);
		}
		return mensagem;
    }

     /**
     * Metodo responsavel por verificar e alterar se o usuario estiver com outro
     * dispositivo
     * @param grupo_id id do grupo do jogo
     * @return ArrayList lista de jogadores que estao naquele grupo
     */
    public ArrayList<Jogador> getJogadores(int grupo_id) {
            	Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Jogador> jogadores = null;		
		conexao = criarConexao();		
		try {
                        jogadores = new ArrayList<Jogador>();
			pstmt = conexao.prepareStatement("SELECT jogador_jogos.jogador_id, jogadores.iddispositivo, jogadores.email FROM jogador_jogos "
                                + "LEFT JOIN jogadores ON (jogadores.id = jogador_jogos.jogador_id) "
                                + "WHERE jogador_jogos.grupo_id ="+grupo_id);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Jogador jogador = new Jogador();				
				jogador.setId(rs.getInt("jogador_jogos.jogador_id"));
				jogador.setIddispositivo(rs.getString("jogadores.iddispositivo"));				
                                jogador.setEmail(rs.getString("jogadores.email"));				
				jogadores.add(jogador);
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao listar todos por grupo getJogadores: " + e.getMessage());
		} finally {
			fecharConexao(conexao, pstmt, rs);
		}
		return jogadores;
    }
    /**
     * Metodo responsavel lista todos os arquivos necessario para o inicio do jogo
     * dispositivo
     * @param grupo_id id do grupo do jogo
     * @param latitude localização do jogador
     * @param longitude localização do jogador
     * @return JSONArray lista com todos os arquivos e o id de sua mecanica
     */
    public JSONArray getTodosArquivos(int grupo_id, String latitude, String longitude){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JSONArray arquivos = new JSONArray();
                JSONArray listMissoes = new JSONArray();
		Connection conexao = criarConexao();
		try {
                         listMissoes = MissoesDAO.getInstance().getMissoesRegiao(1, grupo_id, latitude, longitude, listMissoes,"");
                         listMissoes = MissoesDAO.getInstance().getMissoesRegiao(2, grupo_id, latitude, longitude , listMissoes, sqlMissoesAnd(listMissoes));
                         listMissoes = MissoesDAO.getInstance().getMissoesRegiao(3, grupo_id, latitude, longitude, listMissoes, sqlMissoesAnd(listMissoes));
                         listMissoes = MissoesDAO.getInstance().getMissoesRegiao(4, grupo_id, latitude, longitude, listMissoes, sqlMissoesAnd(listMissoes));
                         listMissoes = MissoesDAO.getInstance().getMissoesRegiao(0, grupo_id, latitude, longitude, listMissoes, sqlMissoesAnd(listMissoes));
                         for(int j=0; j<listMissoes.length(); j++){
                            arquivos.put(MissoesDAO.getInstance().getMissoes(listMissoes.getJSONObject(j).getInt("id"),
                                    listMissoes.getJSONObject(j).getInt("prioridade")));
                         }
		} catch (JSONException e) {
			System.out.println("Erro ao listar getTodosArquivos: " + e.getMessage());
                } finally {
			fecharConexao(conexao, pstmt, rs);
		}
		return arquivos;
    }
    
    private String sqlMissoesAnd(JSONArray listMissoes){
        String sql ="";
        try {
            for(int j=0; j<listMissoes.length(); j++){
                 if((j+1) ==listMissoes.length()){
                     sql+=" missoes.id != "+listMissoes.getJSONObject(j).getInt("id");
                 }else{
                     sql+=" missoes.id != "+listMissoes.getJSONObject(j).getInt("id")+" AND ";
                 }     
            }    
        } catch (Exception ex) {
             System.out.println("Erro em sqlMissoesAnd: " + ex.getMessage()); 
        }
          if(sql.length()>0){
              sql=" AND ("+sql+")";
          }
        return sql;
    }

    public JSONArray getTodosArquivos(int grupo_id) {
        PreparedStatement pstmt = null;
		ResultSet rs = null;
		JSONArray arquivos = new JSONArray();
                JSONArray listMissoes = new JSONArray();
		Connection conexao = criarConexao();
		try {
                         listMissoes = MissoesDAO.getInstance().getMissoesRegiao(0, grupo_id, "", "", listMissoes,"");
                         for(int j=0; j<listMissoes.length(); j++){
                            arquivos.put(MissoesDAO.getInstance().getMissoes(listMissoes.getJSONObject(j).getInt("id"),
                                    listMissoes.getJSONObject(j).getInt("prioridade")));
                         }
		} catch (JSONException e) {
			System.out.println("Erro ao listar getTodosArquivos: " + e.getMessage());
                } finally {
			fecharConexao(conexao, pstmt, rs);
		}
		return arquivos;
    }

	
}
    
