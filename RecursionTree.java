import java.util.Scanner;

public class RecursionTree {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a: ");
        int a = sc.nextInt();
        System.out.print("Enter b: ");
        int b = sc.nextInt();
        System.out.print("Enter k (power of n in f(n) = n^k): ");
        int k = sc.nextInt();
        System.out.print("Enter n: ");
        int n = sc.nextInt();

        int levels = (int) (Math.log(n) / Math.log(b)) + 1;
        System.out.println("Number of levels: " + levels);

        double totalCost = 0;
        double currentSize = n;

        for (int i = 0; i < levels; i++) {
            long nodes = (long) Math.pow(a, i);
            double costPerNode = Math.pow(currentSize, k);
            double levelCost = nodes * costPerNode;
            System.out.println("Level " + i + ": nodes = " + nodes + ", subproblem size = " + currentSize + ", cost = " + levelCost);
            totalCost += levelCost;
            currentSize = currentSize / b;
        }

        System.out.println("Total Cost: " + totalCost);
    }
}
