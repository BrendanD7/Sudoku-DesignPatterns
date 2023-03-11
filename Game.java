import java.util.Scanner;
public class Game{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        System.out.println("Select a board size: (4x4, 9x9, 12x12)");
        SudokuBoard board = new SudokuBoard(scan.nextInt());
        board.printBoard();
        System.out.println(board.isBoardValid());
    }
}