import java.util.ArrayList;

import java.util.*;
public class Suscripcion {
    private String origen;
    private String destino;
    private ArrayList<Pasajero> pasajeros;
    public Suscripcion(String origen, String destino){
        this.origen = origen;
        this.destino = destino;
        this.pasajeros = new ArrayList<>();
    }
    public void addPasajero(Pasajero p){
        if(!pasajeros.contains(p))
            pasajeros.add(p);
    }
    public void eliminarPasajero(Pasajero p){
        pasajeros.remove(p);
    }
    public boolean equals(Object s){
        if (s instanceof Suscripcion) {
            Suscripcion sus = (Suscripcion) s;
            return ((origen.equals(sus.getOrigen())) && (destino.equals(sus.getDestino())));
        }
        else
            return false;
    }
    public String getOrigen(){
        return origen;
    }
    public String getDestino(){
        return destino;
    }
    public void notificar(){
        for(Pasajero p:pasajeros){
            System.out.println(p.getNombre());
            //para cada pasajero se le envia un mail con el viaje en descuento
        }
    }
    public boolean existePasajero(Pasajero p){
        for(Pasajero pasajero:pasajeros){
            if(p.equals(pasajero))
                return true;
        }
        return false;
    }
}
