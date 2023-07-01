import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Plataforma {
    private ArrayList<Empresa> empresas;
    private ArrayList<Usuario> usuarios;
    private Banco bancoAsociado;
    private ArrayList<EmpresaAdherida> empresasConvenio;

    public Plataforma(Banco banco) {
        empresas = new ArrayList<>();
        usuarios = new ArrayList<>();
        empresasConvenio = new ArrayList<>();
        bancoAsociado = banco;
    }

    public void setBanco(Banco banco) {
        bancoAsociado = banco;
    }

    public Usuario login(){
        Scanner s = new Scanner(System.in);
        System.out.println("Ingresar dni: "); int dni = s.nextInt();
        s.nextLine(); //descarta el salto de linea genereado por nextint
        System.out.println("Ingresar clave: "); String clave = s.nextLine();
        for(int i=0; i<usuarios.size();i++){
            Usuario u = usuarios.get(i);
            if ((u.getDni() == dni) && (u.getClave().equals(clave))){
                System.out.println("Has ingresado a la plataforma 9 3/4");
                return u;
            }
            System.out.println("Tu dni o contraseña son incorrectos");
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
    
   public void registro(){
        Scanner s = new Scanner(System.in);
        System.out.println("Ingresar nombre: "); String nombre = s.nextLine();
        System.out.println("Ingresar apellido: "); String apellido = s.nextLine();
        System.out.println("Ingresar dni: "); int dni = s.nextInt();
        s.nextLine(); //descarta el salto de linea genereado por nextint
        System.out.println("Ingresar Email: "); String email = s.nextLine();
        System.out.println("Ingresar clave: "); String clave = s.nextLine();
        if (buscaPasejero(dni) == null){
            Pasajero p = new Pasajero(nombre,apellido,dni,email,clave);
            System.out.println("Quiere asociar una tarjeta de credito?");
            System.out.println("1) Si" + "\n2) No");
            int aux = s.nextInt();
            if (aux == 1)
                agregarTarjeta();
            System.out.println("Te has registrado con exito!!");
            this.addUsuario(p);
        }
   }
    public vois agregarTarjeta(){
        Scanner s = new Scanner(System.in);
        System.out.println("Ingresar numero de Tarjeta:"); int num = s.nextInt();
        s.nextLine(); //descarta el salto de linea genereado por nextint
        System.out.println("Ingresar marca: "); String marc = s.nextLine();
        Tarjeta t = bancoAsociado.buscarTarjeta(num,marc);
        if (t != null){
            p.agregarTarjeta(t);
        } else
            System.out.println("No se ha encontrado la tarjeta");
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
        usuario.add(u);
    }
    public void addEmpresa(Empresa emp){
        if (!empresas.contains(emp))        
            empresas.add(emp);
    }
    public void addEmpresaConv(EmpresaAdherida emp){
        if (!empresasConvenio.contains(emp))
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
        for(int i = 0; i< usuarios.size(); i++){
            for(int j = 0; j<suscripciones.size(); j++) {
                Suscripcion suscripcion = suscripciones.get(j);
                if (usuarios.get(i).suscrito(suscripcion))
                    this.notificarPasajero(usuarios.get(i), suscripcion);
            }
        }
    }
    public void notificarPasajero(Pasajero p, Suscripcion s){
        System.out.println("Te avisamos "+p.getNombre()+ " que el viaje que va de "+s.getOrigen()+" a "+s.getDestino()+" Esta con un descuento por viaje improvisado");
    }

    private Pasaje generarPasaje() {
        System.out.println("Ingrese su dni: " +"\n");
        int dni = s.nextInt();
        s.nextLine();
        Pasajero p = this.buscaPasajero(dni);
        if (p == null) {
            System.out.print("Ingrese el nombre del pasajero: " +"\n");
            String nombre = s.nextString(); s.nextLine();
            System.out.print("Ingrese el apellido del pasajero: " +"\n");
            String apellido = s.nextString(); s.nextLine();
            p = new Pasajero(nombre,apellido,dni,"","");
        }
        Pasaje pasaje = new Pasaje(p,viaje,0,viaje.getMonto());
        return pasaje;
    }

    public void sugerirViajeImprovisado(String origen, String destino, Pasajero comprador) {
        if (comprador.cantViajes(origen,destino) >= 3) {
            System.out.println(" Notamos que realiza este viaje con frecuencia.\n¿Desea suscribirse al servicio de viaje improvisado para esta ruta?");
            System.out.print(" Ingresar 1 (si) o 0 (no): ");
            if (s.nextInt()==1)
                suscribirViaje(comprador);
        }
    }
    
    public void generarCompra(Viaje viaje, Pasajero pasajero1){
        sugerirViajeImprovisado(viaje.getOrigen(), viaje.getDestino(), pasajero1);
        Scanner s = new Scanner(System.in);
        System.out.println("Ingresar cantidad de pasajeros: ");
        int cantidad = s.nextInt();
        if (viaje.tieneDisponibilidad(cantidad)) {
            ArrayList<Pasaje> pasajes = new ArrayList<>();
            for (int i = 0; i < cantidad; i++)
                pasajes.add(generarPasaje(viaje));
            viaje.setCantAsientosDisponibles(cantidad);
            // Gestión del pago:
            // ignoramos pago con creditos
            if (pasajero1.getTarjeta() == null)
                pasajero1.addTarjeta();
            if (bancoAsociado.cobrar(pasajero1.getTarjeta(),viajes.getMonto()*cantidad)) {
                // Enviar mail de notificación
                pasajero1.addPasajes(pasajes);
            }
        }
    }
}
