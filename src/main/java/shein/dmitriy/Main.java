package shein.dmitriy;

import shein.dmitriy.services.OrderService;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String[] files;
        String stringIn = "";
        String fileOut;
        String fileIn;

        Scanner in = new Scanner(System.in);

        while (stringIn.split(" ").length != 2){
            System.out.println("Enter file name: ");
            stringIn = in.nextLine();
        }
        files = stringIn.split(" ");

        fileOut = files[0];
        fileIn = files[1];

        try {
            RandomAccessFile raf = new RandomAccessFile(fileOut, "rw");
            FileChannel channel = raf.getChannel();

            OrderService orderService = new OrderService(channel);
            orderService.getOrderField();
            orderService.jsonWriteFile(fileIn);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
