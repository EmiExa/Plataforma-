public class Pasaje {

    //revisar si hay que agregar pasajero, sino sacarlo del diagrama
    private Double valor;
    private Viaje viaje;
    public Pasaje(Viaje viaje, Double costo){
        this.viaje = viaje;
        valor = costo;
    }
}
