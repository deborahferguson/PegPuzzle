/**
 * Deborah Ferguson
 * CS 315 Honors --  Algorithms
 * Department of Computer Science
 * University of Kentucky
 * December 17, 2015
 */
import java.awt.*;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.*;
import java.util.ArrayList;
import javax.swing.Timer;

public class Panel extends JPanel implements ActionListener {

    //vatiables
    public ArrayList<State> solutions;
    int currentIndex = 0;
    Timer timer=new Timer(1000, this);

    public Panel(){
        timer.start();
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

            int[][][] locations = new int[solutions.get(0).board.length][][];
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
            if(currentIndex<solutions.size()-1)
                currentIndex++;
            repaint();// this will call at every 1 second
        }

    }

}