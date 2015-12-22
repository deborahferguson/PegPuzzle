/**
 * Deborah Ferguson
 * CS 315 Honors --  Algorithms
 * Department of Computer Science
 * University of Kentucky
 * December 17, 2015
 */
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.*;
import java.util.ArrayList;
import java.util.Stack;
import javax.swing.Timer;

public class Panel extends JPanel implements ActionListener {

    // Variables
    public ArrayList<State> solutions;
    int currentIndex = 0;
    Timer timer=new Timer(1000, this);
    int[][][] locations;
    int row = 0;
    int rowPos = 0;
    private JLabel boardSizesLabel;
    private JLabel instructions;
    private JComboBox boardSizes;
    private JButton solveButton;
    
    public Panel(){
	setLayout(null);
	setSize(600,600);
	boardSizesLabel = new JLabel("Board Size:");
	boardSizesLabel.setSize(100, 20);
	boardSizesLabel.setLocation(20,20);
	boardSizes = new JComboBox(new String[]{"3","4","5","6"});
	boardSizes.setLocation(120,20);
	boardSizes.setSize(100,20);
	boardSizes.addActionListener(this);
	solveButton = new JButton("Solve");
	solveButton.setLocation(20,100);
	solveButton.setSize(80,20);
	solveButton.addActionListener(this);
    solveButton.setVisible(false);
    instructions = new JLabel("Click a peg to be the initial hole");
    instructions.setLocation(20,60);
    instructions.setSize(300,20);
    instructions.setVisible(false);
	add(boardSizesLabel);
	add(boardSizes);
	add(solveButton);
    add(instructions);
	solutions = new ArrayList<State>();

	//Mouse things
	addMouseListener(new MouseAdapter() {
		public void mousePressed(MouseEvent me) {
		    int mouseX = me.getX();
		    int mouseY = me.getY();

		    // locations[i][j][0] -> x
		    // locations[i][j][1] -> y
		    // locations[i][j][2] -> width?
		    for(int i=0; i<locations.length; i++) {
			boolean found = false;
			for(int j=0; j<locations[i].length; j++) {
			    int radius = locations[i][j][2]/2;
			    int centerX = locations[i][j][0]+radius;
			    int centerY = locations[i][j][1]+radius;

			    // Check if mouse clicked this hole
			    double distance = Math.sqrt(Math.pow(centerX-mouseX,2) + Math.pow(centerY-mouseY,2));
			    if(distance < radius) {
				row = i;
				rowPos = j;
				found = true;
				break;
			    }
			}
			if(found) { break; }
		    }

		    currentIndex = 0;
		    solutions = new ArrayList<State>();
		    solutions.add(new State(new Integer((String)boardSizes.getSelectedItem()), row, rowPos));
		    repaint();
		}
	    });
	

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //g.drawRect(200,200,200,200);


        g.setColor(Color.gray);
        //draw triangle
        int[] xvalues={10,300,590};
        int[] yvalues={(int)(300+150*Math.sqrt(3)),(int)(300-150*Math.sqrt(3)),(int)(300+150*Math.sqrt(3))};
        g.fillPolygon(xvalues,yvalues,3);




        //store the locations for all of the holes
        if(solutions.size()!=0) {
            int width = 500/(2*solutions.get(0).getBoard().length);

            int firsty = (int)(300-150*Math.sqrt(3))+2*width/2;
            int firstx = (int)(10+(2*width/2)*Math.sqrt(3));
            int lastx = (int)(590-(2*width/2)*Math.sqrt(3));
            int lasty = (int)(300+150*Math.sqrt(3)-2*width);

            locations = new int[solutions.get(0).board.length][][];
                for(int i=0; i<locations.length; i++){
                    locations[i] = new int[i+1][3];
                }

                for(int i=0; i<locations.length; i++){
                    if(i==0){
                        locations[0][0][0]=300-width/2;
                        locations[0][0][1]=firsty+width/2;
                        locations[0][0][2]=width;
                    }
                    else {
                        for (int j = 0; j < locations[i].length; j++) {
                            int rowxfirst = 300 - i * (lastx - firstx) / (locations.length - 1) / 2;
                            int rowxlast = 300 + i * (lastx - firstx) / (locations.length - 1) / 2;
                            locations[i][j][0] = rowxfirst + j * ((rowxlast - rowxfirst) / (locations[i].length - 1))-width/2;
                            locations[i][j][1] = firsty + i * ((lasty - firsty) / (locations.length - 1))+width/2;
                            locations[i][j][2] = width;
                        }
                    }
                }

            /*
            locations[4][0][0]=75;
            locations[4][0][1]=470;
            locations[4][1][0]=175;
            locations[4][1][1]=470;
            locations[4][2][0]=275;
            locations[4][2][1]=470;
            locations[4][3][0]=375;
            locations[4][3][1]=470;
            locations[4][4][0]=475;
            locations[4][4][1]=470;

            locations[3][0][0]=125;
            locations[3][0][1]=390;
            locations[3][1][0]=225;
            locations[3][1][1]=390;
            locations[3][2][0]=325;
            locations[3][2][1]=390;
            locations[3][3][0]=425;
            locations[3][3][1]=390;

            locations[2][0][0]=175;
            locations[2][0][1]=310;
            locations[2][1][0]=275;
            locations[2][1][1]=310;
            locations[2][2][0]=375;
            locations[2][2][1]=310;

            locations[1][0][0]=225;
            locations[1][0][1]=230;
            locations[1][1][0]=325;
            locations[1][1][1]=230;

            locations[0][0][0]=275;
            locations[0][0][1]=150;

*/
            //paint the current states on the holes
             State current = solutions.get(currentIndex);
             for (int i = 0; i < current.board.length; i++) {
                for (int j = 0; j < current.board[i].length; j++) {
                    //black if hole
                    if (current.board[i][j] == 0) {
                        g.setColor(Color.black);
                    }
                    //red if peg
                    else {
                        g.setColor(Color.red);
                    }
                    g.fillOval(locations[i][j][0], locations[i][j][1], locations[i][j][2], locations[i][j][2]);
                }
             }


        }
    }
    //timer to go through the solution
    public void actionPerformed(ActionEvent ev){
        if(ev.getSource()==timer){
            if(currentIndex<solutions.size()-1) {
                currentIndex++;
	    } else {
		timer.stop();
	    }
            repaint();// this will call at every 1 second
        }

	if(ev.getSource() == boardSizes) {
	    currentIndex = 0;
	    solutions = new ArrayList();
	    solutions.add(new State(new Integer((String)boardSizes.getSelectedItem()), 0, 0));
        solveButton.setVisible(true);
        instructions.setVisible(true);
	    repaint();
	}

	if(ev.getSource() == solveButton) {
	    solve(new Integer((String)boardSizes.getSelectedItem()), row, rowPos);
	}
    }

    private boolean solve(int size, int holeRow, int holeRowPos) {
	//the initial state
        State firststate = new State(size,holeRow,holeRowPos);
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
        if(solvable) {
            System.out.println("Solvable");
	}
        else {
            System.out.println("Not Solvable");
	}

	currentIndex = 0;
	timer.start();
	solutions = solutionpath;
	return solvable;
    }
}
