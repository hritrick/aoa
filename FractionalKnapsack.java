import java.util.Scanner;

public class FractionalKnapsack {
    static class Item {
        int id;
        double val;
        double wt;
        double ratio;

        Item(int id, double val, double wt) {
            this.id = id;
            this.val = val;
            this.wt = wt;
            this.ratio = val / wt;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of items: ");
        int n = sc.nextInt();

        Item[] items = new Item[n];
        System.out.println("Enter value and weight for each item:");
        for (int i = 0; i < n; i++) {
            System.out.print("Item " + (i + 1) + " (value weight): ");
            double v = sc.nextDouble();
            double w = sc.nextDouble();
            items[i] = new Item(i + 1, v, w);
        }

        System.out.print("Enter knapsack capacity: ");
        double capacity = sc.nextDouble();

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (items[j].ratio < items[j + 1].ratio) {
                    Item temp = items[j];
                    items[j] = items[j + 1];
                    items[j + 1] = temp;
                }
            }
        }

        double totalVal = 0;
        double remain = capacity;

        System.out.println("Items taken:");
        for (int i = 0; i < n; i++) {
            if (remain <= 0) {
                break;
            }
            if (items[i].wt <= remain) {
                remain -= items[i].wt;
                totalVal += items[i].val;
                System.out.println("Item " + items[i].id + ": weight = " + items[i].wt + ", value = " + items[i].val + " (taken 100%)");
            } else {
                double fraction = remain / items[i].wt;
                double takenVal = items[i].val * fraction;
                totalVal += takenVal;
                System.out.println("Item " + items[i].id + ": weight = " + remain + ", value = " + takenVal + " (taken " + (fraction * 100) + "%)");
                remain = 0;
            }
        }

        System.out.println("Maximum value: " + totalVal);
    }
}
