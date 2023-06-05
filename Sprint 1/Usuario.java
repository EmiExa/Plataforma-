public abstract class Usuario {
    private String nombre;
    private String apellido;
    private int dni;
    public Usuario(String nom, String ap, int dni){
        nombre = nom;
        apellido = ap;
        this.dni = dni;
    }
    public abstract void login(String nombre, int dni, String clave);

    public String getNombre() {
        return nombre;
    }
}
