package util.Hiber.Model;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Роман on 16.06.2017.
 */
@Entity
@Table(name = "basket_products")
public class BasketProductsEntity {
    private int id;
    private int idOrder;
    private int countProduct;
    private Date date;
    private UserdbEntity userdbByIdBuyer;
    private ProductdbEntity productdbByIdProduct;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "id_order", nullable = false)
    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    @Basic
    @Column(name = "count_product", nullable = false)
    public int getCountProduct() {
        return countProduct;
    }

    public void setCountProduct(int countProduct) {
        this.countProduct = countProduct;
    }

    @Basic
    @Column(name = "date", nullable = false)
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

        BasketProductsEntity that = (BasketProductsEntity) o;

        if (id != that.id) return false;
        if (idOrder != that.idOrder) return false;
        if (countProduct != that.countProduct) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + idOrder;
        result = 31 * result + countProduct;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "id_buyer", referencedColumnName = "id", nullable = false)
    public UserdbEntity getUserdbByIdBuyer() {
        return userdbByIdBuyer;
    }

    public void setUserdbByIdBuyer(UserdbEntity userdbByIdBuyer) {
        this.userdbByIdBuyer = userdbByIdBuyer;
    }

    @ManyToOne
    @JoinColumn(name = "id_product", referencedColumnName = "id", nullable = false)
    public ProductdbEntity getProductdbByIdProduct() {
        return productdbByIdProduct;
    }

    public void setProductdbByIdProduct(ProductdbEntity productdbByIdProduct) {
        this.productdbByIdProduct = productdbByIdProduct;
    }

    @Override
    public String toString() {
        return "BasketProductsEntity{" +
                "id=" + id +
                ", idOrder=" + idOrder +
                ", countProduct=" + countProduct +
                ", date=" + date +
                ", userdbByIdBuyer=" + userdbByIdBuyer +
                ", productdbByIdProduct=" + productdbByIdProduct +
                '}';
    }
}
