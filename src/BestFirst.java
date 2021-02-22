import java.util.*;

public class BestFirst {
    static class State {
        private Ilayout layout;
        private State father;
        private double g;
        public State(Ilayout l, State n) {
            layout = l;
            father = n;
            if (father!=null)
                g = father.g + l.getG();
            else g = 0.0;
        }
        public String toString() { return layout.toString(); }
        public double getG() {return g;}
        public State getFather(){
            return this.father;
        }
    }



    protected Queue<State> abertos;
    private List<State> fechados;
    private State actual;
    private Ilayout objective;

    final private List<State> sucessores(State n) {
        List<State> sucs = new ArrayList<>();
        List<Ilayout> children = n.layout.children();
        for(Ilayout e: children) {
            if (n.father == null || !e.equals(n.father.layout)){
                State nn = new State(e, n);
                sucs.add(nn);
            }
        }
        return sucs;
    }

    final public Iterator<State> solve(Ilayout s, Ilayout goal) {
        objective = goal;
        Queue<State> abertos = new PriorityQueue<>(10,
                (s1, s2) -> (int) Math.signum(s1.getG() - s2.getG()));
        List<State> fechados = new ArrayList<>();
        abertos.add(new State(s, null));
        List<State> sucs;
        Iterator<State> i = abertos.iterator();
        State solution = null;
        while (!abertos.isEmpty()){
            State v = abertos.poll();
            if(v.layout.isGoal(objective)){
                solution = v;
                break;
            }else{
                sucs = sucessores(v);
                for(State state : sucs){
                    if(!fechados.contains(state)){
                        abertos.add(state);
                    }
                }
            }
            fechados.add(v);
        }
        return new IT(solution);
    }
    }

    class IT implements Iterator<BestFirst.State> {
    private BestFirst.State last;
    private Stack<BestFirst.State> stack;

    public IT(BestFirst.State actual) {
        last = actual;
        stack = new Stack<BestFirst.State>();
        while (last != null) {
            stack.push(last);
            last = last.getFather();
        }
    }

    public boolean hasNext() {
        return !stack.empty();
    }

    public BestFirst.State next() {
        return stack.pop();
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}

