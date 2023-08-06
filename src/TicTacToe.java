import java.util.ArrayList;
import java.util.Scanner;

public class TicTacToe {

    public static void main(String[] args) {
        ArrayList<Integer> checkedPosition = new ArrayList<Integer>();
        char[][] gameBoard = {
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}
        };

        printGameBoard(gameBoard);

        char user = 'O';
        int moveCount = 0;

        while (true) {
            turnPlay(user, gameBoard, checkedPosition);
            printGameBoard(gameBoard);

            if (checkWin(gameBoard, user)) {
                System.out.println(user + " WINS!");
                break;
            }

            moveCount++;
            if (moveCount == 9) {
                System.out.println("It's a DRAW!");
                break;
            }

            user = (user == 'O') ? 'X' : 'O'; // Switch user turn
        }
    }

    public static void printGameBoard(char[][] gameBoard) {
        for (char[] row : gameBoard) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void turnPlay(char user, char[][] gameBoard, ArrayList<Integer> checkedPosition) {
        Scanner scan = new Scanner(System.in);
        int pos;

        while (true) {
            System.out.println("Enter your placement (1-9): ");
            pos = scan.nextInt();
            if (pos >= 1 && pos <= 9) {
                if (!checkedPosition.contains(pos)) {
                    checkedPosition.add(pos);
                    break;
                } else {
                    System.out.println("Position already taken. Try again!");
                }
            } else {
                System.out.println("Invalid input. Try again!");
            }
        }

        int row = (pos - 1) / 3 * 2;
        int col = (pos - 1) % 3 * 2;
        gameBoard[row][col] = user;
    }

    public static boolean checkWin(char[][] gameBoard, char user) {
        for (int i = 0; i < 5; i += 2) {
            if ((gameBoard[i][0] == user && gameBoard[i][2] == user && gameBoard[i][4] == user) || // Horizontal
                    (gameBoard[0][i] == user && gameBoard[2][i] == user && gameBoard[4][i] == user)) { // Vertical
                return true;
            }
        }
        return (gameBoard[0][0] == user && gameBoard[2][2] == user && gameBoard[4][4] == user) || // Diagonal
                (gameBoard[0][4] == user && gameBoard[2][2] == user && gameBoard[4][0] == user); // Reverse Diagonal
    }
}