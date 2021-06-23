package shein.dmitriy.entitys;

import lombok.Data;

import java.util.List;

@Data
public class Order {

    private String dateTime;

    private int orderNumber;

    private String customerName;

    private List<Item> items;
}
