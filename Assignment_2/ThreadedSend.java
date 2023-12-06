public class ThreadedSend extends Thread {
    private Sender sender;
    private String message;

    public ThreadedSend(Sender sender, String message) {
        this.sender = sender;
        this.message = message;
    }

    @Override
    public void run() {
        synchronized (sender) {
            sender.send(message);
        }
    }

}

