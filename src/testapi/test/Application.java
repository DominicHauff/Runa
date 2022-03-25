package testapi.test;

public class Application implements Runnable {
    private String[] args;
    public Application(String[] args) {
        this.args = args;
    }

    @Override
    public void run() {
        runasstrive.Application.main(this.args);
    }

    public void setArgs(String[] args) {
        this.args = args;
    }
}
