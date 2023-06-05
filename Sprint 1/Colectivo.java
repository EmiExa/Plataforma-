public class Colectivo {
    private int id;
    private int asientos;
    private Empresa empresa;
    public Colectivo(int id, int asientos, Empresa empresa){
        this.id = id;
        this.asientos = asientos;
        this.empresa = empresa;
    }

    public int getAsientos() {
        return asientos;
    }
}
