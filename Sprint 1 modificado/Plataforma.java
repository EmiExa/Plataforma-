import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Plataforma {
    private ArrayList<Empresa> empresas;
    private ArrayList<Administrador> admins;
    private ArrayList<Pasajero> pasajeros;
    private Banco bancoAsociado;
    private ArrayList<EmpresaAdherida> empresasConvenio;
    private ArrayList<Suscripcion> suscripciones; 

    public Plataforma(Banco banco) {
        empresas = new ArrayList<>();
        admins = new ArrayList<>();
        pasajeros = new ArrayList<>();
        empresasConvenio = new ArrayList<>();
        bancoAsociado = banco;
    }

    public void setBanco(Banco banco) {
        bancoAsociado = banco;
    }

    public Pasajero login(int dni, String clave) {
        for (int i = 0; i < pasajeros.size(); i++) {
            Pasajero p = pasajeros.get(i);
            if ((p.getDni() == dni) && (p.getClave() == clave)) {
                return p;
            }
        }
        return null;
    }

    public void registro() {
        Scanner s = new Scanner(System.in);
        System.out.println("Ingresar nombre: ");
        String nombre = s.nextLine();
        System.out.println("Ingresar apellido: ");
        String apellido = s.nextLine();
        System.out.println("Ingresar dni: ");
        int dni = s.nextInt();
        System.out.println(" ");
        System.out.println("Ingresar Email: ");
        String email = s.nextLine();
        System.out.println("Ingresar clave: ");
        String clave = s.nextLine();
        Pasajero p = new Pasajero(nombre, apellido, dni, email, clave);
        System.out.println("Quiere asociar una tarjeta de credito?");
        System.out.println("1) Si" + "\n2) No");
        int aux = s.nextInt();
        while (aux == 1) {
            System.out.println("Ingresar numero de Tarjeta:");
            int num = s.nextInt();
            System.out.println(" ");
            System.out.println("Ingresar marca: ");
            String marc = s.nextLine();
            Tarjeta t = bancoAsociado.buscarTarjeta(num, marc);
            if (t != null) {
                p.agregarTarjeta(t);
                aux = 10;
            }
        }
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

    public void suscribirseViaje(Pasajero p){
        Scanner s = new Scanner(System.in);
        System.out.println("Ingresar el origen al que se quiere suscribir:");
        String origen = s.nextLine();
        System.out.println("Ingresar el destino al que se quiere suscribir:");
        String destino = s.nextLine();   
        int i = 0;
        boolean encontrado = false;
        while ((i<suscripciones.size())&&(encontrado = false)){
            Suscripcion suscActual = suscripciones.get(i);
            if ((origen.equals(suscActual.getOrigen()))&&(destino.equals(suscActual.getDestino()))){
                //Si existe una suscripcion de viaje con ese origen y destino se agrega el pasajero a esta.
                suscActual.addPasajero(p);
                encontrado = true;
            }
            i++;
        }
        if ((encontrado = false)&&(origen != destino)){
            Suscripcion sus = new Suscripcion(origen,destino);
            sus.addPasajero(p);
            suscripciones.add(sus);
        }
        
    }
    
    public void darseBaja(Pasajero p){
        Scanner s = new Scanner(System.in);
        System.out.println("Ingresar el origen de la suscripcion para darse de baja:");
        String origen = s.nextLine();
        System.out.println("Ingresar el destino de la suscripcion para darse de baja:");
        String destino = s.nextLine();  
        int i = 0;
        boolean encontrado = false;
        while ((i<suscripciones.size())&&(encontrado = false)){
            Suscripcion suscActual = suscripciones.get(i);
            if ((origen.equals(suscActual.getOrigen()))&&(destino.equals(suscActual.getDestino()))){
                suscActual.eliminarPasajero(p);
                encontrado = true;
            }
            i++;
        }
    }
    
    public void notificarPasajero(Pasajero p, Suscripcion s){
        System.out.println("Te avisamos "+p.getNombre()+ " que el viaje que va de "+s.getOrigen()+" a "+s.getDestino()+" Esta con un descuento por viaje improvisado");
    }
    public void generarCompra(Viaje viajes, Pasajero pasajero1){
        Scanner s = new Scanner(System.in);
        System.out.println("Ingresar origen: ");
        int cantidad = s.nextInt();
        // solicita cantidad de pasajeros
        // por cada pasajero pide asiento y datos: dni - funcion aparte
        // notificar pasajeros
        // control de cantidad de pasajeros
        // control de  pago: preguntar si existe tarjeta cargada en el sistema
        // ignoramos pago con creditos
        // decrementamos el cantAsientosDisponibles del viaje
        // 
    }
}
