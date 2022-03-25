package testapi.utils;

import testapi.test.CodeTester;

import java.io.*;
import java.util.LinkedList;

public class OutStream extends PrintStream {
    private final LinkedList<String> printHistory;
    private boolean terminated;
    private boolean newElement;
    private int elementCounter;
    private boolean skip;

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
        this.skip = false;
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
                wait(1000);
                if (newElement) return getNewElement();
                Thread listener = new Thread(() -> {
                    BufferedReader br = new BufferedReader(new InputStreamReader(CodeTester.systemIn));
                    if (!terminated) {
                        CodeTester.terminal.println(
                                CodeTester.ANSI_PURPLE +
                                        "waiting for output... if you want to continue type something and hit enter!"
                                        + CodeTester.ANSI_RESET);
                    }
                    try {
                        while (!newElement && !terminated && !br.ready()) {
                            synchronized (this) {
                                wait(10);
                            }
                        }
                        if (br.ready()) {
                            skip = true;
                            String ignore = br.readLine();
                        }
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace(CodeTester.terminal);
                    }
                }, "input-listener");
                listener.start();
                while (!terminated && !skip) {
                    wait(5);
                    if (newElement) {
                        listener.stop();
                        return getNewElement();
                    }
                }
                if (terminated) {
                    CodeTester.terminal.println(CodeTester.ANSI_PURPLE + "APPLICATION TERMINATED" + CodeTester.ANSI_RESET);
                }
                skip = false;
            }
            return null;
        } catch (InterruptedException i) {
            return null;
        }
    }

    private String getNewElement() {
        try {
            int temp = this.elementCounter;
            wait(5);
            while (temp != this.elementCounter) {
                temp = this.elementCounter;
                wait(2);
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
