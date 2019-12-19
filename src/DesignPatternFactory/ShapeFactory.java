package DesignPatternFactory;

public class ShapeFactory {
    public Shape getShape(String s) {
        if (s == null) return null;
        if (s.equalsIgnoreCase("Circle")) {
            return new ShapeCircle();
        } else if (s.equalsIgnoreCase("Square")) {
            return new ShapeSquare();
        }

        return null;
    }
}
