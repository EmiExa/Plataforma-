import java.util.ArrayList;
import java.util.Scanner;

public class Plataforma {
    private ArrayList<Empresa> empresas;
    private ArrayList<Administrador> admins;
    private ArrayList<Pasajero> pasajeros;
    private Banco bancoAsociado;
    private ArrayList<EmpresaAdherida> empresasConvenio;
    public Plataforma(Banco banco){
        empresas = new ArrayList<>();
        admins = new ArrayList<>();
        pasajeros = new ArrayList<>();
        empresasConvenio = new ArrayList<>();
        bancoAsociado = banco;
    }
    public void setBanco(Banco banco){
        bancoAsociado = banco;
    }
    public Pasajero login(){
        Scanner s = new Scanner(System.in);
        System.out.println("Ingresar dni: "); int dni = s.nextInt();
        s.nextLine(); //descarta el salto de linea genereado por nextint
        System.out.println("Ingresar clave: "); String clave = s.nextLine();
        for(int i=0; i<pasajeros.size();i++){
            Pasajero p = pasajeros.get(i);
            if ((p.getDni() == dni) && (p.getClave().equals(clave))){
                System.out.println("Has ingresado a la plataforma 9 3/4");
                return p;
            }
            System.out.println("Tu dni o contraseña son incorrectos");
        }
        return null;
    }
    public void registro(){
        Scanner s = new Scanner(System.in);
        System.out.println("Ingresar nombre: "); String nombre = s.nextLine();
        System.out.println("Ingresar apellido: "); String apellido = s.nextLine();
        System.out.println("Ingresar dni: "); int dni = s.nextInt();
        s.nextLine(); //descarta el salto de linea genereado por nextint
        System.out.println("Ingresar Email: "); String email = s.nextLine();
        System.out.println("Ingresar clave: "); String clave = s.nextLine();
        Pasajero p = new Pasajero(nombre,apellido,dni,email,clave);
        System.out.println("Quiere asociar una tarjeta de credito?");
        System.out.println("1) Si" + "\n2) No");
        int aux = s.nextInt();
        while(aux == 1){
            System.out.println("Ingresar numero de Tarjeta:"); int num = s.nextInt();
            s.nextLine(); //descarta el salto de linea genereado por nextint
            System.out.println("Ingresar marca: "); String marc = s.nextLine();
            Tarjeta t = bancoAsociado.buscarTarjeta(num,marc);

            if (t != null){
                p.agregarTarjeta(t);
                aux = 10;
            } else
                System.out.println("No se ha encontrado la tarjeta");
        }
        System.out.println("Te has registrado con exito!!");
        this.addPasajero(p);
    }
   public ArrayList<Viaje> buscarPasaje(LocalDate dia) {
        Scanner s = new Scanner(System.in);
        System.out.println("Ingresar origen: ");
        String origen = s.nextLine();
        System.out.println("Ingresar destino: ");
        String destino = s.nextLine();
        System.out.println("Ingresar Fecha: ");
        //convert String to LocalDate
        String dateString= s.nextLine();
        DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("d/MM/yyyy");
        
        LocalDate localDate = LocalDate.parse(dateString, formatter);

        ArrayList<Viaje> salida= new ArrayList<>();
        for (int i = 0; i < empresas.size(); i++) {
            ArrayList<Viaje> viajes = empresas.get(i).buscarPasajes(dia,origen,destino);
            if (viajes != null){
                salida.addAll(viajes);
            }
        }
        listarServicios(salida);
        return salida;
    }
    public void listarServicios(ArrayList<Viaje> salida){
        for (int i = 0; i < salida.size(); i++) {
            Viaje actual = salida.get(i);
            System.out.println(i+")"+"Origen: "+actual.getOrigen()+","+"Destino:"+actual.getDestino()+","+ "Fecha: "+ actual.getFechaSalida()+"\n");
        }
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
