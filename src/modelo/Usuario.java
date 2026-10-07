package modelo;

import util.HashUtil;
/**
 * Clase que representa un usuario autorizado a manipular la aplicación.
 */
public class Usuario extends Persona
{
    private String contraseña;  // Atributo para asignar una contraseña al usuario.
    private TipoUsuario rol;    // Atributo para asignar un rol al usuario.

    // Se establece un constructor sin parámetros
    public Usuario(){}

    // Se establece un constructor con parámetros
    public Usuario (int id, String nombre, String rut, String correo, String contraseña, TipoUsuario rol)
    {
        super(id,nombre,rut,correo);
        this.contraseña = HashUtil.sha256(contraseña); // Se llama al método HashUtil para almacenar la contraseña proporcionada por el usuario
        this.rol = rol;
    }

    // Método para verificar la contraseña
    public boolean verificarContraseña(String contraseñaIngresada)
    {
        if (contraseñaIngresada == null || this.contraseña == null)
        {
            return false;
        }
        String hashIngresado = HashUtil.sha256(contraseñaIngresada);
        return this.contraseña.equals(hashIngresado);
    }

    // Se implementan los GETTERS:

    /**
     * Método que devuelve la contraseña del usuario.
     * @return contraseña del usuario.
     */
    public String getContraseña() {return contraseña;}

    /**
     * Método que devuelve el tipo de usuario.
     * @return "Enum" con el tipo de usuario.
     */
    public TipoUsuario getRol() {return rol;}

    // Se implementan los SETTERS:

    /**
     * Método para ajustar el valor de la variable contraseña.
     * @param contraseña nueva contraseña que se requiere asignar al objeto.
     */
    public void setContraseña(String contraseña)
    {
        if (contraseña == null)
        {
            throw new IllegalArgumentException("La contraseña no puede estar vacía");
        }
        this.contraseña = contraseña;
    }

    /**
     * Método para ajustar el valor de la variable contraseña.
     * @param rol nuevo rol que se requiere asignar al objeto.
     */
    public void setRol(TipoUsuario rol) {this.rol = rol;}

    // Se implementa el método toString:

    /**
     * Método que devuelve una cadena de texto con información del objeto.
     * Hereda algunos atributos de la clase padre.
     * @return "ID" + "nombre" + "RUT" + "correo" + "contraseña" + "rol"
     */
    @Override
    public String toString()
    {
        String base = super.toString();
        return base + " - " + "******" + " - " + rol;
    }
}
