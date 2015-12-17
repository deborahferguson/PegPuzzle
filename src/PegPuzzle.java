/**
 * Created by debbie on 12/10/15.
 */

import oracle.jrockit.jfr.JFR;
import java.awt.*;
import javax.swing.*;
import java.util.*;
public class PegPuzzle {

    public static void main(String[] args){
        State firststate = new State(5,0,0);
        boolean solvable = false;

        Stack<State> mystack = new Stack<State>();
        mystack.push(firststate);
        State current=mystack.peek();
        while(!mystack.isEmpty()){
            //System.out.println(mystack.size());
            current = mystack.peek();
            if(current.isFinal()){
                solvable=true;
                break;
            }
            mystack.pop();
            ArrayList<State> children = current.childstates();
            for(int i=0; i<children.size(); i++){
                mystack.push(children.get(i));
            }
        }
        State newcurrent=current;
        ArrayList<State> solutionpath = new ArrayList<State>();
        solutionpath.add(newcurrent);
        while(newcurrent.parent!=null){
            newcurrent=newcurrent.parent;
            solutionpath.add(0,newcurrent);
        }
        for(int i=0; i<solutionpath.size(); i++){
            solutionpath.get(i).printBoard();
        }

        if(solvable)
            System.out.println("Solvable");
        else
            System.out.println("Not Solvable");


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
