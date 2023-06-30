import java.util.ArrayList;

public class Banco {
    private ArrayList<Tarjeta> tarjetas;
    private String nombre;
    public Banco(String nombre){
        this.nombre = nombre;
        tarjetas = new ArrayList<>();
    }
    public void addTarjeta(Tarjeta tar){
        tarjetas.add(tar);
    }
    public void verificarDatos(){
        //verifica los datos de la tarjeta
    }
    public void cobrar(Tarjeta tar, int monto){
        //cobrar
    }
}
