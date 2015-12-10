/**
 * Created by debbie on 12/10/15.
 */
public class PegPuzzle {

    public static void main(String[] args){
        int[][] board = new int[5][];
        board[0]= new int[1];
        board[1]= new int[2];
        board[2]= new int[3];
        board[3]= new int[4];
        board[4]= new int[5];

        board[0][0]=0;
        for(int i=1; i<board.length; i++){
            for(int j=0; j<board[i].length; j++){
                board[i][j]=1;
            }
        }

        printBoard(board);

    }

    public static void printBoard(int[][] board){
        int boardsize = board.length;
        for(int i=0; i<boardsize; i++){
            for(int n=0; n<boardsize-i; n++){
                System.out.print(" ");
            }
            for(int j=0; j<board[i].length; j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }

}
