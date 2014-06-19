package com.polaris.psi.repository.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.polaris.psi.repository.entity.OrderSegment;
import com.polaris.pwf.dao.AbstractPolarisDealersExtensionDao;

@Repository
public class OrderSegmentDao extends AbstractPolarisDealersExtensionDao<OrderSegment> {

	public OrderSegmentDao() {
		super(OrderSegment.class);
	}
	
	public OrderSegment retrieveByName(String name) {
        Map<String, Object> keyMap = new HashMap<String, Object>(1);
        keyMap.put("name", name);
        
        List<OrderSegment> orderSegments = selectByMap(keyMap, null);
        
        if(orderSegments.size() > 0 && orderSegments.get(0).getSubSegment() != null) {
        	return orderSegments.get(0);
        }
        
        return null;
	}
}
