package ufpi.grafos.grupo9;

public class TesteGerador {
    public static void main(String[] args) {
        GeradorGrafos gerador = new GeradorGrafos();
    
        // 2. Teste do Grafo Completo
        int verticesCompleto = 4;
        System.out.println("\n[1] Gerando Grafo COMPLETO com " + verticesCompleto + " vértices...");
        Grafo gCompleto = gerador.gerarGrafoCompleto(verticesCompleto, 10);
        
        System.out.println("Peso entre 0 e 1: " + gCompleto.getPeso(0, 1).orElse(-1L));
        System.out.println("Peso entre 0 e 2: " + gCompleto.getPeso(0, 2).orElse(-1L));
        System.out.println("Peso entre 1 e 3: " + gCompleto.getPeso(1, 3).orElse(-1L));
        System.out.println("Peso entre 2 e 3: " + gCompleto.getPeso(2, 3).orElse(-1L));

        // 3. Teste do Grafo Incompleto
        int verticesIncompleto = 5;
        double densidade = 0.5; 
        System.out.println("\n[2] Gerando Grafo INCOMPLETO (" + verticesIncompleto + " vértices, densidade " + (densidade * 100) + "%)...");
        Grafo gIncompleto = gerador.gerarGrafoIncompleto(verticesIncompleto, densidade, 10);
        
        System.out.println("Vamos checar algumas conexões possíveis:");
        System.out.println("Peso entre 0 e 1: " + gIncompleto.getPeso(0, 1).map(String::valueOf).orElse("Sem aresta"));
        System.out.println("Peso entre 1 e 2: " + gIncompleto.getPeso(1, 2).map(String::valueOf).orElse("Sem aresta"));
        System.out.println("Peso entre 3 e 4: " + gIncompleto.getPeso(3, 4).map(String::valueOf).orElse("Sem aresta"));
        
    }
}