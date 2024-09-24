package main.java.org.solvd.structure.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ConnectionPool {
    private final int size;
    private final String url;
    private List<Connection> availableConnections;
    private List<Connection> connectionsInUse;

    public ConnectionPool(int size, String url) {
        this.size = size;
        this.url = url;
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
        ConnectionPool connectionPool = new ConnectionPool(5, "abc");
        ThreadPoolExecutor executor =(ThreadPoolExecutor) Executors.newFixedThreadPool(7);
        for (int i = 0; i < 7; i++) {
            executor.execute(() -> {
                while (connectionPool.isAvailableConnectionsSizeEmpty() &&
                        !connectionPool.isConnectionsInUseSizeEmpty()) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                connectionPool.getConnection();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                connectionPool.releaseConnection();
                System.out.println("thread finished");
            });
        }





    }


}