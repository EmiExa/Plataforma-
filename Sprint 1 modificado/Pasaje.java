public class Pasaje {

    private Pasajero pasajero;
    private int asiento;
    private Double valor;
    private Viaje viaje;
    
    public Pasaje(Pasajero pasajero, Viaje viaje, int asiento, Double costo) {
        this.pasajero = pasajero;
        this.asiento = asiento;
        this.viaje = viaje;
        valor = costo;
    }
    
}
