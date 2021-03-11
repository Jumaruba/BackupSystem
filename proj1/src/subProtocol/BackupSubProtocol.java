package subProtocol;

import factory.BackupMessageFactory;
import file.Chunk;
import main.Peer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;



/**
 * 1) Send request;
 * 2) Handle the request and call Backup handler.
 * 3) Send the response.
 * 4) Handle the response.
 */
public class BackupSubProtocol extends SubProtocol {
    private String filePath;
    private String fileId;
    private String senderId;
    private int replicationDeg;
    Chunk[] chunks;

    public BackupSubProtocol(String filePath, String fileId, String senderId, int replicationDeg, Chunk[] chunks) {
        this.filePath = filePath;
        this.fileId = fileId;
        this.senderId = senderId;
        this.replicationDeg = replicationDeg;
        this.chunks = chunks;
    }

    public void run() {

        try {
            MulticastSocket socket = new MulticastSocket();


            byte[] message = new BackupMessageFactory(filePath, senderId, replicationDeg, this.chunks[0]).createMessage();
            sendMessage(socket, message); /// ??

            String received = receiveMessage(socket);
            displayRequest(received);
        } catch (IOException var2) {
            var2.printStackTrace();
        }
    }

    private static String receiveMessage(DatagramSocket socket) throws IOException{
        byte[] buf = new byte[256];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);

        byte[] messageBytes = packet.getData();
        return new String(messageBytes);
    }

    private static void sendMessage(DatagramSocket socket, byte[] message) throws IOException {
        InetAddress address = InetAddress.getByName(Peer.mcast_addr);

        DatagramPacket packet = new DatagramPacket(message, message.length, address, Peer.mcast_port);
        socket.send(packet);
    }

    private static void displayRequest(String requestMessage){
        System.out.println("Server: " + requestMessage);
    }


}
