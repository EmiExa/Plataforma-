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

    private Usuario getUsuario(int dni) {
    	for (Usuario u: usuarios)
    		if (u.getDni()==dni)
    			return u;
    	return null;
    }
    
    public Usuario login(){
        Scanner s = new Scanner(System.in);
        System.out.println("Ingresar DNI: "); int dni = s.nextInt();
        s.nextLine(); //descarta el salto de linea genereado por nextint
        System.out.println("Ingresar clave: "); String clave = s.nextLine();
        Usuario u = getUsuario(dni);
        if (u!=null) {
            if ((u.getClave().equals(clave)))
                System.out.println("Has ingresado correctamente a Plataforma 9 3/4");
            else {
            	System.out.println("Tu DNI o clave son incorrectos.");
            	u = null;
            }
        } else System.out.println("Tu DNI o clave son incorrectos.");
        return u;	
    }
    
    public void darBajaEmpresa(Empresa empresa){
        //Buscamos en ambas listas Empresa o en empresa adherida en caso de que este en alguna de las 2 las damos de baja.
    }

    public String crearClave(){
        Scanner s = new Scanner(System.in);
        String clave = "a";
        boolean valida = false;
        while(!valida) {
            System.out.println("Ingrese su clave de al menos 8 digitos, teniendo al menos un caracter en minuscula, uno en mayusucula y al menos un numero");
            clave = s.nextLine();
            if (clave.length() > 7) {
                if (!clave.equals(clave.toLowerCase())) {
                    if (!clave.equals(clave.toUpperCase())) {
                        int i = 0;
                        while ((i < clave.length()) && (Character.isDigit(clave.charAt(i)))) {
                            i++;
                        }
                        if (i < clave.length())
                            valida = true;
                    }
                }
            }
        }
            return clave;
    }
    public void registro(){
        Scanner s = new Scanner(System.in);
        System.out.println("Ingresar nombre: "); String nombre = s.nextLine();
        System.out.println("Ingresar apellido: "); String apellido = s.nextLine();
        System.out.println("Ingresar dni: "); int dni = s.nextInt();
        s.nextLine(); //descarta el salto de linea genereado por nextint
        System.out.println("Ingresar Email: "); String email = s.nextLine();
        String clave = this.crearClave();
        if (getUsuario(dni) == null){
            Pasajero p = new Pasajero(nombre,apellido,dni,email,clave);
            System.out.println("Quiere asociar una tarjeta de credito?");
            System.out.println("1) Si" + "\n2) No");
            int aux = s.nextInt();
            if (aux == 1)
                p.addTarjeta(bancoAsociado);
            System.out.println("Te has registrado con exito!!");
            this.addUsuario(p);
        }  else System.out.println("ERROR: ya existe un usuario registrado con ese DNI.");
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
        System.out.println("Viajes disponibles de "+origen.toUpperCase()+" a "+destino.toUpperCase()+":");
        listarServicios(salida);
        return salida;
    }
    
    public void listarServicios(ArrayList<Viaje> salida){
        for (int i = 0; i < salida.size(); i++) {
            Viaje actual = salida.get(i);
            System.out.println(i+". Empresa "+actual.getEmpresa()+". Fecha de salida: "+ actual.getFechaSalida()+" a las "+actual.getHorarioSalida()+". "+actual.getCantAsientosDisponibles()+" lugares disponibles!\n");
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
                sus.notificar(viajes.get(i));
            }
        }
    }

    private Pasaje generarPasaje(Viaje v,int nro) {
    	System.out.println("GENERAR PASAJE #"+nro+":");
        Scanner s = new Scanner(System.in);
        System.out.print("  Ingrese DNI del pasajero: ");
        int dni = s.nextInt();
        s.nextLine();
        Usuario p = this.getUsuario(dni);
        if (p == null) {
            System.out.print("  Ingrese el nombre del pasajero: ");
            String nombre = s.nextLine();
            System.out.print("  Ingrese el apellido del pasajero: ");
            String apellido = s.nextLine();
            p = new Pasajero(nombre,apellido,dni,"","");
        } else System.out.println("  El DNI corresponde a un usuario registrado (los datos se autocompletan)");
        Pasaje pasaje = new Pasaje((Pasajero) p,v,0,v.getMonto());
        System.out.println("PASAJE #"+nro+" GENERADO EXITOSAMENTE."+"\n");
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
                pasajes.add(generarPasaje(viaje,i+1));
            viaje.decrementarAsientosDisponibles(cantidad);
            // Gestión del pago:
            // ignoramos pago con creditos
            System.out.println("MEDIO DE PAGO: Tarjeta de credito.");
            boolean tarjetaValida = false;
            if (comprador.getTarjeta() == null) {
            	System.out.println("Usted NO posee una tarjeta de credito asociada.");
                if (comprador.addTarjeta(bancoAsociado))
                	tarjetaValida = true;
            } else {
            	System.out.println("Usted posee una tarjeta de credito asociada.");
            	tarjetaValida = true;
            }
            if (!tarjetaValida)
            	System.out.println("ERROR: la tarjeta ingresada no fue validada por el banco.\n");
            else if (tarjetaValida && bancoAsociado.cobrar(comprador.getTarjeta(),viaje.getMonto()*cantidad)) {
                // Enviar mail de notificiacion
                comprador.addPasajes(pasajes);
                System.out.println("El banco ha autorizado el pago: LA COMPRA FUE EXITOSA.\n");
                sugerirViajeImprovisado(viaje, comprador);
            } else System.out.println("El banco NO ha autorizado el pago: LA COMPRA NO FUE EXITOSA.\n");
        } else System.out.println("ERROR: el viaje seleccionado no cuenta con dicha disponibilidad.\n");
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
        while ((i<suscripciones.size())&&(encontrado == false)){
            Suscripcion suscActual = suscripciones.get(i);
            if ((origen.equals(suscActual.getOrigen()))&&(destino.equals(suscActual.getDestino()))){
                suscActual.eliminarPasajero(p);
                encontrado = true;
                System.out.println("Se dio de baja correctamente");
            }
            i++;
        }
        if(encontrado == false)
            System.out.println("Usted no se encuentra suscripto a este viaje");

    }
}
