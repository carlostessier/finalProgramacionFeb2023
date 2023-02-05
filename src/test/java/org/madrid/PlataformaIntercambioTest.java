package org.madrid;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

class PlataformaIntercambioTest {

    @Test
    void addCartera() {
        PlataformaIntercambio p = new PlataformaIntercambio();
        Cartera c1 = new Cartera("Carlos");
        Cartera c2 = new Cartera("María");
        try {
            p.addCartera(c1);
            p.addCartera(c2);
        } catch (Exception e) {
            fail("No debería lanzar excepción");
        }
        assertEquals("Carlos", p.getListaCarteras().get(0).getUsuario());
        assertEquals("María", p.getListaCarteras().get(1).getUsuario());
        assertEquals(2, p.getListaCarteras().size());
    }

    @Test
    void guardarCartera() {
        PlataformaIntercambio p = new PlataformaIntercambio();
        Cartera c1 = new Cartera("Carlos");
        Cartera c2 = new Cartera("María");
        CriptoMoneda bitcoin = new CriptoMoneda("Bitcoin", 2.5, 20000.0,"SHA-256");
        CriptoMoneda etherum = new CriptoMoneda("Ether", 3.3, 1500.0,"Ethash");

        try {
            p.addCartera(c1);
            p.addCartera(c2);
        } catch (Exception e) {
            fail("No debería lanzar excepción");
        }

        p.addMonedaCartera("Carlos", bitcoin);
        p.addMonedaCartera("Carlos", etherum);
        p.addMonedaCartera("María", bitcoin);

        p.guardarCartera("src/main/resources/carteras.csv");


    }

    @Test
    void cargarCartera() {

        PlataformaIntercambio p = new PlataformaIntercambio();
        // si existe el fichero
        File file = new File("src/main/resources/prueba.csv");
        if (!file.exists()){
            try (FileWriter writer = new FileWriter(file)) {
                writer.write("Carlos;Bitcoin;2.5;20000.0\n");
                writer.write("Carlos;Ether;1.0;1500.0\n");
                writer.write("María;Bitcoin;3.5;20000.0\n");
            } catch (Exception e) {
               fail("No debería lanzar excepción");
            }
        }
        p.cargarCartera("src/main/resources/prueba.csv");
        assertEquals(2, p.getListaCarteras().size());
        assertEquals(2, p.buscarCarteraRecursiva("Carlos",0).getNumCriptomonedas());
        assertEquals(2.5, p.buscarCarteraRecursiva("Carlos",0).getCriptomonedas()[0].getCantidad());
        assertEquals(1, p.buscarCarteraRecursiva("María",0).getNumCriptomonedas());
        assertEquals(20000.0, p.buscarCarteraRecursiva("María",0).getCriptomonedas()[0].getValor());
        assertEquals(1500.0, p.buscarCarteraRecursiva("Carlos",0).getCriptomonedas()[1].getValor());

        }

    @Test
    void buscarCartera() {
        PlataformaIntercambio p = new PlataformaIntercambio();
        Cartera c1 = new Cartera("Carlos");
        Cartera c2 = new Cartera("María");
        Cartera c3 = new Cartera("Lucia");

        try {
            p.addCartera(c1);
            p.addCartera(c2);
            p.addCartera(c3);
        } catch (Exception e) {
            fail("No debería lanzar excepción");
        }
        assertEquals(c1, p.buscarCarteraRecursiva("Carlos",0));
        assertEquals(c2, p.buscarCarteraRecursiva("María",0));
        assertEquals(c3, p.buscarCarteraRecursiva("Lucia",0));
    }

    @Test
    void addMonedaCartera() {
        PlataformaIntercambio p = new PlataformaIntercambio();
        Cartera c1 = new Cartera("Carlos");
        Cartera c2 = new Cartera("María");
        CriptoMoneda ayusocoin = new CriptoMoneda("AyusoCoin", 2.5, 20000.0,"SHA-256");
        CriptoMoneda etherum = new CriptoMoneda("Ether", 3.3, 1500.0,"Ethash");

        try {
            p.addCartera(c1);
            p.addCartera(c2);
        } catch (Exception e) {
            fail("No debería lanzar excepción");
        }

        p.addMonedaCartera("Carlos", ayusocoin);
        p.addMonedaCartera("Carlos", etherum);
        ayusocoin.setCantidad(3.5);
        p.addMonedaCartera("María", ayusocoin);

        assertEquals(2, p.buscarCarteraRecursiva("Carlos",0).getNumCriptomonedas());
        assertEquals(1, p.buscarCarteraRecursiva("María",0).getNumCriptomonedas());
        assertEquals(2.5, p.buscarCarteraRecursiva("Carlos",0).getCriptomonedas()[0].getCantidad());
        assertEquals(3.3, p.buscarCarteraRecursiva("Carlos",0).getCriptomonedas()[1].getCantidad());
        assertEquals(3.5, p.buscarCarteraRecursiva("María",0).getCriptomonedas()[0].getCantidad());

    }
}