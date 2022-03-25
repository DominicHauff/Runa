package testapi.test;

public class IOResult {
    private final boolean passed;
    private final String expected;
    private final String actual;

    public IOResult(boolean passed) {
        this.passed = passed;
        expected = null;
        actual = null;
    }

    public IOResult(boolean passed, String expected, String actual) {
        this.passed = passed;
        this.actual = actual;
        this.expected = expected;
    }

    public boolean failed() {
        return !this.passed;
    }
}
