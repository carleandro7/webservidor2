package br.com.great.contexto;
public class Posicao {

	private double latitude;

	private double longitude;
    public Posicao(){
        
    }
    public Posicao(double latitide, double longitude){
        this.latitude = latitide;
        this.longitude = longitude;
    }
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
        

}
