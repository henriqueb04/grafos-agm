package ufpi.grafos.grupo9;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Prim implements AlgoritmoAGM {
    private record ArestaCandidata(int origem, int destino, long peso){}
    public String getNome() {
        return "Prim";
    }
    public Grafo gerarAGM(Grafo grafo){
        int tamanho = grafo.getTamanho();
        Grafo agm = new Grafo(tamanho);
        boolean[] visitado = new boolean[tamanho];
        PriorityQueue<ArestaCandidata> fila = new PriorityQueue<>(
              Comparator.comparingLong(ArestaCandidata::peso)
        );
        if (tamanho == 0){
            return agm;
        }
        int inicio = 0;
        visitado[inicio] = true;
        adicionarArestaDoVertice(grafo, inicio, visitado, fila);
        int arestasAdicionadas = 0;
        while (!fila.isEmpty() && arestasAdicionadas < tamanho -1){
            ArestaCandidata candidata = fila.poll();
            if (visitado[candidata.destino()]){
                continue;
            }
            agm.setAdjacente(candidata.origem(), candidata.destino(), candidata.peso());
            arestasAdicionadas++;
            visitado[candidata.destino()] = true;
            adicionarArestaDoVertice(grafo, candidata.destino(), visitado, fila);
        }
        return agm;
    }
    private void adicionarArestaDoVertice(Grafo grafo, int v, boolean[] visitado, PriorityQueue<ArestaCandidata> fila){
        int grau = grafo.getGrau(v);
        for (int i = 0; i < grau; i++){
            int vizinho = grafo.getDestino(v, i);
            if (!visitado[vizinho]){
                fila.add(new ArestaCandidata(v, vizinho, grafo.getPeso(v, i)));
            }
        }
    }
}
