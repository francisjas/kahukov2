package com.capstone.kuhako.models.ClientModules;

import javax.persistence.*;

@Entity
@Table(name = "contractsClient")
public class ContractsClient {
    @Id
    private Long id;

    @Column
    private String itemName;
    @Column
    private double itemPrice;

    public ContractsClient() {
    }

    public ContractsClient(Long id, String itemName, double itemPrice) {
        this.id = id;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
