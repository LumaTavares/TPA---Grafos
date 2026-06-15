package grafos.grafos;

public class aresta<T> {
    private vertice<T> origem;
    private vertice<T> destino;
    private float peso; // peso da aresta 

    public aresta(vertice<T> origem, vertice<T> destino, float peso) {
        this.origem = origem;
        this.destino = destino;
        this.peso = peso;
    }

    public vertice<T> getOrigem() {
        return origem;
    }

    public vertice<T> getDestino() {
        return destino;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }
}
