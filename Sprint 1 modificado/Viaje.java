import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Viaje {
    private String origen;
    private String destino;
    private int cantAsientosDisponibles;
    private Colectivo colectivo;
    private LocalDateTime fechaSalida;
    private double monto;

    public Viaje(String origen, String destino, Colectivo colectivo, LocalDateTime fechaSalida, double monto) { 
        this.origen = origen;                                                                                                                               
        this.destino = destino;
        this.colectivo = colectivo;
        this.cantAsientosDisponibles = this.colectivo.getAsientos();
        this.fechaSalida = fechaSalida;
        this.monto = monto;
    }

    public boolean esImprovisado(int horasRestantes, int lugares) {
    	LocalDateTime ahora = LocalDateTime.now();
        if ((fechaSalida.minus(horasRestantes,ChronoUnit.HOURS)).compareTo(ahora)<=0 && (cantAsientosDisponibles < lugares))
            return true;
        else
            return false;
    }



    public void addPasajero(Pasaje pas){
        if(cantAsientosDisponibles>0) {
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
        return fechaSalida.toLocalDate();
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

    public void decrementarAsientosDisponibles(int cantidad){
        this.cantAsientosDisponibles -= cantidad;
    }
    public void setFechaSalida(LocalDateTime fechaSalida) {
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

    public String getEmpresa() {
		return colectivo.getEmpresa().getNombre();
	}

	public String getHorarioSalida() {
		return fechaSalida.toLocalTime().toString().substring(0,5);
	}

}
