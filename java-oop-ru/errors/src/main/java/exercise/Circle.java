package exercise;

// BEGIN
public class Circle {
    public Point point;
    public int radius;

    public Circle(Point point, int radius) {
        this.point = point;
        this.radius = radius;
    }

    public double getSquare() throws NegativeRadiusException {
        if(this.radius < 0) {
            throw new NegativeRadiusException("Не удалось посчитать площадь");
        }

        return Math.PI * (radius * radius);
    }

    public int getRadius() {
        return radius;
    }
}
// END
