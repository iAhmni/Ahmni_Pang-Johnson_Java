package com.company.gameStore.models;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "tshirt")
public class TShirt implements Serializable {
    @Id
    @Column(name = "tshirt_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "You must supply a value for Size.")
    private String size;

    @NotEmpty(message = "You must supply a value for Color.")
    private String color;

    @NotEmpty(message = "You must supply a value for Description.")
    private String description;

    @NotNull(message = "You must supply a value for Price.")
    @Digits(integer = 5, fraction = 2, message = "Price must be a number with up to 2 decimal places.")
    private BigDecimal price;

    @NotNull(message = "You must supply a value for Quantity.")
    @Digits(integer = 10, fraction = 0, message = "Quantity must be a whole number.")
    private int quantity;

    public TShirt() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TShirt TShirt = (TShirt) o;
        return getId() == TShirt.getId() &&
                getQuantity() == TShirt.getQuantity() &&
                Objects.equals(getSize(), TShirt.getSize()) &&
                Objects.equals(getColor(), TShirt.getColor()) &&
                Objects.equals(getDescription(), TShirt.getDescription()) &&
                Objects.equals(getPrice(), TShirt.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),
                getSize(),
                getColor(),
                getDescription(),
                getPrice(),
                getQuantity());
    }

    @Override
    public String toString() {
        return "TShirt{" +
                "id=" + id +
                ", size='" + size + '\'' +
                ", color='" + color + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
