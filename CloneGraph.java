// Time Complexity : O(v+e)
// Space Complexity :  O(e)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : Yes, had to learn

/*
 * Problem# : 133
 * v is vertices; e- edges
 * 
 * To return a cloned graph: we can have a utility function to either do the clone and return or just return if clone is already available.
 * 
 * its a graph problem: We get to all Nodes either by BFS or DFS approach.
 * 
 * BFS: We use a queue (LinkedList)
 * add the rootNode and also create a cloneOfIt
 * 
 * while the queue is not empty; pop the element in the queue & its neighbors.
 * if we have neighbors, go through the neighbors and check if they are already existing the cache(hashMap) 
 * cache(hashMap) serves as a way to check if the node is already visited and also return the clone node if its visted
 * 
 * if the child or neighbor is not existing in the cache, i.e., the node is not visited, add it to queue
 * clone the child and add the clone to the map.get(node).neighbor.
 * 
 * return map.get(node)
 * 
 * DFS: default DS stack
 * variables: cache a HashMap
 * dfs will create a cloneNode ; if neighbours are available go through each neightbor, if the neighbor is not in cache, dfs from the neighbor
 * clone the child and add the clone to the cloned node 
 */

import java.util.*;

public class CloneGraph {
    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    Map<Node, Node> cache = null;

    // BFS

    public Node cloneGraph(Node node) {
        // how do i know when to end? : look at when and how to add to queue.
        // how to establish connection /neigbhors? : get clone neighbor
        if (node == null)
            return null;
        Queue<Node> queue = new LinkedList<>();
        this.cache = new HashMap<>();
        queue.add(node);
        Node clone = cloneNode(node);

        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            List<Node> neighbNodes = curr.neighbors;
            if (neighbNodes != null) {
                for (Node neighbor : neighbNodes) {
                    if (!cache.containsKey(neighbor)) {// remember we ara adding neighbor to the queue
                        queue.add(neighbor); // this controls the queue size. If the node is already visited we dont add
                                             // to the queue.
                    }
                    Node cloneNeighr = cloneNode(neighbor);

                    cache.get(curr).neighbors.add(cloneNeighr);
                }
            }

        }

        return clone;

    }

    private Node cloneNode(Node node) {
        if (!cache.containsKey(node)) {
            cache.put(node, new Node(node.val));
        }
        return cache.get(node);
    }

    // DFS:
    public Node cloneGraph_dfs(Node node) {
        if (node == null)
            return null;
        cache = new HashMap<>();
        dfs(node);
        return cache.get(node); // assuming the cache.get(node) would get the clone node
    }

    private void dfs(Node node) {
        // base case

        Node clonedNode = cloneNode(node);
        if (node.neighbors != null) {
            for (Node child : node.neighbors) {
                if (!cache.containsKey(child)) {
                    dfs(child); // base case; rememeber we are adding the child to the queue
                }
                Node clonedChild = cloneNode(child);
                cache.get(node).neighbors.add(clonedChild);
            }
        }
    }

}
