package testapi.utils;

import testapi.test.CodeTester;

import java.io.*;
import java.util.LinkedList;

public class OutStream extends PrintStream {
    private final LinkedList<String> printHistory;
    private boolean terminated;
    private boolean newElement;
    private int elementCounter;
    private int speed;

    /**
     * Creates a new print stream, without automatic line flushing, with the
     * specified OutputStream. Characters written to the stream are converted
     * to bytes using the platform's default character encoding.
     *
     * @see PrintWriter#PrintWriter(OutputStream)
     */
    public OutStream() {
        super(new ByteArrayOutputStream());
        printHistory = new LinkedList<>();
        this.terminated = false;
        this.elementCounter = 0;
        this.speed = 15;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    synchronized public void println(String x) {
        super.println(x);
        this.printHistory.push(x + System.lineSeparator());
        this.newElement = true;
        this.elementCounter++;
        notifyAll();
    }

    synchronized public String getNextString(boolean outputExpected) {
        if (newElement) return getNewElement();
        if (terminated) {
            CodeTester.terminal.println(CodeTester.ANSI_PURPLE + "APPLICATION TERMINATED" + CodeTester.ANSI_RESET);
            return null;
        }
        try {
            if (!outputExpected) {
                for (int i = 0; i < 20; i++) {
                    if (newElement) return getNewElement();
                    wait(1);
                }
            } else {
                while (!terminated) {
                    wait(1);
                    if (newElement) {
                        return getNewElement();
                    }
                }
                CodeTester.terminal.println(CodeTester.ANSI_PURPLE + "APPLICATION TERMINATED" + CodeTester.ANSI_RESET);
            }
            return null;
        } catch (InterruptedException i) {
            return null;
        }
    }

    private String getNewElement() {
        try {
            int temp = this.elementCounter;
            wait(speed);
            while (temp != this.elementCounter) {
                temp = this.elementCounter;
                wait(5);
            }
        } catch (InterruptedException e) {
            e.printStackTrace(CodeTester.terminal);
        }
        this.newElement = false;
        final StringBuilder sb = new StringBuilder();
        for (int i = this.elementCounter - 1; i >= 0; i--) {
            sb.append(this.printHistory.get(i));
        }
        this.elementCounter = 0;
        this.printHistory.clear();
        return sb.toString().trim();
    }


    synchronized public void readingThreadTerminated() {
        this.terminated = true;
    }

}
