import java.util.ArrayList;

public abstract class Empresa {
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
}
