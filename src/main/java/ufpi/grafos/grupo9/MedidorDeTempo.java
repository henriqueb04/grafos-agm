package ufpi.grafos.grupo9;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class MedidorDeTempo {
    private static final int QUANTIDADE_AMOSTRAS = 10;
    private static final int[] TAMANHOS = {250, 500, 1000, 1250, 1500, 1750, 2000, 2500, 3000, 3500, 4000, 4500, 5000, 6000};
    private static final long PESO_MAXIMO = 10000;
    private static final double DENSIDADE_PADRAO = 0.05;

    public MedidorDeTempo() {
        IO.println("Aquecendo...");
        executarAquecimento();
    }

    public Map<Integer, Double> medirTempoPrimCompletos() {
        Prim prim = new Prim();
        return medirTemposAlgo(prim, true);
    }

    public Map<Integer, Double> medirTempoPrimNaoCompletos() {
        Prim prim = new Prim();
        return medirTemposAlgo(prim, false);
    }

    public Map<Integer, Double> medirTempoKruskalCompletos() {
        Kruskal kruskal = new Kruskal();
        return medirTemposAlgo(kruskal, true);
    }

    public Map<Integer, Double> medirTempoKruskalNaoCompletos() {
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
        Map<Integer, Double> medianas = new HashMap<>();
        GeradorGrafos gerador = new GeradorGrafos();
        IO.println("=======================");
        IO.println("ALGORITMO: " + algo.getNome());
        IO.println("=======================");
        for (var tamanho : TAMANHOS) {
            IO.println("Tamanho: " + tamanho);
            ArrayList<Double> tempos = new ArrayList<>();
            for (int i = 0; i < QUANTIDADE_AMOSTRAS; i++) {
                Grafo g;
                if (isCompleto) {
                    g = gerador.gerarGrafoCompleto(tamanho, PESO_MAXIMO);
                } else {
                    g = gerador.gerarGrafoNaoCompleto(tamanho, DENSIDADE_PADRAO, PESO_MAXIMO);
                }
                IO.print("Tentativa " + (i+1) + ": ");
                long startTime = System.nanoTime();
                algo.gerarAGM(g);
                long endTime = System.nanoTime();
                double tempo = (double) (endTime - startTime) / 1_000_000_000.0;
                IO.println(tempo + "s");
                tempos.add(tempo);
                g = null;
            }
            tempos.sort(Comparator.naturalOrder());
            double mediana;
            if ((QUANTIDADE_AMOSTRAS & 1) != 0) {
                mediana = tempos.get(QUANTIDADE_AMOSTRAS/2);
            } else {
                mediana = (tempos.get(QUANTIDADE_AMOSTRAS/2 - 1) + tempos.get(QUANTIDADE_AMOSTRAS/2)) / 2;
            }
            medianas.put(tamanho, mediana);
        }
        return medianas;
    }
}
