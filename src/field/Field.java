package field;

import cell.Cell;
import coordinate.*;
import java.util.ArrayList;

public class Field {

	private Cell[][] field;
	private XY[] neighbour;//邻居离中心点的矢量坐标，顺时针、由内向外
	private Border border;
	private int width;
	private int height;
	private ArrayList<Rule>[] ruleLists;



	public Field(XY[] neighbour,Border border,int type_cnt,String rule_txt) {
		this.width = border.getWidth();
		this.height = border.getHeight();
		this.field = new Cell[height][width];
		this.neighbour=neighbour;
		this.border=border;
		this.ruleLists=Rule.make(rule_txt,type_cnt,neighbour.length);
	}
	
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	
	public Cell place(int row, int col, Cell cell) {
		Cell ret = field[row][col];
		field[row][col] = cell;
		return ret;
	}

	public Cell place(Cell[][] field,XY xy,Cell cell) {
		Cell ret = field[xy.y][xy.x];
		field[xy.y][xy.x] = cell;
		return ret;
	}

	
	public Cell get(int row, int col) {
		return field[row][col];
	}

	public Cell get(XY xy) {
		return field[xy.y][xy.x];
	}
	
	public Cell[] getNeighbour(XY xy) {
		Cell[] cells=new Cell[neighbour.length];

		for (int i=0;i<neighbour.length;i++) {
			XY vector=neighbour[i];
//			System.out.print("矢量——");
//			vector.print();
//			System.out.println();
			XY neigh=xy.move(vector);
			int black_sheep = neigh.out_of_bouds(width, height);//判断是否越界，以及越界方式
			if (black_sheep != 0) {
				if (border.getIsBound()){//有边界
					cells[i] = new Cell(0);
					continue;
				}else {//无边界
					neigh = border.out(neigh, black_sheep);//越界处理
				}
			}
			cells[i] = get(neigh);
//			System.out.print("邻居——");
//			neigh.print();
//			System.out.println();
		}
		return cells;
	}
	
	public void clear() {
		for (int i=0;i<height;i++) {
			for (int j=0;j<width;j++) {
				field[i][j] = null;
			}
		}
	}

	public void step(){//todo
		int[][] moment=new int[height][width];
		Cell[][] field = new Cell[height][width];
		for ( int row = 0; row<this.height; row++ ) {
			for ( int col = 0; col<this.width; col++ ) {
				XY xy = new XY(row,col);
				Cell cell=shift(xy);
				place(field,xy,cell);
				moment[row][col]=cell.getNumber();
			}
		}
		this.field=field;
	}

	private Cell shift(XY xy){
		Cell centre = get(xy);
		Cell[] neighbour = getNeighbour(xy);
		for (Rule rule:ruleLists[centre.getNumber()]){
			if (rule.condition_equals(neighbour)){
//				boolean yes=false;
//				for (Cell cell:rule.getNeigh()){
//					if (cell.getNumber()==4){
//						yes=true;
//						break;
//					}
//				}
//				if (rule.getCentre().getNumber()==4||yes) {
//					xy.print();
//					rule.print();
//				}
				return rule.getOutcome().clone();
			}
		}
		return centre.clone();
	}

	public void fill(int number){
		for ( int row = 0; row<this.getHeight(); row++ ) {
			for ( int col = 0; col<this.getWidth(); col++ ) {
				this.place(row, col, new Cell(number));
			}
		}
	}

}
