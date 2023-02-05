package org.madrid;

import java.util.ArrayList;

public interface Ficheros {

    /**
     * Guarda la cartera en un fichero CSV con el formato
     * Carlos;Bitcoin;2.5;20000.0
     * Carlos;Ether;1.0;1500.0
     * María;Bitcoin;3.5;20000.0
     * Eva;Luna;1.0;0.0001
     * @param ficheroCSV
     */
    public void guardarCartera(String ficheroCSV);

    /**
     * Carga una cartera desde un fichero CSV con el formato
     * Carlos;Bitcoin;2.5;20000.0
     * Carlos;Ether;1.0;1500.0
     * María;Bitcoin;3.5;20000.0
     * Eva;Luna;1.0;0.0001
     * @param ficheroCSV
     */
    public void cargarCartera(String ficheroCSV);

}
