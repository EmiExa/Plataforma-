import java.util.ArrayList;

public class Administrador extends Usuario{
    //private static ArrayList<Usuario> usuarios;
    //private static ArrayList<Empresa> empresas;
    //private static ArrayList<EmpresaAdherida> empresaAdheridas;
    // las de arriba no van acá, existen en plataforma
    private static String clave = "12345678Ab";

    public Administrador(String nombre, String apellido, int dni) {
        super(nombre,apellido,dni);
        this.usuarios = new ArrayList<>();
        this.empresas = new ArrayList<>();
        this.empresaAdheridas = new ArrayList<>();
    }

    public void getUsuarios(){
        ArrayList<Usuario> usuario = new ArrayList<>();
        usuario.addAll(usuarios);
        return usuario;
    }
  
        public String getClave() {
        return this.clave();
    }
  

    public void login(String nombre, int dni, String clave){
        //if((super.getNombre().equals(nombre)) && (this.clave.equals(clave)) && (super.getDni() ==dni)) {
            //loguear
        }
        //else{}
        //nologuear




}
