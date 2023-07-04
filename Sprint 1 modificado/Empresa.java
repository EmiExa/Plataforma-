import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Empresa {
    private String nombre;
    private ArrayList<Colectivo> colectivos;
    protected ArrayList<Viaje> viajesFuturos;
    public Empresa(String nombre){
        this.nombre = nombre;
        viajesFuturos = new ArrayList<Viaje>();
        colectivos = new ArrayList<Colectivo>();
    }
    public void addViaje(Viaje viaje){
        viajesFuturos.add(viaje);
    }
    public ArrayList<Viaje> getViajes(){  //fix
        ArrayList<Viaje> viajes = new ArrayList<>();
        viajes.addAll(viajesFuturos);
        return viajes;
    }

    public void addColectivo(Colectivo cole){
        colectivos.add(cole);
    }
    public int getCantColectivos(){
        return colectivos.size();
    }
    public ArrayList<Viaje>  buscarPasajes(LocalDate dia, String origen, String destino){

        ArrayList<Viaje> salida = new ArrayList<>();
        for(int i =0; i< viajesFuturos.size();i++){
            Viaje viajeactual= viajesFuturos.get(i);
            if ((origen.equals(viajeactual.getOrigen())) && (destino.equals(viajeactual.getDestino())) && (dia.compareTo(viajeactual.getFechaSalida())<=0) && (viajeactual.getCantAsientosDisponibles() > 0)) {
                salida.add(viajeactual);
            }
        }
            return salida;
    }
    public String getNombre() {
        return nombre;
    }
    public double getDescuento(){
        return 0;
    }
}
