import java.time.*;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Scanner;

public class main234 {
    public main234() {
    }

    public void cargarEmpresasEstatica(Plataforma p) {
       //Evita el menu de administrador para cargar las empresas mas rapido y poder probarlo.
        Administrador admin = new Administrador("Pia","Bedini",12345);
        //CREACION EMPRESAS
        Empresa e1 = new Empresa("El gorrion");
        Empresa e2 = new Empresa("Langueyu");
        Empresa e3 = new Empresa("Condor Estrella");
        EmpresaAdherida e4 = new EmpresaAdherida("Plusmar",0.5,10,50);
        EmpresaAdherida e5 = new EmpresaAdherida("Flechabus",0.4,10,50);
        EmpresaAdherida e6 = new EmpresaAdherida("El Rapido",0.8,10,50);

        //CREACION COLECTIVOS
        Colectivo c1 = new Colectivo(1, 5, e1);
        Colectivo c2 = new Colectivo(1, 5, e4);

        //CREACION VIAJES
        Viaje v1 = new Viaje("Tandil", "MarDelPlata",  c1, LocalDateTime.now(), 1500.0);
        Viaje v2 = new Viaje("Tandil", "Azul",c2, LocalDateTime.now(), 2000.0);
        Viaje v3 = new Viaje("Tandil", "Ayacucho",  c1, LocalDateTime.now(), 5000.0);
        Viaje v4 = new Viaje("Tandil", "Olavarria",  c2, LocalDateTime.now(), 1200.0);
        Viaje v5 = new Viaje("Olavarria", "Tandil", c1, LocalDateTime.now(), 6000.0);
        LocalDateTime salida = LocalDateTime.of(LocalDate.now(),LocalTime.now().plusHours(3));
        Viaje v6 = new Viaje("Olavarria", "CABA",  c2, salida, 1500.0);

        //CARGA DE PASAJERO DEFAULT
        Pasajero lu = new Pasajero("Lu","Delgado",1,"","1");
        Pasajero vico = new Pasajero("Vico","Dibar",44870875,"","1");
        Pasajero del = new Pasajero("Delfi","Ferreri",44377571,"","1");
        
        //AGREGO COLECTIVOS Y VIAJES A LAS EMPRESAS
        e1.addColectivo(c1);
        e1.addViaje(v1);
        e1.addViaje(v3);

        e4.addColectivo(c2);
        e4.addViaje(v2);
        e4.addViaje(v4);
        e4.addViaje(v5);
        e4.addViaje(v6);
        
        //SE AGREGAN PASAJEROS
        p.addUsuario(lu);
        p.addUsuario(vico);
        p.addUsuario(del);

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
            System.out.println(" 1.Buscar pasajes" + "\n 2.Rastreo" + "\n 3.Suscribirse a viaje improvisado" +"\n 4.Cancelar compra" + "\n 5.Baja de viaje improvisado" +"\n 6.Salir");
            Scanner s = new Scanner(System.in);
            System.out.println("Ingrese el numero de opcion que desea realizar");
            opcion = s.nextInt();
            s.nextLine();
            switch(opcion){
                case 1:
                    //Buscar pasajes
                    ArrayList<Viaje> resultadoBusqueda = nueve.buscarPasaje();
                    //Elegir asiento
                    if(resultadoBusqueda.size()>0) {
                        System.out.println("Desea comprar un pasaje? 1.SI 2.NO");
                        int realizaCompra = s.nextInt();
                        if (realizaCompra == 1) {
                            System.out.println("Seleccione el numero de viaje que desea comprar:");
                            int op = s.nextInt();
                            if ((op > -1) && (op < resultadoBusqueda.size())) {
                                nueve.generarCompra(resultadoBusqueda.get(op), p);
                            } 
                            else System.out.println("OPCION INCORRECTA");
                        } 
                        else System.out.println("OPCION INCORRECTA");
                    }
                    else
                        System.out.println("No hay ningun viaje con los parametros ingresados");
                    break;
                case 2:
                    //Suscribirse a rastreo
                    break;
                case 3:
                    //Suscribirse a viaje improvisado
                    nueve.suscribirseViaje(p);
                    nueve.notificarViajesImprovisados();
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

    public void mostrarMenuPasajero(Plataforma nueve){
        int opcion = 0;
        while (opcion != 3){
            System.out.println("INICIO: ");
            System.out.println(" 1.Loguearse" + "\n 2.Registrarse" + "\n 3.Salir");
            System.out.println("Ingrese la opcion que desea realizar:");
            Scanner s = new Scanner(System.in);
            opcion = s.nextInt();
            s.nextLine();
            if (opcion == 1){

                 Usuario p = nueve.login();
                if (p != null)
                    mostrarMenuPlataforma(((Pasajero)p),nueve); //Esto esta bien hay que hacer un casting pero estaria y ver lo del static
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

    public void mostrarMenuAdmin (Plataforma plat){
        Scanner s = new Scanner(System.in);
        boolean ingreso = false;
        int opcion =0;
        while(!ingreso){
            System.out.println("LOGIN");
            Usuario admin = plat.login();
            if (admin != null){
                ingreso = true;
                while (opcion != 3)
                System.out.println("MENU ADMINISTRADOR: ");
                System.out.println("1.Agregar convenio"+ "\n 2.Ver estadisticas" +"\n 3.Salir");
                System.out.println("Ingrese el numero de opcion que desea realizar:");
                opcion = s.nextInt();
                s.nextLine();
                if (opcion == 1){
                    //Agregar convenio
                    String nombre = s.nextLine();

                    int opc = 0;
                    while ((opc != 1) && (opc != 2)){
                        System.out.println("Desea que su empresa esta adherida? 1.SI 2.NO");
                        opc = s.nextInt();
                        s.nextLine();
                        if (opc == 1) {
                            EmpresaAdherida emp = new EmpresaAdherida(nombre, 0.7, 4, 6);
                            ((Administrador) admin).addConvenioAdherido(emp, plat);
                        }
                        else if (opc == 2) {
                            Empresa empa = new Empresa(nombre);
                            ((Administrador) admin).addConvenio(empa, plat);
                        }
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
            } else if (realizaCompra!=2)
                System.out.println("El usuario ingresado es incorrecto vuelva a intentar");
        }
    }

    public void menuSeleccion(Plataforma p){
        int opcion = 0;

        while ((opcion != 1) && (opcion!= 2)){
            System.out.println("Ingrese 1 si sos usuario o ingresa 2 si sos administrador:");
            Scanner s = new Scanner(System.in);
            opcion = s.nextInt();
            s.nextLine();
            if (opcion == 1)
                mostrarMenuPasajero(p); //Falta el usuario/pasajero ver
            else if (opcion == 2)
                mostrarMenuAdmin(p);
            if ((opcion != 1) && (opcion != 2))
                System.out.println("La opcion ingresada no es correcta vuelva a intentar");
        }

    }


}
