package org.madrid;

public interface OperacionesCartera {

    /**
     * Añade una criptomoneda a la cartera si hay espacio libre
     * En caso de existir añade la cantidad a la que ya existe
     *
     * @param criptoMoneda
     * @throws Exception si no hay espacio para más criptomonedas
     * Devuelve una excepción con el mensaje "No hay espacio libre"
     */
    public void addCriptoMoneda(CriptoMoneda criptoMoneda) throws Exception;

    /**
     * Borra una criptomoneda a la cartera
     *
     * @param criptoMoneda
     */
    public void deleteCriptoMoneda(CriptoMoneda criptoMoneda);


        /**
         * Devuelve una cantidad de criptomonedas de la cartera
         * si esta existe y hay suficiente cantidad
         * En ese caso se descontará la cantidad de la cartera
         *
         * @param nombre
         * @param cantidad
         * @return cantidad de criptomonedas que se han retirado
         * @throws Exception si no existe la criptomoneda o no hay suficiente cantidad
         */
    public double takeCriptoMoneda(String nombre, double cantidad) throws Exception;

    /**
     * Devuelve la posición de la criptomoneda en la cartera
     * En caso de no encontrarla devolverá -1
     *
     * @param nombre
     * @return
     */
    public int buscarCriptomoneda(String nombre);
}

