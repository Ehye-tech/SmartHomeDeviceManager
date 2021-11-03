package com.antra.smarthomedeviceapp.repository;

import com.antra.smarthomedeviceapp.model.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device,Long> {
//    @Autowired
//    boolean isDevice(List<Device> devices, String s);
}
