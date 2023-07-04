import java.time.LocalDate;
import java.util.ArrayList;

public class EmpresaAdherida extends Empresa{
    private double descuento;
    private int horasRestantes;
    private int lugaresDisponibles;
    private ArrayList<Viaje> viajesImprovisados;
    public EmpresaAdherida(String nombre, double desc, int horas, int lugares){
        super(nombre);
        descuento = desc;
        horasRestantes= horas;
        lugaresDisponibles = lugares;
        viajesImprovisados = new ArrayList<>();
    }
   public ArrayList<Viaje> chequearViajeimprovisados(){
        ArrayList<Viaje> viajes = new ArrayList<>();
        for(int i= 0; i < viajesFuturos.size(); i++) {
            Viaje viaje = viajesFuturos.get(i);
            if (viaje.esImprovisado(horasRestantes,lugaresDisponibles)) {
                viaje.setMonto(viaje.getMonto() - viaje.getMonto() * descuento);
                viajes.add(viaje);
                viajesImprovisados.add(viaje);
                viajesFuturos.remove(viaje);
            }
        }
        return viajes;
    }
    public ArrayList<Viaje>  buscarPasajes(LocalDate dia, String origen, String destino){
        ArrayList<Viaje> salida = new ArrayList<>();
        salida = super.buscarPasajes(dia,origen,destino);
        for(int i =0; i< viajesImprovisados.size();i++){
            Viaje viajeactual= viajesImprovisados.get(i);
            if ((origen.equals(viajeactual.getOrigen())) && (destino.equals(viajeactual.getDestino())) && (dia.compareTo(viajeactual.getFechaSalida())<=0) && (viajeactual.getCantAsientosDisponibles() > 0)) {
                salida.add(viajeactual);
            }
        }
            return salida;
    }
    public int getHorasRestantes() {
        return horasRestantes;
    }
    public double getDescuento() {
        return descuento;
    }
    public ArrayList<Viaje> getViajes(){
        ArrayList<Viaje> viajes = new ArrayList<>();
        viajes = super.getViajes();
        viajes.addAll(viajesImprovisados);
        return viajes;
    }
}
