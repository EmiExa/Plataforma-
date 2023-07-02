import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public void cargarEmpresasEstatica(Plataforma p){
        //Evita el menu de administrador para cargar las empresas mas rapido y poder probarlo.
        
        //CREACION EMPRESAS
        Empresa e1 = new Empresa("El gorrion");
        Empresa e2 = new Empresa("Langueyu");
        Empresa e3 = new Empresa("Condor Estrella");
        Empresa e4 = new Empresa("Plusmar");
        Empresa e5 = new Empresa("Flechabus");
        Empresa e6 = new Empresa("El Rapido");
        
        //CREACION COLECTIVOS
        Colectivo c1 = new Colectivo(1,30,e1);
        Colectivo c2 = new Colectivo(1,20,e4);
            
        //CREACION VIAJES
        Viaje v1 = new Viaje("Tandil","MarDelPlata",30,c1,localDate.now(),1500);
        Viaje v2 = new Viaje("Tandil","Azul",20,c2,localDate.now(),2000);
        Viaje v3 = new Viaje("Tandil","Ayacucho",30,c1,localDate.now(),5000);
        Viaje v4 = new Viaje("Tandil","Olavarria",20,c2,localDate.now(),1200);
        Viaje v5 = new Viaje("Olavarria","Tandil",20,c1,localDate.now(),6000);

        //AGREGO COLECTIVOS Y VIAJES A LAS EMPRESAS
        e1.addColectivo(c1);
        e1.addViaje(v1);
        e1.addViaje(v3);
        
        e4.addColectivo(c2);
        e4.addViaje(v2);
        e4.addViaje(v4);
        e4.addViaje(v5);
            
        //SE AGREGAN LAS EMPRESAS A LA PLATAFORMA
        admin.addConvenio(e1, p);
        admin.addConvenio(e2, p);
        admin.addConvenio(e3, p);
        admin.addConvenioAdherido(e4, p);
        admin.addConvenioAdherido(e5, p);
        admin.addConvenioAdherido(e6, p);
    }
    
    public void mostrarMenuPlataforma (Pasajero p, Plataforma nueve){
        int opcion = 0;
        while(opcion != 6){
            System.out.println("MENU: ");
            System.out.println("1.Buscar pasajes" + "\n 2.Rastreo" + "\n 3.Suscribirse a viaje improvisado" +"\n 4.Cancelar compra" + "\n 5.Baja de viaje improvisado" +"\n 6.Salir");
            Scanner s = new Scanner(System.in);
            System.out.println("Ingrese el numero de opcion que desea realizar");
            opcion = s.nextInt();
            s.nextLine();
            switch(opcion){
                case 1:
                    //Buscar pasajes
                    ArrayList<Viaje> resultadoBusqueda = nueve.buscarPasaje();
                    //Elegir asiento
                    System.out.println("Desea comprar un pasaje? 1.SI 2.NO");
                    int realizaCompra = s.nextInt();
                    if (realizaCompra == 1){
                        System.out.println("Seleccione el numero de viaje que desea comprar:");
                        int op = s.nextInt();
                        if ((op > 0) && (op < resultadoBusqueda.size())){
                            nueve.generarCompra(resultadoBusqueda.get(op), p);
                        }
                    }
                    break;
                case 2:
                    //Suscribirse a rastreo
                    break;
                case 3:
                    //Suscribirse a viaje improvisado
                    nueve.suscribirseViaje(p);
                    break;
                case 4:
                    //Cancelar compra
                    break;
                case 5:
                    //Darse de baja de viaje improvisado
                    nueve.darseBaja(p);
                    break;
            }
        }
    }
    
    public static void MostrarMenuPasajero(Plataforma nueve){
        int opcion = 0;
        while (opcion != 3){
            System.out.println("INICIO: ");
            System.out.println("1.Loguearse" + "\n 2.Registrarse" + "\n 3.Salir");
            System.out.println("Ingrese la opcion que desea realizar:");
            Scanner s = new Scanner(System.in);
            opcion = s.nextInt();
            s.nextLine();
            if (opcion == 1){
                //Loguearse
                int dni = s.nextInt();
                s.nextLine();
                String clave = s.nextLine();
                Pasajero p = nueve.login(dni,clave);
                if (p != null)
                    mostrarMenuPlataforma(p,nueve);
                else{
                    System.out.println("Su usuario no fue encontrado");
                }          
            } else if (opcion == 2){
                nueve.registro();
            } else if (opcion != 3){
                System.out.println("La opcion ingresada no es correcta, vuelva a intentar");
            }
        }
    }

    public static void MostrarMenuAdmin (Plataforma plat){
        boolean ingreso = false;
        while(!ingreso){
            System.out.println("LOGIN");
            Administrador admin = plat.login();
            if (admin != null){
                ingreso = true;
                while (opcion != 3)
                    int opcion = 0;
                    System.out.println("MENU ADMINISTRADOR: ");
                    System.out.println("1.Agregar convenio"+ "\n 2.Ver estadisticas" +"\n 3.Salir");
                    System.out.println("Ingrese el numero de opcion que desea realizar:");
                    opcion = s.nextInt();
                    s.nextLine();
                    if (opcion == 1){
                        //Agregar convenio
                        Scanner s = new Scanner(System.in);
                        String nombre = s.nextLine();
                        Empresa emp = new Empresa(nombre);
                        int opc = 0;
                        while ((opc != 1) && (opc != 2)){
                            System.out.println("Desea que su empresa esta adherida? 1.SI 2.NO");
                            opc = s.nextInt();
                            s.nextLine();
                            if (opc == 1)
                                admin.addConvenioAdherido(emp, plat);
                            else if (opc == 2)
                                admin.addConvenio(emp, plat);
                            if ((opc != 1) && (opc != 2))
                                System.out.println("La opcion ingresada no es correcta vuelva a intentar");
                        }
                    }
                    if (opcion == 2){
                        //Ver Estadisticas
                        //Queda a implementar 
                    }
                    if (opcion != 3){
                        System.out.println("La opcion ingresada es incorrecta vuelva a intentar");
                    }
            } else
                System.out.println("El usuario ingresado es incorrecto vuelva a intentar");
        }
    }

    public void menuSeleccion(Plataforma p){
        int opcion = 0;
        while ((opc != 1) && (opc != 2)){        
            System.out.println("Ingrese 1 si sos usuario o ingresa 2 si sos administrador:");
            Scanner s = new Scanner(System.in);
            opcion = s.nextInt();
            s.nextLine();   
            if (opcion == 1)
                mostrarMenuPasajero(p);
            else if (opcion == 2)
                mostrarMenuAdmin(p);   
            if ((opcion != 1) && (opcion != 2))
                System.out.println("La opcion ingresada no es correcta vuelva a intentar");
        }
        
    }
    
    public static void main(String[] args) {
        Banco banco = new Banco("santander");
        Plataforma nueve = new Plataforma(banco);
        Tarjeta tarjeta = new Tarjeta(12,banco,"black");
        banco.addTarjeta(tarjeta);
        /*  //Creacion Pasajero
        Banco banco = new Banco("santander");
        Pasajero pia = new Pasajero("pia","Bedini", 12345678, tarjeta,"piabedini@gmail.com", "abc123");
        pia.suscribirseViaje("Tandil","La plata");
        //Creacion Empresa adherida
        EmpresaAdherida viaTac = new EmpresaAdherida("Viatac", 0.5,6,20);
        //CreacionColectivo
        Colectivo cole = new Colectivo(10,15,viaTac);
        //CreacionViaje
        LocalDate fecha = LocalDate.of(2022,06,5);
        Viaje viaje = new Viaje("Tandil", "La plata",cole, cole.getAsientos(), fecha, 1200.0, 5);
        viaTac.addViaje(viaje);
        nueve.registro();
        nueve.addEmpresaConv(viaTac);
        nueve.notificarViajesImprovisados();*/
        MostrarMenuIngresar(nueve);
    }
}
