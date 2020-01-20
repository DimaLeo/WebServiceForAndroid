package com.example.demo;

import javax.persistence.*;

@Entity
@Table(name="devices" , schema = "users")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="device_id")
    private int device_id;

    @Column(name="device_name")
    private String device_name;

    @Column(name="device_type")
    private String device_type;

    @Column(name="device_brand")
    private int device_brand;

    @Column(name="device_wattage")
    private int device_wattage;

    @Column(name="device_runtime")
    private double device_runtime;

    @Column(name="username")
    private String username;

    @Column(name="device_activity_status")
    private int device_activity_status;

    public Device(String device_name, String device_type, int device_brand, int device_wattage, String username) {
        this.device_name = device_name;
        this.device_type = device_type;
        this.device_brand = device_brand;
        this.device_wattage = device_wattage;
        this.username = username;
    }

    public Device() {
    }

    public int getDevice_activity_status() {
        return device_activity_status;
    }

    public void setDevice_activity_status(int device_activity_status) {
        this.device_activity_status = device_activity_status;
    }

    public int getDevice_id() {
        return device_id;
    }

    public void setDevice_id(int device_id) {
        this.device_id = device_id;
    }

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }

    public String getDevice_type() {
        return device_type;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    public int getDevice_brand() {
        return device_brand;
    }

    public void setDevice_brand(int device_brand) {
        this.device_brand = device_brand;
    }

    public int getDevice_wattage() {
        return device_wattage;
    }

    public void setDevice_wattage(int device_wattage) {
        this.device_wattage = device_wattage;
    }

    public double getDevice_runtime() {
        return device_runtime;
    }

    public void setDevice_runtime(double device_runtime) {
        this.device_runtime = device_runtime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
