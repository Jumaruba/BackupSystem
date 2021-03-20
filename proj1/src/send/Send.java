package send;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;


/**
 * Design pattern: Method Template.
 * https://refactoring.guru/pt-br/design-patterns/template-method
 */
public class Send extends Thread {
    protected String type;
    protected String fileId;
    protected String addr;
    protected int port;

    public Send(String type, String fileId, String addr, int port) {
        this.type = type;
        this.fileId = fileId;
        this.addr = addr;
        this.port = port;
    }

    @Override
    public void run() {
        try {
            MulticastSocket socket = new MulticastSocket();

            // Build message.
            MessageBuilder messageBuilder = new MessageBuilder(type, fileId);
            byte[] message = buildMessage(messageBuilder);

            // Send message
            InetAddress address = InetAddress.getByName(addr);
            DatagramPacket packet = new DatagramPacket(message, message.length, address, port);
            socket.send(packet);

            System.out.println("Send Message Backup\t:: Message sent!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected byte[] buildMessage(MessageBuilder messageBuilder){
        return messageBuilder.build();
    }
}
