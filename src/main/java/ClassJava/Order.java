package ClassJava;

import DAO.ProductDao;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Роман on 14.06.2017.
 */
public class Order {
    private int id_order;
    private String name_buyer;
    private Map<ProductDao, Integer> list_product;
    private int summ_order;

    public Order() {
        list_product = new ConcurrentHashMap<>();

    }

    public Order(int id_order, String name_buyer, Map<ProductDao, Integer> list_product, int summ_order) {
        this.id_order = id_order;
        this.name_buyer = name_buyer;
        this.list_product = list_product;
        this.summ_order = summ_order;
    }

    public Order(int id_order, String name_buyer, String json, int summ_order) {
        Type itemsMapType = new TypeToken<Map<Product, Integer>>() {
        }.getType();

        this.list_product = new Gson().fromJson(json, itemsMapType);
        this.id_order = id_order;
        this.name_buyer = name_buyer;
        this.summ_order = summ_order;
    }


    public int getId_order() {
        return id_order;
    }

    public void setId_order(int id_order) {
        this.id_order = id_order;
    }

    public String getName_buyer() {
        return name_buyer;
    }

    public void setName_buyer(String name_buyer) {
        this.name_buyer = name_buyer;
    }

    public Map<ProductDao, Integer> getList_product() {
        return list_product;
    }

    public void setList_product(String json) {
        Type itemsMapType = new TypeToken<Map<Product, Integer>>() {
        }.getType();

        this.list_product = new Gson().fromJson(json, itemsMapType);
    }

    public void setList_product(Map<ProductDao, Integer> list_product) {
        this.list_product = list_product;
    }

    public int getSumm_order() {
        return summ_order;
    }

    public void setSumm_order(int summ_order) {
        this.summ_order = summ_order;
    }
}
