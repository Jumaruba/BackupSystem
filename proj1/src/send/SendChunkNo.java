package send;

public class SendChunkNo extends Send {
    String chunkNo;
    String fileId;
    public SendChunkNo(String type, String fileId, String chunkNo, String addr, int port) {
        super(type, addr, port);
        this.chunkNo = String.valueOf(chunkNo);
        this.fileId = fileId;
    }

    @Override
    protected byte[] buildMessage(MessageBuilder messageBuilder) {
        messageBuilder.addFileId(fileId);
        messageBuilder.addChunkNo(chunkNo);
        return messageBuilder.build();
    }
}
