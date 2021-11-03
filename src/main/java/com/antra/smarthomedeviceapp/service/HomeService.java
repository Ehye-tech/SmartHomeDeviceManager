package com.antra.smarthomedeviceapp.service;

import com.antra.smarthomedeviceapp.model.Device;
import com.antra.smarthomedeviceapp.model.Home;
import com.antra.smarthomedeviceapp.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class HomeService {
    
    public List<Device> devicesMatchedHomeName(Map<Long,Device> map, Long homeId){
        List<Device> devices = new ArrayList<Device>();
        for(Long id : map.keySet()){
            if(id == homeId){
                devices.add(map.get(id));
            }
        }
        return devices;
    }
    
    public Map<Long, Device> getDeviceHomeName(List<Device> devices) {
        Map<Long, Device> deviceHomeNameMap = null;
        devices.stream().forEach(device -> {
            deviceHomeNameMap.put(device.getHome().getId(), device);
        });
        return deviceHomeNameMap;
    }
}
