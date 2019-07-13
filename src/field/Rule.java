package field;


import cell.Cell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Rule {
    private Cell[] neigh;
    private Cell centre;
    private Cell outcome;
    private int type_cnt;
    private static int neigh_cnt;

    public Rule(Cell[] neigh,Cell centre,Cell outcome,int type_cnt){
        this.type_cnt=type_cnt;
        this.neigh_cnt=neigh.length;
        this.centre=centre;
        this.outcome=outcome;
        this.neigh=neigh;
    }

    public Rule(String str,int type_cnt){
        Cell[] cells = new Cell[str.length()];
        for (int i=0;i<str.length();i++) {
            cells[i]=new Cell(str.charAt(i)-'0');
        }
        setRule(cells,type_cnt);
    }
    public Rule(int[] code,int type_cnt){
        Cell[] cells = new Cell[code.length];
        for (int i=0;i<code.length;i++) {
            cells[i]=new Cell(code[i]);
        }
        setRule(cells,type_cnt);
    }

    public Rule(Cell[] cells,int type_cnt,int neigh_cnt){
       setRule(cells,type_cnt);
    }

    private void setRule(Cell[] cells,int type_cnt){
        this.type_cnt=type_cnt;
        this.neigh_cnt=cells.length-2;
        this.centre=cells[0];
        this.outcome=cells[cells.length-1];
        this.neigh=Arrays.copyOfRange(cells, 1, cells.length-1);
    }

    public Rule shift_neigh(Cell[] neigh){
        return new Rule(neigh,this.centre,this.outcome,this.type_cnt);
    }

    public Cell[] getNeigh(){
        return neigh;
    }

    public Cell getOutcome() {
        return outcome;
    }

    public Cell getCentre() {
        return centre;
    }

    public boolean condition_equals(Cell[] neigh){
        for (int i=0;i<neigh_cnt;i++){
            if (!neigh[i].equals(this.neigh[i])){
                 return false;
            }
        }
        return true;
    }


    private static int mod(int x,int y,int len){
        int n=x+y;
        if (n>=len){
            return n-len;
        }else if (n<0){
            return len+n;
        }else{
            return n;
        }
    }

    public static ArrayList<Rule>[] make(String rule_txt, int type_cnt, int neigh_cnt){
        //ArrayList<Rule>数组把centre元胞 类型相同的放入同一行，方便查找
        ArrayList<Rule>[] ruleLists = new ArrayList[type_cnt];
        for (int i=0;i<ruleLists.length;i++){
            ruleLists[i]=new ArrayList<>();
        }
        String[] ruleArray = rule_txt.split(" ");
        ArrayList<String> rule_all = new ArrayList<>();
        for (String ruleStr:ruleArray) {
            String rule_neigh=ruleStr.substring(1,ruleStr.length()-1);
            char centre = ruleStr.charAt(0);
            char outcome = ruleStr.charAt(ruleStr.length()-1);
            for (String neigh:rotate(rule_neigh)) {
                rule_all.add(centre+neigh+outcome);
                //System.out.println(centre+" "+neigh+" "+outcome);
            }
        }
        for (String s:rule_all) {
            Rule rule = new Rule(s,type_cnt);
            ruleLists[s.charAt(0)-'0'].add(rule);
            rule.print();
        }
        return ruleLists;
    }



    private static HashSet<String> rotate(String ruleStr){
        HashSet<String> ruleStr_all=new HashSet<>();
        String positive =ruleStr;
        //String inverted=invert(ruleStr);//逆序邻居
        ruleStr_all.add(positive);
        //ruleStr_all.add(inverted);
        for(int i=0;i<ruleStr.length();i++){
            positive=turn(positive);
            //inverted=turn(inverted);
            ruleStr_all.add(positive);
            //ruleStr_all.add(inverted);
        }
        return ruleStr_all;
    }

    private static String turn(String cellStr){
        char[] turned = new char[cellStr.length()] ;
        for (int i=0;i<cellStr.length();i++){
            turned[i]=cellStr.charAt(mod(i,-1,cellStr.length()));
        }
        return new String(turned);
    }

    private static String invert(String cellStr){
        char[] inverted = new char[cellStr.length()];
        for (int i=0;i<cellStr.length();i++){
            inverted[cellStr.length()-1-i]=cellStr.charAt(i);
        }
        return new String(inverted);
    }

    public void print(){
        System.out.print("中间："+centre.getNumber());
        System.out.print("  邻居：");
        for (int i=0;i<neigh.length;i++){
            System.out.print(neigh[i].getNumber()+" ");
        }
        System.out.print("  变换："+outcome.getNumber());
        System.out.println();
    }

}



