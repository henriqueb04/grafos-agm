package ufpi.grafos.grupo9;

public class Prim implements AlgoritmoAGM {
    public Grafo gerarAGM(Grafo grafo) {
        // Rascunho
        Grafo grafo2 = new Grafo(grafo.getTamanho());
        try {
            Thread.sleep(grafo.getTamanho() * 10L);
        } catch (Exception _) {}
        return grafo2;
    }
}
