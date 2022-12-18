package lab1.implication;

public class GoguenImplication implements Implication {
    @Override
    public double evaluate(double x, double y) {
        if ((x < 0 || x > 1) || (y < 0 || y > 1))
            throw new IllegalArgumentException("Wrong args");

        if (x > 0) return Math.min(1, y/x);
        else return 1;
    }
}
