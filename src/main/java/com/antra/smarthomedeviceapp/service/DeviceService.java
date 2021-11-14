package com.antra.smarthomedeviceapp.service;

import com.antra.smarthomedeviceapp.model.Device;
import com.antra.smarthomedeviceapp.model.Home;
import com.antra.smarthomedeviceapp.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeviceService {

    public boolean doesDevicesContainDeviceName(List<Device> devices, String deviceName){

        for(Device d : devices){
            if(d.getName().equals(deviceName)){
                return true;
            }
        }
        return false;
    }

}
