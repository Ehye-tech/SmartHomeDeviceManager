package com.antra.smarthomedeviceapp.dto;

import com.antra.smarthomedeviceapp.model.Device;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HomeRequest {
    private String name;
    private List<String> devices;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
