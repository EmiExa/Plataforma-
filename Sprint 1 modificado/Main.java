import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.time.LocalDate;
public class Main {

    public void mostrarMenuPlataforma (Pasajero p){
        int opcion = 0;
        while(opcion !=54){
            System.out.println("MENU: ");
            System.out.println("1.Buscar pasajes" + "\n 2.Suscribirse a rastreo" + "\n 3.Suscribirse a viaje improvisado" +"\n 4.Cancelar compra" + "\n 5.Salir");
            Scanner s = new Scanner(System.in);
            System.out.println("Ingrese el numero de opcion que desea realizar");
            opcion = s.nextInt();
            s.nextLine();
            switch(opcion){
                case 1:
                    //Buscar pasajes
                    Arraylist<Viaje> resultadoBusqueda = nueve.buscarPasajes();
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
                    
                    break;
                case 4:
                    //Cancelar compra
                    break;
            }
        }
    }
    
    public void MostrarMenuIngresar (){
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
                Pasajero p = nueve.login();
                if (p != null)
                    mostrarMenuPlataforma(p);
                else{
                    System.out.println("Su usuario no fue encontrado");
                }          
            } else if (opcion == 2){
                //Registrarse
                Pasajero p = nueve.registro();
            } else if (opcion != 3){
                System.out.println("La opcion ingresada no es correcta, vuelva a intentar");
            }
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
*/
        //Creacion Empresa adherida
        EmpresaAdherida viaTac = new EmpresaAdherida("Viatac", 0.5,6,20);
        //CreacionColectivo
        Colectivo cole = new Colectivo(10,15,viaTac);
        //CreacionViaje
        LocalDate fecha = LocalDate.of(2022,06,5);
        Viaje viaje = new Viaje("Tandil", "La plata",cole, cole.getAsientos(), fecha, 1200.0, 5);
        viaTac.addViaje(viaje);
        nueve.registro();
        nueve.login();
        nueve.addEmpresaConv(viaTac);
        nueve.notificarViajesImprovisados();
    }
}
