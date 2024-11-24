// Isaac de Oliveira Carvalho (isaac.dev.br) - MAPA ESOFT PROGRAMAÇÃO DE SISTEMAS II - RA: 23027292-5

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Inicializa o Scanner a Arraylist e o Int de Opção de Escolha do Usuário
        Scanner scanner = new Scanner(System.in);
        ArrayList<Cliente> clientes = new ArrayList<>();
        int option = 0;

        System.out.println("\nBem-vindo(a) ao CRM!");
        
        // Definimos a opção 6 como encerrar o programa, então a aplicação irá rodar até que option seja diferente de 6
        while (option!=6) {
            System.out.println("\n1 - Inserir cliente");
            System.out.println("2 - Listar clientes");
            System.out.println("3 - Buscar pelo ID");
            System.out.println("4 - Editar cliente");
            System.out.println("5 - Deletar Cliente");
            System.out.println("6 - Sair");
            System.out.print("\nDigite a opção desejada no menu: ");

            // Ao final do código criamos dois métodos para validar entrada de Inteiros e outro para validar entrada de Strings
            // sem causar interrupção no programa
            option = integerInput(scanner);

            // Switch case para implementação das opções escolhidas pelo usuário
            switch (option) {
                case 1: // Inserir Cliente
                    System.out.print("\nInsira o ID do cliente que será adicionado: ");
                    int idCliente = integerInput(scanner);

                    System.out.print("\nDigite o nome do cliente: ");
                    String nameCliente = stringInput(scanner);

                    System.out.print("\nDigite o email do cliente: ");
                    String emailCliente = stringInput(scanner);

                    System.out.print("\nDigite o telefone do cliente: ");
                    String phoneCliente = stringInput(scanner);
                    
                    // o método de adicionar retorna true se for sucesso, o mesmo serve para o Update e o Delete
                    if(addCliente(clientes, new Cliente(idCliente, nameCliente, emailCliente, phoneCliente))) {
                        System.out.print("\nCliente adicionado!");
                        waitUser(" [Enter para voltar ao Menu]");
                    }

                    break;

                case 2: // Listar Cliente
                    
                    listCliente(clientes);
                    System.out.println();
                    
                    break;

                case 3: // Buscar Cliente
                    System.out.print("\nDigite o ID do Cliente desejado para fazer a busca: ");
                    int searchId = integerInput(scanner);
                    searchCliente(clientes, searchId);
                
                    break;

                case 4: // Editar Cliente

                    System.out.print("\nInsira o ID do cliente que será editado: ");
                    int idClienteEdit= integerInput(scanner);

                    System.out.print("\nNome do Cliente (ou deixe em branco para manter o atual): ");
                    String nameClienteEdit = stringInput(scanner);

                    System.out.print("\nEmail do cliente (ou deixe em branco para manter o atual): ");
                    String emailClienteEdit = stringInput(scanner);

                    System.out.print("\nTelefone do cliente (ou deixe em branco para manter o atual): ");
                    String phoneClienteEdit = stringInput(scanner);
                    
                    if(updateCliente(clientes, idClienteEdit, nameClienteEdit, emailClienteEdit, phoneClienteEdit)) {
                        System.out.print("\nCliente alterado com sucesso!");
                        waitUser(" [Enter para voltar ao Menu]");
                    }
                    
                    break;

                case 5: // Deletar Cliente
                    System.out.print("\nDigite o ID do Cliente desejado para fazer a exclusão: ");
                    int deleteID = integerInput(scanner);
                    
                    if(deleteCliente(clientes, deleteID, scanner)) {
                        System.out.print("\nCliente removido!");
                        waitUser(" [Enter para voltar ao Menu]");
                    }
                
                    break;
            
                default: // O programa continua rodando se digitar outro numero fora desse intervalo
                    break;
            }
            
        }
        
    }


    // Métodos do Crud

    // Create
    public static Boolean addCliente(ArrayList<Cliente> listaClientes, Cliente cliente) {

        // Verifica se já não tem um id na lista antes de adicionar, garantindo a chave única.
        for (Cliente c : listaClientes) {
            if (c.getId() == cliente.getId()) {
                System.out.println("\nJá existe um cliente com este ID");
                waitUser("[Enter para voltar ao Menu]");
                return false;
            }
        }
        return listaClientes.add(cliente);
    }

    // Read
    public static void listCliente(ArrayList<Cliente> listaClientes) {

        // Percorre a lista chamando o método de printInfo de cada cliente.
        System.out.println("\n#ID | Nome | Email | Telefone");
        for (Cliente c : listaClientes) {
            c.printInfo();
        } 

        if(listaClientes.isEmpty()) {
            System.out.println("Lista Vazia.");
        }
        System.out.println();
        waitUser("[Enter para voltar ao Menu]");
    }

    // Update
    public static Boolean updateCliente(ArrayList<Cliente> listaClientes, int id, String name, String email, String phone) {

        // Verifica na lista se existe o cliente com o ID especificado, e deleta da lista.
        for (Cliente c : listaClientes) {
            if (c.getId() == id) {

                // Implementamos dessa forma para caso o usuário deixe o campo em branco, este campo não será alterado
                if(!name.isBlank())
                    c.setName(name);

                if(!email.isBlank())
                    c.setEmail(email);

                if(!phone.isBlank())
                    c.setPhone(phone);
                return true;
            }
        }

        System.out.print("\nNão foi encontrado um cliente com este ID na Lista");
        waitUser(" [Enter para voltar ao Menu]");
        return false;
    }

    // Delete
    public static Boolean deleteCliente(ArrayList<Cliente> listaClientes, int id, Scanner scanner) {

        // Verifica na lista se existe o cliente com o ID especificado, e deleta da lista.
        for (Cliente c : listaClientes) {
            if (c.getId() == id) {
                System.out.println("\nConfirme a Exclusão do Cliente [Y/N]");
                c.printInfo();

                // Implementação de um método de confirmação, requer que usuário Digite Y ou N para confirmar.
                // Utilizou o método de comparação que ignora se foi em maiusculo ou minusculo

                String confirmation = "";

                while (!confirmation.equalsIgnoreCase("Y") && !confirmation.equalsIgnoreCase("N")) {
                    confirmation = stringInput(scanner);
                    
                    if(confirmation.equalsIgnoreCase("Y"))
                        return listaClientes.remove(c);
                }
            }
        }

        // Idependente se foi por cancelamento ou se não encontrou o ID, exibe a mensagem
        System.out.print("\nCliente não removido");
        waitUser(" [Enter para voltar ao Menu]");
        return false;
    }

    // Search
    public static Cliente searchCliente(ArrayList<Cliente> listaClientes, int id) {

        // Verifica na lista se existe o cliente com o ID especificado, e retorna o objeto Cliente.
        for (Cliente c : listaClientes) {
            if (c.getId() == id) {
                System.out.println("\nCliente Econtrado.\n#ID | Nome | Email | Telefone");
                c.printInfo();
                System.out.println();
                waitUser("[Enter para voltar ao Menu]");
                return c;
            }
        }
        System.out.print("\nNão foi encontrado um cliente com este ID na Lista");
        waitUser(" [Enter para voltar ao Menu]");
        return null;
    }

    // Um método para garantir que a entrada do usuário vai ser um Int
    private static int integerInput(Scanner scanner) {
        int number;
        while (true) {
            try {
                number = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Por favor, insira apenas um número: ");
            }
        }
        return number;
    }

     // Um método para garantir que a entrada do usuário vai ser uma String
    private static String stringInput(Scanner scanner) {
        String text;
        while (true) {
            try {
                text = scanner.nextLine();
                break;
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Digite o dado corretamente: ");
            }
        }
        return text;
    }

    // Para melhorar a interação com usuário foi cirado um método que aguarda um Input qualquer para seguir para o próximo passo
    private static void waitUser(String message) {
        System.out.println(message);
        try{System.in.read();}
        catch(Exception e){}

    }

}
