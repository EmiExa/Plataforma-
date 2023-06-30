import java.util.ArrayList;

public class Plataforma {
    ArrayList<Empresa> empresas;
    ArrayList<Administrador> admins;
    ArrayList<Pasajero> pasajeros;
    ArrayList<EmpresaAdherida> empresasConvenio;
    public Plataforma(){
        empresas = new ArrayList<>();
        admins = new ArrayList<>();
        pasajeros = new ArrayList<>();
        empresasConvenio = new ArrayList<>();
    }
    public void addPasajero(Pasajero p){
        pasajeros.add(p);
    }
    public void addAdmin(Administrador a){
        admins.add(a);
    }
    public void addEmpresa(Empresa emp){
        empresas.add(emp);
    }
    public void addEmpresaConv(EmpresaAdherida emp){
        empresasConvenio.add(emp);
    }
    public void agregarConvenio(Empresa emp, Double descuento, int horaMin, int asientosMin ){
        EmpresaAdherida empresa = new EmpresaAdherida(emp.getNombre(), descuento, horaMin, asientosMin);
        empresas.remove(emp);
        empresasConvenio.add(empresa);
    }
    public void notificarViajesImprovisados(){
        ArrayList<Suscripcion> suscripciones = new ArrayList<>();
        for(int i = 0; i<empresasConvenio.size(); i++){
            ArrayList<Suscripcion> s = new ArrayList<>();
            s = empresasConvenio.get(i).chequearViajeimprovisados();
            for(int j = 0; j< s.size(); j++){
                if(!suscripciones.contains(s.get(j)))
                    suscripciones.add(s.get(j));
            }
        }
        for(int i = 0; i< pasajeros.size(); i++){
            for(int j = 0; j<suscripciones.size(); j++) {
                Suscripcion suscripcion = suscripciones.get(j);
                if (pasajeros.get(i).suscrito(suscripcion))
                    this.notificarPasajero(pasajeros.get(i), suscripcion);
            }
        }
    }
    public void notificarPasajero(Pasajero p, Suscripcion s){
        System.out.println("Te avisamos "+p.getNombre()+ " que el viaje que va de "+s.getOrigen()+" a "+s.getDestino()+" Esta con un descuento por viaje improvisado");
    }
}
