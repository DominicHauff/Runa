package testapi.utils;

import java.io.IOException;
import java.io.InputStream;

public class InStream extends InputStream {
    private String input;
    private int i;
    private boolean readingThreadTerminated = false;
    private boolean newInputAvailable = false;

    /**
     * Reads the next byte of data from the input stream. The value byte is
     * returned as an {@code int} in the range {@code 0} to
     * {@code 255}. If no byte is available because the end of the stream
     * has been reached, the value {@code -1} is returned. This method
     * blocks until input data is available, the end of the stream is detected,
     * or an exception is thrown.
     *
     * <p> A subclass must provide an implementation of this method.
     *
     * @return the next byte of data, or {@code -1} if the end of the
     * stream is reached.
     * @throws IOException if an I/O error occurs.
     */
    @Override
    synchronized public int read() throws IOException {
        if (!newInputAvailable) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (i >= this.input.length()) {
            this.newInputAvailable = false;
            notifyAll();
            throw new IOException();
        }
        return input.toCharArray()[i++];
    }

    synchronized public void setInput(String input) {
        if (newInputAvailable) {
            try {
                if (!readingThreadTerminated) {
                    wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.input = input;
        this.newInputAvailable = true;
        this.i = 0;
        notifyAll();
    }

    public void readingThreadTerminated() {
        this.readingThreadTerminated = true;
    }
}
