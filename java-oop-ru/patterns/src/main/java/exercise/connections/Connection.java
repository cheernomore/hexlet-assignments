package exercise.connections;

public interface Connection {
    // BEGIN
    String getCurrentState();
    String connect();
    String disconnect();
    void write(String message);
    // END
}
