package controlador;

import modelo.Bibliotecario;

public class ControladorBibliotecario
{
    public Bibliotecario crearBibliotecario(String nombre, int idPrestamo, int idEstudiante, int idLibro)
    {
        return new Bibliotecario(nombre, idPrestamo, idEstudiante, idLibro, System.out::println);
    }
}
