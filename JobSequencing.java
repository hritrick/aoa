import java.util.Scanner;

public class JobSequencing {
    static class Job {
        String id;
        int deadline;
        int profit;

        Job(String id, int deadline, int profit) {
            this.id = id;
            this.deadline = deadline;
            this.profit = profit;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of jobs: ");
        int n = sc.nextInt();

        Job[] jobs = new Job[n];
        int maxDeadline = 0;
        System.out.println("Enter Job ID, Deadline, and Profit for each job:");
        for (int i = 0; i < n; i++) {
            System.out.print("Job " + (i + 1) + ": ");
            String id = sc.next();
            int d = sc.nextInt();
            int p = sc.nextInt();
            jobs[i] = new Job(id, d, p);
            if (d > maxDeadline) {
                maxDeadline = d;
            }
        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (jobs[j].profit < jobs[j + 1].profit) {
                    Job temp = jobs[j];
                    jobs[j] = jobs[j + 1];
                    jobs[j + 1] = temp;
                }
            }
        }

        String[] result = new String[maxDeadline + 1];
        boolean[] slot = new boolean[maxDeadline + 1];
        int totalProfit = 0;

        for (int i = 0; i < n; i++) {
            for (int j = jobs[i].deadline; j > 0; j--) {
                if (!slot[j]) {
                    slot[j] = true;
                    result[j] = jobs[i].id;
                    totalProfit += jobs[i].profit;
                    break;
                }
            }
        }

        System.out.print("Job order: ");
        for (int i = 1; i <= maxDeadline; i++) {
            if (slot[i]) {
                System.out.print(result[i] + " ");
            }
        }
        System.out.println();
        System.out.println("Total profit: " + totalProfit);
    }
}
