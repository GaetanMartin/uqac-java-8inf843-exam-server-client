package app;

import java.io.Serializable;

public class Message implements Serializable {

    static final long serialVersionUID = 42L;

    private static final String OPERATION_SEPARATOR = ":";
    private static final String OPERANDE_SEPARATOR = "-";

    String operation;
    int a, b;

    public String getOperation() {
        return operation;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public Message(String operation, int a, int b) {
        this.operation = operation;
        this.a = a;
        this.b = b;
    }

    @Override
    public String toString() {
        return operation + OPERANDE_SEPARATOR + a + OPERATION_SEPARATOR + b;
    }
}
