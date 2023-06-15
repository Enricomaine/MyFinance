import Cadastros.*;
import DAO.*;
import Operacoes.Lancamento;
import Relatorios.Relatorio;

import javax.swing.*;
import java.sql.Connection;
import java.text.ParseException;

public class Main {

    public static void main(String[] args) throws ParseException {
        int opcaoPrincipal = 0, opcaoCadastro, opcaoCadastroOperacao, opcaoOperacao, opcaoRelatorio;

        mySQL mysql = new mySQL();
        Connection c = mysql.getConexao();
        Banco banco = new Banco();
        Conta conta = new Conta();
        CaixaTransacao caixatransacao = new CaixaTransacao();
        Lancamento lancamento = new Lancamento();
        Relatorio relatorio = new Relatorio();

        while (opcaoPrincipal != 4) {
            opcaoPrincipal = Integer.parseInt(JOptionPane.showInputDialog(
                    "Menu principal\n" +
                            "1-Cadastros\n" +
                            "2-Operacoes\n" +
                            "3-Relatorios\n" +
                            "4-Sair\n" +
                            "Digite uma opcao:"));

            switch (opcaoPrincipal) {
                case 1: {
                    opcaoCadastro = Integer.parseInt(JOptionPane.showInputDialog(
                            "Cadastros\n" +
                                    "1-Bancos\n" +
                                    "2-Conta\n" +
                                    "3-Transacao\n" +
                                    "4-Voltar ao menu\n" +
                                    "Digite uma opcao:"));
                    opcaoCadastroOperacao = Integer.parseInt(JOptionPane.showInputDialog(
                            "1-Criar\n" +
                                    "2-Editar\n" +
                                    "3-Excluir\n" +
                                    "4-Voltar ao menu\n" +
                                    "Digite uma opcao:"));
                    switch (opcaoCadastro) {
                        case 1:
                            switch (opcaoCadastroOperacao) {
                                case 1:
                                    banco.criaBanco(banco, c);
                                    break;
                                case 2:
                                    banco.editaBanco(c);
                                    break;
                                case 3:
                                    banco.excluiBanco(c);
                                    break;
                            }
                            break;
                        case 2:
                            switch (opcaoCadastroOperacao) {
                                case 1:
                                    conta.criaConta(conta, c);
                                    break;
                                case 2:
                                    conta.editaConta();
                                    break;
                                case 3:
                                    conta.excluiConta();
                                    break;
                            }
                            break;
                        case 3:
                            switch (opcaoCadastroOperacao) {
                                case 1:
                                    caixatransacao.criaTransacao(caixatransacao, c);
                                    break;
                                case 2:
                                    caixatransacao.editaTransacao();
                                    break;
                                case 3:
                                    caixatransacao.excluiTransacao();
                                    break;
                            } break;
                        default:
                            JOptionPane.showMessageDialog(null, "Valor invalido, voltando ao menu principal");
                    }
                } break;
                case 2: {
                    opcaoOperacao = Integer.parseInt(JOptionPane.showInputDialog(
                            "Lancamento\n"+
                                    "1-Novo\n"+
                                    "2-editar\n"+
                                    "3-excluir\n"+
                                    "Digite a sua opcao: "));
                    switch (opcaoOperacao) {
                        case 1: lancamento.criaLancamento(lancamento, c); break;
                        case 2: lancamento.editaLancamento(c); break;
                        case 3: lancamento.excluiLancamento(c); break;
                        default: JOptionPane.showMessageDialog(null,"Nenhuma opcao valida selecionada, voltando ao Menu"); break;
                    }
                }

                case 3: {
                    opcaoRelatorio = Integer.parseInt(JOptionPane.showInputDialog(
                            "1-Saldo da conta\n" +
                                    "2-Lancamentos em um período\n" +
                                    "3-Lancamentos por transacao"));
                    switch (opcaoRelatorio) {
                        case 1: relatorio.saldoEmConta(c);
                        case 2: relatorio.lancamentoPorPeriodo(c);
                        case 3: relatorio.lancamentosPorTransacao(c);
                    }
                }
            }
        }
    }
}

