package modelo;

/**
 * Tipo especial de clase que representa un conjunto fijo de constantes.
 * Para este caso representa los roles de los usuarios de esta aplicación.
 */
public enum TipoUsuario
{
    BIBLIOTECARIO,  // Entidad que puede manipular todas las funciones de la app.
    ESTUDIANTE      // Entidad con privilegios limitados para manipular la app.
}
