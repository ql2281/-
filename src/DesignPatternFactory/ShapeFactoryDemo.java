package DesignPatternFactory;

public class ShapeFactoryDemo {
    public static void main(String[] args) {
        ShapeFactory shapeFactory = new ShapeFactory();
        Shape shape1 = shapeFactory.getShape("Circle");
        shape1.draw();

        Shape shape2 = shapeFactory.getShape("Square");
        shape2.draw();


        // abstract method factory
        MethodFactory factory = new MethodCircleFactory();
        Shape shape3 = factory.getShape();
        shape3.draw();
    }
}
