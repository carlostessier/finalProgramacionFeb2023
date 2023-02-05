package org.madrid;

import java.io.*;
import java.util.ArrayList;

public class PlataformaIntercambio implements OperacionesPlataforma, Ficheros {

    String nombre;
    ArrayList<Cartera> listaCarteras;

    public PlataformaIntercambio(ArrayList<Cartera> listaCarteras) {
        this.listaCarteras = listaCarteras;
    }

    public PlataformaIntercambio() {
        this.listaCarteras = new ArrayList<>();
    }

    public void deleteCartera(Cartera cartera) throws Exception{
        if (listaCarteras.contains(cartera)) {
            listaCarteras.remove(cartera);
        } else {
            throw new Exception("No existe la cartera");
        }
    }

    @Override
    public void addCartera(Cartera cartera) throws Exception {
        boolean creada = true;
        // comprobamos si la cartera existe
        Cartera carteraUsuario = buscarCarteraRecursiva(cartera.getUsuario(),0);
        if (!carteraUsuario.getUsuario().equals("")) {
            throw new Exception("La cartera ya existe");
        } else {
            listaCarteras.add(cartera);
        }

    }

    @Override
    public void guardarCartera(String ficheroCSV) {
        // Creamos un fichero CSV para guardar todas las carteras
        // Cada cartera se guarda en una línea
        // El formato de cada línea es:
        // usuario;criptomoneda;;cantidad
        File fichero = new File(ficheroCSV);
        try (FileWriter fw = new FileWriter(fichero)) {
            for (Cartera cartera : listaCarteras) {
                for (int i = 0; i < cartera.getNumCriptomonedas(); i++) {

                        StringBuilder linea = new StringBuilder();
                        linea.append(cartera.getUsuario());
                        linea.append(";");
                        linea.append(cartera.getCriptomonedas()[i].getNombre());
                        linea.append(";");
                        linea.append(cartera.getCriptomonedas()[i].getCantidad());
                        linea.append(";");
                        linea.append(cartera.getCriptomonedas()[i].getValor());
                        linea.append("\n");
                        fw.write (linea.toString());

                }

            }
        } catch (
                IOException e) {
            System.err.println("Error al guardar el fichero");
        }

    }

    @Override
    public void cargarCartera(String ficheroCSV) {
        File fichero = new File(ficheroCSV);
        String linea;
        try (BufferedReader fr = new BufferedReader(new FileReader(fichero))) {
            linea = fr.readLine();
            while (linea != null) {
                String[] datos = linea.split(";");
                String usuario = datos[0];
                String cripto = datos[1];
                double cantidad = Double.parseDouble(datos[2]);
                double valor = Double.parseDouble(datos[3]);

                Cartera cartera = buscarCarteraRecursiva(usuario,0);
                if (cartera.getUsuario().equals("")) {
                    cartera = new Cartera(usuario);
                    listaCarteras.add(cartera);
                }
                try {
                    CriptoMoneda criptoMoneda = new CriptoMoneda(cripto,cantidad,valor);
                    cartera.addCriptoMoneda(criptoMoneda);
                } catch (Exception e) {
                    System.out.println("Error al añadir la criptomoneda "+nombre);
                    System.out.println(e.getMessage());
                }
                linea = fr.readLine();
            }

        } catch (FileNotFoundException e) {
            System.err.println("Error al cargar el fichero");

        } catch (IOException e) {
            System.err.println("Error al guardar el fichero");
        }
    }

    @Override
    public Cartera buscarCarteraRecursiva(String nombre, int posicion) {
        if (posicion == listaCarteras.size()) {
            return new Cartera();
        } else {
            if (listaCarteras.get(posicion).getUsuario().equals(nombre)) {
                return listaCarteras.get(posicion);
            } else {
                return buscarCarteraRecursiva(nombre, posicion + 1);
            }
        }
    }

    @Override
    public boolean addMonedaCartera(String usuario, CriptoMoneda crito) {
        boolean añadida = false;
        Cartera cartera = buscarCarteraRecursiva(usuario,0);
        if (!cartera.getUsuario().equals("")) {
            try {
                cartera.addCriptoMoneda(crito);
                añadida = true;
            } catch (Exception e) {

            }
        }
        return añadida;
    }

    public ArrayList<Cartera> getListaCarteras() {
        return listaCarteras;
    }

    public void setListaCarteras(ArrayList<Cartera> listaCarteras) {
        this.listaCarteras = listaCarteras;
    }

    public void imprimirCarteras() {
        for (Cartera cartera : listaCarteras) {
            System.out.println(cartera);

        }
    }


}
