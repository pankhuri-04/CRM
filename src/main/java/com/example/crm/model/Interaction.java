package com.example.crm.model;

import jakarta.persistence.*;

@Entity
@Table(name = "interactions")
public class Interaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String notes;
    private String type; // e.g., call, email, meeting

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }
}
