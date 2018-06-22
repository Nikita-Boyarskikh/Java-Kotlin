import java.util.InputMismatchException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Point {
    double x, y;

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static double distance(Point a, Point b) {
        return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    }

    public static double perimeter(Point[] points) {
        if (points.length < 2) {
            return 0;
        }

        double perimeter = distance(points[0], points[points.length - 1]);

        for (int i = 1; i < points.length; i++) {
            perimeter += distance(points[i], points[i - 1]);
        }

        return perimeter;
    }

    public static void main(String[] args) {
        final List<Point> points = new ArrayList<>();
        final boolean success = inputPoints(points);

        if (success) {
            final double perimeter = Point.perimeter(points.toArray(new Point[points.size()]));
            System.out.printf("Total perimeter: %f\n", perimeter);
        }
    }

    private static boolean inputPoints(List<Point> points) {
        final Scanner scanner = new Scanner(System.in);

        System.out.print("Enter points number: ");
        int pointsNumber = 0;

        if (scanner.hasNextInt()) {
            pointsNumber = scanner.nextInt();
        } else {
            System.err.println("Point number must be an integer!");
            return false;
        }

        if (pointsNumber < 0) {
            System.err.println("Point number can't be negative!");
            return false;
        }

        for (int i = 0; i < pointsNumber; i++) {
            double x, y;

            try {
                System.out.printf("%d)\tx: ", i + 1);
                x = scanner.nextDouble();

                System.out.printf("\ty: ", i + 1);
                y = scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.err.println("Wrong input format! Please, try again...");
                scanner.next();
                i--;
                continue;
            }

            Point point = new Point(x, y);
            points.add(point);

            System.out.println();
        }

        return true;
    }
}