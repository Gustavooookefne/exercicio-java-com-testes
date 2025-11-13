package org.example.model;

public class Produto {

    private int id;

    private String nome;

    private double preco;

    private int quantidade;

    private String categoria;

    public Produto() {

    }
    public Produto(String nome, double preco, int quantidade, String categoria) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.categoria = categoria;
    }
    public Produto(int id, String nome, double preco, int quantidade, String categoria) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.categoria = categoria;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public double getPreco() {
        return preco;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                "Nome: " + nome +
                "Preço: " + preco +
                "Quantidade: " + quantidade +
                "Categoria: " + categoria;
    }
}