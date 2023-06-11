import Cadastros.*;
import DAO.*;

import javax.swing.*;
import java.sql.Connection;

public class Main {

    public static void main(String[] args) {
        int opcaoPrincipal = 0, opcaoCadastro, opcaoOperacao, OpacaoRelatorio;

        mySQL mysql = new mySQL();
        Connection c = mysql.getConexao();
        Banco banco = new Banco();
        Conta conta = new Conta();
        Transacao transacao = new Transacao();

        while (opcaoPrincipal != 4) {
            opcaoPrincipal = Integer.parseInt(JOptionPane.showInputDialog(
                    "Menu principal\n" +
                            "1-Cadastros\n" +
                            "2-Operacoes\n" +
                            "3-Relatorios\n" +
                            "4-Sair\n" +
                            "Digite uma opcao:"));

            if (opcaoPrincipal == 1) {
                opcaoPrincipal = Integer.parseInt(JOptionPane.showInputDialog(
                        "Cadastros\n" +
                                "1-Bancos\n" +
                                "2-Conta\n" +
                                "3-Transacao\n" +
                                "4-Voltar ao menu\n" +
                                "Digite uma opcao:"));
                opcaoCadastro = Integer.parseInt(JOptionPane.showInputDialog(
                        "1-Criar\n"+
                                "2-Editar\n"+
                                "3-Excluir\n"+
                                "4-Voltar ao menu\n"+
                                "Digite uma opcao:"));
                switch (opcaoPrincipal) {
                    case 1: switch (opcaoCadastro) {
                        case 1: banco.criaBanco(banco, c); break;
                        case 2: banco.editaBanco(); break;
                        case 3: banco.excluiBanco(); break;
                    } break;
                    case 2: switch (opcaoCadastro) {
                        case 1: conta.criaConta(); break;
                        case 2: conta.editaConta(); break;
                        case 3: conta.excluiConta(); break;
                    } break;
                    case 3: switch (opcaoCadastro) {
                        case 1: transacao.criaTransacao(); break;
                        case 2: transacao.editaTransacao(); break;
                        case 3: transacao.excluiTransacao(); break;
                    } break;
                    default:
                        JOptionPane.showMessageDialog(null,"Valor invalido, voltando ao menu principal");
                }
            }
            else if (opcaoPrincipal == 2) {
                while (opcaoPrincipal != 10) {
                    opcaoPrincipal = Integer.parseInt(JOptionPane.showInputDialog("Operacoes\n1-lancamentos\n2-contas a receber\n3-contas a pagar\n4-voltar\nDigite uma opcao:"));
                }
            }
            else if (opcaoPrincipal == 3) {

            }
        }
    }
}

