package main.java.org.solvd.structure.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ConnectionPool {
    private final int size;
    private final String url;
    private List<Connection> availableConnections;
    private List<Connection> connectionsInUse;
    private static ConnectionPool INSTANCE;

    private ConnectionPool(int size, String url) {
        this.size = size;
        this.url = url;
    }

    public static ConnectionPool getInstance(int size, String url){
        if (INSTANCE == null){
            INSTANCE = new ConnectionPool(size, url);
        }
        return INSTANCE;
    }

    public synchronized Connection getConnection() {
        if (availableConnections == null) {
            availableConnections = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                availableConnections.add(new Connection(url));
            }
            connectionsInUse = new ArrayList<>();
        }
//        if (availableConnections.isEmpty()) {
//            return null;
//        }
        Connection connection = availableConnections.removeLast();
        connectionsInUse.add(connection);
        return connection;
    }

    public synchronized Connection releaseConnection(){
        if (connectionsInUse == null || connectionsInUse.isEmpty()){
            return null;
        }
        Connection connection = connectionsInUse.removeFirst();
        availableConnections.add(connection);
        return connection;
    }

    public synchronized boolean isAvailableConnectionsSizeEmpty(){
        if (availableConnections == null) {
            return true;
        }
        return availableConnections.isEmpty();
    }

    public synchronized boolean isConnectionsInUseSizeEmpty(){
        if (connectionsInUse == null) {
            return true;
        }
        return connectionsInUse.isEmpty();
    }


    public static void main(String[] args) {
        ConnectionPool connectionPool = ConnectionPool.getInstance(5, "url");
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CompletableFuture<Void>[] futures = new CompletableFuture[7];
        MyThread thread = new MyThread();

        for (int i = 1; i < 8; i++) {
            final int taskId = i;
            futures[i-1] = CompletableFuture.supplyAsync(() -> {
                Connection connection = null;
                try {
                    connection = connectionPool.getConnection();
                    System.out.println("Task " + taskId + " is using a connection.");
                    Thread.sleep(1000);
                    return "Task " + taskId + " completed.";
                } catch (InterruptedException | RuntimeException e) {
                    e.printStackTrace();
                    return "Task " + taskId + " failed.";
                } finally {
                    connectionPool.releaseConnection();
                    System.out.println("Task " + taskId + " returned the connection.");
                }
            }, executorService).thenAccept(System.out::println);
        }

        CompletableFuture<Void> allOf = CompletableFuture.allOf(futures);
        allOf.join();
        executorService.shutdown();
        System.out.println("All connections closed.");





    }


}