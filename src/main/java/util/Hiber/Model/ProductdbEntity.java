package util.Hiber.Model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Роман on 16.06.2017.
 */
@Entity
@Table(name = "productdb")
public class ProductdbEntity extends AbstractModel {
    private String name;
    private long price;
    private String description;
    private int count;
    private Collection<BasketProductsEntity> basketProductsById;



    @Basic
    @Column(name = "name", nullable = false, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "price", nullable = false, precision = 0)
    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 200)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "count", nullable = false)
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductdbEntity that = (ProductdbEntity) o;

        if (id != that.id) return false;
        if (price != that.price) return false;
        if (count != that.count) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (int) (price ^ (price >>> 32));
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + count;
        return result;
    }

    @OneToMany(mappedBy = "productdbByIdProduct")
    public Collection<BasketProductsEntity> getBasketProductsById() {
        return basketProductsById;
    }

    public void setBasketProductsById(Collection<BasketProductsEntity> basketProductsById) {
        this.basketProductsById = basketProductsById;
    }

}
