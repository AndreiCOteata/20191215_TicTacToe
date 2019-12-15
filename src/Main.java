import java.util.Scanner;
public class Main {
    private static final int EmptyBoard = 0;
    private static final int PlayerX = 1;
    private static final int PlayerO = 2;
    private static final int Computer = 3;
    private static final int PLAYING = 0;
    private static final int DRAW = 1;
    private static final int PlayerX_WON = 2;
    private static final int PLayerO_WON = 3;
    private static final int Computer_WON = 4;
    private static final int Randuri = 3, Coloane = 3;
    private static int[][] board = new int[Randuri][Coloane];
    private static int currentState;
    private static int currentPlayer;
    private static int RandCurent, ColoanaCurenta;
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        initiateGame();
        System.out.println();
        do {
            mutarePlayer(currentPlayer);
            updateGame(currentPlayer, RandCurent, ColoanaCurenta);
            tabla();
            if (currentState == PlayerX_WON) {
                System.out.println("Player X won. Good Game! See ya!");
            } else if (currentState == PLayerO_WON) {
                System.out.println("Player O won. Good Game! See ya!");
            } else if (currentState == Computer_WON) {
                System.out.println("Computer has won. You suck! Bye.");
            } else if (currentState == DRAW) {
                System.out.println("Remiza! Good Game! Bye!");
            }
            currentPlayer = (currentPlayer == PlayerX) ? PlayerO : PlayerX;
        } while (currentState == PLAYING);
    }

    private static void initiateGame() {
        for (int row = 0; row < Randuri; row++) {
            for (int col = 0; col < Coloane; col++) {
                board[row][col] = EmptyBoard;
            }
            currentState = PLAYING;
            currentPlayer = firstPlayer();
        }
    }

    private static void mutarePlayer(int player) {
        boolean input = false;
        do {
            if (player == PlayerX) {
                System.out.println("Player X, choose you index move(rand(1-3) si coloana(1-3)):");
            } else {
                System.out.println("Player 0, choose you index move(rand(1-3) si coloana(1-3)):");
            }
            int row = in.nextInt() - 1;
            int col = in.nextInt() - 1;
            if (row >= 0 && row < Randuri && col >= 0 && col < Coloane && board[row][col] == EmptyBoard) {
                RandCurent = row;
                ColoanaCurenta = col;
                board[RandCurent][ColoanaCurenta] = player;
                input = true;
            } else {
                System.out.println("This move at (" + (row + 1) + "," + (col + 1) + ") is not valid. Try again...");
            }
        } while (!input);
    }

    private static boolean remiza() {
        for (int row = 0; row < Randuri; row++) {
            for (int col = 0; col < Coloane; col++) {
                if (board[row][col] == EmptyBoard) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean hasWon(int player, int currentRow, int currentCol) {
        return (board[0][currentCol] == player
                && board[1][currentCol] == player
                && board[2][currentCol] == player
                || board[currentRow][0] == player
                && board[currentRow][1] == player
                && board[currentRow][2] == player
                || currentRow == currentCol
                && board[0][0] == player
                && board[1][1] == player
                && board[2][2] == player
                || currentRow + currentCol == 2
                && board[0][2] == player
                && board[1][1] == player
                && board[2][0] == player);
    }

    private static void updateGame(int theSeed, int currentRow, int currentCol) {
        if (hasWon(theSeed, currentRow, currentCol)) {
            currentState = (theSeed == PlayerX) ? PlayerX_WON : PLayerO_WON;
        } else if (remiza()) {
            currentState = DRAW;
        }
    }

    private static void tabla() {
        for (int row = 0; row < Randuri; row++) {
            for (int col = 0; col < Coloane; col++) {
                cells(board[row][col]);
                if (col != Coloane - 1) {
                    System.out.print("||");
                }
            }
            System.out.println();
            if (row != Randuri - 1) {
                System.out.println("==============");
            }
        }
        System.out.println();
    }

    private static void cells(int content) {
        switch (content) {
            case EmptyBoard:
                System.out.print("   ");
                break;
            case PlayerO:
                System.out.print(" O ");
                break;
            case PlayerX:
                System.out.print(" X ");
                break;
        }
    }

    private static int firstPlayer() {
        int rand = (int) (Math.random() * 2 + 1);
        if (rand == 2) {
            return PlayerX;
        } else {
            return PlayerO;
        }
    }
}