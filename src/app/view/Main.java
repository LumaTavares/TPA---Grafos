package app.view;
import java.util.Scanner;
import app.service.*;

public class Main {
    public static void main(String[] args) {

        RecService serv = new RecService();
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        System.out.println("*******************************************");
        System.out.println("      Recomendação de Colaboradores");
        System.out.println("\n\n\nCarregando rede de projetos...\n");
        
        String path = "entrada.txt";
        try {
            serv.carregarDados(path);
        } catch (Exception e) {
            System.out.println("Aviso: Inicializando sem arquivo base. Use o menu para inserir dados manuais.\n");
        }

        do{
            System.out.println("Escolha uma opcão:");
            System.out.println("1 - Cadastrar funcionário");
            System.out.println("2 - Adicionar colaboração");
            System.out.println("3 - Detectar comunidades");
            System.out.println("4 - Encerrar programa");
            System.out.print(">>> ");
 
            try{
                opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1://cadastrar funcionario
                        System.out.print("Nome: "); String nomeFunc = scanner.nextLine();
                        System.out.print("CPF: "); long cpfFunc = Long.parseLong(scanner.nextLine());
                        if (serv.addNovoFunc(nomeFunc, cpfFunc))
                            System.out.println("Funcionário cadastrado com sucesso!\n");
                        else
                            System.out.println("Funcionário já cadastrado (CPF duplicado).\n");
                        break;

                    case 2://adicionar colaboracao entre funcionarios ja cadastrados
                        //lista os funcionarios antes para o usuario escolher pelos CPFs
                        serv.listarFuncs();
                        if (serv.getQtdFuncionarios(    ) < 2){
                            System.out.println("É necessário ao menos 2 funcionários cadastrados para adicionar uma colaboração.\n");
                            break;
                        }

                        System.out.print("CPF do Colaborador A: "); long cpf1 = Long.parseLong(scanner.nextLine());
                        System.out.print("CPF do Colaborador B: "); long cpf2 = Long.parseLong(scanner.nextLine());

                        if (cpf1 == cpf2){
                            System.out.println("Erro: os dois CPFs são iguais. Um funcionário não pode colaborar com ele mesmo.\n");
                            break;
                        }

                        System.out.print("Quantidade de projetos em comum (Peso): "); int peso = Integer.parseInt(scanner.nextLine());

                        if (peso <= 0){
                            System.out.println("Erro: Insira uma quantidade válida.\n");
                            break;
                        }

                        if (serv.addNovaColaboracao(cpf1, cpf2, peso))
                            System.out.println("Nova conexão adicionada ao grafo!\n");
                        else {
                            //identifica qual dos CPFs nao foi encontrado
                            if (!serv.funcExiste(cpf1))
                                System.out.println("Erro: CPF " + cpf1 + " não encontrado.\n");
                            else if (!serv.funcExiste(cpf2))
                                System.out.println("Erro: CPF " + cpf2 + " não encontrado.\n");
                            else
                                System.out.println("Erro: um ou ambos os CPFs não foram encontrados.\n");
                        }
                        break;

                    case 3://detectar comunidades
                        serv.detectarComunidades();
                        break;
                    case 4:
                        System.out.println("Encerrando o programa.");
                        break;
                    default:
                        System.out.println("Opção inválida! Tente novamente\n");
                }

                } catch (Exception e){
                    System.out.println("Erro ao processar operação: " + e.getMessage());
                }
            } while (opcao != 4);


            scanner.close();

            }
        }