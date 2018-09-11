package dataStructure.graph;

import java.util.HashSet;
import java.util.Set;

public class Graph {

    private int nodeCount;

    private int edgeCount;

    private Set<Integer>[] table;

    public Graph(int nodeSize) {
        nodeCount = nodeSize;
        table = (Set<Integer>[]) new HashSet[nodeCount];
        for (int i = 0; i < nodeSize; i++) {
            table[i] = new HashSet<>();
        }
    }

    public void addEdge(int start, int end) {
        table[start].add(end);
        table[end].add(start);
        edgeCount++;
    }

    public int nodeCount() {
        return nodeCount;
    }

    public int edgeCount() {
        return edgeCount;
    }

}
