package grafos;
import java.util.ArrayList;
import java.util.List;

public class Vertice<T> {
    private T valor;
    private List<Aresta<T>> arestas; // Lista de arestas conectadas a este vértice
    
    public Vertice(T valor){
        this.valor = valor;
        this.arestas = new ArrayList<>();
    }
    
    public T getValor() {
        return valor;
    }
    
    public void setValor(T valor) {
        this.valor = valor;
    }

    public List<Aresta<T>> getArestas() {
        return arestas;
    }
    
    public void addAresta(Aresta<T> a) {
        this.arestas.add(a);
    }
}
