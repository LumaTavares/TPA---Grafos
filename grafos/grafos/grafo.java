package grafos.grafos;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.HashMap;
import java.util.Map;


//grafo é de um tipo generico
public class grafo<T> {
    private ArrayList<vertice<T>> vertices; // Lista de vértices do grafo

    
    public grafo() { //cria grafo vazio
        this.vertices = new ArrayList<>();
    }

    public vertice<T> buscarVertice(T valor) { //buscar vertice no grafo pelo seu valor
        for (vertice<T> v : vertices) {
            if (v.getValor().equals(valor)) {
                return v;
            }
        }
        return null;
    }

    public boolean existeVertice(T valor) { //verificar se o vertice com o vlaor ja existe
        return buscarVertice(valor) != null;
    }

    public ArrayList<vertice<T>> getVertices() { //retorna lista de vertices no grafo
        return vertices;
    }

   
    public boolean addVertice(T valor) { //adiciona um vertice no grafo
      
        if (existeVertice(valor)) { //verifica se ja existe
            return false;
        }
        
        vertice<T> novoVertice = new vertice<>(valor);
        vertices.add(novoVertice);
        return true;
    }

    public boolean addAresta(T origem, T destino, float peso) { //adiciona uma aresta no grafo
        vertice<T> vOrigem = buscarVertice(origem);
        vertice<T> vDestino = buscarVertice(destino);
        
        if (vOrigem == null || vDestino == null) { //verifca se ambos vertices ja existem
            return false;
        }
        
        //grafo nao direcionado vai adicionar aresta em ambas direções
        aresta<T> arestaOrigemDestino = new aresta<>(vOrigem, vDestino, peso);
        aresta<T> arestaDestinoOrigem = new aresta<>(vDestino, vOrigem, peso);
        
        vOrigem.addAresta(arestaOrigemDestino);
        vDestino.addAresta(arestaDestinoOrigem);
        
        return true;
    }

    public void bfs(T valorInicial) {
        vertice<T> inicial = buscarVertice(valorInicial);
        
        if (inicial == null) {
            System.out.println("Vértice inicial não encontrado!");
            return;
        }
        
        Map<vertice<T>, Boolean> visitados = new HashMap<>(); //mapa para copntrole de vertices visistados
        for (vertice<T> v : vertices) {
            visitados.put(v, false);
        }
        
        Queue<vertice<T>> fila = new LinkedList<>(); //fila pra contrrolar ordem de visitacao
        
        visitados.put(inicial, true); //marca inicial como visitado e add na fila
        fila.add(inicial);
        
        System.out.println("=== Caminhamento em Largura (BFS) ===");
        
        while (!fila.isEmpty()) {
            vertice<T> atual = fila.poll();
            System.out.print(atual.getValor() + " ");
            
            for (aresta<T> aresta : atual.getArestas()) {//percorre todos os vizinhos do vertice atual
                vertice<T> vizinho = aresta.getDestino();
                
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
        
        for (vertice<T> v : vertices) {
            System.out.print("\nVértice [" + v.getValor() + "] -> ");
            
            if (v.getArestas().isEmpty()) {
                System.out.print("(sem conexões)");
            } else {
                for (aresta<T> a : v.getArestas()) {
                    System.out.print(a.getDestino().getValor() + "(peso:" + a.getPeso() + ") ");
                }
            }
        }
        
        System.out.println("\n========================================\n");
    }
}

