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

    public void imprimir() {
        System.out.println(" PASAJERO: "+pasajero.getNombre()+" "+pasajero.getApellido()+"\n"+viaje.getInfo()+".\n ASIENTO "+asiento+" - VALOR: "+valor+"\n");
    }
      public int getAsiento() {
        return asiento;
    }

    public Double getValor() {
        return valor;
    }

    public Viaje getViaje() {
        return viaje;
    }
    
}
