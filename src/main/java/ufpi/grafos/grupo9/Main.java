package ufpi.grafos.grupo9;

public class Main {
    static void main() {
        Grafo g = new Grafo(5);
        g.setAdjacente(1, 2, 4);
        g.setAdjacente(2, 4, 2);
        g.setAdjacente(3, 4, 3);
        IO.println(g.getPeso(1, 2));
        IO.println(g.getPeso(2, 4));
        IO.println(g.getPeso(2, 3));
    }
}
