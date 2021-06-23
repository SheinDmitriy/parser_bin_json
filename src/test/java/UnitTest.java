import org.junit.Test;
import shein.dmitriy.util.Tools;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UnitTest {

    private ByteBuffer buf;
    private String file = "src/test/resources/test.txt";
    private FileChannel channel;

    public FileChannel readFile(){
        RandomAccessFile reader;
        try {
            reader = new RandomAccessFile(file, "r");
            return channel = reader.getChannel();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void writeInFile(ByteBuffer buf) {
        RandomAccessFile writer;
        try {
            writer = new RandomAccessFile(file, "rw");
            FileChannel channel = writer.getChannel();
            buf.flip();
            channel.write(buf);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetDate() throws IOException {
        int it = 10000;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String dateTest = formatter.format(new Date(it));
        buf = ByteBuffer.allocate(8);
        buf.putInt(it);
        writeInFile(buf);
        Tools tools = new Tools(readFile());
        assertEquals(dateTest, tools.getDate(8));
    }

    @Test
    public void testGetString() throws IOException {
        String stringTest ="test";
        buf = ByteBuffer.allocate(4);
        buf.put(stringTest.getBytes());
        writeInFile(buf);
        Tools tools = new Tools(readFile());
        assertEquals(stringTest, tools.getString(4));
    }

    @Test
    public void testGetVLN() throws IOException {
        int intTest = 10;
        buf = ByteBuffer.allocate(4);
        buf.put((byte) intTest);
        writeInFile(buf);
        Tools tools = new Tools(readFile());
        assertEquals(intTest, tools.getVLN(4));
    }

    @Test
    public void testGetFVLN() throws IOException {
        byte i1 = 0;
        int i2 = 2;
        buf = ByteBuffer.allocate(2);
        buf.put( i1);
        buf.compact();
        buf.put((byte) i2);
        writeInFile(buf);
        Tools tools = new Tools(readFile());
        assertTrue(Math.abs(i2 - tools.getFVLN(2)) < 0.0001);
    }
}
