package grafos.grafos;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.HashMap;
import java.util.Map;


//grafo é de um tipo generico
public class Grafo<T> {
    private ArrayList<Vertice<T>> vertices; // Lista de vértices do grafo

    
    public Grafo() { //cria grafo vazio
        this.vertices = new ArrayList<>();
    }

    public Vertice<T> buscarVertice(T valor) { //buscar vertice no grafo pelo seu valor
        for (Vertice<T> v : vertices) {
            if (v.getValor().equals(valor)) {
                return v;
            }
        }
        return null;
    }

    public boolean existeVertice(T valor) { //verificar se o vertice com o vlaor ja existe
        return buscarVertice(valor) != null;
    }

    public ArrayList<Vertice<T>> getVertices() { //retorna lista de vertices no grafo
        return vertices;
    }

    public boolean addVertice(T valor) { //adiciona um vertice no grafo
      
        if (existeVertice(valor)) { //verifica se ja existe
            return false;
        }
        
        Vertice<T> novoVertice = new Vertice<>(valor);
        vertices.add(novoVertice);
        return true;
    }

    public boolean addAresta(T origem, T destino, float peso) { //adiciona uma aresta no grafo
        Vertice<T> vOrigem = buscarVertice(origem);
        Vertice<T> vDestino = buscarVertice(destino);
        
        if (vOrigem == null || vDestino == null) { //verifca se ambos vertices ja existem
            return false;
        }
        
        //grafo nao direcionado vai adicionar aresta em ambas direções
        Aresta<T> arestaOrigemDestino = new Aresta<>(vOrigem, vDestino, peso);
        Aresta<T> arestaDestinoOrigem = new Aresta<>(vDestino, vOrigem, peso);
        
        vOrigem.addAresta(arestaOrigemDestino);
        vDestino.addAresta(arestaDestinoOrigem);
        
        return true;
    }

    public void bfs(T valorInicial) {
        Vertice<T> inicial = buscarVertice(valorInicial);
        
        if (inicial == null) {
            System.out.println("Vértice inicial não encontrado!");
            return;
        }
        
        Map<Vertice<T>, Boolean> visitados = new HashMap<>(); //mapa para copntrole de vertices visistados
        for (Vertice<T> v : vertices) {
            visitados.put(v, false);
        }
        
        Queue<Vertice<T>> fila = new LinkedList<>(); //fila pra contrrolar ordem de visitacao
        
        visitados.put(inicial, true); //marca inicial como visitado e add na fila
        fila.add(inicial);
        
        System.out.println("=== Caminhamento em Largura (BFS) ===");
        
        while (!fila.isEmpty()) {
            Vertice<T> atual = fila.poll();
            System.out.print(atual.getValor() + " ");
            
            for (Aresta<T> a : atual.getArestas()) {//percorre todos os vizinhos do vertice atual
                Vertice<T> vizinho = a.getDestino();
                
                if (!visitados.get(vizinho)) { //se o vizinho nao foi visitado
                    visitados.put(vizinho, true);
                    fila.add(vizinho);
                }
            }
        }
        
        System.out.println("\n====================================");
    }

    
    public void imprimirGrafo() { //pra imprimir os vertices e conexoes
        System.out.println("\n========== ESTRUTURA DO GRAFO ==========");
        System.out.println("Total de vértices: " + vertices.size());
        
        for (Vertice<T> v : vertices) {
            System.out.print("\nVértice [" + v.getValor() + "] -> ");
            
            if (v.getArestas().isEmpty()) {
                System.out.print("(sem conexões)");
            } else {
                for (Aresta<T> a : v.getArestas()) {
                    System.out.print(a.getDestino().getValor() + "(peso:" + a.getPeso() + ") ");
                }
            }
        }
        
        System.out.println("\n========================================\n");
    }
    //codigo para o algoritmo de louvain
  public void detectarComunidade(){
        int n = vertices.size(); //numero de vertices
        if (n==0){
            System.out.println("Grafo vazio, nao ha comunidades a detectar.");
            return;
        }

        Map<Vertice<T>, Integer> vToIndex = new HashMap<>(); // De vertice para ID numérico
        Map<Integer, Vertice<T>> indexToV = new HashMap<>(); // De ID numérico para vertice        

        int[] comunidade = new int[n]; // Comunidade de cada vértice
        double[] totalPeso = new double[n]; // Peso total de cada comunidade
        double[] node_degree = new double[n]; // Grau de cada vértice
        double totalPesoGrafo = 0; // Peso total do grafo

        //no comeco cada vertice é considerado uma comunidade
        for(int i=0;i<n;i++){
            Vertice<T> v= vertices.get(i);

            //registra o mapeamento ida e volta
            vToIndex.put(v, i);
            indexToV.put(i, v);

            comunidade[i] = i; 

            double grau = 0;
            for(Aresta<T> a : v.getArestas()){
                grau += a.getPeso();
            }
            node_degree[i] = grau;
            totalPeso[i] = grau;
            totalPesoGrafo += grau;
        }

        totalPesoGrafo = totalPesoGrafo / 2.0; // Cada aresta é contada duas vezes entao precisa dividir
        boolean melhoria;
        int maxIteracoes = 100; // Limite de iterações para evitar loop infinito
        int iteracao = 0;
        
        do{
            melhoria = false; //se assume que ninguem vai se mover 
            iteracao++;
            
            // CORREÇÃO 1: A chave do for agora engloba toda a lógica de movimentação
            for(int i=0;i<n;i++){
                int ComunidaAtual = comunidade[i];
                Vertice<T> v = indexToV.get(i);

                // Remove o vértice de sua comunidade atual para cálculo correto do ganho
                double ki = node_degree[i];
                totalPeso[ComunidaAtual] -= ki;

                Map<Integer, Double> pesosCompartilhados = new HashMap<>(); // Peso das conexões com cada comunidade
                for(Aresta<T> a : v.getArestas()){
                    int vizinhoIndex = vToIndex.get(a.getDestino());
                    int comunidadeVizinho = comunidade[vizinhoIndex];

                    pesosCompartilhados.put(comunidadeVizinho, pesosCompartilhados.getOrDefault(comunidadeVizinho, 0.0) + a.getPeso());
                }

                int melhorComunidade = ComunidaAtual;
                double melhorGanho = 0.0;

                // Avalia TODAS as comunidades, incluindo a atual
                for (Map.Entry<Integer, Double> entrada : pesosCompartilhados.entrySet()) {
                    int comunidadeAlvo = entrada.getKey();
                    double pesoCompartilhado = entrada.getValue();
                    double totC = totalPeso[comunidadeAlvo]; // Peso total da comunidade alvo
                    double m = totalPesoGrafo; // Peso total do grafo

                    // Calcula o ganho de modularidade
                    double ganho = pesoCompartilhado - ((ki * totC) / (2.0 * m)); 
                    
                    if (ganho > melhorGanho) {
                        melhorGanho = ganho;
                        melhorComunidade = comunidadeAlvo;
                    }
                }
                
                // Reatribui o vértice à melhor comunidade
                totalPeso[melhorComunidade] += ki;
                
                if (melhorComunidade != ComunidaAtual){
                    comunidade[i] = melhorComunidade;
                    melhoria = true; // Houve uma melhoria, então continuaremos o processo
                }

            } // CORREÇÃO 1: Fim do FOR agora é aqui!

        } while(melhoria && iteracao < maxIteracoes);
        
        if (iteracao >= maxIteracoes) {
            System.out.println("Aviso: Limite de iterações (" + maxIteracoes + ") atingido no algoritmo de Louvain.");
        }

        System.out.println("\n========== GRUPOS RECOMENDADOS (LOUVAIN) ==========");
        Map<Integer, StringBuilder> grupos = new HashMap<>();
        
        // Agrupa as strings formatadas pelo ID da comunidade
        for (int i = 0; i < n; i++) {
            // CORREÇÃO 4: Nome do array consertado de community para comunidade
            int comId = comunidade[i];
            if (!grupos.containsKey(comId)) {
                grupos.put(comId, new StringBuilder());
            }
            // Usa o mapa indexToV para pegar o objeto original e imprimir seu valor (ex: o nome do funcionário)
            grupos.get(comId).append(indexToV.get(i).getValor().toString()).append(" | ");
        }
        
        // Imprime no console de forma limpa
        int cont = 1;
        for (StringBuilder sb : grupos.values()) {
            System.out.println("Comunidade " + cont + " -> [ " + sb.toString() + "]");
            cont++;
        }
        System.out.println("===================================================\n");
    }
}