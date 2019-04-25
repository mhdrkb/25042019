package com.mhd.realstate.jasper;

import com.mhd.realstate.entity.Buildings;
import com.mhd.realstate.repo.BuilldingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("buildingService")
public class BuidingServiceImpl implements BuildingService {
    @Autowired
    private BuilldingRepo builldingRepo;

    @Override
    public List<Map<String, Object>> buildingReport() {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        for(Buildings buildings : builldingRepo.findAll()){
            Map<String , Object> item = new HashMap<String, Object>();
            item.put("id",buildings.getId());
            item.put("name",buildings.getBuildingName());
            item.put("size",buildings.getFlatSize());
            item.put("bedrooms",buildings.getBedRooms());
            item.put("baths",buildings.getBathRooms());
            item.put("price",buildings.getPrice());
            item.put("builtindate",buildings.getBuiltInDate());
            result.add(item);
        }
        return result;
    }
}
