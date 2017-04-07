package br.com.grafo;


public class Aresta {

    private Vertice v1;
    private Vertice v2;
    private int peso;

    public Vertice getV1() {
        return v1;
    }

    public void setV1(Vertice v) {
        this.v1 = v;
    }

    public Vertice getV2() {
        return v2;
    }

    public void setV2(Vertice v2) {
        this.v2 = v2;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

}
