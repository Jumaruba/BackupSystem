package file;

import main.Definitions;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static java.nio.file.Files.readAllBytes;

public class FileHandler {

    public static byte[] readFile(String filePath) throws IOException {
        File file = new File(filePath);

        if (file.length() > Integer.MAX_VALUE)
            throw new IOException("File too large to be read");
        try {
            return readAllBytes(file.toPath());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Chunk[] splitFile(byte[] fileContent) {

        if (fileContent.length == 0){
            Chunk chunk = new Chunk("0", new byte[0]);
            return new Chunk[]{chunk};
        }

        byte[] data;

        // Includes the last chunk be it zero or not.
        int numSplits = (int) Math.ceil((float) fileContent.length / (float)Definitions.CHUNK_MAX_SIZE);
        int lastChunkPos = numSplits - 1;
        int bytePos = 0;

        int remainSize = fileContent.length % Definitions.CHUNK_MAX_SIZE;   // Size of the last chunk.
        int emptyChunk = 0;
        if (remainSize == 0)
            emptyChunk = 1;

        Chunk[] chunks = new Chunk[numSplits + emptyChunk];

        // Does not compute the last chunk.
        for (int i = 0; i < lastChunkPos; i++){
            data = Arrays.copyOfRange(fileContent, bytePos, bytePos + Definitions.CHUNK_MAX_SIZE);
            chunks[i] = new Chunk(Integer.toString(i), data);
            bytePos += Definitions.CHUNK_MAX_SIZE;
        }

        // Last chunk computation.
        if (emptyChunk == 1)
            chunks[lastChunkPos] = new Chunk(Integer.toString(lastChunkPos), new byte[0]);
        else {
            data= Arrays.copyOfRange(fileContent, bytePos, bytePos + remainSize);
            chunks[lastChunkPos] = new Chunk(Integer.toString(lastChunkPos), data);
        }

        return chunks;
    }
}
