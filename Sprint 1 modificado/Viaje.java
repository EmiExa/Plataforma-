import java.time.LocalDate;
import java.util.ArrayList;

public class Viaje {
    private String origen;
    private String destino;
    private int cantAsientosDisponibles;
    private Colectivo colectivo;
    private LocalDateTime fechaSalida;
    private Double monto;

    public Viaje(String origen, String destino, Colectivo colectivo, LocalDateTime fechaSalida, Double monto, int horasRestantes) { //estabamos un toq complicados con el tema del Localdate
        this.origen = origen;                                                                                                                               //entonces hicimos el horas restantes para parchearlo
        this.destino = destino;
        this.colectivo = colectivo;
        this.cantAsientosDisponibles = this.colectivo.getAsientos();
        this.fechaSalida = fechaSalida;
        this.monto = monto;
        this.horasRestantes = horasRestantes;
        this.pasajesVendidos = new ArrayList<Pasaje>();
    }
    
    public boolean esImprovisado( int horas, int lugares){
        int horasRestantes = (LocalDateTime.now().until(fechaSalida)).get(ChronoUnits.HOURS);
        if((horasRestantes <= horas) && (cantAsientosDisponibles <= lugares))
            return true;
        else
            return false;
    }


    
    public void addPasajero(Pasaje pas){
        if(cantAsientosDisponibles>0) {
            this.pasajesVendidos.add(pas);
            this.cantAsientosDisponibles--;
        }
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public int getCantAsientosDisponibles() {
        return cantAsientosDisponibles;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public Double getMonto() {
        return monto;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public void setCantAsientosDisponibles(int cantAsientosDisponibles) {
        this.cantAsientosDisponibles = this.cantAsientosDisponibles - cantAsientosDisponibles;
    }
    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public boolean tieneDisponibilidad() {
        return this.cantAsientosDisponibles > 0;
    }
    
    public boolean tieneDisponibilidad(int cantidad){
        return  this.cantAsientosDisponibles >= cantidad;
    }

    public String getInfo() {
        return " desde "+origen+" hasta "+destino+". "+fechaSalida;
    }

}
