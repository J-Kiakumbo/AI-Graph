package br.com.grafo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Main {

    private static final Scanner ler = new Scanner(System.in);
    private static final Graph grafo = new Graph();

    public static void menu() {
        System.out.println("\tGRAF");
        System.out.println("0. Zakończ");
        System.out.println("1. Dodaj Węzeł");
        System.out.println("2. Dodaj krawędzi");
        System.out.println("3. Koloruj");
        System.out.println("4. Otwórz graf z pliku graf.txt");
        System.out.println("5. Wyswietlaj Graf");
        System.out.println("\nProszę wybrać jeden z powyszej opcji:");
    }

   
    public static void main(String[] args) {
        int opcao;
        grafo.setOrientado(false); //grafo orientado?

        do {
            menu();
            opcao = ler.nextInt();
            switch (opcao) {
                case 1:
                    inserirVertice();
                    break;
                case 2:
                    inserirAresta();
                    break;
                case 3:
                    grafo.colorirGrafo(grafo);
                    break;
                case 4:
                    povoarGrafo();
                    break;
                case 5:
                    grafo.imprimirGrafo();
                    break;
                default:
            }
        } while (opcao != 0);

        System.out.println("Até mais.");
    }


    private static void inserirVertice() {
        System.out.print("Rotulo: ");
        int addVertice = ler.nextInt();
        if (grafo.inserirVertice(addVertice)) {
            System.out.println("Vertice adicionado.");
        } else {
            System.out.println("Erro.");
        }
    }

    private static void inserirAresta() {
        System.out.print("Aresta: ");
        int a1 = ler.nextInt();
        System.out.print("Aresta: ");
        int a2 = ler.nextInt();
        System.out.print("Peso: ");
        int peso = ler.nextInt();

        if (grafo.inserirAresta(a1, a2, peso)) {
            System.out.println("Aresta adicionada.");
        } else {
            System.out.println("Erro.");
        }
    }
    //Cria um grafo apartir da arquivo graf.txt
    private static void povoarGrafo() {
        ArrayList<String> dados = new ArrayList<>();
        String linha;
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("graf.txt"));
            StringTokenizer dado = null;

            while ((linha = reader.readLine()) != null) {
                dado = new StringTokenizer(linha, "-> :");
                while (dado.hasMoreTokens()) {
                    dados.add(dado.nextToken());
                }
            }
            reader.close();

            if (dados.get(0).equals("0")) {
                grafo.setOrientado(false);
            } else {
                if (dados.get(0).equals("1")) {
                    grafo.setOrientado(true);
                }
            }
            for (int i = 1; i < (dados.size()); i = i + 3) {
                grafo.inserirVertice(Integer.parseInt(dados.get(i)));
            }
            for (int i = 2; i < (dados.size()); i = i + 3) {
                grafo.inserirVertice(Integer.parseInt(dados.get(i)));
            }
            for (int i = 1; i < (dados.size()); i = i + 3) {
                grafo.inserirAresta(Integer.parseInt(dados.get(i)), Integer.parseInt(dados.get(i + 1)), Integer.parseInt(dados.get(i + 2)));
            }

        } catch (IOException | NumberFormatException e) {
        }
    }
}
