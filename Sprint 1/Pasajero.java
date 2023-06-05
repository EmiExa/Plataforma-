import java.time.LocalDate;
import java.util.ArrayList;

public class Pasajero extends Usuario{
    private Tarjeta tarjeta;
    private String email;
    private String clave;
    private ArrayList<Suscripcion> suscripciones;
    private ArrayList<Pasaje> historialPasajes;
    public Pasajero(String nombre, String apellido, int dni,Tarjeta tarjeta, String email){
        super(nombre,apellido,dni);
        this.tarjeta = tarjeta;
        this.email = email;
        this.setClave();
        suscripciones = new ArrayList<>();
        historialPasajes = new ArrayList<Pasaje>();
    }
    private void setClave(){
        //generar la clave con sus condiciones
    }
    public void login(String nombre, int dni, String clave){
        //ingresar dni nombre y clave y chequea en una base de datos
    }
    public void suscribirseViaje(String origen, String destino){
        Suscripcion s = new Suscripcion(origen,destino);
        suscripciones.add(s);
    }
    public void darseBaja(String origen, String destino){
        Suscripcion s = new Suscripcion(origen,destino);
        suscripciones.remove(s);
    }
    public boolean suscrito(Suscripcion s){
        return suscripciones.contains(s);
    }
    //public Viaje buscarPasaje(LocalDate dia, String origen, String destino){
        //muestra los viajes correspondientes y el usuario selecciona uno
    //}
    //public Arraylist<Pasaje> comprarPasaje(Viaje Viaje, int numeroPasajes){
        //consulta si ese viaje tiene los asientos disponibles etc...
        //realiza la transaccion autocompletado etc...
        //genera los pasajes
        //agrega el viaje a historial
        //se fija si en el historial se repite el viaje mas de 3 veces
        //Si no tiene una suscripcion a ese viaje(origen destino) se le notifica que se puede suscribir al viaje improvisado
    //}

}
