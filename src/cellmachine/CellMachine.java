package cellmachine;


import View.CAFrame;
import View.CAView;
import coordinate.XY;
import field.*;
import parm.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class CellMachine {
    private static int time = 0;
    private static int speed = 1;
    private static boolean pause = true;
	Border border;
	Field field;
	CAFrame frame;
	CAView view;


    public  CellMachine(){
	    this.border = new Border(50,50,Parm.LOOP);
        this.field = new Field(Parm.VON_NEUMANN, border,Parm.LANTON_LOOP_CNT,Parm.RULE_LANGTON_LOOP_TXT);
        this.field.fill(0);
        this.view= new CAView(field);
        this.frame = new CAFrame("AL",Parm.LANGTON_LOOP_TYPE);
        this.frame.add(view, BorderLayout.CENTER);

        this.view.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                XY xy = new XY(e.getX()/view.getGrid_size(),e.getY()/view.getGrid_size());
                field.get(xy).setNumber(frame.getChoice());
                frame.repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        this.frame.getNext_btn().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                for (int i = 0; i < frame.speed(); i++) {
                    field.step();
                    time++;
                }
                frame.setTime(time);
                frame.repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });


        this.frame.pack();
        this.frame.setVisible(true);
	}

    public CAFrame getFrame() {
        return frame;
    }

    public static void shift_pause() {
        pause=!pause;
    }

    public static boolean isPause() {
        return pause;
    }


}
