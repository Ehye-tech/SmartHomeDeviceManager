package com.antra.smarthomedeviceapp.model;

import com.antra.smarthomedeviceapp.dto.HomeRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "homes")
public class Home {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "homeName")
    private String name;

    public Home(HomeRequest request) {
        this.name = request.getName();
    }

    public Home(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
