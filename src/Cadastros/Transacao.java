package Cadastros;

public class Transacao {
    private int idTransacao;
    private String descricao;

    public void criaTransacao(){
        System.out.println("Transacao criado");
    };
    public void editaTransacao(){
        System.out.println("Transacao editado");
    };
    public void excluiTransacao() {
        System.out.println("Transacao excluido");
    };
}
