package exercise;

// BEGIN
public class Segment {
    Point beginPoint;
    Point endPoint;

    public Segment(Point start, Point end) {
        this.beginPoint = start;
        this.endPoint = end;
    }

    public Point getMidPoint() {
        return new Point((beginPoint.x + endPoint.x) / 2, (beginPoint.y + endPoint.y) / 2);
    }

    public Point getBeginPoint() {
        return beginPoint;
    }

    public void setBeginPoint(Point beginPoint) {
        this.beginPoint = beginPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }
}
// END
