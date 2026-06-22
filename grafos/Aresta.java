package grafos;

public class Aresta<T> {
    private Vertice<T> origem;
    private Vertice<T> destino;
    private float peso; // peso da aresta 

    public Aresta(Vertice<T> origem, Vertice<T> destino, float peso) {
        this.origem = origem;
        this.destino = destino;
        this.peso = peso;
    }

    public Vertice<T> getOrigem() {
        return origem;
    }

    public Vertice<T> getDestino() {
        return destino;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }
}
