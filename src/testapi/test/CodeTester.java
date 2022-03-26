package testapi.test;

import testapi.utils.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.*;

public class CodeTester {
    public static final PrintStream terminal = System.out;
    public static final InputStream systemIn = System.in;
    public static final List< File > testFiles = new ArrayList<>();
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    private static final Map< String, TestResult > testResultMap = new HashMap<>();
    private static final String EXIT_KEYWORD = "quit";
    private static InStream inStream;
    private static OutStream outStream;
    private final Application runnable;
    private ApplicationThread applicationThread;


    public CodeTester(final Application runnable, final String testFileDirectoryPath, final FileConverter curseOfTheHanseaticFileConvert) {
        this.runnable = runnable;
        String[] testFilePaths = new File(testFileDirectoryPath).list();
        if (testFilePaths == null) {
            throw new RuntimeException("could not find testFile directory :( ...try another path");
        }
        curseOfTheHanseaticFileConvert.convert();
        Arrays.stream(testFilePaths)
                .forEach(path -> testFiles.add(new File(testFileDirectoryPath + "/" + path)));
    }

    public static void initialize() {
        inStream = new InStream();
        outStream = new OutStream();
        System.setIn(inStream);
        System.setOut(outStream);
        System.setErr(outStream);
    }

    public void runTests() {
        testFiles.forEach(testFile -> {
            initialize();
            final TestFileReader reader = new TestFileReader(testFile);
            runnable.setArgs(reader.getArgs());
            this.applicationThread = new ApplicationThread(runnable, inStream, outStream);
            applicationThread.start();
            terminal.printf(ANSI_GREEN + "[TESTING TESTFILE '%s']%n" + ANSI_RESET, testFile.getName());
            InOutPair pair;
            List< IOResult > IOResults = new ArrayList<>();
            while ((pair = reader.getNextPair()) != null) {
                try {
                    IOResults.add(interact(pair));
                } catch (InterruptedException e) {
                    e.printStackTrace(terminal);
                }
            }
            if (!applicationThread.hasTerminated()) {
                terminal.println(ANSI_RED + "expect application to terminate but found it still running" + ANSI_RESET);
                applicationThread.kill();
            }
            final int passedSum = IOResults.size() - (int) IOResults.stream().filter(IOResult::failed).count();
            final int percentage = (int) (((double) passedSum / IOResults.size()) * 100);
            printResults(IOResults, passedSum, IOResults.size(), percentage);
            testResultMap.put(testFile.getName(), new TestResult(
                    testFile.getName(), percentage == 100, passedSum, IOResults.size(), percentage));
        });
        terminal.println(ANSI_PURPLE + "________________________________________________________________" + ANSI_RESET);
        terminal.println(ANSI_PURPLE + "-----------------------------<SUMMARY>--------------------------\n" + ANSI_RESET);
        StringBuilder sb = new StringBuilder();
        List< TestResult > toSort = new ArrayList<>();
        Collection<TestResult> bruh = testResultMap.values();
        for (TestResult testResult : testResultMap.values()) {
            toSort.add(testResult);
        }
        toSort.sort(null);
        for (TestResult testResult : toSort) {
            sb.append(testResult).append(System.lineSeparator());
        }
        terminal.println(sb);
    }

    private void printResults(List< IOResult > results, int passedSum, int total, int percentage) {
        final boolean passed = results.stream().noneMatch(IOResult::failed);
        terminal.println(ANSI_PURPLE + "________________________________________________________________" + ANSI_RESET);
        terminal.println(ANSI_PURPLE + "--------------------------<TEST-RESULTS>------------------------\n" + ANSI_RESET);
        terminal.print("                         ");
        if (passed) {
            terminal.print(ANSI_GREEN + "[TEST PASSED!!!]\n" + ANSI_RESET);
        } else {
            terminal.print(ANSI_RED + "[TEST FAILED!!!]\n" + ANSI_RESET);
        }
        terminal.printf("passed: %s / %s - %s", passedSum, total, percentage + "%\n");
        terminal.println(ANSI_PURPLE + "________________________________________________________________\n" + ANSI_RESET);
    }

    synchronized private IOResult interact(InOutPair pair) throws InterruptedException {
        if (pair.input() != null) {
            inStream.setInput(pair.input() + System.lineSeparator());
        }
        terminal.println(ANSI_BLUE + ">" + pair.input() + ANSI_RESET);
        if (pair.input() != null && pair.input().equals(EXIT_KEYWORD)) {
            if (!applicationThread.hasTerminated())
                wait(50);
            if (!applicationThread.hasTerminated()) {
                terminal.println(ANSI_RED + "expect application to terminate but found it still running!" + ANSI_RESET);
                applicationThread.kill();
                return new IOResult(false, "termination of application", "application still running");
            }
            return new IOResult(true);
        }
        final String result = outStream.getNextString(!pair.output().equals(""));
        if (pair.output().equals("")) {
            if (result != null) {
                terminal.printf(ANSI_RED + "expect no output but found: '%s'%n" + ANSI_RESET, result);
                return new IOResult(false, "no output", result);
            }
            return new IOResult(true);
        } else if (pair.output().startsWith("Error,")) {
            if (result == null || !result.startsWith("Error,")) {
                if (result != null) terminal.println(result);
                terminal.println(ANSI_RED + "expected an error message" + ANSI_RESET);
                return new IOResult(false, "Error,...", result == null ? "no output" : result);
            }
            terminal.println(result);
            return new IOResult(true);
        } else if (result != null && !this.outputEquals(pair.output(), result)) {
            terminal.println(result);
            terminal.printf(ANSI_RED + "Expected:%n%s%n" + ANSI_RESET, pair.output());
            return new IOResult(false, String.format("Expected:%n%s%n", pair.input()), result);
        } else if (result == null) {
            terminal.printf(ANSI_RED + "Expected:%n%s%n" + ANSI_RESET, pair.output());
            return new IOResult(false, pair.output(), "");
        }
        terminal.println(result);
        return new IOResult(true);
    }

    public boolean outputEquals(String expected, String actual) {
        List<String> expectedStrings = Arrays.asList(expected.split("\r\n"));
        List<String> actualListNewLine = Arrays.asList(actual.split("\n"));
        List<String> actualList = new ArrayList<>();
        actualListNewLine
                .forEach(s -> {
                    if (s.endsWith("\r")) actualList.add(s.substring(0, s.indexOf("\r")));
                    else actualList.add(s);
                });
        return expectedStrings.equals(actualList);
    }

}