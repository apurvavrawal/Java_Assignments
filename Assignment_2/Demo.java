public class Demo {
    public static void main(String[] args) {
        Sender sender = new Sender();

        ThreadedSend threadHi = new ThreadedSend(sender, "Hi");
        ThreadedSend threadBye = new ThreadedSend(sender, "Bye");

        threadHi.start();
        threadBye.start();

        try {
            threadHi.join();
            threadBye.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
