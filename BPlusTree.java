import java.util.Scanner;

public class BPlusTree {
    static class Node {
        boolean isLeaf;
        int n;
        int[] keys;
        Node[] child;
        Node next;

        Node(boolean isLeaf, int t) {
            this.isLeaf = isLeaf;
            this.keys = new int[2 * t];
            this.child = new Node[2 * t + 1];
            this.n = 0;
            this.next = null;
        }
    }

    static class SplitResult {
        int key;
        Node newNode;

        SplitResult(int key, Node newNode) {
            this.key = key;
            this.newNode = newNode;
        }
    }

    Node root;
    int t;

    public BPlusTree(int t) {
        this.t = t;
        this.root = new Node(true, t);
    }

    public void insert(int key) {
        SplitResult res = insertInternal(root, key);
        if (res != null) {
            Node newRoot = new Node(false, t);
            newRoot.keys[0] = res.key;
            newRoot.child[0] = root;
            newRoot.child[1] = res.newNode;
            newRoot.n = 1;
            root = newRoot;
        }
    }

    private SplitResult insertInternal(Node node, int key) {
        if (node.isLeaf) {
            int i = 0;
            while (i < node.n && node.keys[i] < key) {
                i++;
            }
            for (int j = node.n; j > i; j--) {
                node.keys[j] = node.keys[j - 1];
            }
            node.keys[i] = key;
            node.n++;

            if (node.n == 2 * t) {
                Node newNode = new Node(true, t);
                int mid = t;
                newNode.n = node.n - mid;
                for (int j = 0; j < newNode.n; j++) {
                    newNode.keys[j] = node.keys[mid + j];
                }
                node.n = mid;
                newNode.next = node.next;
                node.next = newNode;
                return new SplitResult(newNode.keys[0], newNode);
            }
            return null;
        } else {
            int i = 0;
            while (i < node.n && key >= node.keys[i]) {
                i++;
            }
            SplitResult res = insertInternal(node.child[i], key);
            if (res != null) {
                int insertKey = res.key;
                Node insertChild = res.newNode;
                int j = node.n;
                while (j > 0 && node.keys[j - 1] > insertKey) {
                    node.keys[j] = node.keys[j - 1];
                    node.child[j + 1] = node.child[j];
                    j--;
                }
                node.keys[j] = insertKey;
                node.child[j + 1] = insertChild;
                node.n++;

                if (node.n == 2 * t) {
                    Node newNode = new Node(false, t);
                    int mid = t;
                    int promotedKey = node.keys[mid];
                    newNode.n = node.n - mid - 1;
                    for (int k = 0; k < newNode.n; k++) {
                        newNode.keys[k] = node.keys[mid + 1 + k];
                    }
                    for (int k = 0; k <= newNode.n; k++) {
                        newNode.child[k] = node.child[mid + 1 + k];
                    }
                    node.n = mid;
                    return new SplitResult(promotedKey, newNode);
                }
            }
            return null;
        }
    }

    public boolean search(int key) {
        Node curr = root;
        while (!curr.isLeaf) {
            int i = 0;
            while (i < curr.n && key >= curr.keys[i]) {
                i++;
            }
            curr = curr.child[i];
        }
        for (int i = 0; i < curr.n; i++) {
            if (curr.keys[i] == key) {
                return true;
            }
        }
        return false;
    }

    public void printLeaves() {
        Node curr = root;
        while (!curr.isLeaf) {
            curr = curr.child[0];
        }
        while (curr != null) {
            for (int i = 0; i < curr.n; i++) {
                System.out.print(curr.keys[i] + " ");
            }
            System.out.print("-> ");
            curr = curr.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter degree t: ");
        int t = sc.nextInt();
        BPlusTree tree = new BPlusTree(t);

        System.out.print("Enter number of elements to insert: ");
        int n = sc.nextInt();
        System.out.println("Enter " + n + " elements:");
        for (int i = 0; i < n; i++) {
            tree.insert(sc.nextInt());
        }

        System.out.print("Leaf nodes in order: ");
        tree.printLeaves();

        System.out.print("Enter element to search: ");
        int key = sc.nextInt();
        if (tree.search(key)) {
            System.out.println("Element " + key + " found in B+ Tree");
        } else {
            System.out.println("Element " + key + " not found in B+ Tree");
        }
    }
}

/*
SAMPLE INPUT:
Enter degree t: 3
Enter number of elements to insert: 6
Enter 6 elements:
10 20 5 6 12 30
Enter element to search: 12

SAMPLE OUTPUT:
Leaf nodes in order: 5 6 10 -> 12 20 30 -> null
Element 12 found in B+ Tree
*/

