package br.edu.ifal.domain;

public class Produto {
    private int id;
    private String nome;
    private double valorUnit;
    private double quantidade;

    public Produto(int id, String nome, double valorUnit, double quantidade) {
        this.id = id;
        this.nome = nome;
        this.valorUnit = valorUnit;
        this.quantidade = quantidade;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getValorUnit() {
        return valorUnit;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setValorUnit(double valorUnit) {
        this.valorUnit = valorUnit;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }


    @Override
    public String toString() {
        return "Produto {" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", valorUnit=" + valorUnit +
                ", quantidade=" + quantidade +
                '}';
    }
}
