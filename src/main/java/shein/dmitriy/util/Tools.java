package shein.dmitriy.util;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Date;

public class Tools {

    private ByteBuffer buf = ByteBuffer.allocate(2);

    private FileChannel channel;

    public Tools(FileChannel channel) {
        this.channel = channel;
    }

    public int getVLN(int length) throws IOException {
        bufferRead(length);
        int number = buf.get();
        buf.clear();
        return number;
    }

    public String getString(int length) throws IOException {
        bufferRead(length);
        String s = new String(buf.array(), "CP866");
        buf.clear();
        return s;
    }

    public int getTagOrLength() throws IOException {
        buf = ByteBuffer.allocate(2);
        buf.clear();
        int i = channel.read(buf);
        if(i != -1) {
            buf.flip();
            int tag = buf.get();
            buf.clear();
            return tag;
        }
        return 0;
    }

    public Date getDate(int length) throws IOException {
        bufferRead(length);
        Date date = new Date((int) buf.get());
        buf.clear();
        System.out.println(date);
        return date;
    }

    public double getFVLN(int length) throws IOException {
        bufferRead( 1 );
        int p = buf.get();
        buf.clear();
        bufferRead(length - 1 );
        int qua = buf.get();
        double quantity = qua / Math.pow(10, p);
        buf.clear();
        return quantity;
    }

    private ByteBuffer bufferRead(int length) throws IOException {
        buf = ByteBuffer.allocate(length);
        buf.clear();
        channel.read(buf);
        buf.flip();
        return buf;
    }
}
