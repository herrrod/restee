package hello;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String firstName;
    private String lastName;

    protected Customer() {}

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    private Customer parent;

    @OneToMany(fetch = FetchType.EAGER, mappedBy="parent", cascade = CascadeType.ALL)
    private Collection<Customer> children;

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, firstName='%s', lastName='%s', children='%d']",
                id, firstName, lastName, getChildren().size());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Customer getParent() {
        return parent;
    }

    public void setParent(Customer parent) {
        this.parent = parent;
    }

    public Collection<Customer> getChildren() {
        return children;
    }

    public void setChildren(Collection<Customer> children) {
        this.children = children;
    }
}
