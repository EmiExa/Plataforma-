public class Suscripcion {
    private String origen;
    private String destino;
    private Arraylist<Pasajero> pasajeros;
    public Suscripcion(String origen, String destino){
        this.origen = origen;
        this.destino = destino;
        this.pasajeros = new ArrayList<>();
    }
    public void addPasajero(Pasajero p){
            pasajeros.add(p);
    }
    public void eliminarPasajero(Pasajero p){
        pasajero.remove(p);
    }
    public boolean equals(Object s){
        if (s instanceof Suscripcion) {
            Suscripcion sus = (Suscripcion) s;
            return ((origen.equals(sus.getOrigen())) && (destino.equals(sus.getDestino())));
        }
        else
            return false;
    }
    public String getOrigen(){
        return origen;
    }
    public String getDestino(){
        return destino;
    }
}
