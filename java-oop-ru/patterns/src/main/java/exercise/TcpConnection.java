package exercise;
import exercise.connections.Connected;
import exercise.connections.Connection;
import exercise.connections.Disconnected;

// BEGIN
public class TcpConnection {
    private Connection state;
    String ipAddress;
    int port;

    public TcpConnection(String ipAddress, int port) {
        this.state = new Disconnected(this);
        this.ipAddress = ipAddress;
        this.port = port;
    }
    public Connection getState() {
        return this.state;
    }
    public void setState(Connection state) {
        this.state = state;
    }
    public String getIpAddress() {
        return ipAddress;
    }
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
    public int getPort() {
        return port;
    }
    public void setPort(int port) {
        this.port = port;
    }

    public String getCurrentState() {
        return this.getState().getCurrentState();
    }

    public String connect() {
        this.getState().connect();
        return null;
    }

    public String disconnect() {
        this.getState().disconnect();
        return null;
    }

    public void write(String message) {
        this.getState().write(message);
    }
}
// END
