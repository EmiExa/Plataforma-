import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Plataforma {
    private ArrayList<Empresa> empresas;
    private ArrayList<Usuario> usuarios;
    private Banco bancoAsociado;
    private ArrayList<EmpresaAdherida> empresasConvenio;
    private ArrayList<Suscripcion> suscripciones;

    public Plataforma(Banco banco) {
        empresas = new ArrayList<>();
        usuarios = new ArrayList<>();
        empresasConvenio = new ArrayList<>();
        bancoAsociado = banco;
        this.suscripciones= new ArrayList<>();
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
    public void darBajaEmpresa(Empresa empresa){
        //Buscamos en ambas listas Empresa o en empresa adherida en caso de que este en alguna de las 2 las damos de baja.
    }
    public Usuario buscaPasajero(int dni){  // buscar usuario cambiar nombre
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario p = usuarios.get(i);
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
        if (buscaPasajero(dni) == null){
            Pasajero p = new Pasajero(nombre,apellido,dni,email,clave);
            System.out.println("Quiere asociar una tarjeta de credito?");
            System.out.println("1) Si" + "\n2) No");
            int aux = s.nextInt();
            if (aux == 1)
                p.addTarjeta(bancoAsociado);
            System.out.println("Te has registrado con exito!!");
            this.addUsuario(p);
        }
    }

    public ArrayList<Viaje> buscarPasaje() {
        //Ingresar parametros del viaje
        Scanner s = new Scanner(System.in);
        System.out.println("Ingresar origen: ");
        String origen = s.nextLine();
        System.out.println("Ingresar destino: ");
        String destino = s.nextLine();
        System.out.println("Ingresar dia: ");
        //convert String to LocalDate
        String dateString = s.nextLine();
        System.out.println("Ingresar mes: ");
        String dateString2 = s.nextLine();
        System.out.println("Ingresar año: ");
        String dateString3 = s.nextLine();
        dateString = dateString +"/" + dateString2+ "/" + dateString3;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        System.out.println(localDate);

        ArrayList<Viaje> salida = new ArrayList<>();
        for (int i = 0; i < empresas.size(); i++) {
            ArrayList<Viaje> viajes = empresas.get(i).buscarPasajes(localDate, origen, destino);
            if (viajes != null) {
                salida.addAll(viajes);
            }
        }
        listarServicios(salida);
        return salida;
    }

    public void listarServicios(ArrayList<Viaje> salida){
        for (int i = 0; i < salida.size(); i++) {
            Viaje actual = salida.get(i);
            System.out.println(i+" )"+"Origen: "+actual.getOrigen()+","+"Destino:"+actual.getDestino()+","+ "Fecha: "+ actual.getFechaSalida()+"\n");
        }
    }


    public void addUsuario(Usuario u){
        usuarios.add(u);
    }
    public void addEmpresa(Empresa emp){
        if (!empresas.contains(emp))
            empresas.add(emp);
    }
    public void addEmpresaConv(EmpresaAdherida emp){ //aca el problema
        this.addEmpresa(emp);
        if (!empresasConvenio.contains(emp))
            empresasConvenio.add(emp);
    }
    public void agregarConvenio(Empresa emp, Double descuento, int horaMin, int asientosMin ){
        EmpresaAdherida empresa = new EmpresaAdherida(emp.getNombre(), descuento, horaMin, asientosMin);
        empresas.remove(emp);
        empresasConvenio.add(empresa);
    }
    public Suscripcion existeSuscripcion(Viaje viaje){
        for(Suscripcion sus:suscripciones){
            if ((sus.getOrigen().equals(viaje.getOrigen())) && (sus.getDestino().equals(viaje.getDestino())))
                return sus;
        }
        return null;
    }
    public void notificarViajesImprovisados(){
        ArrayList<Viaje> viajes = new ArrayList<>();
        for(EmpresaAdherida emp:empresasConvenio) {
            viajes.addAll(emp.chequearViajeimprovisados());
        }
        System.out.println(viajes.size());
        for(int i = 0; i<viajes.size(); i++){
            Suscripcion sus = existeSuscripcion(viajes.get(i));
            if(sus != null){
                sus.notificar();
            }
        }
    }

    private Pasaje generarPasaje(Viaje v) {
    	System.out.println("GENERANDO PASAJE -");
        Scanner s = new Scanner(System.in);
        
        System.out.println("Ingrese su dni: " +"\n");
        int dni = s.nextInt();
        s.nextLine();
        Usuario p = this.buscaPasajero(dni);
        if (p == null) {
            System.out.print("Ingrese el nombre del pasajero: " +"\n");
            String nombre = s.nextLine(); s.nextLine();
            System.out.print("Ingrese el apellido del pasajero: " +"\n");
            String apellido = s.nextLine(); s.nextLine();
            p = new Pasajero(nombre,apellido,dni,"","");
        }
        Pasaje pasaje = new Pasaje((Pasajero)p,v,0,v.getMonto());
        return pasaje;
    }

    public void sugerirViajeImprovisado(Viaje viaje, Pasajero comprador) {
        Scanner s = new Scanner(System.in);
        Suscripcion sus = existeSuscripcion(viaje);
        if ((comprador.cantViajes(viaje.getOrigen(),viaje.getDestino()) >= 3) && ((sus==null) || (sus!=null && !sus.existePasajero(comprador)))) {
            System.out.println(" Notamos que realiza este viaje con frecuencia.\n¿Desea suscribirse al servicio de viaje improvisado para esta ruta?");
            System.out.print(" Ingresar 1 (si) o 0 (no): ");
            if (s.nextInt()==1)
                suscribirseViaje(comprador);
        }
    }

    public void generarCompra(Viaje viaje, Pasajero comprador){
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
            if (comprador.getTarjeta() == null)
                comprador.addTarjeta(bancoAsociado);
            if (bancoAsociado.cobrar(comprador.getTarjeta(),viaje.getMonto()*cantidad)) {
                // Enviar mail de notificiacion
                comprador.addPasajes(pasajes);
                System.out.println("LA COMPRA FUE EXITOSA");
                sugerirViajeImprovisado(viaje, comprador);
            }
            else System.out.println("LA COMPRA NO FUE EXITOSA");
        } 
    }
    public void suscribirseViaje(Pasajero p, String origen, String destino){
        int i = 0;
        boolean encontrado = false;
        while ((i<suscripciones.size())&&(encontrado == false)){
            Suscripcion suscActual = suscripciones.get(i);
            if ((origen.equals(suscActual.getOrigen()))&&(destino.equals(suscActual.getDestino()))){
                //Si existe una suscripcion de viaje con ese origen y destino se agrega el pasajero a esta.
                suscActual.addPasajero(p);
                encontrado = true;
                System.out.println("La Suscripcion se realizo con exito");
            }
            i++;
        }
        if ((encontrado == false)&&(origen != destino)){
            Suscripcion sus = new Suscripcion(origen,destino);
            sus.addPasajero(p);
            suscripciones.add(sus);
            encontrado=true;
            System.out.println("La Suscripcion se realizo con exito");
        }
        if(encontrado == false)
            System.out.println("No se realizo la suscripcion con exito");

    }
    
    public void suscribirseViaje(Pasajero p) {
        Scanner s = new Scanner(System.in);
        System.out.println("Ingresar el origen al que se quiere suscribir:");
        String origen = s.nextLine();
        System.out.println("Ingresar el destino al que se quiere suscribir:");
        String destino = s.nextLine();
        suscribirseViaje(p,origen,destino);
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
}
