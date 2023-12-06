public class SynchronizedNumbers implements Runnable {
    private static final Object lock = new Object();
    private static int counter = 1;
    private final int id;

    public SynchronizedNumbers(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        while (counter <= 10) {
            synchronized (lock) {
                if (counter % 2 == id) {
                    System.out.println("Thread " + id + ": " + counter);
                    counter++;
                }
            }
        }
    }
    public static void main(String[] args) {
        Thread thread1 = new Thread(new SynchronizedNumbers(1));
        Thread thread2 = new Thread(new SynchronizedNumbers(0));

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
