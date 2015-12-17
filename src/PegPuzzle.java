/**
 * Created by debbie on 12/10/15.
 */

import java.util.*;
public class PegPuzzle {

    public static void main(String[] args){
        State firststate = new State(5,0,0);

        Stack<State> mystack = new Stack<State>();
        mystack.push(firststate);
        State current=mystack.peek();
        while(!mystack.isEmpty()){
            current = mystack.peek();
            if(current.isFinal()){
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

        /*for(int i=0; i<childrenoffirststate.size(); i++){
            childrenoffirststate.get(i).printBoard();
        }
        */
    }

}
