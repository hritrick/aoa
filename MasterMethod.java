import java.util.Scanner;

public class MasterMethod {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a: ");
        double a = sc.nextDouble();
        System.out.print("Enter b: ");
        double b = sc.nextDouble();
        System.out.print("Enter k (exponent of n in f(n) = n^k): ");
        double k = sc.nextDouble();

        double p = Math.log(a) / Math.log(b);
        System.out.println("log_b(a) = " + p);
        System.out.println("k = " + k);

        if (p > k) {
            System.out.println("Case 1 applies: log_b(a) > k");
            System.out.println("Time Complexity: T(n) = Theta(n^" + p + ")");
        } else if (Math.abs(p - k) < 1e-6) {
            System.out.println("Case 2 applies: log_b(a) == k");
            System.out.println("Time Complexity: T(n) = Theta(n^" + k + " * log n)");
        } else {
            System.out.println("Case 3 applies: log_b(a) < k");
            System.out.println("Time Complexity: T(n) = Theta(n^" + k + ")");
        }
    }
}
