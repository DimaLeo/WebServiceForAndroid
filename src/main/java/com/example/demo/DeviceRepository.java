package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device,Integer> {

    List<Device> findAllByUsername(String username);

}
