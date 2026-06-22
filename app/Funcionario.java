package app;
//import colecao.IColecao;

public class Funcionario {
    private String nome;
    private long cpf;
    //private float salario;


    public Funcionario(String nome, long cpf){//, float salario){
        this.nome = nome;
        this.cpf = cpf;
        //this.salario = salario;
    }

    //
    public String getNome(){
        return this.nome;
    }

    public long getCpf(){
        return this.cpf;
    }

    //setters
    public void setNome(String novo_nome){
        this.nome = novo_nome;
    }

    public void setCpf(long novo_cpf){
        this.cpf = novo_cpf;
    }



    //tostring
    @Override
    public String toString(){
        return this.nome + "  -  " + Long.toString(this.cpf);
    }

    @Override
    public boolean equals(Object func){
        if (func instanceof Funcionario)
            return this.cpf==((Funcionario) func).cpf;
        else
            return false;
    }
    

}
