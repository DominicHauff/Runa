package testapi.test;

public record TestResult(String testName, boolean passed, int sumPassed, int total, int percentage)
        implements Comparable< TestResult > {
    @Override
    public int compareTo(TestResult o) {
        if (this.passed == o.passed) return this.testName.compareTo(o.testName);
        return Integer.compare(this.percentage, o.percentage);
    }

    @Override
    public String toString() {
        String fString = "%70s:  %10s %s / %s  - %s\n";
        String passed = String.format(fString, testName.substring(0, testName.indexOf('.')), "passed", sumPassed, total, percentage + "%");
        return this.passed
                ? CodeTester.ANSI_GREEN
                + passed +
                CodeTester.ANSI_RESET
                : CodeTester.ANSI_RED
                + passed +
                CodeTester.ANSI_RESET;
    }
}
