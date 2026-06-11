package grafos.grafos;
import java.util.ArrayList;
import java.util.List;

public class vertice<T> {
    private T valor;
    private List<aresta> arestas; // Lista de arestas conectadas a este vértice
    
    public vertice(T valor){
        this.valor = valor;
        this.arestas = new ArrayList<>();
    }
    public T getValor() {
        return valor;
    }
    public void setValor(T valor) {
        this.valor = valor;
    }

    public List<aresta> getArestas() {
        return arestas;
    }
    public void addAresta(aresta a) {
        this.arestas.add(a);
    }
}
