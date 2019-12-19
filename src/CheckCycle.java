import org.junit.Test;

import java.util.*;

class Node {
    char val;
    List<Node> next;
    Status status;
    public Node(char val) {
        this.val = val;
        next = new ArrayList<>();
        status = Status.INITIAL;
    }
}

enum Status {
    INITIAL, VISITING, VISITED
}

public class CheckCycle {
    public boolean checkCycle(char[][] input) {
        if (input == null || input.length == 0 || input[0] == null || input[0].length == 0) {
            return false;
        }

        Node[] graph = new Node[26];
        for (int i = 0; i < input.length; i++) {
            if (graph[input[i][0] - 'a'] == null) {
                Node node = new Node(input[i][0]);
                graph[input[i][0] - 'a'] = node;
            }
            if (graph[input[i][1] - 'a'] == null) {
                Node next = new Node(input[i][1]);
                graph[input[i][1] - 'a'] = next;
            }
            graph[input[i][0]].next.add(graph[input[i][1]]);
        }

        for (Node node : graph) {
            if (node != null && dfs(node)) {
                return true;
            }
        }
        return false;
    }

    private boolean dfs(Node node) {
        if (node.status == Status.VISITING) return true;
        if (node.status == Status.VISITED) return false;

        node.status = Status.VISITING;
        for (Node next: node.next) {
            if (dfs(next)) {
                return true;
            }
        }
        node.status = Status.VISITED;
        return false;
    }

    @Test
    public void test() {
        char[][] array = {{'a', 'c'}, {'a', 'd'}, {'d', 'c'}, {'c', 'd'}};
        boolean res = checkCycle(array);
        System.out.println(res);
//        Assert.assertEquals(true, res);
    }
}
