package conta.micro.to;

public class NumeroTO {

    public NumeroTO() {
    }

    public NumeroTO(Integer conta) {
        this.conta = conta;
    }

    public Integer conta;

    public Integer getConta() {
        return conta;
    }

    public void setConta(Integer conta) {
        this.conta = conta;
    }
}
