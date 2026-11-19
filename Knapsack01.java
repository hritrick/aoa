import java.util.Scanner;

public class Knapsack01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of items: ");
        int n = sc.nextInt();

        int[] val = new int[n];
        int[] wt = new int[n];

        System.out.println("Enter values of " + n + " items:");
        for (int i = 0; i < n; i++) {
            val[i] = sc.nextInt();
        }

        System.out.println("Enter weights of " + n + " items:");
        for (int i = 0; i < n; i++) {
            wt[i] = sc.nextInt();
        }

        System.out.print("Enter knapsack capacity: ");
        int W = sc.nextInt();

        int[][] dp = new int[n + 1][W + 1];

        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= W; w++) {
                if (wt[i - 1] <= w) {
                    dp[i][w] = Math.max(val[i - 1] + dp[i - 1][w - wt[i - 1]], dp[i - 1][w]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        System.out.println("DP Table:");
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= W; w++) {
                System.out.print(dp[i][w] + "\t");
            }
            System.out.println();
        }

        System.out.println("Maximum value: " + dp[n][W]);
    }
}

/*
SAMPLE INPUT:
Enter number of items: 4
Enter values of 4 items:
10 40 30 50
Enter weights of 4 items:
5 4 6 3
Enter knapsack capacity: 10

SAMPLE OUTPUT:
DP Table:
0	0	0	0	0	0	0	0	0	0	0	
0	0	0	0	0	10	10	10	10	10	10	
0	0	0	0	40	40	40	40	40	50	50	
0	0	0	0	40	40	40	40	40	50	70	
0	0	0	50	50	50	50	90	90	90	90	
Maximum value: 90
*/

