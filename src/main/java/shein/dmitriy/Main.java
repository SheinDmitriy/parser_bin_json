package shein.dmitriy;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String files[];
        String stringIn;
        String fileOut;
        String fileIn;

        Scanner in = new Scanner(System.in);
        System.out.println("Введите названия файлов: ");
        stringIn = in.nextLine();

        while (stringIn.split(" ").length != 2){
            System.out.println("Введите названия файлов: ");
            stringIn = in.nextLine();
        }
        files = stringIn.split(" ");

        fileOut = files[0];
        fileIn = files[1];

    }
}
