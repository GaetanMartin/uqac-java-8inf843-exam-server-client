package app;

public class Calculatrice implements CalculatriceItf {

    @Override
    public int multiply(int a, int b) {
        return  a*b;
    }

    @Override
    public int add(int a, int b) {
        return a+b;
    }

    @Override
    public int substract(int a, int b) {
        return a-b;
    }
}
