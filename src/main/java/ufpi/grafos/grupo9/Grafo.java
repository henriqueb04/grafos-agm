package ufpi.grafos.grupo9;

import java.util.Arrays;

public class Grafo {
    private final int tamanho;

    private int[][] adjDestino;
    private long[][] adjPeso;
    private int[] grau;

    public Grafo(int tamanho) {
        this.tamanho = tamanho;
        this.adjDestino = new int[tamanho][];
        this.adjPeso = new long[tamanho][];
        this.grau = new int[tamanho];

        for (int i = 0; i < tamanho; i++) {
            adjDestino[i] = new int[4];
            adjPeso[i] = new long[4];
        }
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setAdjacente(int u, int v, long peso) {
        adicionar(u, v, peso);
        adicionar(v, u, peso);
    }

    private void adicionar(int u, int v, long peso) {
        if (grau[u] == adjDestino[u].length) {
            int novoTamanho = adjDestino[u].length * 2;
            adjDestino[u] = Arrays.copyOf(adjDestino[u], novoTamanho);
            adjPeso[u] = Arrays.copyOf(adjPeso[u], novoTamanho);
        }

        adjDestino[u][grau[u]] = v;
        adjPeso[u][grau[u]] = peso;
        grau[u]++;
    }

    public int getGrau(int u) {
        return grau[u];
    }
    public int getDestino(int u, int index) {
        return adjDestino[u][index];
    }
    public long getPeso(int u, int index) {
        return adjPeso[u][index];
    }
}
