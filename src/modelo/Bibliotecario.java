package modelo;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.function.Consumer;
import controlador.*;

/**
 * Clase que representa un trabajador de la Biblioteca.
 */
public class Bibliotecario implements Runnable
{
    private String nombre;      // Atributo que almacena el nombre del bibliotecario
    private int id_estudiante;  // Atributo que almacena el "id" del estudiante a quien le entregará el libro
    private int id_libro;   // Atributo que almacena el "id" del libro que transporta.
    private final PriorityBlockingQueue<Libros> bolsaDeLibros = new PriorityBlockingQueue<>();  // Atributo para almacenar los libros que lleva.
    private Consumer<String> logger;    // Callback para enviar mensajes a la GUI
    ControladorLibros controladorLibros = new ControladorLibros();

    // Constructor sin parámetros:
    public Bibliotecario(){}

    // Constructor con parámetros:
    public Bibliotecario (String nombre, int id_estudiante, int id_libro, Consumer<String> logger)
    {
        this.nombre = nombre;
        this.id_estudiante = id_estudiante;
        this.id_libro = id_libro;
        this.logger = logger != null ? logger: System.out::println; // fallback por si no se pasa
    }

    // Se implementan los GETTERS:

    public String  getNombre() {return nombre;}
    public int getId_estudiante() {return id_estudiante;}
    public int getId_libro() {return id_libro;}
    public PriorityBlockingQueue <Libros> getBolsaDeLibros() {return bolsaDeLibros;}

    // Se implementan los SETTERS:
    public void setNombre(String nombre) {this.nombre = nombre;}
    public int setId_estudiante(int id_estudiante) {return this.id_estudiante = id_estudiante;}
    public void setId_libro(int id_libro) {this.id_libro = id_libro;}
    public void setLogger(Consumer<String> logger) {this.logger = logger != null ? logger : System.out::println;}


    // Método auxiliar para escribir mensajes de forma uniforme
    private void log (String mensaje) {logger.accept(mensaje);}

    /**
     * Método que se utiliza para simular un tiempo de espera.
     * @return un valor tipo integer con la cantidad de milisegundos que se desea pausar la ejecución de un método.
     */
    public int tiempoAleatorio() {
        return (int) (Math.random() * 3500) + 500;
    }

    @Override
    public void run()
    {
        try
        {
            enCaminoABodega();
            Thread.sleep(tiempoAleatorio());
            bibliotecarioEntregaLibros();
            Thread.sleep(tiempoAleatorio());
            bibliotecarioActualizaBD();
            Thread.sleep(tiempoAleatorio());
            finalizarCiclo();
        }
        catch (InterruptedException e)
        {
                Thread.currentThread().interrupt();
                log("[MAIN] se ha interrumpido el proceso 'run()'.");
        }
    }

    public void enCaminoABodega()
    {
        try
        {
            Thread.sleep(2000);
            log("[" + Thread.currentThread().getName() + "] " + "[BIBLIOTECARIO] " +
                    getNombre() +
                    " se encuentra caminando a la bodega a buscar el libro.");
            Thread.sleep(2000);
            if (controladorLibros.retirarLibro(id_libro))
            {
                log("[" + Thread.currentThread().getName() + "] " + "[BIBLIOTECARIO] " +
                        getNombre() +
                        " retiró el libro con éxito y se dirige a entregarlo al estudiante.");
            }
            Thread.sleep(2000);
        }
        catch (InterruptedException e)
        {
            log("[" + Thread.currentThread().getName() + "] " + "[REPARTIDOR] " + getNombre() + " no pudo llegar a la bodega de libros.");
            Thread.currentThread().interrupt();
        }
    }

    public void bibliotecarioEntregaLibros()
    {
        try
        {
            Thread.sleep(2000);
            log("[" + Thread.currentThread().getName() + "] " + "[BIBLIOTECARIO] " +
                    getNombre() + " " + "está entregando el libro al estudiante.");
            Thread.sleep(2000);

        }
        catch (InterruptedException e)
        {
            log("[" + Thread.currentThread().getName() + "] " + "[BIBLIOTECARIO] " + getNombre() + " no pudo entregar el libro.");
            Thread.currentThread().interrupt();
        }
    }

    public void bibliotecarioActualizaBD()
    {
        try
        {
            Thread.sleep(2000);
            log("[" + Thread.currentThread().getName() + "] " + "[BIBLIOTECARIO] " +
                    getNombre() + " " + "está actualizando la base de datos.");
            Thread.sleep(2000);

        }
        catch (InterruptedException e)
        {
            log("[" + Thread.currentThread().getName() + "] " + "[BIBLIOTECARIO] " + getNombre() + " no pudo actualizar la base de datos.");
            Thread.currentThread().interrupt();
        }
    }

    public void finalizarCiclo() throws InterruptedException {
        log("[" + Thread.currentThread().getName() + "] [BIBLIOTECARIO] : " + getNombre() +
                " finaliza su jornada.");
    }
}

