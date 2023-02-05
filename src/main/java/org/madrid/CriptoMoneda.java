package org.madrid;

public class CriptoMoneda extends Moneda {
    private String algoritmoHash;

    public CriptoMoneda() {
        this("", 0, 0,"");
    }


    public CriptoMoneda(String nombre, double cantidad, double valor) {
        this(nombre,cantidad, valor,"");

    }

    public CriptoMoneda(String nombre, double cantidad, double valor, String algoritmoHash) {
        super(nombre,cantidad, valor);
        this.algoritmoHash = algoritmoHash;
    }

    public String getAlgoritmoHash() {
        return algoritmoHash;
    }

    public void setAlgoritmoHash(String algoritmoHash) {
        this.algoritmoHash = algoritmoHash;
    }

    @Override
    public String toString() {
        return "CriptoMoneda{" +
                "algoritmoHash='" + algoritmoHash + '\'' +
                ", nombre='" + nombre + '\'' +
                ", cantidad=" + cantidad +
                ", valor=" + valor +
                '}';
    }
}
