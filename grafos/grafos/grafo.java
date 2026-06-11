package grafos.grafos;
import java.util.ArrayList;
import java.util.List;

//grafo é de um tipo generico
public class grafo <T>{
    private ArrayList<T> vertices; //elemento
    private ArrayList<aresta> arestas;//relaçao

    public grafo() {
        this.vertices = new ArrayList<>();
        this.arestas = new ArrayList<>();
    }
    public void addVertice(T valor){
        this.vertices.add(valor);
    }
    public void addAresta(T origem, T destino){
        
    }
}

