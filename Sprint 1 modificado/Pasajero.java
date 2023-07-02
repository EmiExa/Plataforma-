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

    public void addTarjeta(Banco banco) {
    	System.out.println("GENERAR NUEVA TARJETA: ");
        Scanner s = new Scanner(System.in);
        System.out.print(" Ingresar número de tarjeta: "); int nroTarjeta = s.nextInt();
        s.nextLine();
        String marca = "visa";
        if (banco.validar(nroTarjeta)) {
            Tarjeta tarjeta = new Tarjeta(nroTarjeta,banco, marca);
            this.tarjeta = tarjeta;	
            System.out.println("TARJETA GENERADA.");
        }
    }

    private void cambiarClave(String contraseña){
        //generar la clave con sus condiciones
    }

    public boolean suscrito(Suscripcion s){
        return suscripciones.contains(s);
    }

    public int cantViajes(String origen, String destino) {
        int suma = 0;
        for (Pasaje p: historialPasajes) {
            Viaje v = p.getViaje();
            if (v.getOrigen().equals(origen) && v.getDestino().equals(destino))
                suma++;
        }
        return suma;
    }
    
    public String getClave(){
        return clave;
    }
    public boolean equals(Pasajero p){
        if((super.getDni() == p.getDni()) && (this.getClave() == p.getClave()))
            return true;
        return false;
    }

    public Tarjeta getTarjeta() {
        return tarjeta;
    }
}
