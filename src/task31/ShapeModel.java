package task31;

import java.util.Arrays;
import java.util.Comparator;

public class ShapeModel {
    private final Shape[] shapes;

    public ShapeModel(Shape[] shapes) {
        this.shapes = shapes;
    }

    public Shape[] getShapes() {
        return shapes;
    }

    public double getTotalArea() {
        double total = 0;
        for (Shape shape : shapes) {
            total += shape.calcArea();
        }
        return total;
    }

    public double getTotalAreaByType(Class<? extends Shape> type) {
        double total = 0;
        for (Shape shape : shapes) {
            if (type.isInstance(shape)) {
                total += shape.calcArea();
            }
        }
        return total;
    }

    public void sort(Comparator<Shape> comparator) {
        Arrays.sort(shapes, comparator);
    }
}
