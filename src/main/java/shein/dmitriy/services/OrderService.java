package shein.dmitriy.services;

import shein.dmitriy.entitys.Item;
import shein.dmitriy.entitys.Order;
import shein.dmitriy.util.Tools;


import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

public class OrderService {

    private FileChannel channel;
    private Tools tools;
    private Order order = new Order();
    private ItemService itemService ;

    public OrderService(FileChannel channel) {
        this.channel = channel;
        this.tools = new Tools(channel);
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
                itemService = new ItemService(channel, tools);
                addItem(order, itemService.getItem());
                break;
            case -1:
                System.out.println("000000000000");
                break;

        }
        System.out.println(order);
    }

    public void addItem(Order order, Item item){
        List<Item> list = new ArrayList<Item>();
        if(order.getItems() != null){
            list = order.getItems();
            list.add(item);
            order.setItems(list);
        }
        list.add(item);
        order.setItems(list);
    }









}
