public class Tarjeta {
    private int numero;
    private Banco bancoEmisor;
    private String marca;
    public Tarjeta(int num, Banco banco, String marc){
        numero = num;
        bancoEmisor = banco;
        marca = marc;
    }
    public int getNum(){
        return numero;
    }
    public String getMarca(){
        return marca;
    }
}
