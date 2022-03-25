package testapi.test;

import testapi.utils.InStream;
import testapi.utils.OutStream;

public class ApplicationThread extends Thread {
    private final Thread runner;
    private final UncaughtExceptionHandler exceptionHandler;
    private final OutStream outStream;
    private final InStream inStream;

    public ApplicationThread(Application runnable, InStream inStream, OutStream outStream) {
        super("ApplicationThread");
        this.outStream = outStream;
        this.inStream = inStream;
        this.runner = new Thread(runnable, "runner");
        exceptionHandler = (t, e) -> {
            inStream.readingThreadTerminated();
            outStream.readingThreadTerminated();
            if (!e.getClass().equals(ThreadDeath.class)) {
                CodeTester.terminal.println(CodeTester.ANSI_PURPLE + "APPLICATION TERMINATED" + CodeTester.ANSI_RESET);
                e.printStackTrace(CodeTester.terminal);
            }
        };
    }

    @Override
    public void run() {
        runner.setUncaughtExceptionHandler(exceptionHandler);
        checkRunner();
        runner.start();
    }

    private void checkRunner() {
        new Thread(() -> {
            while (!runner.getState().equals(State.TERMINATED)) {
                try {
                    synchronized (this) {
                        wait(1);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace(CodeTester.terminal);
                }
            }
            outStream.readingThreadTerminated();
            inStream.readingThreadTerminated();
        }).start();
    }

    public boolean hasTerminated() {
        return runner.getState().equals(State.TERMINATED);
    }

    public void kill() {
        runner.stop();
    }
}
