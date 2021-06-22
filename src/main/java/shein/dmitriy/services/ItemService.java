package shein.dmitriy.services;

import shein.dmitriy.entitys.Item;
import shein.dmitriy.util.Tools;

import java.io.IOException;
import java.nio.channels.FileChannel;

public class ItemService {

    private FileChannel channel;
    private Tools tools;
    private OrderService orderService;

    public ItemService(FileChannel channel, Tools tools) {
        this.channel = channel;
        this.tools = tools;
    }

    public void getItemField(Item item) throws IOException {

        switch (tools.getTagOrLength()){
            case 11:
                item.setName(tools.getString(tools.getTagOrLength()));
                getItemField(item);
                break;
            case 12:
                item.setPrice(tools.getVLN(tools.getTagOrLength()));
                getItemField(item);
                break;
            case 13:
                item.setQuantity(tools.getFVLN(tools.getTagOrLength()));
                getItemField(item);
                break;
            case 14:
                item.setSum(tools.getVLN(tools.getTagOrLength()));
                orderService = new OrderService(channel);
                orderService.getOrderField();
                break;
        }
    }

    public Item getItem() throws IOException {
        Item item = new Item();
        getItemField(item);
        return item;
    }
}
