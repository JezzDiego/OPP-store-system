package br.edu.ifal.domain;

public class ItemPedido {
    private int id;
    private int idPedido;
    private int idProduto;
    private double quantidade;
    private double valor;

    public ItemPedido(int id, int idPedido, int idProduto, double quantidade, double valor) {
        this.id = id;
        this.idPedido = idPedido;
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public double getValor() {
        return valor;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
