package com.company.gameStore.models;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "invoice")
public class Invoice implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "invoice_id")
    private int invoiceId;
    @NotNull(message = "You must provide a name.")
    private String name;
    @NotNull(message = "You must provide a street.")
    private String street;
    @NotNull(message = "You must provide a city.")
    private String city;
    @NotNull(message = "You must provide a state.")
    private String state;
    @NotNull(message = "You must provide a zipcode.")
    private String zipcode;
    @Column(name = "item_type")
    @NotNull(message = "You must provide an item type.")
    private String itemType;
    @Column(name = "item_id")
    @NotNull(message = "You must provide an item ID.")
    private int itemId;
    @Column(name = "unit_price")
    @NotNull(message = "You must provide a unit price.")
    @Digits(integer = 8, fraction = 2, message = "Unit price must be a number with up to 2 decimal places.")
    private BigDecimal unitPrice;
    @NotNull(message = "You must provide a quantity.")
    private int quantity;
    @NotNull(message = "You must provide a subtotal.")
    @Digits(integer = 8, fraction = 2, message = "Subtotal must be a number with up to 2 decimal places.")
    private BigDecimal subtotal;
    @NotNull(message = "You must provide a tax.")
    @Digits(integer = 8, fraction = 2, message = "Tax must be a number with up to 2 decimal places.")
    private BigDecimal tax;
    @Column(name = "processing_fee")
    @NotNull(message = "You must provide a processing fee.")
    @Digits(integer = 8, fraction = 2, message = "Processing fee must be a number with up to 2 decimal places.")
    private BigDecimal processingFee;
    @NotNull(message = "You must provide a total price.")
    @Digits(integer = 8, fraction = 2, message = "Total must be a number with up to 2 decimal places.")
    private BigDecimal total;

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(BigDecimal processingFee) {
        this.processingFee = processingFee;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return invoiceId == invoice.invoiceId && itemId == invoice.itemId && quantity == invoice.quantity && Objects.equals(name, invoice.name) && Objects.equals(street, invoice.street) && Objects.equals(city, invoice.city) && Objects.equals(state, invoice.state) && Objects.equals(zipcode, invoice.zipcode) && Objects.equals(itemType, invoice.itemType) && Objects.equals(unitPrice, invoice.unitPrice) && Objects.equals(subtotal, invoice.subtotal) && Objects.equals(tax, invoice.tax) && Objects.equals(processingFee, invoice.processingFee) && Objects.equals(total, invoice.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoiceId, name, street, city, state, zipcode, itemType, itemId, unitPrice, quantity, subtotal, tax, processingFee, total);
    }
}
