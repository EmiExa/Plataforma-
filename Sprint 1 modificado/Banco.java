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
    public boolean cobrar(Tarjeta tar, double monto){
        for (int i = 0; i < tarjetas.size(); i++) {
            Tarjeta aux = tarjetas.get(i);
            if (aux.getNum() == tar.getNum()){
                // if cobro es exitoso
                return true;
            }
        }
        return false;
    }
    public Tarjeta  buscarTarjeta(int num, String marca){
        for(int i=0;i<tarjetas.size();i++){
            Tarjeta t = tarjetas.get(i);
            if((t.getNum() == num) && (t.getMarca().equals( marca) ))
                return t;
        }
        return null;
    }
	public boolean validar(int nroTarjeta) {
		for(Tarjeta t: tarjetas) {
			if (t.getNum()==nroTarjeta)
				return true;
		}
		return false;
			
	}
}
