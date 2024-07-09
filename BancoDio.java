import java.util.Scanner;

public class BancoDio {
    public static void main(String[] args) {
        Conta conta_corrente = new ContaCorrente();
        Conta conta_poupanca = new ContaPoupanca();

        conta_corrente.imprimirExtrato();
        conta_poupanca.imprimirExtrato();
    }

    public static class Banco {
        private String nome;

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }
    }

    public static class Cliente {
        private String nome;

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }
    }

    public interface IConta {
        void sacar(double valor);
        void depositar(double valor);
        void transferir(double valor, Conta contaDestino);
        void imprimirExtrato();
    }

    public abstract static class Conta implements IConta {
        private static int SEQUENCIAL = 1;
        private static final int AGENCIA_PADRAO = 1;

        protected int agencia;
        protected int numero;
        protected double saldo;

        public Conta() {
            this.agencia = AGENCIA_PADRAO;
            this.numero = SEQUENCIAL++;
            this.saldo = 0.0;
        }

        public int getAgencia() {
            return agencia;
        }

        public int getNumero() {
            return numero;
        }

        public double getSaldo() {
            return saldo;
        }

        protected void imprimirInfosComuns() {
            System.out.println(String.format("Agência: %d", this.agencia));
            System.out.println(String.format("Número: %d", this.numero));
            System.out.println(String.format("Saldo: %.2f", this.saldo));
        }

        @Override
        public void sacar(double valor) {
            if (saldo >= valor) {
                saldo -= valor;
            } else {
                System.out.println("Saldo insuficiente!");
            }
        }

        @Override
        public void depositar(double valor) {
            saldo += valor;
        }

        @Override
        public void transferir(double valor, Conta contaDestino) {
            if (saldo >= valor) {
                this.sacar(valor);
                contaDestino.depositar(valor);
            } else {
                System.out.println("Saldo insuficiente!");
            }
        }
    }

    public static class ContaCorrente extends Conta {
        @Override
        public void imprimirExtrato() {
            System.out.println("==== Extrato Conta Corrente ====");
            super.imprimirInfosComuns();
        }
    }

    public static class ContaPoupanca extends Conta {
        @Override
        public void imprimirExtrato() {
            System.out.println("==== Extrato Conta Poupança ====");
            super.imprimirInfosComuns();
        }
    }
}
