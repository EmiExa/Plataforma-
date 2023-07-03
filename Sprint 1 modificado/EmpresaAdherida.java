import java.util.ArrayList;

public class EmpresaAdherida extends Empresa{
    private double descuento;
    private int horasRestantes;
    private int lugaresDisponibles;
    public EmpresaAdherida(String nombre, double desc, int horas, int lugares){
        super(nombre);
        descuento = desc;
        horasRestantes= horas;
        lugaresDisponibles = lugares;
    }
   public ArrayList<Viaje> chequearViajeimprovisados(){
        ArrayList<Viaje> viajesimprovisados = new ArrayList<>();
        for(int i= 0; i < viajesFuturos.size(); i++) {
            Viaje viaje = viajesFuturos.get(i);
            if (viaje.esImprovisado(horasRestantes,lugaresDisponibles)) {
                viaje.setMonto(viaje.getMonto() - viaje.getMonto() * descuento);
                viajesimprovisados.add(viaje);
            }
        }
        return viajesimprovisados;
    }
    public int getHorasRestantes() {
        return horasRestantes;
    }
    public double getDescuento() {
        return descuento;
    }
}
