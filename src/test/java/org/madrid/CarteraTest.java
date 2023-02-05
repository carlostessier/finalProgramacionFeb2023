package org.madrid;

import static org.junit.jupiter.api.Assertions.*;

class CarteraTest {

    @org.junit.jupiter.api.Test
    void buscarCriptomoneda() {
        CriptoMoneda ayusoCoin = new CriptoMoneda("AyusoCoin", 1, 10000.0,"SHA-256");

        CriptoMoneda luna = new CriptoMoneda("Luna", 1, 0);
        Cartera cartera = new Cartera("Carlos");
        try {
            cartera.addCriptoMoneda(ayusoCoin);
            cartera.addCriptoMoneda(luna);
        } catch (Exception e) {
            fail("No debería lanzar excepción");
        }
        assertEquals(0, cartera.buscarCriptomoneda("Bitcoin"));
        assertEquals(1, cartera.buscarCriptomoneda("Luna"));
        assertEquals(-1, cartera.buscarCriptomoneda("Ether"));
    }

    @org.junit.jupiter.api.Test
    void addCriptoMoneda() {
        CriptoMoneda bitcoin = new CriptoMoneda("Bitcoin", 1, 10000);
        CriptoMoneda luna = new CriptoMoneda("Luna", 2.5, 0);
        CriptoMoneda bitcoinComprados = new CriptoMoneda("Bitcoin", 5, 10000);

        Cartera cartera = new Cartera("Carlos");
        try {
            cartera.addCriptoMoneda(bitcoin);
            cartera.addCriptoMoneda(luna);
            assertEquals("Bitcoin", cartera.getCriptomonedas()[0].getNombre());
            assertEquals(1, cartera.getCriptomonedas()[0].getCantidad());
            assertEquals("Luna", cartera.getCriptomonedas()[1].getNombre());
            assertEquals(2.5, cartera.getCriptomonedas()[1].getCantidad());
            assertNotNull(cartera.getCriptomonedas()[2]);
            assertEquals("", cartera.getCriptomonedas()[2].getNombre());
            cartera.addCriptoMoneda(bitcoinComprados);
            assertEquals(6.0, cartera.getCriptomonedas()[0].getCantidad());


        } catch (Exception e) {
            fail("No debería lanzar excepción");
        }


    }

    @org.junit.jupiter.api.Test
    void takeCriptoMoneda() {
        CriptoMoneda bitcoin = new CriptoMoneda("Bitcoin", 3, 10000);
        CriptoMoneda luna = new CriptoMoneda("Luna", 0, 0);
        Cartera cartera = new Cartera("Carlos");

        try {
            cartera.addCriptoMoneda(bitcoin);
            assertThrows(Exception.class, () -> cartera.takeCriptoMoneda("Bitcoin", 4));
            cartera.takeCriptoMoneda("Bitcoin", 2);
            assertEquals(1, cartera.getCriptomonedas()[0].getCantidad());
            cartera.takeCriptoMoneda("Bitcoin", 1);
            assertEquals(0, cartera.getCriptomonedas()[0].getCantidad());
            assertThrows(Exception.class, () -> cartera.takeCriptoMoneda("Bitcoin", 1));
            assertEquals(0, cartera.getCriptomonedas()[0].getCantidad());
            assertThrows(Exception.class, () -> cartera.takeCriptoMoneda("Luna", 1));

        } catch (Exception e) {
            fail("No debería lanzar excepción");
        }





    }

    @org.junit.jupiter.api.Test
    void getUsuario() {
        Cartera cartera = new Cartera("Carlos");
        assertEquals("Carlos", cartera.getUsuario());
    }

    @org.junit.jupiter.api.Test
    void setUsuario() {
        Cartera cartera = new Cartera("Carlos");
        cartera.setUsuario("María");
        assertEquals("María", cartera.getUsuario());
        assertEquals("María", cartera.getUsuario());
    }

    @org.junit.jupiter.api.Test
    void getCriptomonedas() {
        CriptoMoneda bitcoin = new CriptoMoneda("Bitcoin", 1, 10000);
        CriptoMoneda luna = new CriptoMoneda("Luna", 1, 0);
        Cartera cartera = new Cartera("Carlos");
        try {
            cartera.addCriptoMoneda(bitcoin);
            cartera.addCriptoMoneda(luna);

        } catch (Exception e) {
            fail("No debería lanzar excepción");
        }
        assertEquals("Bitcoin", cartera.getCriptomonedas()[0].getNombre());
        assertEquals("Luna", cartera.getCriptomonedas()[1].getNombre());
        assertNotNull(cartera.getCriptomonedas()[9]);
        assertEquals("", cartera.getCriptomonedas()[9].getNombre());
    }

    @org.junit.jupiter.api.Test
    void setCriptomonedas() {
        CriptoMoneda[] criptomonedas = new CriptoMoneda[10];
        criptomonedas[0] = new CriptoMoneda("Bitcoin", 1, 10000);
        criptomonedas[1] = new CriptoMoneda("Luna", 1, 0);

        Cartera cartera = new Cartera("Carlos");
        cartera.setCriptomonedas(criptomonedas);
        assertEquals("Bitcoin", cartera.getCriptomonedas()[0].getNombre());
        assertEquals("Luna", cartera.getCriptomonedas()[1].getNombre());
        assertEquals(null, cartera.getCriptomonedas()[9]);
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        CriptoMoneda bitcoin = new CriptoMoneda("Bitcoin", 1, 10000);
        CriptoMoneda luna = new CriptoMoneda("Luna", 1, 0);
        Cartera cartera = new Cartera("Carlos");
        try {
            cartera.addCriptoMoneda(bitcoin);
            cartera.addCriptoMoneda(luna);

        } catch (Exception e) {
            fail("No debería lanzar excepción");
        }
        assertEquals("Usuario: Carlos Monedas:Bitcoin 1.0 Luna 1.0", cartera.toString().trim());
    }
}