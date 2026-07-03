package ufpi.grafos.grupo9;

import java.util.HashMap;
import java.util.Map;

public class MedidorDeTempo {
    private static final int QUANTIDADE_AMOSTRAS = 10;
    private static final int[] TAMANHOS = {10, 25, 50, 100, 250, 500, 1000, 1500, 2000, 5000, 10000, 20000};
    private static final long PESO_MAXIMO = 10000;
    private static final double DENSIDADE_PADRAO = 0.4;

    public MedidorDeTempo() {
        executarAquecimento();
    }

    public Map<Integer, Double> medirTempoPrimCompletos() {
        Prim prim = new Prim();
        return medirTemposAlgo(prim, true);
    }

    public Map<Integer, Double> medirTempoPrimIncompletos() {
        Prim prim = new Prim();
        return medirTemposAlgo(prim, false);
    }

    public Map<Integer, Double> medirTempoKruskalCompletos() {
        Kruskal kruskal = new Kruskal();
        return medirTemposAlgo(kruskal, true);
    }

    public Map<Integer, Double> medirTempoKruskalIncompletos() {
        Kruskal kruskal = new Kruskal();
        return medirTemposAlgo(kruskal, false);
    }

    private void executarAquecimento() {
        GeradorGrafos gerador = new GeradorGrafos();
        Prim prim = new Prim();
        Kruskal kruskal = new Kruskal();
        Grafo g = gerador.gerarGrafoCompleto(500, PESO_MAXIMO);
        for (int i = 0; i < 10; i++) {
            prim.gerarAGM(g);
            kruskal.gerarAGM(g);
        }
    }

    private Map<Integer, Double> medirTemposAlgo(AlgoritmoAGM algo, boolean isCompleto) {
        Map<Integer, Double> medias = new HashMap<>();
        GeradorGrafos gerador = new GeradorGrafos();
        for (var tamanho : TAMANHOS) {
            double tempoTotal = 0;
            Grafo[] grafosGerados = new Grafo[QUANTIDADE_AMOSTRAS];
            for (int i = 0; i < QUANTIDADE_AMOSTRAS; i++) {
                if (isCompleto) {
                    grafosGerados[i] = gerador.gerarGrafoCompleto(tamanho, PESO_MAXIMO);
                } else {
                    grafosGerados[i] = gerador.gerarGrafoIncompleto(tamanho, DENSIDADE_PADRAO, PESO_MAXIMO);
                }
            }
            for (int i = 0; i < QUANTIDADE_AMOSTRAS; i++) {
                long startTime = System.nanoTime();
                algo.gerarAGM(grafosGerados[i]);
                long endTime = System.nanoTime();
                tempoTotal += endTime - startTime;
            }
            medias.put(tamanho, (tempoTotal / QUANTIDADE_AMOSTRAS) / 1_000_000_000.0);
        }
        return medias;
    }
}
