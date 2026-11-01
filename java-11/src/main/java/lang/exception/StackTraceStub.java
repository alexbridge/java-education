package lang.exception;

public class StackTraceStub {
    public void method1() throws Exception {
        method2();
    }

    public void method2() throws Exception {
        throw method3();
    }

    public Exception method3() {
        return new Exception("method3");
    }
}
