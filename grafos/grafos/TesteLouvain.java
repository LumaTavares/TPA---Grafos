package grafos.grafos; // Certifique-se de que o package corresponde ao seu projeto

public class TesteLouvain {

    public static void main(String[] args) {
        
        // 1. Instanciar o grafo (os vértices serão Strings com os nomes dos colaboradores)
        Grafo<String> redeColaboradores = new Grafo<>();

        // 2. Adicionar os colaboradores (Vértices)
        // Equipa A (Ex: Engenharia)
        redeColaboradores.addVertice("Alice");
        redeColaboradores.addVertice("Bruno");
        redeColaboradores.addVertice("Carlos");
        
        // Equipa B (Ex: Marketing)
        redeColaboradores.addVertice("Diana");
        redeColaboradores.addVertice("Eduardo");
        redeColaboradores.addVertice("Fernanda");

        // 3. Adicionar as colaborações (Arestas ponderadas)
        // O peso representa a intensidade (ex: número de projetos em comum)

        // Ligações FORTES dentro da Equipa A
        redeColaboradores.addAresta("Alice", "Bruno", 5.0f);
        redeColaboradores.addAresta("Alice", "Carlos", 4.0f);
        redeColaboradores.addAresta("Bruno", "Carlos", 6.0f);

        // Ligações FORTES dentro da Equipa B
        redeColaboradores.addAresta("Diana", "Eduardo", 5.0f);
        redeColaboradores.addAresta("Diana", "Fernanda", 7.0f);
        redeColaboradores.addAresta("Eduardo", "Fernanda", 4.0f);

        // Ligação FRACA entre as equipas (Apenas 1 projeto em comum)
        // É aqui que o algoritmo de Louvain vai atuar para separar os grupos
        redeColaboradores.addAresta("Carlos", "Diana", 1.0f);

        // 4. (Opcional) Visualizar como o grafo está estruturado em memória
        System.out.println("A carregar a rede de colaboradores...");
        redeColaboradores.imprimirGrafo();

        // 5. Executar o Algoritmo de Louvain!
        System.out.println("\n\nA iniciar a análise de Modulariedade (Louvain)...");
        redeColaboradores.detectarComunidade();
    }
}