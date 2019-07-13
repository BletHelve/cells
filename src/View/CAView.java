package View;

import field.Field;

import cell.Cell;
import javax.swing.*;
import java.awt.*;

public class CAView extends JPanel {
	private static final long serialVersionUID = -5258995676212660595L;
	private int grid_size = 12;

	private Field theField;
	
	public CAView(Field field) {
		theField = field;
		setBackground(Color.gray);
	}

	public int getGrid_size() {
		return grid_size;
	}

	public void setGrid_size(int grid_size) {
		this.grid_size=grid_size;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for ( int row = 0; row<theField.getHeight(); row++ ) {
			for ( int col = 0; col<theField.getWidth(); col++ ) {
				Cell cell = theField.get(row, col);
				if ( cell != null ) {
					cell.draw(g, col*grid_size, row*grid_size, grid_size);
				}
			}
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(theField.getWidth()*grid_size+1, theField.getHeight()*grid_size+1);
	}







	/*public static void main(String[] args) {
		Border border = new Border(30,30,true);
		Field field = new Field(Neighbour.MOORE,border);
		for ( int row = 0; row<field.getHeight(); row++ ) {
			for ( int col = 0; col<field.getWidth(); col++ ) {
				field.place(row, col, Cell.nothing());
			}
		}
		View view = new View(field);
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setTitle("Cells");
		frame.add(view);
		frame.pack();
		frame.setVisible(true);
	}
*/
}
