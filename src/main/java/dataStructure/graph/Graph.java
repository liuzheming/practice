package dataStructure.graph;

import java.util.HashSet;
import java.util.Set;

public class Graph {

    private int V;

    private int E;

    private Set<Integer>[] adj;

    public Graph(int v) {
        V = v;
        E = 0;
        adj = (Set<Integer>[]) new HashSet[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new HashSet<>();
        }
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

}
