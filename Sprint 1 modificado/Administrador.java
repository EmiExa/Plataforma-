import java.util.ArrayList;

public class Administrador extends Usuario{
    private static int colesMin = 5;
    private static String clave = "12345678Ab";

    public Administrador(String nombre, String apellido, int dni) {
        super(nombre,apellido,dni);
    }
  
    public String getClave() {
        return this.clave();
    }

    public void addConvenio(Empresa convenio, Plataforma plataforma){
        int cantColes = convenio.getCantColectivos();
        if (cantColes > colesMin)
            plataforma.addEmpresa(convenio);
    }
    
    public void addConvenioAdherido(Empresa convenio, Plataforma plataforma){
        int cantColes = convenio.getCantColectivos();
        if (cantColes > colesMin)
            plataforma.addEmpresaConv(convenio);
    }

    public void verEstadisticas(){
        //Queda para implementar
    }
    

}
