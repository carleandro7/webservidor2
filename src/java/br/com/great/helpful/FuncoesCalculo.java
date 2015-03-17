/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.great.helpful;

/**
 *
 * @author carleandro
 */
public class FuncoesCalculo {
    
    public double distanciaKM(double latA, double longA, double latB, double longB){
        int fatorKM=6371;
        double PI = 3.14159265;
        return fatorKM *Math.acos(Math.cos(PI*(90-latB)/180)*Math.cos((90-latA)*PI/180)+Math.sin((90-latB)*PI/180)*Math.sin((90-latA)*PI/180)*Math.cos(( longA - longB)*PI/180));
    }
    
    public double distanciaMetros(double latA, double longA, double latB, double longB){
        int fatorMetros = 6371000;
        double PI = 3.14159265;
        return fatorMetros *Math.acos(Math.cos(PI*(90-latB)/180)*Math.cos((90-latA)*PI/180)+Math.sin((90-latB)*PI/180)*Math.sin((90-latA)*PI/180)*Math.cos(( longA - longB)*PI/180));
    }
}
