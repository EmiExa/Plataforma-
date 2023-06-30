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
    public Tarjeta  buscarTarjeta(int num, String marca){
        for(int i=0;i<tarjetas.size();i++){
            Tarjeta t = tarjetas.get(i);
            if((t.getNum() == num) && (t.getMarca().equals( marca) ))
                return t;
        }
        return null;
    }
}
