/**
 * Deborah Ferguson
 * CS 315 Honors --  Algorithms
 * Department of Computer Science
 * University of Kentucky
 * December 17, 2015
 */

import java.awt.*;
import javax.swing.*;
import java.util.*;
public class PegPuzzle {

    //this contains the main algorithm and is used to solve the puzzle
    public static void main(String[] args){
        //the initial state
        State firststate = new State(5,3,2);
        boolean solvable = false;
        //uses a stack to implement a depth first search algorithm
        Stack<State> mystack = new Stack<State>();
        //adds the initial state
        mystack.push(firststate);
        State current=mystack.peek();
        //while there are still states in the stack
        while(!mystack.isEmpty()){
            //System.out.println(mystack.size());
            //look at the top element
            current = mystack.peek();
            //if it is the solution, stop now
            if(current.isFinal()){
                solvable=true;
                break;
            }
            //pop of top element
            mystack.pop();
            //create children
            ArrayList<State> children = current.childstates();
            //add each child to the stack
            for(int i=0; i<children.size(); i++){
                mystack.push(children.get(i));
            }
        }
        //working backwards to find the path to get to the solution
        State newcurrent=current;
        ArrayList<State> solutionpath = new ArrayList<State>();
        solutionpath.add(newcurrent);
        while(newcurrent.parent!=null){
            newcurrent=newcurrent.parent;
            solutionpath.add(0,newcurrent);
        }
        //print the path
        for(int i=0; i<solutionpath.size(); i++){
            solutionpath.get(i).printBoard();
        }
        //display whether or not it was solved
        if(solvable)
            System.out.println("Solvable");
        else
            System.out.println("Not Solvable");


        //This is the graphical aspect
        JFrame myframe = new JFrame();
        Panel mypanel = new Panel();
        mypanel.solutions=solutionpath;
        myframe.setSize(600,600);
        myframe.add(mypanel);
        myframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myframe.setVisible(true);

        mypanel.setBackground(Color.cyan);
    }

}
