package sendMessage;

import factory.BackupMessageFactory;
import file.Chunk;
import main.Definitions;
import main.Peer;

import java.io.IOException;
import java.net.MulticastSocket;


/**
 * 1) Send request;
 * 2) Handle the request and call Backup handler.
 * 3) Send the response.
 * 4) Handle the response.
 */
public class SendMessageBackup extends SendMessage {
    private String filePath;
    private String replicationDeg;
    Chunk[] chunks;

    public SendMessageBackup(String filePath, String fileId, String replicationDeg, Chunk[] chunks) {
        super(Peer.version, Definitions.PUTCHUNK, fileId);
        this.filePath = filePath;
        this.replicationDeg = replicationDeg;
        this.chunks = chunks;
    }

    @Override
    public void run() {
        try {

            System.out.println("SendMessBackup\t:: Sending multicast requests...");
            MulticastSocket socket = new MulticastSocket();

            byte[] message =new BackupMessageFactory(filePath, replicationDeg, this.chunks[0]).createMessage();
            sendMessage(socket, message);

            System.out.println("sendMessBackup\t:: Message sent!");

            //String received = receiveMessage(socket);
            //displayRequest(received);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
