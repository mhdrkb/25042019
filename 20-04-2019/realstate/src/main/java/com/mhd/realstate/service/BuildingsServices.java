package com.mhd.realstate.service;

import com.mhd.realstate.entity.Buildings;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BuildingsServices {
    public List<Buildings> getBuildingsList();
    public Buildings getBuildingById(long id);
}
