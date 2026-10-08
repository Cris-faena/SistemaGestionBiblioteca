package controlador;

import modelo.Bibliotecario;

public class ControladorBibliotecario
{
    public Bibliotecario crearBibliotecario(String nombre, int idEstudiante, int idLibro)
    {
        return new Bibliotecario(nombre, idEstudiante, idLibro, System.out::println);
    }
}
