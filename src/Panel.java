/**
 * Created by debbie on 12/16/15.
 */
import java.awt.*;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.*;
import java.util.ArrayList;
import javax.swing.Timer;

public class Panel extends JPanel implements ActionListener {

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
        //first row
        //first triangle
        int[] xvalues={10,300,590};
        int[] yvalues={(int)(300+175*Math.sqrt(2)),(int)(300-175*Math.sqrt(2)),(int)(300+175*Math.sqrt(2))};
        g.fillPolygon(xvalues,yvalues,3);

        if(solutions.size()!=0) {
            int[][][] locations = new int[solutions.get(0).board.length][][];
            for(int i=0; i<locations.length; i++){
                locations[i] = new int[i+1][2];
            }
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

//            g.fillOval(75, 470, 50, 50);
//            g.fillOval(175, 470, 50, 50);
//            g.fillOval(275, 470, 50, 50);
//            g.fillOval(375, 470, 50, 50);
//            g.fillOval(475, 470, 50, 50);

//            g.fillOval(125, 390, 50, 50);
//            g.fillOval(225, 390, 50, 50);
//            g.fillOval(325, 390, 50, 50);
//            g.fillOval(425, 390, 50, 50);

//            g.fillOval(175, 310, 50, 50);
//            g.fillOval(275, 310, 50, 50);
//            g.fillOval(375, 310, 50, 50);

//            g.fillOval(225, 230, 50, 50);
//            g.fillOval(325, 230, 50, 50);

//            g.fillOval(275, 150, 50, 50);

             State current = solutions.get(currentIndex);
             for (int i = 0; i < current.board.length; i++) {
                for (int j = 0; j < current.board[i].length; j++) {
                    if (current.board[i][j] == 0) {
                        g.setColor(Color.black);
                    }
                    else {
                        g.setColor(Color.red);
                    }
                    g.fillOval(locations[i][j][0], locations[i][j][1], 50, 50);
                }
             }

//            g.setColor(Color.black);
//            g.setColor(Color.red);
        }
    }
    public void actionPerformed(ActionEvent ev){
        if(ev.getSource()==timer){
            if(currentIndex<solutions.size()-1)
                currentIndex++;
            repaint();// this will call at every 1 second
        }

    }

}