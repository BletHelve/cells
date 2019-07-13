package cell;

import java.awt.*;
import parm.Parm;

public class Cell {
    private int number;
    private Color color;
    public Cell(int number) {
        this.color=Parm.COLOR_MAP.get(number);
        this.number=number;
    }

    public static Cell[] cellArray(int[] number){
        Cell[] cells = new Cell[number.length];
        for (int i=0;i<number.length ;i++){
            cells[i]=new Cell(number[i]);
        }
        return cells;
    }

    public void setNumber(int number){
        this.setColor(Parm.COLOR_MAP.get(number));
        this.number=number;
    }

    public int getNumber(){
        return number;
    }

    public static int[] getNumbers(Cell[] cells){
        int[] nums=new int[cells.length];
        for (int i=0;i<cells.length;i++){
            nums[i]=cells[i].getNumber();
        }
        return nums;
    }


    public Color getColor() {
        return color;
    }

    public void setColor(Color color){
        this.color=color;
    }


    public boolean equals(Cell cell) {
        if (this.number==cell.number){
            return true;
        }else{
            return false;
        }
    }

    public Cell clone(){
        return new Cell(this.number);
    }

    public void draw(Graphics g, int x, int y, int size) {
        g.setColor(this.color);
        g.fillRect(x, y, size, size);
        g.setColor(Color.gray);
        g.drawRect(x, y, size, size);
    }

}
