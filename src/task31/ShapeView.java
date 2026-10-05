package task31;

public class ShapeView {
    public void showShapes(String title, Shape[] shapes) {
        System.out.println("\n" + title);
        for (Shape shape : shapes) {
            shape.draw();
        }
    }

    public void showArea(String title, double area) {
        System.out.printf("%s: %.2f%n", title, area);
    }
}
