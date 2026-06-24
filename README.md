# 📊 Projeto de Grafos - Detecção de Comunidades

Trabalho prático de implementação de estruturas de dados de grafos e algoritmo de Louvain para detecção de comunidades.

## 📝 Descrição

Este projeto implementa uma estrutura de grafo não-direcionado e ponderado usando **lista de adjacências**, com foco na aplicação do **Algoritmo de Louvain** para identificar grupos em redes sociais ou de colaboração.

## 🎯 Funcionalidades

- ✅ Criação de grafos com vértices genéricos
- ✅ Adição de arestas ponderadas
- ✅ Busca em Largura (BFS)
- ✅ Detecção de comunidades usando o Algoritmo de Louvain
- ✅ Visualização da estrutura do grafo

## 🏗️ Estrutura do Projeto

```
grafos/
└── grafos/
    ├── Vertice.java          # Representa um vértice do grafo
    ├── Aresta.java           # Representa uma aresta entre vértices
    ├── Grafo.java            # Estrutura principal e algoritmos
    └── TesteLouvain.java     # Exemplo de uso
```

## 🚀 Como Executar

1. Compile os arquivos:
```bash
javac grafos/grafos/*.java
```

2. Execute o teste:
```bash
java grafos.grafos.TesteLouvain
```

## 💡 Exemplo de Uso

```java
// Criar o grafo
Grafo<String> rede = new Grafo<>();

// Adicionar vértices
rede.addVertice("Alice");
rede.addVertice("Bruno");
rede.addVertice("Carlos");

// Adicionar arestas ponderadas
rede.addAresta("Alice", "Bruno", 5.0f);
rede.addAresta("Bruno", "Carlos", 6.0f);

// Visualizar o grafo
rede.imprimirGrafo();

// Executar BFS
rede.bfs("Alice");

// Detectar comunidades
rede.detectarComunidades();
```

## 🧮 Algoritmos Implementados

### Busca em Largura (BFS)
- **Complexidade:** O(V + E)
- **Uso:** Travessia do grafo por níveis

### Algoritmo de Louvain (Fase 1)
- **Complexidade:** O(E × I), onde I é o número de iterações
- **Uso:** Detecção de comunidades baseada em otimização de modularidade

## 📊 Representação do Grafo

O projeto utiliza **lista de adjacências** onde:
- Cada vértice mantém uma lista das arestas que saem dele
- Grafo não-direcionado: cada aresta é armazenada nas duas direções
- Suporte a pesos nas arestas para representar intensidade de conexões

## 🔍 Caso de Teste

O exemplo em `TesteLouvain.java` simula uma rede de colaboradores divididos em duas equipes:

- **Equipe A:** Alice, Bruno, Carlos (conexões fortes entre si)
- **Equipe B:** Diana, Eduardo, Fernanda (conexões fortes entre si)
- **Conexão fraca** entre as equipes (Carlos ↔ Diana)

O algoritmo de Louvain identifica corretamente os dois grupos distintos.

## 👥 Autores

Trabalho Prático de Algoritmos - Grafos

## 📚 Referências

- Blondel, V. D., et al. (2008). "Fast unfolding of communities in large networks"
- Cormen, T. H., et al. "Introduction to Algorithms"

---

**Nota:** Este projeto implementa a Fase 1 do algoritmo de Louvain (otimização local de modularidade), adequada para grafos de pequeno e médio porte.
