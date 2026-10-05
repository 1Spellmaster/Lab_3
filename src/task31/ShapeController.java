package task31;

import java.util.Comparator;

public class ShapeController {
    private final ShapeModel model;
    private final ShapeView view;

    public ShapeController(ShapeModel model, ShapeView view) {
        this.model = model;
        this.view = view;
    }

    public void run(Class<? extends Shape> type) {
        view.showShapes("Початковий набір фігур", model.getShapes());
        view.showArea("Сумарна площа всіх фігур", model.getTotalArea());
        view.showArea("Сумарна площа фігур типу " + type.getSimpleName(),
                model.getTotalAreaByType(type));

        Comparator<Shape> areaComparator = Comparator.comparingDouble(Shape::calcArea);
        model.sort(areaComparator);
        view.showShapes("Сортування за зростанням площі", model.getShapes());

        Comparator<Shape> colorComparator = Comparator.comparing(Shape::getShapeColor);
        model.sort(colorComparator);
        view.showShapes("Сортування за кольором", model.getShapes());
    }
}
