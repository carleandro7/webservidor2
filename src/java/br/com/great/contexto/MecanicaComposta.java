package br.com.great.contexto;
import java.util.ArrayList;
import java.util.Collection;

public class MecanicaComposta extends Mecanica {
        private int meccomposta_id;
	private ArrayList<Mecanica> mecanica;

	/**
	 *  
	 */
	public void adicionarMecanica() {

	}

    public int getMeccomposta_id() {
        return meccomposta_id;
    }

    public void setMeccomposta_id(int meccomposta_id) {
        this.meccomposta_id = meccomposta_id;
    }

    public ArrayList<Mecanica> getMecanica() {
        return mecanica;
    }

    public void setMecanica(ArrayList<Mecanica> mecanica) {
        this.mecanica = mecanica;
    }
        
        

}
