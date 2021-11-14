package com.antra.smarthomedeviceapp.service;

import com.antra.smarthomedeviceapp.model.Device;
import com.antra.smarthomedeviceapp.model.Home;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DeviceServiceTest {

    DeviceService deviceService;

    @Before
    public void setup(){
        deviceService = new DeviceService();
    }

    @Test
    public void doesDevicesContainDeviceNameWhenDeviceIsInListThenReturnTrue(){
        Home myHome = new Home(1, "1 star street");
        Device googleHome = new Device(1, "google home", myHome);
        List<Device> devices = Arrays.asList(googleHome);
        assertTrue(deviceService.doesDevicesContainDeviceName(devices, "google home"));
    }

    @Test
    public void doesDevicesContainDeviceNameWhenDeviceIsNotInListThenReturnFalse(){
        Home myHome2 = new Home(2, "Star Oops dr");
        Device alexaHome = new Device(2, "Alexa", myHome2);
        List<Device> devices2 = Arrays.asList(alexaHome);
        assertFalse(deviceService.doesDevicesContainDeviceName(devices2,"google"));
    }

    @Test
    public void doesDevicesContainDeviceNameWhenDeviceIsNullThenReturnFalse(){
        Home myHome2 = new Home(2, "Star Oops dr");
        Device alexaHome = new Device(2, "Alexa", myHome2);
        List<Device> devices2 = Arrays.asList(alexaHome);
        assertFalse(deviceService.doesDevicesContainDeviceName(devices2,null));
    }

    public Home getHome(long id, String name){
        return new Home(id, name);
    }
    public Device getDevice(long id, String name, Home home){
        return new Device(id, name, home);
    }


}