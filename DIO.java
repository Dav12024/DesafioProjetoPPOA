// Exceção SaldoInsuficienteException
public class SaldoInsuficienteException extends RuntimeException {
    public SaldoInsuficienteException() {
        super("Saldo insuficiente na conta. Operação não pode ser realizada.");
    }
}

// Classes de Conta Bancária
public abstract class ContaBancaria {
    protected double saldo;

    public ContaBancaria(double saldoInicial) {
        this.saldo = saldoInicial;
    }

    public double getSaldo() {
        return saldo;
    }

    public void sacar(double valor) {
        if (saldo >= valor) {
            saldo -= valor;
        } else {
            throw new SaldoInsuficienteException();
        }
    }
}

public class ContaCorrente extends ContaBancaria {
    public ContaCorrente(double saldoInicial) {
        super(saldoInicial);
    }
}

public class ContaPoupanca extends ContaBancaria {
    public ContaPoupanca(double saldoInicial) {
        super(saldoInicial);
    }
}

public class ContaSalario extends ContaBancaria {
    public ContaSalario(double saldoInicial) {
        super(saldoInicial);
    }
}

public class ContaInvestimento extends ContaBancaria {
    public ContaInvestimento(double saldoInicial) {
        super(saldoInicial);
    }
}

// Defina outras classes de conta aqui...

// Aspecto de Verificação de Saldo
public aspect VerificacaoDeSaldoAspect {
    pointcut operacaoDeSaque(ContaBancaria conta, double valor): 
        execution(* ContaBancaria.sacar(double)) && target(conta) && args(valor);

    before(ContaBancaria conta, double valor): operacaoDeSaque(conta, valor) {
        if (conta.getSaldo() < valor) {
            System.out.println("Saldo insuficiente na conta. Operação não pode ser realizada.");
        }
    }
}
