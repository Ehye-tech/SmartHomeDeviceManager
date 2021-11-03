package com.antra.smarthomedeviceapp.controller;

import com.antra.smarthomedeviceapp.dto.DeviceResponse;
import com.antra.smarthomedeviceapp.model.Device;
import com.antra.smarthomedeviceapp.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/v1/api")
public class DeviceController {
    @Autowired
    DeviceRepository deviceRepository;

//    @GetMapping("/devices")
//    public ResponseEntity<List<DeviceResponse>> getAllDevices(){
//        try{
//            List<Device> deviceList = deviceRepository.findAll();
//            List<DeviceResponse> responseList = new ArrayList<>();
//            deviceList.forEach(device -> {
//                DeviceResponse response = new DeviceResponse();
//                response.setName(device.getName());
//                response.setHome(device.getHome());
//                responseList.add(response);
//                    });
//            return new ResponseEntity<>(responseList, HttpStatus.OK);
//        } catch (Exception e){
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @GetMapping("/devices")
    public ResponseEntity<List<Device>> getAllDevices(){
        try {
            return new ResponseEntity<>(deviceRepository.findAll(),HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/devices/{id}")
    public ResponseEntity<Device> getDevicesById(@PathVariable("id") long id){
        Optional<Device> device = deviceRepository.findById(id);
        if(device.isPresent()){
            return new ResponseEntity<>(device.get(),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}
