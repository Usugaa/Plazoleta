package com.microservicio.restaurant.domain.model;

import java.util.Date;

public class Order {

    private Long id;
    private Long idClient;
    private Date date;
    private String status;
    private Long idEmpleoyee;
    private Long idRestaurant;

    public Order(Long id, Long idClient, Date date, String status, Long idEmpleoyee, Long idRestaurant) {
        this.id = id;
        this.idClient = idClient;
        this.date = date;
        this.status = status;
        this.idEmpleoyee = idEmpleoyee;
        this.idRestaurant = idRestaurant;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdCliente(Long idClient) {
        this.idClient = idClient;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getIdEmpleoyee() {
        return idEmpleoyee;
    }

    public void setIdEmpleoyee(Long idEmpleoyee) {
        this.idEmpleoyee = idEmpleoyee;
    }

    public Long getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(Long idRestaurant) {
        this.idRestaurant = idRestaurant;
    }
}
