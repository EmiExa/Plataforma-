public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco("santander");
        Plataforma nueve = new Plataforma(banco);
        Tarjeta tarjeta = new Tarjeta(12,banco,"black");
        banco.addTarjeta(tarjeta);
        Menu menuprueba = new Menu();
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
        menuprueba.cargarEmpresasEstatica(nueve);
        menuprueba.menuSeleccion(nueve);
    }
}
