package sample;

//Класс в котором идет сортировка ArrayList-ов по Х без потери индекса связи с У

public class Points implements Comparable<Points> {
    double x;
    double y;
    public Points(double x, double y) {
        this.x=x;
        this.y=y;
    }


    @Override
    public int compareTo(Points o) {
        return x > o.x ? 1 : x == o.x ? 0 : -1;
    }
}
