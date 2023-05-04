package exercise.connections;

import exercise.TcpConnection;

// BEGIN
public class Connected implements Connection {

    private TcpConnection tcpConnection;

    public Connected(TcpConnection tcpConnection) {
        this.tcpConnection = tcpConnection;
    }

    @Override
    public String getCurrentState() {
        System.out.println("State: " + "connected listening for " + tcpConnection.getIpAddress() + " on port " + tcpConnection.getPort());
        return "connected";
    }

    @Override
    public String connect() {
        System.out.println("Error");
        return "Error";
    }

    @Override
    public String disconnect() {
        this.tcpConnection.setState(new Disconnected(this.tcpConnection));
        return "Error";
    }

    @Override
    public void write(String message) {
        System.out.println(message);
    }
}
// END
