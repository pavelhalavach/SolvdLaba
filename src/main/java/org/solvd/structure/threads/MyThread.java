package main.java.org.solvd.structure.threads;

public class MyThread extends Thread{
    public void run() {
        System.out.println("Hello from a thread!");
        for (int i = 1; i <= 5; i++) {
            Counter.increment();
            System.out.println("Count: " + Counter.getCount());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted: " + e.getMessage());
            }
        }
    }

    private class Counter {
        private static int count;
        public static void increment() {
            count++;
        }

        public static int getCount() {
            return count;
        }
    }
}
