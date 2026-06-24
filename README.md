# 📊 Projeto de Grafos - Sistema de Recomendação de Colaboradores

## 📝 Descrição

Este projeto implementa um sistema de recomendação de colaboradores baseado em grafos, desenvolvido como trabalho prático da disciplina de Teoria dos Grafos.

A aplicação modela uma rede de colaboração entre funcionários de uma empresa por meio de um grafo não-direcionado e ponderado, onde:

* Cada funcionário é representado por um vértice;
* Cada colaboração entre dois funcionários é representada por uma aresta;
* O peso da aresta corresponde à quantidade de projetos realizados em conjunto.

Para identificar grupos de colaboradores com históricos de trabalho semelhantes, o sistema utiliza o Algoritmo de Louvain para detecção de comunidades. Com base nas comunidades encontradas, são geradas recomendações de possíveis colaboradores.

---

## 🎯 Funcionalidades

* ✅ Cadastro de funcionários
* ✅ Registro de colaborações entre funcionários
* ✅ Representação de grafos por lista de adjacências
* ✅ Busca em Largura (BFS)
* ✅ Detecção de comunidades utilizando o Algoritmo de Louvain
* ✅ Recomendação de colaboradores com base nas comunidades identificadas
* ✅ Leitura de dados a partir de arquivo de entrada

---

## 🏗️ Estrutura do Projeto

```text
src/
└── app/
    ├── model/
    │   ├── domain/
    │   │   └── Funcionario.java
    │   └── grafos/
    │       ├── Aresta.java
    │       ├── Grafo.java
    │       ├── TesteLouvain.java
    │       └── Vertice.java
    ├── service/
    │   └── RecService.java
    └── view/
        └── Main.java
```

---

## 🚀 Como Executar

### 1. Compilar o projeto

Na raiz do projeto, execute:

```bash
javac -d bin src/app/model/domain/*.java src/app/model/grafos/*.java src/app/service/*.java src/app/view/*.java
```

Os arquivos compilados serão armazenados na pasta `bin`.

### 2. Executar a aplicação

Após a compilação, execute:

```bash
java -cp bin app.view.Main
```

### 3. Utilizar o sistema

Após iniciar a aplicação, utilize o menu interativo exibido no terminal para:

* Carregar dados de funcionários e colaborações;
* Registrar novas colaborações;
* Visualizar informações da rede;
* Detectar comunidades utilizando o algoritmo de Louvain;
* Obter recomendações de colaboradores.

> **Observação:** Os comandos acima foram testados considerando a estrutura de diretórios presente neste repositório.

---

## 📂 Formato do Arquivo de Entrada

O sistema utiliza arquivos de entrada contendo os funcionários e suas colaborações.

Exemplo:

```text
5

1;Ana;5000;11111111111
2;Bruno;6000;22222222222
3;Carlos;4500;33333333333
4;Daniela;7000;44444444444
5;Eduardo;5500;55555555555

4

1;2;5
1;3;3
2;3;7
4;5;4
```

### Funcionários

Cada linha possui o formato:

```text
id;nome;salario;cpf
```

### Colaborações

Cada linha possui o formato:

```text
idFuncionario1;idFuncionario2;peso
```

Onde o peso representa a quantidade de projetos realizados em conjunto pelos dois funcionários.

---

## 🧮 Algoritmos Implementados

### Busca em Largura (BFS)

* Complexidade: **O(V + E)**
* Aplicação: travessia e exploração do grafo.

### Algoritmo de Louvain

* Aplicação: detecção de comunidades em grafos ponderados.
* Objetivo: identificar grupos de funcionários com forte histórico de colaboração.
* Baseado na otimização da modularidade da rede.

---

## 📊 Representação do Grafo

O sistema utiliza **lista de adjacências**.

Características:

* Grafo não-direcionado;
* Grafo ponderado;
* Cada aresta é armazenada para ambos os vértices incidentes;
* Os pesos representam a intensidade da colaboração entre funcionários.

---

## 🎯 Estratégia de Recomendação

Após a identificação das comunidades pelo algoritmo de Louvain, o sistema recomenda possíveis colaboradores pertencentes à mesma comunidade que ainda não possuem histórico de colaboração direta.

A recomendação é baseada na premissa de que funcionários inseridos na mesma comunidade possuem maior potencial de colaboração devido à proximidade estrutural na rede.

---

## 👥 Autores

* Larissa Santos
* Luma Tavares
* Sofia Nascimento
