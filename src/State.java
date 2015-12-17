/**
 * Created by debbie on 12/16/15.
 */

import java.util.*;

public class State {
    public int[][] board;
    public State parent;

    //constructor an initial state
    public State(int size, int igap, int jgap){
        board = new int[size][];
        for(int i=0; i<size; i++){
            board[i]=new int[i+1];
        }
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[i].length; j++){
                if(i==igap&&j==jgap)
                    board[i][j]=0;
                else
                    board[i][j]=1;
            }
        }
        parent = null;

    }

    //constructor for other states
    public State(int[][] boardvalue, State p){
        board=boardvalue;
        parent = p;
    }

    //accessor for board
    public int[][] getBoard(){
        return board;
    }

    //print the board
    public void printBoard(){
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

    //find children states
    public ArrayList<State> childstates(){
        int size = board.length;
        ArrayList<State> children = new ArrayList<State>();
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[i].length; j++){
                //check neighbors for possible jumps
                //currently set up for board of size 5


                //[i+1][j], [i+2][j]
                //[i+1][j+1], [i+2][j+2]
                if(board[i][j]==1){
//                  System.out.println(i);
//                  System.out.println(j);
                    if(i<size-2){
                        if(j<(board[i].length)-2) {
                            if (board[i + 1][j + 1] == 1 && board[i + 2][j + 2] == 0){
//                                System.out.println("had a child");
                                int[][] childstate1 = new int[size][];
                                for(int a=0; a<size; a++){
                                    childstate1[a]=new int[a+1];
                                }
                                for(int a=0; a<board.length; a++) {
                                    for (int b = 0; b < board[a].length; b++) {
                                        childstate1[a][b] = board[a][b];
                                    }
                                }
                                childstate1[i][j] = 0;
                                childstate1[i+1][j+1] = 0;
                                childstate1[i+2][j+2] = 1;
                                children.add(new State(childstate1, this));
                            }
                        }
                        if (board[i + 1][j] == 1 && board[i + 2][j] == 0){
//                            System.out.println("had a child");
                            int[][] childstate2 = new int[size][];
                            for(int a=0; a<size; a++){
                                childstate2[a]=new int[a+1];
                            }
                            for(int a=0; a<board.length; a++) {
                                for (int b = 0; b < board[a].length; b++) {
                                    childstate2[a][b] = board[a][b];
                                }
                            }
                            childstate2[i][j] = 0;
                            childstate2[i+1][j] = 0;
                            childstate2[i+2][j] = 1;
                            children.add(new State(childstate2,this));
                        }
                    }
                    //[i-1][j], [i-2][j]
                    //[i-1][j-1], [i-2][j-2]

                    if(i>=2){
                        if(j>=2){
                            if (board[i - 1][j - 1] == 1 && board[i - 2][j - 2] == 0) {
//                                System.out.println("had a child");
                                int[][] childstate3 = new int[size][];
                                for(int a=0; a<size; a++){
                                    childstate3[a]=new int[a+1];
                                }
                                for(int a=0; a<board.length; a++) {
                                    for (int b = 0; b < board[a].length; b++) {
                                        childstate3[a][b] = board[a][b];
                                    }
                                }
                                childstate3[i][j] = 0;
                                childstate3[i - 1][j - 1] = 0;
                                childstate3[i - 2][j - 2] = 1;
                                children.add(new State(childstate3,this));
                            }
                        }
                        if(j<=i-2) {
                            if (board[i - 1][j] == 1 && board[i - 2][j] == 0) {
//                                System.out.println("had a child");
                                int[][] childstate4 = new int[size][];
                                for (int a = 0; a < size; a++) {
                                    childstate4[a] = new int[a + 1];
                                }
                                for (int a = 0; a < board.length; a++) {
                                    for (int b = 0; b < board[a].length; b++) {
                                        childstate4[a][b] = board[a][b];
                                    }
                                }
                                childstate4[i][j] = 0;
                                childstate4[i - 1][j] = 0;
                                childstate4[i - 2][j] = 1;
                                children.add(new State(childstate4,this));
                            }
                        }
                    }
                    //[i][j+1], [i][j+2]
                    //[i][j-1], [i][j-2]
                    if(j<(board[i].length)-2){
                        if (board[i][j + 1] == 1 && board[i][j + 2] == 0) {
//                            System.out.println("had a child");
                            int[][] childstate5 = new int[size][];
                            for(int a=0; a<size; a++){
                                childstate5[a]=new int[a+1];
                            }
                            for(int a=0; a<board.length; a++) {
                                for (int b = 0; b < board[a].length; b++) {
                                    childstate5[a][b] = board[a][b];
                                }
                            }
                            childstate5[i][j] = 0;
                            childstate5[i][j + 1] = 0;
                            childstate5[i][j + 2] = 1;
                            children.add(new State(childstate5,this));
                        }
                    }
                    if(j>=2) {
                        if (board[i][j - 1] == 1 && board[i][j - 2] == 0) {
//                            System.out.println("had a child");
                            int[][] childstate6 = new int[size][];
                            for(int a=0; a<size; a++){
                                childstate6[a]=new int[a+1];
                            }
                            for(int a=0; a<board.length; a++) {
                                for (int b = 0; b < board[a].length; b++) {
                                    childstate6[a][b] = board[a][b];
                                }
                            }
                            childstate6[i][j] = 0;
                            childstate6[i][j - 1] = 0;
                            childstate6[i][j - 2] = 1;
                            children.add(new State(childstate6,this));
                        }
                    }
                }
            }
        }

        return children;
    }

    public boolean isFinal(){
        int sum=0;
        boolean finished = false;
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[i].length; j++){
                sum+=board[i][j];
                if(sum>1)
                    break;
            }
        }
        if(sum==1){
            finished = true;
        }
        return finished;
    }

}

