package com.capstone.kuhako.models.JoinModule;
import com.capstone.kuhako.models.Client;
import com.capstone.kuhako.models.Collector;
import com.capstone.kuhako.models.Reseller;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "contractsHistory")
public class ContractsHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contractsHistory_id;

    @ManyToOne
    @JoinColumn(name="reseller_id")
    private Reseller reseller;

    @ManyToOne
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


    @OneToMany(mappedBy = "contractsHistory", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Transactions> transactions;

    public ContractsHistory() {
    }

    public ContractsHistory(Reseller reseller, Client client, Collector collector, String itemName, double itemPrice,  String paymentType, String specifications,  Set<Transactions> transactions) {
        this.reseller = reseller;
        this.client = client;
        this.collector = collector;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.paymentType = paymentType;
        this.specifications = specifications;
        this.transactions = transactions;
    }

    public Long getContractsHistory_id() {
        return contractsHistory_id;
    }

    /*public void setContracts_id(Long contracts_id) {
        this.contracts_id = contracts_id;
    }*/

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
    public Set<Transactions> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transactions> transactions) {
        this.transactions = transactions;
    }
}
