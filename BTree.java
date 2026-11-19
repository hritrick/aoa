import java.util.Scanner;

public class BTree {
    static class Node {
        int[] keys;
        int t;
        Node[] child;
        int n;
        boolean leaf;

        Node(int t, boolean leaf) {
            this.t = t;
            this.leaf = leaf;
            this.keys = new int[2 * t - 1];
            this.child = new Node[2 * t];
            this.n = 0;
        }

        void traverse() {
            int i;
            for (i = 0; i < n; i++) {
                if (!leaf) {
                    child[i].traverse();
                }
                System.out.print(keys[i] + " ");
            }
            if (!leaf) {
                child[i].traverse();
            }
        }

        Node search(int k) {
            int i = 0;
            while (i < n && k > keys[i]) {
                i++;
            }
            if (i < n && keys[i] == k) {
                return this;
            }
            if (leaf) {
                return null;
            }
            return child[i].search(k);
        }

        void insertNonFull(int k) {
            int i = n - 1;
            if (leaf) {
                while (i >= 0 && keys[i] > k) {
                    keys[i + 1] = keys[i];
                    i--;
                }
                keys[i + 1] = k;
                n++;
            } else {
                while (i >= 0 && keys[i] > k) {
                    i--;
                }
                i++;
                if (child[i].n == 2 * t - 1) {
                    splitChild(i, child[i]);
                    if (keys[i] < k) {
                        i++;
                    }
                }
                child[i].insertNonFull(k);
            }
        }

        void splitChild(int i, Node y) {
            Node z = new Node(y.t, y.leaf);
            z.n = t - 1;
            for (int j = 0; j < t - 1; j++) {
                z.keys[j] = y.keys[j + t];
            }
            if (!y.leaf) {
                for (int j = 0; j < t; j++) {
                    z.child[j] = y.child[j + t];
                }
            }
            y.n = t - 1;
            for (int j = n; j >= i + 1; j--) {
                child[j + 1] = child[j];
            }
            child[i + 1] = z;
            for (int j = n - 1; j >= i; j--) {
                keys[j + 1] = keys[j];
            }
            keys[i] = y.keys[t - 1];
            n++;
        }
    }

    Node root;
    int t;

    public BTree(int t) {
        this.root = null;
        this.t = t;
    }

    public void traverse() {
        if (root != null) {
            root.traverse();
            System.out.println();
        } else {
            System.out.println("Tree is empty");
        }
    }

    public Node search(int k) {
        if (root == null) {
            return null;
        }
        return root.search(k);
    }

    public void insert(int k) {
        if (root == null) {
            root = new Node(t, true);
            root.keys[0] = k;
            root.n = 1;
        } else {
            if (root.n == 2 * t - 1) {
                Node s = new Node(t, false);
                s.child[0] = root;
                s.splitChild(0, root);
                int i = 0;
                if (s.keys[0] < k) {
                    i++;
                }
                s.child[i].insertNonFull(k);
                root = s;
            } else {
                root.insertNonFull(k);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter minimum degree t: ");
        int t = sc.nextInt();
        BTree tree = new BTree(t);

        System.out.print("Enter number of elements to insert: ");
        int n = sc.nextInt();
        System.out.println("Enter " + n + " elements:");
        for (int i = 0; i < n; i++) {
            tree.insert(sc.nextInt());
        }

        System.out.print("Traversal of B-Tree: ");
        tree.traverse();

        System.out.print("Enter element to search: ");
        int key = sc.nextInt();
        if (tree.search(key) != null) {
            System.out.println("Element " + key + " found in B-Tree");
        } else {
            System.out.println("Element " + key + " not found in B-Tree");
        }
    }
}

/*
SAMPLE INPUT:
Enter minimum degree t: 3
Enter number of elements to insert: 7
Enter 7 elements:
10 20 5 6 12 30 7
Enter element to search: 12

SAMPLE OUTPUT:
Traversal of B-Tree: 5 6 7 10 12 20 30 
Element 12 found in B-Tree
*/

