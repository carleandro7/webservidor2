/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.great.util;

import br.com.great.contexto.Posicao;

/**
 *
 * @author carleandro
 */
public class FuncoesCalculo {

    public double distanciaKM(double latA, double longA, double latB, double longB) {
        int fatorKM = 6371;
        double PI = 3.14159265;
        return fatorKM * Math.acos(Math.cos(PI * (90 - latB) / 180) * Math.cos((90 - latA) * PI / 180) + Math.sin((90 - latB) * PI / 180) * Math.sin((90 - latA) * PI / 180) * Math.cos((longA - longB) * PI / 180));
    }

    public double distanciaMetros(double latA, double longA, double latB, double longB) {
        int fatorMetros = 6371000;
        double PI = 3.14159265;
        return fatorMetros * Math.acos(Math.cos(PI * (90 - latB) / 180) * Math.cos((90 - latA) * PI / 180) + Math.sin((90 - latB) * PI / 180) * Math.sin((90 - latA) * PI / 180) * Math.cos((longA - longB) * PI / 180));
    }

    public int distanciaMecJogador(Posicao p1, Posicao p2) {
        double distancia = distanciaKM(p1.getLatitude(), p1.getLongitude(), p2.getLatitude(), p2.getLongitude());
        if (distancia < 1) {
            return 1;
        } else if (distancia < 2) {
            return 2;
        } else if (distancia < 3) {
            return 3;
        } else if (distancia < 4) {
            return 4;
        } else if (distancia < 5) {
            return 5;
        }
        return 6;
    }
}
