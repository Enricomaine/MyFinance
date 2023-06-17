import Cadastros.*;
import DAO.*;
import Operacoes.Lancamento;
import Relatorios.Relatorio;

import javax.swing.*;
import java.security.Principal;
import java.sql.Connection;
import java.text.ParseException;

public class Main {

    public static void main(String[] args) throws ParseException {
        String opcaoPrincipal = "";

        mySQL mysql = new mySQL();
        Connection c = mysql.getConexao();
        Banco banco = new Banco();
        Conta conta = new Conta();
        CaixaTransacao caixatransacao = new CaixaTransacao();
        Lancamento lancamento = new Lancamento();
        Relatorio relatorio = new Relatorio();

        while (opcaoPrincipal != "Sair") {
            String[] principal = {"Cadastros", "Operacoes", "Relatorios", "Sair"};
            opcaoPrincipal = (String) JOptionPane.showInputDialog(null,"Selecione uma das opcoes","MENU PRINCIPAL",JOptionPane.QUESTION_MESSAGE,null, principal, principal[0]);
            switch (opcaoPrincipal) {
                case "Cadastros": {
                    String opcaoCadastro, opcaoCadastroOperacao;
                    String[] cadastros = {"Bancos", "Conta", "Transacao", "Voltar ao menu"};
                    opcaoCadastro = (String) JOptionPane.showInputDialog(null,"Selecione uma das opcoes","Cadastros",JOptionPane.QUESTION_MESSAGE,null, cadastros, cadastros[0]);
                    String[] cadastroOperacoes = {"Novo", "Editar", "Excluir", "Sair"};
                    opcaoCadastroOperacao = (String) JOptionPane.showInputDialog(null,"Selecione uma das opcoes","Operacao",JOptionPane.QUESTION_MESSAGE,null, cadastroOperacoes, cadastroOperacoes[0]);

                    switch (opcaoCadastro) {
                        case "Bancos":
                            switch (opcaoCadastroOperacao) {
                                case "Novo":
                                    banco.criaBanco(banco, c);
                                    break;
                                case "Editar":
                                    banco.editaBanco(c);
                                    break;
                                case "Excluir":
                                    banco.excluiBanco(c);
                                    break;
                            }
                            break;
                        case "Conta":
                            switch (opcaoCadastroOperacao) {
                                case "Novo":
                                    conta.criaConta(conta, c);
                                    break;
                                case "Editar":
                                    conta.editaConta();
                                    break;
                                case "Excluir":
                                    conta.excluiConta();
                                    break;
                            }
                            break;
                        case "Transacao":
                            switch (opcaoCadastroOperacao) {
                                case "Novo":
                                    caixatransacao.criaTransacao(caixatransacao, c);
                                    break;
                                case "Editar":
                                    caixatransacao.editaTransacao(c);
                                    break;
                                case "Excluir":
                                    caixatransacao.excluiTransacao(c);
                                    break;
                            } break;
                        default:
                            JOptionPane.showMessageDialog(null, "Valor invalido, voltando ao menu principal");
                    }
                } break;
                case "Operacoes": {
                    String[] opcoes = {"Novo", "Editar", "Excluir"};
                    String opcaoOperacao;
                    opcaoOperacao = (String) JOptionPane.showInputDialog(null,"Selecione uma das opcoes","Lancamento",JOptionPane.QUESTION_MESSAGE,null,opcoes, opcoes[0]);
                    switch (opcaoOperacao) {
                        case "Novo": lancamento.criaLancamento(lancamento, c); break;
                        case "Editar": lancamento.editaLancamento(c); break;
                        case "Excluir": lancamento.excluiLancamento(c); break;
                        default: JOptionPane.showMessageDialog(null,"Nenhuma opcao valida selecionada, voltando ao Menu"); break;
                    }
                }

                case "Relatorios": {
                    String[] opcoes = {"Saldo em conta", "Lancamentos por periodo", "Lancamentos por transacao"};
                    String opcaoRelatorio;
                    opcaoRelatorio = (String) JOptionPane.showInputDialog(null,"Selecione uma das opcoes","Relatorios",JOptionPane.QUESTION_MESSAGE,null,opcoes, opcoes[0]);
                    switch (opcaoRelatorio) {
                        case "Saldo em conta": relatorio.saldoEmConta(c); break;
                        case "Lancamentos por periodo": relatorio.lancamentoPorPeriodo(c); break;
                        case "Lancamentos por transacao": relatorio.lancamentosPorTransacao(c); break;
                    }
                }
            }
        }
    }
}

