package ufpi.grafos.grupo9;

import java.util.HashMap;
import java.util.Optional;

public class Grafo {
    private final int tamanho;
    private final HashMap<Integer, Integer>[] adjacencia;

    public Grafo(int tamanho) {
        this.tamanho = tamanho;
        this.adjacencia = new HashMap[tamanho];
        for (int i = 0; i < tamanho; i++) {
            this.adjacencia[i] = new HashMap<>();
        }
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setAdjacente(int v1, int v2, int peso) {
        adjacencia[v1].put(v2, peso);
        adjacencia[v2].put(v1, peso);
    }

    public void unsetAdjacente(int v1, int v2) {
        adjacencia[v1].remove(v2);
        adjacencia[v2].remove(v1);
    }

    public Optional<Integer> getPeso(int v1, int v2) {
        return Optional.ofNullable(adjacencia[v1].get(v2));
    }

    public HashMap<Integer, Integer> getAdjacentes(int v) {
        return adjacencia[v];
    }
}
