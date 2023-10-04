package com.capstone.kuhako.models.ClientModules;

import com.capstone.kuhako.models.Client;
import com.capstone.kuhako.models.Collector;
import com.capstone.kuhako.models.JoinModule.Transactions;
import com.capstone.kuhako.models.Reseller;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "transactionHistory")
public class TransactionHistory {
    @Id
    @GeneratedValue
    private Long transactionHistory_id;

    @ManyToOne
    @JoinColumn(name="reseller_id")
    private Reseller reseller;

    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name="collector_id")
    private Collector collector;

    @Column
    private String itemName;
    @Column
    private double itemPrice;
    @Column
    private String paymentType;
    @Column
    private String specifications;

    @OneToMany(mappedBy = "transactionHistory", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<PayDues> payDues;
    public TransactionHistory() {
    }

    public TransactionHistory(Reseller reseller, Client client, Collector collector, String itemName, double itemPrice, String paymentType, String specifications, Set<PayDues> payDues) {
        this.reseller = reseller;
        this.client = client;
        this.collector = collector;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.paymentType = paymentType;
        this.specifications = specifications;
        this.payDues = payDues;

    }

    public Set<PayDues> getPayDues() {
        return payDues;
    }

    public void setPayDues(Set<PayDues> payDues) {
        this.payDues = payDues;
    }

    public Reseller getReseller() {
        return reseller;
    }

    public void setReseller(Reseller reseller) {
        this.reseller = reseller;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Collector getCollector() {
        return collector;
    }

    public void setCollector(Collector collector) {
        this.collector = collector;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }
}
