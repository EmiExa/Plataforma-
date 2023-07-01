public class Pasaje {

    private Pasajero pasajero;
    private Double valor;
    private Viaje viaje;
    
    public Pasaje(Pasajero pasajero, Viaje viaje, Double costo){
        this.viaje = viaje;
        valor = costo;
    }
    
}
