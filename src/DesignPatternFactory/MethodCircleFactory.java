package DesignPatternFactory;

public class MethodCircleFactory implements MethodFactory {
    @Override
    public Shape getShape() {
        return new ShapeCircle();
    }
}
