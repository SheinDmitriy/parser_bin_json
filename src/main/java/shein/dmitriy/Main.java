package shein.dmitriy;

import sun.nio.cs.StandardCharsets;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String files[];
        String stringIn;
        String fileOut;
        String fileIn;

//        Scanner in = new Scanner(System.in);
//        System.out.println("Введите названия файлов: ");
//        stringIn = in.nextLine();
//
//        while (stringIn.split(" ").length != 2){
//            System.out.println("Введите названия файлов: ");
//            stringIn = in.nextLine();
//        }
//        files = stringIn.split(" ");
//
//
//        fileOut = files[0];
//        fileIn = files[1];

        fileOut = "data-1.bin";

        try {
            RandomAccessFile raf = new RandomAccessFile(fileOut, "r");
            FileChannel channel = raf.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(2);

            for (int i = 0; i < 2; i++){
//                buffer = ByteBuffer.allocate(2);
                channel.read(buffer);
                buffer.flip();
                System.out.println((int) buffer.get());
                buffer.clear();
//                buffer.flip();
//                buffer = ByteBuffer.allocate(1);
            }

            buffer = ByteBuffer.allocate(8);
            buffer.clear();
//            buffer.flip();
            channel.read(buffer);
            buffer.flip();
//            byte[] arr = new byte[buffer.remaining()];
//            buffer.get(arr);

            Date d = new Date((Long) buffer.getLong());

            System.out.println(d.toString());
//            Long l = BitConverter.ToInt64(byteValue, 0);
//            String v = new String(arr);
//            try {
//                Date d = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss ").parse(v);
//                System.out.println(d.toString());
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }




        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
