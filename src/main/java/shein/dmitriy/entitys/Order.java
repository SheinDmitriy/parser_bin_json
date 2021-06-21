package shein.dmitriy.entitys;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Order {

    private Date dateTime;

    private int orderNumber;

    private String customerName;

    private List<Items> items;
}
