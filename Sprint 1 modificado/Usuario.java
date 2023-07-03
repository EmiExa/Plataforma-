public abstract class Usuario {
    
    private String nombre;
    private String apellido;
    private int dni;
    
    public Usuario(String nom, String ap, int dni){
        nombre = nom;
        apellido = ap;
        this.dni = dni;
    }
    public String getNombre() {
        return nombre;
    }
    public String getApellido(){
        return apellido;
    }
    public int getDni(){
        return dni;
    }
    public abstract String getClave();
}
