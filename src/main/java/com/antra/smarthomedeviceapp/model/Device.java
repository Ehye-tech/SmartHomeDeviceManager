package com.antra.smarthomedeviceapp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "devices")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "deviceName")
    private String name;

    @ManyToOne
    @JoinColumn(name = "home_id")
    private Home home;

    public Device(String name, Home home) {
        this.name = name;
        this.home = home;
    }

    public Device() {

    }
}
