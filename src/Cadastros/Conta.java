package Cadastros;

public class Conta {
    private int idConta;
    private Banco banco;
    private String numero;
    private String agencia;

    public void criaConta(){
        System.out.println("Conta criado");
    };
    public void editaConta(){
        System.out.println("Conta editado");
    };
    public void excluiConta() {
        System.out.println("Conta excluido");
    };
}
