package app;
import grafos.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class RecService{

    private Grafo<Funcionario> rede;
    private Map<String, Funcionario> funcionariosMap; //buscar mais rapido pelo id, tipo um dict
    //private

    /*
    carregarDados
    detectarComunidades
    addNovaColaboracao
    addNovoFunc
    obterRecIndividual
    */

    public RecService(){
        this.rede = new Grafo<>();
        this.funcionariosMap = new HashMap<>();
    }

    public boolean addNovaColaboracao(long cpfFunc1, long cpfFunc2, int peso){

        if (cpfFunc1 == cpfFunc2) { //impede colaboracao de um funcionario com ele mesmo
            return false;
        }

        if (peso <= 0) { //peso deve representar ao menos 1 projeto em comum
            return false;
        }

        //busca os funcionarios ja cadastrados pelo CPF no map
        Funcionario func1 = funcionariosMap.get(String.valueOf(cpfFunc1));
        Funcionario func2 = funcionariosMap.get(String.valueOf(cpfFunc2));

        if (func1 == null || func2 == null) { //verifca se ambos vertices ja existem
            return false;
        }

        Vertice<Funcionario> vf1 = rede.buscarVertice(func1);
        Vertice<Funcionario> vf2 = rede.buscarVertice(func2);
        
        if (vf1 == null || vf2 == null) { //verifca se ambos vertices ja existem no grafo
            return false;
        }
        //verificar se ja existe colaboracao (aresta)
        for (Aresta<Funcionario> a: vf1.getArestas()){
            if (a.getDestino().equals(vf2)){
                a.setPeso(a.getPeso()+peso); //ja tinha colaboracao e atualizou o peso pro vertice de origem
                //atualizando o peso do vertice de destino
                for (Aresta<Funcionario> b: vf2.getArestas()){
                    if (b.getDestino().equals(vf1)){
                        b.setPeso(b.getPeso()+peso);
                        return true;
                    }
                }
            }
        }

        //nao existe: criar uma aresta nova
        return rede.addAresta(func1, func2, peso);
    }

    public boolean addNovoFunc(String nome, long cpf){
        //retorna false se ja existir no grafo
        Funcionario novo = new Funcionario(nome, cpf);
        funcionariosMap.put(String.valueOf(cpf), novo);
        return rede.addVertice(novo);
    }

    public boolean carregarDados(String caminhoArq) throws IOException{

        /*
        qtd funcs
        nome func1; cpf func1;
        nome func2; cpf func2;
        ...
        nome func<qtd funcs>; cpf func<qtd funcs>;
        colabs
        cpf func1;cpf func2; qtd colaboracoes (peso)
        ...
        cpf func2;cpf func3; qtd colaboracoes (peso)
        */
        String nome;
        long cpf = 0;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArq))){
            String linha;
            linha = reader.readLine();
            int qtdFuncs = Integer.parseInt(linha);//verif qtd de funcs cadastardos
            
            //lendo funcionários
            for (int i = 0; i < qtdFuncs; i++){
                linha = reader.readLine();
                String[] partes = linha.split(";");//separa as info do func
                nome = partes[0];
                cpf = Long.parseLong(partes[1]);

                Funcionario novo = new Funcionario(nome, cpf);
                funcionariosMap.put(String.valueOf(cpf), novo);
                rede.addVertice(novo);//add vertice com func ao grafo
            }

            //lendo colaboracoes
            long cpfFunc1;
            long cpfFunc2;
            int peso;
            while ((linha = reader.readLine()) != null){//le e verifica
                //cpfFunc1;cpfFunc2;peso
                String[] partes = linha.split(";");
                cpfFunc1 = Long.parseLong(partes[0]);
                cpfFunc2 = Long.parseLong(partes[1]);
                peso = Integer.parseInt(partes[2]);
                
                Funcionario f1 = funcionariosMap.get(String.valueOf(cpfFunc1));
                Funcionario f2 = funcionariosMap.get(String.valueOf(cpfFunc2));
                
                rede.addAresta(f1, f2, peso);
            }

            return true;

        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
            return false;
        }
    }


    public void detectarComunidades(){
        rede.detectarComunidade();
    }    

    public List<Funcionario> listarFuncs() {
        // Retorna todos os funcionários guardados no map como uma lista
        return new ArrayList<>(funcionariosMap.values());
    }

    public boolean funcExiste(long cpf) {
        //verifica se um funcionario com o cpf informado esta cadastrado
        return funcionariosMap.containsKey(String.valueOf(cpf));
    }

}