package grafos.grafos;

public class aresta {
    private vertice origem;
    private vertice destino;

    public aresta(vertice origem, vertice destino) {
        this.origem = origem;
        this.destino = destino;
    }

    public vertice getOrigem() {
        return origem;
    }

    public vertice getDestino() {
        return destino;
    }
}
