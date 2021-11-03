package com.antra.smarthomedeviceapp.dto;

import com.antra.smarthomedeviceapp.model.Home;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DeviceResponse {
    private long id;
    private String name;
    private Home home;
}
