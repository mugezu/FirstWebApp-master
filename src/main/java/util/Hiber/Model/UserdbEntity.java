package util.Hiber.Model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Роман on 16.06.2017.
 */
@Entity
@Table(name = "userdb")
public class UserdbEntity {
    private int id;
    private String name;
    private String password;
    private String email;
    private Collection<BasketProductsEntity> basketProductsById;
    private RoleEntity roleByUserRole;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 45)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 45)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserdbEntity that = (UserdbEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "userdbByIdBuyer")
    public Collection<BasketProductsEntity> getBasketProductsById() {
        return basketProductsById;
    }

    public void setBasketProductsById(Collection<BasketProductsEntity> basketProductsById) {
        this.basketProductsById = basketProductsById;
    }

    @ManyToOne
    @JoinColumn(name = "user_role", referencedColumnName = "role", nullable = false)
    public RoleEntity getRoleByUserRole() {
        return roleByUserRole;
    }

    public void setRoleByUserRole(RoleEntity roleByUserRole) {
        this.roleByUserRole = roleByUserRole;
    }


}
