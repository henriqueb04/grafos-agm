package ufpi.grafos.grupo9;

import java.util.random.RandomGenerator;

public class GeradorGrafos {
    private final RandomGenerator random = RandomGenerator.getDefault();

    public Grafo gerarGrafoCompleto(int tamanho, long pesoMaximo) {
        Grafo grafo = new Grafo(tamanho);

        for (int i = 0; i < tamanho; i++) {
            for (int j = i + 1; j < tamanho; j++) {
                long peso = random.nextLong(1, pesoMaximo + 1);
                grafo.setAdjacente(i, j, peso);
            }
        }
        return grafo;
    }

    public Grafo gerarGrafoIncompleto(int tamanho, double densidade, long pesoMaximo) {
        Grafo grafo = new Grafo(tamanho);

        for (int i = 0; i < tamanho; i++) {
            for (int j = i + 1; j < tamanho; j++) {
                if (random.nextDouble() < densidade) {
                    long peso = random.nextLong(1, pesoMaximo + 1);
                    grafo.setAdjacente(i, j, peso);
                }
            }
        }
        return grafo;
    }
}