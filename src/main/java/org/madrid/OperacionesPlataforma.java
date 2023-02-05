package org.madrid;

public interface OperacionesPlataforma {
    /**
     * Busca una cartera de un usuario
     * En caso de no encontrarla devuelve una Cartera vacía
     * (un objeto Cartera con el usuario "" y sin criptomonedas)
     * @param usuario nombre del usuario
     * @param posicion Posición desde donde empieza a buscar
     * @return Cartera del usuario
     */
    public Cartera buscarCarteraRecursiva(String usuario, int posicion);

    /**
     * Añade una cartera a la plataforma, si no existe el usuario
     * o el usuario no tiene espacio libre devuelva falso
     * @param usuario
     * @param cripto
     * @return
     */
    public boolean addMonedaCartera(String usuario, CriptoMoneda cripto);

    /**
     * Añade una cartera a la plataforma
     *
     * @param cartera
     * @throws Exception
     */
    public void addCartera(Cartera cartera) throws Exception;

    /**
     * Borra una cartera de la plataforma
     * Si no existe el usuario de la cartera lanzará una excepción
     * @param cartera
     * @throws Exception si no existe el usuario de la cartera
     */
    public void deleteCartera(Cartera cartera) throws Exception;

}
