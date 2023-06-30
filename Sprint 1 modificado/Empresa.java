import java.util.ArrayList;

public class Empresa {
    private String nombre;
    private ArrayList<Colectivo> colectivos;
    protected ArrayList<Viaje> viajesFuturos;
    Empresa(String nombre){
        this.nombre = nombre;
        viajesFuturos = new ArrayList<Viaje>();
        colectivos = new ArrayList<Colectivo>();
    }
    public void addViaje(Viaje viaje){
        viajesFuturos.add(viaje);
    }
    public ArrayList<Viaje> getViajes(){
        return viajesFuturos;
    }

    public String getNombre() {
        return nombre;
    }
    public ArrayList<Viaje>  buscarPasajes(LocalDate dia, String origen, String destino){
        ArrayList<Viaje> salida = new ArrayList<>();
        for(int i =0; i< viajesFuturos.size();i++){
            Viaje viajeactual= viajesFuturos.get(i);
            if ((viajeactual.getOrigen()== origen) && (viajeactual.getDestino() == destino) && (dia.isEqual(viajeactual.getFechaSalida().toLocalDate())) {
                salida.add(viajeactual);
            }
        }
        if (salida.size()>0){
            return salida;
        }
        else {
            return null;
        }
    }
}
