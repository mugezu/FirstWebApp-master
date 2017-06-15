package ClassJava;

import java.util.Date;

/**
 * Created by Роман on 14.06.2017.
 */
public class Order {
    private int id_order;
    private int id_buyer;
    private int id_product;
    private int count_product;
    private Date date;

    public Order() {
    }

    public Order(int id_order, int id_buyer, int id_product, int count_product, Date date) {
        this.id_order = id_order;
        this.id_buyer = id_buyer;
        this.id_product = id_product;
        this.count_product = count_product;
        this.date = date;
    }

    public int getId_order() {
        return id_order;
    }

    public void setId_order(int id_order) {
        this.id_order = id_order;
    }

    public int getId_buyer() {
        return id_buyer;
    }

    public void setId_buyer(int id_buyer) {
        this.id_buyer = id_buyer;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public int getCount_product() {
        return count_product;
    }

    public void setCount_product(int count_product) {
        this.count_product = count_product;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id_order != order.id_order) return false;
        if (id_buyer != order.id_buyer) return false;
        if (id_product != order.id_product) return false;
        if (count_product != order.count_product) return false;
        return date != null ? date.equals(order.date) : order.date == null;
    }

    @Override
    public int hashCode() {
        int result = id_order;
        result = 31 * result + id_buyer;
        result = 31 * result + id_product;
        result = 31 * result + count_product;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
