package modelo;

/**
 * Clase que representa un estudiante que solicita libros en la biblioteca.
 */
public class Estudiante extends Persona
{
    private String curso;   // Atributo para asignar el curso escolar del estudiante.

    // Constructor sin parámetros
    public Estudiante(){}

    // Constructor con parámetros. Hereda atributos de la superclase
    public Estudiante(int id, String nombre, String rut, String correo, String curso)
    {
        super(id,nombre,rut,correo);
        this.curso = curso;
    }

    // Se implementan los GETTERS:

    /**
     * Método que devuelve el valor de la variable curso.
     * @return "curso" del estudiante.
     */
    public String getCurso() {return curso;}

    // Se implementan los SETTERS:

    /**
     * Método que modifica el valor de la variable curso.
     * @param curso nuevo curso que se requiere asignar a este objeto.
     */
    public void setCurso(String curso) {this.curso = curso;}

    // Se implementa el método toString

    /**
     * Método que devuelve una cadena de texto con información del objeto creado.
     * Hereda atributos de la superclase "Persona".
     * @return "ID" + "nombre" + "RUT" + "correo" + "curso"
     */
    @Override
    public String toString()
    {
        String base = super.toString();
        return base + " - " + curso;
    }
}
