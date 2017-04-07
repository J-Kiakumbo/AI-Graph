package br.com.grafo;


public class Vertice {

    private int id;
    private int peso;
    private Vertice adjacente;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public Vertice getAdjacente() {
        return adjacente;
    }

    public void setAdjacente(Vertice adjacente) {
        this.adjacente = adjacente;
    }

}
