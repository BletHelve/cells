package coordinate;

public class XY {
    public int x;
    public int y;
    public XY(int x, int y){
        this.x=x;
        this.y=y;
    }
    public void print(){
        System.out.print("X="+x +",Y="+y+"  ");
    }

    public static XY zero(){
        return new XY(0,0);
    }

    public XY move(XY vector){
        return new XY(x+vector.x,y+vector.y);
    }



    XY up(){
        return move(new XY(0,1));
    }

    XY down(){
        return move(new XY(0,-1));
    }

    XY left(){
        return move(new XY(-1,0));
    }

    XY right(){
        return move(new XY(1,0));
    }

    public int out_of_bouds(int width,int height){//0是没有越界,1是左右越界，2是上下越界，3是对角线越界
        int black_sheep = 0; // 越界方向
        if (this.x>=width||this.x<0){
            black_sheep = 1;
        }
        if (this.y>=height||this.y<0){
            if (black_sheep == 1){
                black_sheep = 3;
            }else{
                black_sheep = 2;
            }
        }
        return black_sheep;
    }

}
