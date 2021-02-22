import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class Main {
    public static void main (String [] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        BestFirst s = new BestFirst();
        Iterator<BestFirst.State> it = s.solve(new Board(sc.next()),
                new Board(sc.next()));
        //System.out.println("step 1");
        if (it==null) System.out.println("no solution was found");
        else {
            //System.out.println("step 2");
            while(it.hasNext()) {
                BestFirst.State i = it.next();
                System.out.println(i);
                if (!it.hasNext()) System.out.println((int)i.getG());
            }
        }
        //String s = sc.next();
        sc.close();
/*
        Board b = new Board(s);
        System.out.println(b.toString());
        System.out.println(b.children());*/
    }
}
