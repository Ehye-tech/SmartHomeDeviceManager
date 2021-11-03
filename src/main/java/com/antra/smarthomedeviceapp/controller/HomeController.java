package com.antra.smarthomedeviceapp.controller;

import com.antra.smarthomedeviceapp.dto.HomeRequest;
import com.antra.smarthomedeviceapp.model.Device;
import com.antra.smarthomedeviceapp.model.Home;
import com.antra.smarthomedeviceapp.repository.DeviceRepository;
import com.antra.smarthomedeviceapp.repository.HomeRepository;
import com.antra.smarthomedeviceapp.service.DeviceService;
import com.antra.smarthomedeviceapp.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/v1/api")
public class HomeController {
    @Autowired
    private HomeRepository homeRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private HomeService homeService;

    @GetMapping("/homes")
    public ResponseEntity<List<Home>> getAllHomes(){
        try {
            return new ResponseEntity<>(homeRepository.findAll(),HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/homes/{id}")
    public ResponseEntity<Home> getHomeById(@PathVariable("id") long id){
        Optional<Home> home = homeRepository.findById(id);
        if(home.isPresent()){
            return new ResponseEntity<>(home.get(),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/homes")
    public ResponseEntity<Home> createHome(@RequestBody HomeRequest req){
        Home home = new Home(req);
        home = homeRepository.save(home);
        for (String s:
            req.getDevices() ) {
            Device device = new Device();
            device.setName(s);
            device.setHome(home);
            deviceRepository.save(device);
        }
        return new ResponseEntity<>(home,HttpStatus.CREATED);
    }

    @PutMapping("/homes/{id}")
    public ResponseEntity<Home> updateHome(@PathVariable("id") long id, @RequestBody HomeRequest req){
        Optional<Home> home = homeRepository.findById(id);
        if(home.isPresent()){
            Home updatedHome = home.get();
            updatedHome.setName(req.getName());
            for(String s: req.getDevices()){
               if(!deviceService.doesDevicesContainDeviceName(deviceRepository.findAll(), s)){
                   Device device = new Device(s, updatedHome);
                   deviceRepository.save(device);
               }
            }
            return new ResponseEntity<>(updatedHome,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    @DeleteMapping("/homes")
    public ResponseEntity<HttpStatus> deleteAllHomes(){
        try {
            homeRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/homes/{id}")
    public ResponseEntity<HttpStatus> deleteHomeById(@PathVariable("id") long id){
        try{
            homeRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @DeleteMapping("/homes/{id}/devices")
//    public ResponseEntity<HttpStatus> deleteAllDevices(@PathVariable("id") long id){
//        Optional<Home> home = homeRepository.findById(id);
//        if(home.isPresent()){
//            List<Device> devices = deviceRepository.findAll();
//            List<Device> deviceList = homeService.devicesMatchedHomeName(homeService.getDeviceHomeName(devices),home.get().getId());
//            for(Device device : deviceList){
//                deviceRepository.deleteById(device.getId());
//            }
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//
//        }else{
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
    @DeleteMapping("/homes/{homeId}/devices/{deviceId}")
    public ResponseEntity<Device> deleteDevicesById(@PathVariable("homeId") long homeId, @PathVariable("deviceId") long deviceId){
        Optional<Home> home = homeRepository.findById(homeId);
        if(home.isPresent()) {
            deviceRepository.deleteById(deviceId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
