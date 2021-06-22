package shein.dmitriy;

import shein.dmitriy.services.OrderService;

import java.io.*;
import java.nio.channels.FileChannel;

public class Main {


//    private static ByteBuffer buf4 = ByteBuffer.allocate(4);

//    private static OrderService fieldService = new OrderService();

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
            RandomAccessFile raf = new RandomAccessFile(fileOut, "rw");
            FileChannel channel = raf.getChannel();

//           Tools tools = new Tools(channel);
//           fieldService.getOrderField();
            OrderService orderService = new OrderService(channel);
            orderService.getOrderField();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }




}
