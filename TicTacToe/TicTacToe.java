// Isaac de Oliveira Carvalho (isaac.dev.br) - MAPA ESOFT PROGRAMAÇÃO DE SISTEMAS I - RA: 23027292-5


import java.util.Arrays;
import java.util.Scanner; // Importar a biblioteca Scanner, para receber o input do usuário.

public class TicTacToe {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Declarando a variável que representa o jogo, e inicializando a com o método fill
        // O caractere especial escolhido foi * para nao confundir com o traço do jogo da velha
        char[][] game = new char[3][3];
        

        Boolean player1Turn = true;
        Boolean validPlay = false;
        Boolean gameFinished = false;

        initGame(game); // Inicializa a Matriz do Jogo e Mostra o Jogo Inicial
        
        while (!gameFinished) {
            
            // Verifica qual turno está, indicando para o jogador
            if(player1Turn) {
                System.out.println("Jogador 1 - Digite a posição no tabuleiro");
            } else {
                System.out.println("Jogador 2 - Digite a posição no tabuleiro");
            }

            // Solicita a entrada de um inteiro por vez, para a linha e para a coluna, respectivamente.
            System.out.print("Linha: ");
            int inputI = userInput(scanner);
            System.out.print("Coluna: ");
            int inputJ = userInput(scanner);
            
            // Tenta inserir o simbolo no jogo, se for válido, o método retornará True. Aqui já fazemos uma verificação do turno para poder definir qual simbolo será inserido
            validPlay = insertOnGame(game, new int[]{inputI-1, inputJ-1} , player1Turn ? 'X': 'O');
            
            gameFinished = gameFinished(game, player1Turn); // Verifica o status do jogo

            // Se a jogada foi válida e o jogo ainda não acabou, inverte o turno do jogador.
            if(validPlay && !gameFinished) {
                player1Turn = !player1Turn;
            }

            // Se o jogo acabar, pergunta se quer jogar novamente. Caso sim, chama o método Init
            // Não resetaremos a variável player1Turn, dessa forma, quem ganhou começa na próxima rodada.
            if(gameFinished) {
                System.out.print("Jogo Finalizado! Deseja jogar novamente? Sim = 1, Não = 0");
                int input = userInput(scanner);
                if(input == 1) {
                    gameFinished = false;
                    initGame(game);
                }
            }
        }
    }

    private static void initGame(char[][] game) {
        Arrays.fill(game[0], '*');
        Arrays.fill(game[1], '*');
        Arrays.fill(game[2], '*');
        printGame(game); // Mostra o Jogo Inicial
    }

    // Aqui criamos um método para inserir o simbolo no jogo, na determinada posição respresentada pelo vetor position {i, j} como em uma matriz.
    private static Boolean insertOnGame(char[][] game, int[] position, char symbol ) {
        
        // Validando se a entrada esta no limite do tabuleiro
        if(position[0]< game.length && position[1]< game.length) { 

            // Validando se a célula já não está ocupada
            if(game[position[0]][position[1]]=='*') {
                game[position[0]][position[1]] = symbol;
                printGame(game);
                return true;
            } else {
                System.out.println("\nEsta posição já foi ocupada");
                return false;
            }
            
        } else {
            System.out.println("\nVocê deve digitar uma posição válida dentro do tabuleiro");
            return false;
        }
  
    }

    private static Boolean gameFinished(char[][] game, boolean player1Turn) {
        
        // Criamos um gameResultCode para exibir corretamente se o jogo ganhou ou deu velha.
        int gameResultCode = player1Turn? 1 : 2;
        
        // Verifica uma diagonal
        if(game[0][0] != '*' && game[0][0] == game[1][1] && game[1][1] == game[2][2]) {
            printGameResult(gameResultCode);
            return true;
        }

        // Verifica outra diagonal
        if(game[0][2] != '*' && game[0][2] == game[1][1] && game[1][1] == game[2][0]) {
            printGameResult(gameResultCode);
            return true;
        }

        // Verifica as colunas
        for(int i = 0; i < game.length; i++) {

            // Verifica cada linha, Se a linha não tiver vazia e elas forem todas iguais, alguém ganhou.
            if(game[i][0] != '*' && game[i][0] == game[i][1] && game[i][1] == game[i][2]) {
                printGameResult(gameResultCode);
                return true;
            }

            // Verifica cada coluna, Se a coluna não tiver vazia e elas forem todas iguais, alguém ganhou.
            if(game[0][i] != '*' && game[0][i] == game[1][i] && game[1][i] == game[2][i]) {
                printGameResult(gameResultCode);
                return true;
            }

            
        }

        for(int i = 0; i < game.length; i++) {
            for(int j = 0; j < game.length; j++) {
                if(game[i][j] == '*')
                    return false; // Se tiver qualquer '*' quer dizer que o jogo ainda não acabou.
            }
        }

        printGameResult(-1);
        return true; // Se o código chegar aqui, quer dizer que deu Velha.
    }

    // Função para exibir o jogo
    private static void printGame(char[][] game) {
        int i = 0; // O i vai ser implementado para impedir de colocar o traço horizontal na última linha
        System.out.println();
        for(char[] row: game) {
            i++;
            System.out.print(" " + row[0] + " | ");
            System.out.print(row[1] + " | ");
            System.out.println(row[2]);
            if(i<game.length) { 
                System.out.println("-----------");
            }
        }
        System.out.println();
    }

    // Um método para garantir que a entrada do usuário vai ser um Int
    private static int userInput(Scanner scanner) {
        int number;
        while (true) {
            try {
                number = Integer.parseInt(scanner.next());
                break;
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Por favor, insira apenas um número: ");
            }
        }
        return number;
    }

    public static void printGameResult(int resultCode) {
        switch (resultCode) {
            case 1:
                System.out.println("Jogador 1 Ganhou o Jogo!");
                break;
            case 2:
                System.out.println("Jogador 2 Ganhou o Jogo!");
                break;
            default:
                System.out.println("Velha"); 
                break;
        }
    }
}
