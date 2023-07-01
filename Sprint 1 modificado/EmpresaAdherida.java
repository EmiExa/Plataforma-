import java.util.ArrayList;

public class EmpresaAdherida extends Empresa{
    private Double descuento;
    private int horasRestantes;
    private int lugaresDisponibles;
    public EmpresaAdherida(String nombre, Double desc, int horas, int lugares){
        super(nombre);
        descuento = desc;
        horasRestantes= horas;
        lugaresDisponibles = lugares;
    }
    public ArrayList<Suscripcion> chequearViajeimprovisados(){
        ArrayList<Suscripcion> viajesimprovisados = new ArrayList<>();
        for(int i= 0; i < viajesFuturos.size(); i++) {
            Viaje viaje = viajesFuturos.get(i);
            if (viaje.esImprovisado(horasRestantes,lugaresDisponibles)) {
                viaje.setMonto(viaje.getMonto() - (viaje.getMonto()*descuento));
                Suscripcion s = new Suscripcion(viaje.getOrigen(), viaje.getDestino());
                viajesimprovisados.add(s);
            }
        }
        return viajesimprovisados;
    }
}
