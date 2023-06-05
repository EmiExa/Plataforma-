public class Pasaje {
    private Double valor;
    private Viaje viaje;
    public Pasaje(Viaje viaje, Double costo){
        this.viaje = viaje;
        valor = costo;
    }
}
