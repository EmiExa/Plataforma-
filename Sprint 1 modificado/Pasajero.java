import java.util.ArrayList;
import java.util.Scanner;

public class Pasajero extends Usuario{
    private Tarjeta tarjeta;
    private String email;
    private String clave;
    private ArrayList<Suscripcion> suscripciones;
    private ArrayList<Pasaje> historialPasajes;

    public Pasajero(String nombre, String apellido, int dni,Tarjeta tarjeta, String email, String clave){
        this(nombre,apellido,dni,email,clave);
        this.tarjeta = tarjeta;
        }
    public Pasajero(String nombre, String apellido, int dni, String email, String clave){
        super(nombre,apellido,dni);
        this.email = email;
        this.clave = clave;
        suscripciones = new ArrayList<>();
        historialPasajes = new ArrayList<Pasaje>();
    }

    public void addPasajes(ArrayList<Pasaje> pasajes) {
        historialPasajes.addAll(pasajes);
    }
    public void addTarjeta() {
        System.out.print(" Ingresar número de tarjeta: ");
        this.tarjeta = tarjeta;
    }
    private void cambiarClave(String contraseña){
        //generar la clave con sus condiciones
    }
    
    public boolean suscrito(Suscripcion s){
        return suscripciones.contains(s);
    }

    //public Arraylist<Pasaje> comprarPasaje(Viaje Viaje, int numeroPasajes){
        //consulta si ese viaje tiene los asientos disponibles etc...
        //realiza la transaccion autocompletado etc...
        //genera los pasajes
        //agrega el viaje a historial
        //se fija si en el historial se repite el viaje mas de 3 veces
        //Si no tiene una suscripcion a ese viaje(origen destino) se le notifica que se puede suscribir al viaje improvisado
    //}
    public String getClave(){
        return clave;
    }
    public boolean equals(Pasajero p){
        if((super.getDni() == p.getDni()) && (this.getClave() == p.getClave()))
            return true;
        return false;
    }
}
