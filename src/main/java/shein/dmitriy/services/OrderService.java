package shein.dmitriy.services;

import com.google.gson.Gson;
import shein.dmitriy.entitys.Item;
import shein.dmitriy.entitys.Order;
import shein.dmitriy.util.Tools;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

public class OrderService {

    private FileChannel channel;
    private Tools tools;
    private Order order;
    private static List <Item> list = new ArrayList<Item>();

    public OrderService(FileChannel channel) {
        this.channel = channel;
        this.tools = new Tools(channel);
        order = new Order();
    }

    public void getOrderField() throws IOException {

        switch (tools.getTagOrLength()){
            case 1:
               order.setDateTime(tools.getDate(tools.getTagOrLength()));
               getOrderField();
               break;
            case 2:
                order.setOrderNumber(tools.getVLN(tools.getTagOrLength()));
                getOrderField();
                break;
            case 3:
                order.setCustomerName(tools.getString(tools.getTagOrLength()));
                getOrderField();
                break;
            case 4:
                tools.getTagOrLength();
                ItemService itemService = new ItemService(channel, tools);
                addItem(order, itemService.getItem());
                break;
        }
    }

    public void addItem(Order order, Item item){
        if(order.getItems() != null){
            list = order.getItems();
            list.add(item);
            order.setItems(list);
        }
        list.add(item);
        order.setItems(list);
    }

    public void jsonWriteFile(String filename) throws IOException {
        FileWriter fileWriter = new FileWriter(filename);
        Gson gson = new Gson();
        String jsonDataForWrite = gson.toJson(order, Order.class);
        fileWriter.write(jsonDataForWrite);
        System.out.println("File " + filename + " created successfully");
        fileWriter.flush();
        fileWriter.close();
    }
}
