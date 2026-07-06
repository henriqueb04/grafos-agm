package ufpi.grafos.grupo9;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Kruskal implements AlgoritmoAGM {
    private static class UnionFind {
        private final int[] pai;
        private final int[] altura;

        public UnionFind(int tamanho) {
            this.pai = new int[tamanho];
            this.altura = new int[tamanho];
            for (int i = 0; i < tamanho; i++) {
                pai[i] = i;
            }
        }
        public int find(int v) {
            if (pai[v] != v) {
                pai[v] = find(pai[v]);
            }
            return pai[v];
        }
        public boolean union(int v1, int v2) {
            int raiz1 = find(v1);
            int raiz2 = find(v2);
            if (raiz1 == raiz2) {
                return false;
            }
            if (altura[raiz1] < altura[raiz2]) {
                pai[raiz1] = pai[raiz2];
            } else if (altura[raiz1] > altura[raiz2]) {
                pai[raiz2] = pai[raiz1];
            } else {
                pai[raiz2] = raiz1;
                altura[raiz1]++;
            }
            return true;
        }
    }

    public String getNome() {
        return "Kruskal";
    }

    public Grafo gerarAGM(Grafo grafo) {
        int tamanho = grafo.getTamanho();

        List<long[]> arestas =  new ArrayList<>();
        for (var v1 = 0; v1 < tamanho; v1++){
            int grau = grafo.getGrau(v1);
            for (int i = 0; i < grau; i++){
                int v2 = grafo.getDestino(v1, i);
                if (v1 < v2){
                    arestas.add(new long[]{v1, v2, grafo.getPeso(v1, i)});

                }
            }
        }
        arestas.sort(Comparator.comparingLong(a -> a[2]));

        Grafo agm = new Grafo(tamanho);
        UnionFind uf = new UnionFind(tamanho);

        for(long[] aresta : arestas){
            int v1 = (int) aresta[0];
            int v2 = (int) aresta[1];
            long peso = aresta[2];
            if (uf.union(v1, v2)){
                agm.setAdjacente(v1, v2, peso);
            }
        }

        return agm;
    }
}
