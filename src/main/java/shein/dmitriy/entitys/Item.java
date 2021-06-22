package shein.dmitriy.entitys;

import lombok.Data;

@Data
public class Item {

    private String name;

    private int price;

    private int quantity;

    private int sum;
}
