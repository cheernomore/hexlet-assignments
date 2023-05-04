package exercise.connections;

import exercise.TcpConnection;

// BEGIN
public class Disconnected implements Connection {
    private TcpConnection tcpConnection;

    public Disconnected(TcpConnection tcpConnection) {
        this.tcpConnection = tcpConnection;
    }

    @Override
    public String getCurrentState() {
        System.out.println("Disconnected: ");
        return "disconnected";
    }

    @Override
    public String connect() {
        this.tcpConnection.setState(new Connected(this.tcpConnection));
        return "Error";
    }

    @Override
    public String disconnect() {
        System.out.println("Error");
        return "Error";
    }

    @Override
    public void write(String message) {
        System.out.println("Error");
    }
}
// END
