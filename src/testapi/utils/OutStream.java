package testapi.utils;

import testapi.test.CodeTester;

import java.io.*;
import java.util.LinkedList;

public class OutStream extends PrintStream {
    private final LinkedList<String> printHistory;
    private boolean terminated;
    private boolean newElement;
    private int elementCounter;

    /**
     * Creates a new print stream, without automatic line flushing, with the
     * specified OutputStream. Characters written to the stream are converted
     * to bytes using the platform's default character encoding.
     *
     * @see PrintWriter#PrintWriter(OutputStream)
     * @param
     */
    public OutStream() {
        super(new ByteArrayOutputStream());
        printHistory = new LinkedList<>();
        this.terminated = false;
        this.elementCounter = 0;
    }

    @Override
    synchronized public void println(String x) {
        super.println(x);
        this.printHistory.push(x);
        this.newElement = true;
        this.elementCounter++;
        notifyAll();
    }

    @Override
    synchronized public void print(String x) {
        super.print(x);
        this.printHistory.push(x);
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
                if (newElement) return getNewElement();
                Thread listener = new Thread(() -> {
                    try {
                        while (CodeTester.systemIn.available() == 0) {
                            Thread.sleep(200);
                        }
                    } catch (IOException | InterruptedException ignore) {}
                });
                listener.start();
                wait(300);
                while (!terminated && listener.isAlive()) {
                    wait(5);
                    if (newElement) {
                        listener.interrupt();
                        return getNewElement();
                    }
                }
                listener.interrupt();
                if (terminated) {
                    CodeTester.terminal.println(CodeTester.ANSI_PURPLE + "APPLICATION TERMINATED" + CodeTester.ANSI_RESET);
                }
            }
            return null;
        } catch (InterruptedException i) {
            return null;
        }
    }

    private String getNewElement() {
        try {
            int temp = this.elementCounter;
            wait(50);
            while (temp != this.elementCounter) {
                temp = this.elementCounter;
                wait(10);
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
