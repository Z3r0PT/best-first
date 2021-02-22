import java.util.*;

public class Board  implements Ilayout, Cloneable {
    private static final int dim = 3;
    private int board[][];
    private String s;

    public Board() {
        board = new int[dim][dim];
    }

    public Board(String str) throws IllegalStateException {
        s = str;
        if (str.length() != dim * dim) throw new
                IllegalStateException("Invalid arg in Board constructor");
        board = new int[dim][dim];
        int si = 0;
        for (int i = 0; i < dim; i++)
            for (int j = 0; j < dim; j++)
                board[i][j] = Character.getNumericValue(str.charAt(si++));
    }

    public String toString() {
        String str = s;
        int index = str.indexOf('0');
        StringBuilder sb = new StringBuilder(str);
        sb.setCharAt(index, ' ');
        String show = new String(sb);
        return show.substring(0, 3)+"\n"+
                show.substring(3, 6)+"\n"+
                show.substring(6, 9)+"\n";
    }

    @Override
    public List<Ilayout> children() {
          List<Ilayout> children = new ArrayList<>();
          children = swap(s);
          return children;
    }

    public List<Ilayout> swap(String str){
        List<Ilayout> childs = new ArrayList<>();
        int l = s.indexOf('0');
        int col = l % dim;
        int row = l / dim;
        if((col+1) >= 0 && (col+1) < 3){ //right
            StringBuilder sb = new StringBuilder(str);
            sb.setCharAt(l, str.charAt(l+1));
            sb.setCharAt((l+1), str.charAt(l));
            childs.add(new Board(new String(sb)));
        }
        if((col-1) >= 0 && (col-1) < dim){ //left
            StringBuilder sb = new StringBuilder(str);
            sb.setCharAt(l, str.charAt(l-1));
            sb.setCharAt((l-1), str.charAt(l));
            childs.add(new Board(new String(sb)));
        }
        if((row+1) >= 0 && (row+1) < dim){ //down
            StringBuilder sb = new StringBuilder(str);
            sb.setCharAt(l, str.charAt(l+dim));
            sb.setCharAt((l+dim), str.charAt(l));
            childs.add(new Board(new String(sb)));
        }
        if((row-1) >= 0 && (row-1) < dim){
            StringBuilder sb = new StringBuilder(str);
            sb.setCharAt(l, str.charAt(l-dim));
            sb.setCharAt((l-dim), str.charAt(l));
            childs.add(new Board(new String(sb)));
        }
        return childs;
    }

    @Override
    public boolean isGoal(Ilayout l) {
        return this.equals(l);
    }

    @Override
    public boolean equals(Object that) {
        if (that instanceof Board) {
            Board b = ((Board) that);
            return s.equals(b.s);
        } else
            return false;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(s);
        result = 31 * result + Arrays.hashCode(board);
        return result;
    }

    @Override
    public double getG() {
        return 1;
    }
}
