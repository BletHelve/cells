package field;
import coordinate.*;


public class Border {
    private int width;
    private int height;
    private boolean isBound;
    private int[] overstep_handle;//越界处理
    /*
    * 下标 0 没有越界
    * 下标 1 左右越界
    * 下标 2 上下越界
    * 下标 3 对角线越界
    *
    * 0 周期型
    * 1 镜像型
    *
    * */
    public Border(int width, int height, boolean isBound){
        this.width=width;
        this.height=height;
        this.isBound=isBound;
    }

    public Border(int width,int height ,int[] overstep_handle){
        this.width=width;
        this.height=height;
        this.isBound=false;
        this.overstep_handle=overstep_handle;
    }

    public int getWidth() { return width; }

    public int getHeight() { return height; }

    public void set_overstep_handle(int fence,int black_sheep){
        this.overstep_handle[fence]=black_sheep;
    }

    public boolean getIsBound(){
        return isBound;
    }



    public XY out(XY xy,int black_sheep){
        if (black_sheep!=0) {
            int fence = overstep_handle[black_sheep];//应对越界处理
            if (fence == 0) {
                System.out.println("未设置边界处理");
            } else {
                if (black_sheep == 1) {
                    xy.x = unbounded(fence, xy.x, width);
                }
                if (black_sheep == 2) {
                    xy.y = unbounded(fence, xy.y, height);
                }
                if (black_sheep == 3) {
                    xy.x = unbounded(fence, xy.x, width);
                    xy.y = unbounded(fence, xy.y, height);
                }
            }
        }
        return xy;
    }

    private int unbounded(int fence,int n,int len){
        if (fence==1){
            n=mirror(n,len);
        }
        if (fence==2){
            n=loop(n,len);
        }
        return n;
    }

    private int mirror(int n,int len) {
        if (n >= len || n < 0){
            if (n >= len) {
                n = 2 * len - n - 1;
            } else if (n < 0) {
                n = -n - 1;
            }
            mirror(n, len);
        }
        return n;
    }

    private int loop(int n,int len) {
        //System.out.println(n);
        if (n >= len || n < 0) {
            if (n >= len) {
                n -= len;
            } else if (n < 0) {
                n += len;
            }
            loop(n, len);
        }
        return n;
    }
}
