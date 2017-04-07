package br.com.grafo;

import java.util.ArrayList;
import java.util.Collections;


public class Graph {

    private ArrayList<Vertice> vertices;
    private ArrayList<Aresta> arestas;
    private boolean orientado;

    public Graph() {
        this.vertices = new ArrayList<Vertice>();
        this.arestas = new ArrayList<Aresta>();
    }

    public ArrayList<Vertice> getVertices() {
        return vertices;
    }

    public void setVertices(ArrayList<Vertice> vertices) {
        this.vertices = vertices;
    }

    public ArrayList<Aresta> getArestas() {
        return arestas;
    }

    public void setArestas(ArrayList<Aresta> arestas) {
        this.arestas = arestas;
    }

    public boolean isOrientado() {
        return orientado;
    }

    public void setOrientado(boolean orientado) {
        this.orientado = orientado;
    }

    public boolean verticeExiste(int v) {
        for (Vertice vertice : vertices) {
            if (vertice.getId() == v) {
                return true;
            }
        }
        return false;
    }

    public boolean arestaExiste(int v1, int v2, int peso) {
        for (Aresta aresta : arestas) {
            if (aresta.getV1().getId() == v1 && aresta.getV2().getId() == v2 && aresta.getPeso() == peso) {
                return true;
            }
        }
        return false;
    }

    public boolean inserirVertice(int id) {
        if (!verticeExiste(id)) {
            Vertice addVertice = new Vertice();
            addVertice.setId(id);
            //addVertice.setPeso(peso);
            vertices.add(addVertice);
            return true;
        }
        return false;
    }

    public boolean inserirAresta(int v1, int v2, int peso) {
        if (verticeExiste(v1) && verticeExiste(v2)) {
            for (Vertice vertice : vertices) {
                if (vertice.getId() == v1) {
                    Vertice v = vertice;
                    while (v.getAdjacente() != null) {
                        v = v.getAdjacente();
                    }
                    if (v.getAdjacente() == null) {
                        Vertice vertice2 = new Vertice();
                        vertice2.setId(v2);
                        vertice2.setPeso(peso);
                        v.setAdjacente(vertice2);

                        Aresta addAresta = new Aresta();
                        addAresta.setV1(vertice);
                        addAresta.setV2(vertice2);
                        addAresta.setPeso(peso);
                        arestas.add(addAresta);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int[][] colorirGrafo(Graph grafo) {
        String nomeCor[] = {"Azul", "Roxo", "Ciano", "Rosa", "Vermelho", "Laranja", "Verde", "Violeta", "Alizarina", "Carmesim", "Coral", "Escarlate", "Magenta", "Marrom", "Amarelo"};
        ArrayList<Integer> verticesAdjacentes = new ArrayList<>();
        grafo.vertices = ordernarVertices();
        System.out.println("colorir");
        boolean colorir = true;
        int cor = 1;

        int cores[][] = new int[2][grafo.vertices.size()];

        for (int i = 0; i <= 0; i++) {
            for (int j = 0; j < grafo.vertices.size(); j++) {
                cores[i][j] = grafo.vertices.get(j).getId();
            }
        }
        cores[1][0] = 1;

        for (int i = 0; i < cores[0].length; i++) {
            verticesAdjacentes = getvAdjacentes(cores[0][i]);
            for (int j = 1; j <= cor; j++) {
                colorir = true;
                for (int adj : verticesAdjacentes) {
                    for (int k = 0; k < cores[0].length; k++) {
                        if (cores[0][k] == adj) {
                            if (cores[1][k] == j) {
                                colorir = false;
                            }
                            break;
                        }
                    }
                }
                if (colorir == true) {
                    cores[1][i] = j;
                    break;
                }
            }
            if (colorir == false) {
                cor++;
                cores[1][i] = cor;
            }
        }
        for (int i = 0; i < cores[0].length; i++) {
            System.out.print("Vertice:" + cores[0][i] + "(" + nomeCor[cores[1][i]] + ")\n");
        }
        return cores;
    }

    private ArrayList<Vertice> ordernarVertices() {
        for (int i = vertices.size(); i > 1; i--) {
            for (int j = 1; j < i; j++) {
                if (getGrau(vertices.get(j - 1).getId()) < getGrau(vertices.get(j).getId())) {
                    Collections.swap(vertices, j, j - 1);
                }
            }
        }
        return vertices;
    }

    private int getGrau(int verticeId) {
        int grau = 0;
        if (!orientado) {
            for (Vertice vertice : vertices) {

                if (vertice.getId() == verticeId) {

                    while (vertice.getAdjacente() != null) {
                        grau++;
                        vertice = vertice.getAdjacente();
                    }
                }

                while (vertice.getAdjacente() != null) {
                    if (vertice.getAdjacente().getId() == verticeId) {
                        grau++;
                    }
                    vertice = vertice.getAdjacente();
                }
            }
        }
        return grau;
    }

    private ArrayList<Integer> getvAdjacentes(int verticeId) {
        ArrayList<Integer> adja = new ArrayList<>();
        Vertice atual;
        vertices.stream().filter((vertice) -> (vertice.getId() == verticeId)).forEach((vertice) -> {
            while (vertice.getAdjacente() != null) {
                adja.add(vertice.getAdjacente().getId());
                vertice = vertice.getAdjacente();
            }
        });

        for (Vertice vertice : vertices) {
            atual = vertice;
            while (vertice.getAdjacente() != null) {
                if (vertice.getAdjacente().getId() == verticeId) {
                    adja.add(atual.getId());
                }
                vertice = vertice.getAdjacente();
            }
        }
        return adja;
    }

    public void imprimirGrafo() {
        vertices.stream().map((vertice) -> {
            Vertice v = vertice;
            System.out.print(vertice.getId()+"->");
            while (v.getAdjacente() != null) {
                System.out.print(v.getAdjacente().getId()+"("+v.getAdjacente().getPeso()+")");
                System.out.print(",");
                v = v.getAdjacente();
            }
            return vertice;
        }).forEach((_item) -> {
            System.out.println("");
        });
    }
}
