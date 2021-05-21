import java.util.List;
import java.util.ListIterator;
import java.util.Random;

/**
 * @author Shuyi Shi, Wey Tsong Lai, Hongce Chen
 * @param <E> template variable represents data class
 */
public class SkipList<E extends Comparable> {

    /**
     * This class represents a pair of two template types.
     *
     * @param <T> the first type
     * @param <E> the second type
     */
    private class Pair<T, E> {

        public T first;
        public E second;

        /**
         * Constructor to initialize the pair.
         *
         * @param curr the first
         * @param count the second
         */
        private Pair(T curr, E count) {
            first = curr;
            second = count;
        }
    }

    /**
     * This class implements a node inside the skip list. it contains the data
     * and a reference to the bottom and right node and count of the lower
     * nodes.
     */
    private class Node {

        E data;
        Node down;
        Node right;
        Integer count;

        /**
         * Initializes an empty Node.
         */
        public Node() {
            this.down = null;
            this.right = null;
            this.count = 1;
        }

        /**
         * Initializes a node with a value.
         *
         * @param val
         */
        public Node(E val) {
            data = val;
            this.down = null;
            this.count = 1;
            this.right = null;
        }
    }

    private Node head;
    private Random rand;
    private Integer currentLevels;
    int size = 0;

    /**
     * This constructor initializes an empty list.
     */
    public SkipList() {
        head = new Node();
        rand = new Random();
        currentLevels = 1;
    }

    /**
     * Add new levels according to the probability (1/p).
     *
     * @return number of levels after inserting new levels.
     */
    private int addNewLevels() {
        int levels = 0;
        while (this.rand.nextBoolean()) {
            levels++;
        }

        if (levels > this.currentLevels) {
            for (int i = levels; i > this.currentLevels; --i) {
                addLevel();
            }
            this.currentLevels = levels;
        }
        return levels;
    }

    /**
     * Find the path from the upper level to the node equal or greater than the
     * current key element.
     *
     * @param key the key to find the path to.
     * @return list of the indeces on each level of the list to the element
     * equal to or has a right greater node.
     */
    private List<Pair<Node, Integer>> findPath(E key) {
        List<Pair<Node, Integer>> res = new java.util.LinkedList<>();
        Node curr = head;

        while (curr != null) {
            int count = 0;

            while (curr.right != null && curr.right.data.compareTo(key) <= 0) {
                count += curr.right.count;
                curr = curr.right;
            }
            res.add(new Pair(curr, count));
            curr = curr.down;
        }
        return res;
    }

    /**
     * Find the path from the upper level to the node equal or greater than the
     * current key element. This differences from the previous node. that it
     * gets the nodes before the requested nodes so we able to delete or insert
     * before the requested key.
     *
     * @param key element to find path to list of the indeces on each level of
     * @return the list to the element equal to or has a right greater node.
     */
    private List<Pair<Node, Integer>> findPathBeforeNodes(E key) {
        List<Pair<Node, Integer>> res = new java.util.LinkedList<>();
        Node curr = head;

        while (curr != null) {
            int count = 0;

            while (curr.right != null && curr.right.data.compareTo(key) < 0) {
                count += curr.right.count;
                curr = curr.right;
            }
            res.add(new Pair(curr, count));
            curr = curr.down;
        }
        return res;
    }

    /**
     * This function adds level at index 0.
     */
    private void addLevel() {
        Node node = new Node();
        node.down = head;
        head = node;
    }

    /**
     * Add element to the list in the right order location.
     *
     * @param e the parameter to be added.
     * @return true if the element is inserted successfully.
     */
    boolean add(E e) {
        // add new levels according to the probabiltiy 1/p
        int levels = addNewLevels();
        // find the path
        List<Pair<Node, Integer>> path = findPath(e);

        // get a reveresed iterator
        ListIterator<Pair<Node, Integer>> li = path.listIterator(path.size());

        Node down = null;
        Pair<Node, Integer> curr;
        int i = 0;
        int prevLevelDist = 1;
        while (li.hasPrevious() && i <= levels) {
            curr = li.previous();
            Node newNode = new Node(e);

            // adjusting bottom node
            newNode.down = down;

            // adjusting right node
            newNode.right = curr.first.right;
            curr.first.right = newNode;

            // adjusing count distance
            newNode.count = prevLevelDist;
            prevLevelDist += curr.second;

            if (newNode.right != null) {
                newNode.right.count = (newNode.right.count - newNode.count + 1);
            }

            down = newNode;
            i++;
        }

        // update count in the parent nodes in upper levels
        while (li.hasPrevious()) {
            curr = li.previous();
            Node rn = curr.first.right;
            if (rn != null) {
                rn.count++;
            }
        }

        size++;
        return true;
    }

    /**
     * Add element to the list in the specified location.
     *
     * @param index the index to insert at
     * @param e the element to be inserted
     */
    public void add(int index, E e) {
        int levels = addNewLevels();
        Node nextNode = getNode(index);

        List<Pair<Node, Integer>> path = findPathBeforeNodes(nextNode.data);

        ListIterator<Pair<Node, Integer>> li = path.listIterator(path.size());

        Node down = null;
        Pair<Node, Integer> curr;
        int i = 0;
        int prevLevelDist = 1;
        while (li.hasPrevious() && i <= levels) {
            curr = li.previous();
            Node newNode = new Node(e);

            // adjusting bottom node
            newNode.down = down;

            // adjusting right node
            newNode.right = curr.first.right;
            curr.first.right = newNode;

            // adjusing count distance
            newNode.count = prevLevelDist;
            prevLevelDist += curr.second;

            if (newNode.right != null) {
                newNode.right.count = (newNode.right.count - newNode.count + 1);
            }

            down = newNode;
            i++;
        }

        while (li.hasPrevious()) {
            curr = li.previous();
            Node rn = curr.first.right;
            if (rn != null) {
                rn.count++;
            }
        }
        size++;
    }

    /**
     * Remove element at the specified index.
     *
     * @param index the index to remove the element at
     * @return the element data
     */
    public E remove(int index) {
        Node nodeToDelete = getNode(index);
        List<Pair<Node, Integer>> path = findPathBeforeNodes(nodeToDelete.data);
        for (Pair<Node, Integer> point : path) {
            if (point.first.right == null) {
                // element is not there and nothing to update
                continue;
            }
            if (point.first.right.data == nodeToDelete.data) {
                // element is there, remove then update
                int c = point.first.right.count;
                point.first.right = point.first.right.right;
                if (point.first.right != null) {
                    point.first.right.count += c - 1;
                }
            } else {
                // element is not there, just update
                point.first.right.count--;
            }
        }
        size--;
        return nodeToDelete.data;
    }

    /**
     * Clear the list.
     */
    public void clear() {
        head = new Node();
        currentLevels = 1;
        size = 0;
    }

    /**
     * Return the size of the list
     *
     * @return the size of the list
     */
    public int size() {
        return size;
    }

    /**
     * Get node at the specified index.
     *
     * @param index get node at that index
     * @return the node at that index
     */
    private Node getNode(int index) {
        Node current = head;
        int currentIndex = -1;
        while (current != null) {
            while (current.right != null && currentIndex + current.right.count <= index) {
                // while we didn't reach at the index needed
                currentIndex += current.right.count;
                current = current.right;
            }
            if (currentIndex == index) {
                // we reached the index needed
                break;
            }
            // traverse down
            current = current.down;
        }
        if (currentIndex == index) {
            return current;
        }
        return null;
    }

    /**
     * Get the value at the specified index.
     *
     * @param index the index we need the value at.
     * @return
     */
    public E get(int index) {
        return getNode(index).data;
    }

    /**
     * Return the list as a string.
     *
     * @return the string list.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
           Node current = head;
           int level = currentLevels;
           sb.append("Height of the skiplist: " + currentLevels + ", Size of the skipList: " + size + "\n");
           while (current != null) {
               Node curr = current.right;
               sb.append("Level "+ level +": [" + curr.data);
               curr = curr.right;
               while (curr != null) {
                   sb.append(", ").append(curr.data);
                   curr = curr.right;
               }
               sb.append("]\n");
               current = current.down;
               level--;
           }
           return sb.toString();
       }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	 SkipList<Integer> s = new SkipList<>();

         s.add(10);
         s.add(22);
         s.add(36);
         s.add(45);
         s.add(59);
         s.add(66);
         s.add(78);
         s.add(89);
         s.add(99);
         System.out.println(s);
         s.add(55);
         System.out.println(s);
         s.remove(5);
         System.out.println(s);

         s.add(3, -1000);
         System.out.println(s);

         System.out.println(s.get(0));
         System.out.println(s.get(1));
         System.out.println(s.get(2));
         System.out.println(s.get(3));
         System.out.println(s.get(4));
         System.out.println(s.get(5));
         System.out.println(s.get(6));
         System.out.println(s.get(7));
         System.out.println(s.get(8));
    }

}
