public class Pasaje {

    private Pasajero pasajero;
    private int asiento;
    private double valor;
    private Viaje viaje;
    
    public Pasaje(Pasajero pasajero, Viaje viaje, int asiento, double costo) {
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

    public double getValor() {
        return valor;
    }

    public Viaje getViaje() {
        return viaje;
    }
    
}
