package View;
import parm.Parm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CAFrame extends  JFrame{
    private int choice=0;
    private JPanel time_panel = new JPanel();
    private JPanel parm_panel = new JPanel();
    private JButton time_btn = new JButton("时间");
    private JTextField time_txt = new JTextField("0");
    private JButton start_btn = new JButton("开始");
    private JButton pause_btn = new JButton("暂停");

    private JButton next_btn = new JButton("步入");
    private JTextField next_txt = new JTextField("1") ;
    private JComboBox type_combo;
    private JButton[] type_num;
    private JPanel[] color_grid;


    public CAFrame(String title, String []types){
        time_txt.setColumns(5);
        next_txt.setColumns(5);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle(title);
        this.setLayout(new BorderLayout());

        time_panel.add(time_btn);
        time_panel.add(time_txt);
        time_panel.add(start_btn);
        time_panel.add(pause_btn);
        time_panel.add(next_btn);
        time_panel.add(next_txt);
        this.type_num = new JButton[types.length];
        this.color_grid = new JPanel[types.length];

        for(int i=0;i<types.length;i++){
            type_num[i]=new JButton();
            color_grid[i]=new JPanel();
            type_num[i].setText(types[i]);
            color_grid[i].setBackground(Parm.COLOR_MAP.get(Integer.parseInt(types[i])));
            parm_panel.add(type_num[i]);
            parm_panel.add(color_grid[i]);
        }
        this.add(time_panel, BorderLayout.SOUTH);
        this.add(parm_panel, BorderLayout.NORTH);
        for (int i=0;i<types.length;i++){
            type_num[i].addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JButton btn=(JButton)e.getSource();
                    choice=Integer.parseInt(btn.getText());
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
        }
    }

    public JButton getNext_btn() {
        return next_btn;
    }

    public JButton getStart_btn() {
        return start_btn;
    }

    public JButton getPause_btn() {
        return pause_btn;
    }

    public void setStart_btn_name(String name) {
        this.start_btn.setName(name);
    }

    public void setTime(int time) {
        this.time_txt.setText(Integer.toString(time));
    }

    public int speed(){
        return Integer.parseInt(next_txt.getText());
    }


    public int getChoice(){
        return choice;
    }

}
