package br.edu.ifal.domain;

public class Pedido {
    private int id;
    private String cpfCliente;
    private String cpfFuncionario;
    private double valorTotal;

    public Pedido(int id, String cpfCliente, String cpfFuncionario, double valorTotal) {
        this.id = id;
        this.cpfCliente = cpfCliente;
        this.cpfFuncionario = cpfFuncionario;
        this.valorTotal = valorTotal;
    }

    public int getId() {
        return id;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public String getCpfFuncionario() {
        return cpfFuncionario;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public void setCpfFuncionario(String cpfFuncionario) {
        this.cpfFuncionario = cpfFuncionario;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", cpfCliente='" + cpfCliente + '\'' +
                ", cpfFuncionario='" + cpfFuncionario + '\'' +
                ", valorTotal=" + valorTotal +
                '}';
    }

}
