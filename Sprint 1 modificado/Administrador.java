import java.util.ArrayList;
import java.util.Hashtable;

public class Administrador extends Usuario{
    private static int colesMin = -1;
    private static String clave = "12345678Ab";

    public Administrador(String nombre, String apellido, int dni) {
        super(nombre,apellido,dni);
    }

    public String getClave() {
        return this.clave;
    }
    public void addConvenio(Empresa convenio, Plataforma plataforma){
        int cantColes = convenio.getCantColectivos();
        if (cantColes > colesMin)
            plataforma.addEmpresa(convenio);
    }

    public void addConvenioAdherido(EmpresaAdherida convenio, Plataforma plataforma){ //tendria que ser una empresa adherida es lo mismo pero bueno si no castear
        int cantColes = convenio.getCantColectivos();
        if (cantColes > colesMin)
            plataforma.addEmpresaConv(convenio);
    }
    public void eliminarEmpresa(Empresa convenio,Plataforma plataforma){
        plataforma.darBajaEmpresa(convenio);
    }
    public void verEstadisticas(){
        //Queda para implementar
    }
    public Hashtable generarResumen(){
        Hashtable resumen = new Hashtable<>();
        return resumen;
        //Queda para implementar
        //Es un resumen que muestra las plataformas adheridas la cantidad de viajes vendidos
        //ganancias de la plataforma
    }

}
