/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.great.dao;

import br.com.great.contexto.Foto;
import br.com.great.contexto.Posicao;
import br.com.great.factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Classe responsavel realizar toda a interação com banco de dados relacionado com entidade foto
 * @author carleandro
 */
public class FotosDAO extends ConnectionFactory{
        
        private static FotosDAO instance;
        /**
	 * 
	 * Método responsável por criar uma instancia da classe FotosDAO (Singleton)
	 * @author Carleandro Noleto
         * @return  static
	 * @since 14/01/2015
	 * @version 1.0
	 */
	public static FotosDAO getInstance(){
		if(instance == null)
			instance = new FotosDAO();
		return instance;
	}
        
        /**
	 * 
	 * Método responsável por set os dados da CFotos no banco de dados
	 *
         * @param image String
         * @param jogador_id String
         * @param latitude String
         * @param longitude String
         * @param cfotos_id String
	 * @return boolean
	 * @author Carleandro Noleto
	 * @since 14/01/2015
	 * @version 1.0
	 */
        public boolean setCFoto(String image, String jogador_id, String latitude, String longitude, String cfotos_id){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conexao = criarConexao();
		try {                    
                        String sql = "UPDATE `cfotos` SET  `image` =  '"+image+"',"+
                                " `jogador_id` =  '"+jogador_id+"', `latitude` =  '"+latitude+
                            "',`longitude` =  '"+longitude+"' WHERE  `cfotos`.`id` ="+cfotos_id+";";
                        System.out.println(sql);
                        pstmt = conexao.prepareStatement(sql);
                        pstmt.execute(sql);
			return true;
		} catch (SQLException e) {
			System.out.println("Erro ao salvar foto em grupo : " + e);
                }catch (Exception e){
                    System.out.println("Erro ao salvar arquivo foto em grupo : " + e);
                }finally {
			fecharConexao(conexao, pstmt, rs);
		}
		return false;
	}
        
        /**
	 * 
	 * Método responsável por get os dados da VFotos no banco de dados
	 *
	 * @author Carleandro Noleto
         * @param mecanica_id int
         * @return JSONObject
	 * @since 14/01/2015
	 * @version 1.0
	 */
        public Foto getMecVFotos(int mecanica_id){
                PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conexao = criarConexao();
                Foto vfoto =null;
                try{
                        String sql = "SELECT * FROM  cfotos LEFT JOIN mecsimples ON "
                                + " (mecsimples.id = cfotos.mecsimples_id) WHERE vfotos.mecsimples_id = "+mecanica_id;
                        pstmt = conexao.prepareStatement(sql);
                        rs = pstmt.executeQuery();
			if(rs.next()){
				vfoto = new Foto();
                                vfoto.setFoto_id(rs.getInt("vfotos.id"));
                                vfoto.setArqimage(rs.getString("vfotos.arqimage"));
                                vfoto.setPosicao(new Posicao(rs.getDouble("mecsimples.latitude"), rs.getDouble("mecsimples.longitude")));
                                vfoto.setMecsimples_id(rs.getInt("vfotos.mecsimples_id"));
                        }
		} catch (SQLException e) {
			System.out.println("Erro ao listar dados de uma mecanica vfotos: " + e.getMessage());
                } finally {
			fecharConexao(conexao, pstmt, rs);
		}
		return vfoto;
            
        }
        /**
	 * 
	 * Método responsável por get os dados da CFotos no banco de dados
	 *
	 * @author Carleandro Noleto
         * @param mecanica_id int
         * @return JSONObject
	 * @since 14/01/2015
	 * @version 1.0
	 */
        public Foto getMecCFotos(int mecanica_id){
                PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conexao = criarConexao();
                Foto cFotos =null;
                try{
                        String sql = "SELECT * FROM  cfotos  LEFT JOIN mecsimples ON "
                                + " (mecsimples.id = cfotos.mecsimples_id) WHERE cfotos.mecsimples_id = "+mecanica_id;
                        pstmt = conexao.prepareStatement(sql);
                        rs = pstmt.executeQuery();
			if(rs.next()){
				cFotos = new Foto();
                                cFotos.setFoto_id(rs.getInt("cfotos.id"));
                                cFotos.setArqimage(rs.getString("cfotos.image"));
                                cFotos.setPosicao(new Posicao(rs.getDouble("mecsimples.latitude"), rs.getDouble("mecsimples.longitude")));
                                cFotos.setMecsimples_id(rs.getInt("cfotos.mecsimples_id"));
                                
                        }
		} catch (SQLException e) {
			System.out.println("Erro ao listar dados de uma mecanica cfotos: " + e.getMessage());
                } finally {
			fecharConexao(conexao, pstmt, rs);
		}
		return cFotos;
            
    }
}
