import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.time.LocalDate;
public class Main {
    public static void main(String[] args) {
        Plataforma nueve = new Plataforma();
        //Creacion Pasajero
        Banco banco = new Banco("santander");
        Tarjeta tarjeta = new Tarjeta(12,banco,"black");
        Pasajero pia = new Pasajero("pia","Bedini", 12345678, tarjeta,"piabedini@gmail.com");
        pia.suscribirseViaje("Tandil","La plata");

        //Creacion Empresa adherida
        EmpresaAdherida viaTac = new EmpresaAdherida("Viatac", 0.5,6,20);
        //CreacionColectivo
        Colectivo cole = new Colectivo(10,15,viaTac);
        //CreacionViaje
        LocalDate fecha = LocalDate.of(2022,06,5);
        Viaje viaje = new Viaje("Tandil", "La plata",cole, cole.getAsientos(), fecha, 1200.0, 5);

        viaTac.addViaje(viaje);
        nueve.addPasajero(pia);
        nueve.addEmpresaConv(viaTac);
        nueve.notificarViajesImprovisados();

    }
}