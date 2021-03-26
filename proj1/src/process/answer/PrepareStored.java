package process.answer;

import channel.MessageParser;
import main.Peer;
import main.etc.Logger;
import main.etc.Singleton;
import send.SendChunkNo;
import state.ChunkState;

import static main.etc.FileHandler.saveFileChunks;

/**
 * Saves the chunk and prepares to send the STORED message.
 */
public class PrepareStored extends Thread {
    MessageParser messageParsed;

    public PrepareStored(MessageParser messageParsed) {
        this.messageParsed = messageParsed;
    }

    @Override
    public void run() {
        Boolean fileIsSaved = saveFileChunks(messageParsed, Singleton.getFilePath(Peer.peer_no));


        if (fileIsSaved) {
            new SendChunkNo(Singleton.STORED, messageParsed.getFileId(), messageParsed.getChunkNo(), Peer.mc_addr, Peer.mc_port).start();
            String chunkId = Singleton.buildChunkId(messageParsed.getFileId(), messageParsed.getChunkNo());
            // After saving, update the perceived replication degree.
            Peer.peer_state.addStoredPeer(chunkId, Peer.peer_no);
            Logger.INFO(this.getClass().getName(), "Sending STORED message on " + chunkId);
        } else {
            Logger.ERR(this.getClass().getName(), "Chunk " + Singleton.buildChunkId(messageParsed.getFileId(), messageParsed.getChunkNo()) + "wasn't stored!");
        }
    }

}
