import java.time.LocalDate;
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
    public Pasajero buscaPasajero(int dni){
        for (int i = 0; i < pasajeros.size(); i++) {
            Pasajero p = pasajeros.get(i);
            if (p.getDni() == dni)
                return p;
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

    public ArrayList<Viaje> buscarPasaje() {
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
            ArrayList<Viaje> viajes = empresas.get(i).buscarPasajes(localDate,origen,destino);
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


    public void addUsuario(Usuario u){
        pasajeros.add(u);
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

    private Pasaje generarPasaje() {
        System.out.println("Ingrese su dni: " +"\n");
        int dni = s.nextInt();
        Pasajero p = this.buscaPasajero(dni);
        if (p != null){
            Pasaje pasaje = new Pasaje(p,viaje,0,viaje.getMonto());
            registro();
        } else {
            // solicitar nombre y apellido del pasajero no registrado
            p = new Pasajero(dni,"nombre","apellido")
        }
    }
    
    public void generarCompra(Viaje viaje, Pasajero pasajero1){
        Scanner s = new Scanner(System.in);
        System.out.println("Ingresar origen: ");
        int cantidad = s.nextInt();
        if (viaje.tieneDisponibilidad(cantidad)) {
            ArrayList<Pasaje> pasajes = new ArrayList<>();
            for (int i = 0; i < cantidad; i++)
                pasajes.add(generarPasaje(viaje));
            viaje.setCantAsientosDisponibles(cantidad);
            pasajero1.addPasajes(pasajes);
        }
        if(pasajero1.getTarjeta() != null) {

            bancoAsociado.cobrar(pasajero1.getTarjeta(),viajes.getMonto()*cantidad);
            //Enviar mail de notificiacion
        }
        else {
            //Solicitar datos de tarjeta
        }
        // ignoramos pago con creditos
    }
}
