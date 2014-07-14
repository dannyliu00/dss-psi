/**
 * 
 */
package com.polaris.psi.service.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.polaris.psi.repository.entity.PSIOrderSegment;
import com.polaris.psi.repository.entity.PSIProfileDetail;
import com.polaris.psi.resource.dto.OrderSegmentDto;

/**
 * @author bericks
 *
 */
@Component
public class PSIOrderSegmentMapper {

	public List<OrderSegmentDto> mapToDto(List<PSIOrderSegment> oses, List<PSIProfileDetail> details) {
		List<OrderSegmentDto> dtos = new ArrayList<OrderSegmentDto>();
		
		if(oses.size() == 0) return dtos;
		
		if(details.size() == 0) {
			for (PSIOrderSegment entity : oses) {
				dtos.add(mapToDto(entity, null));
			}
			return dtos;
		}
		
		for (PSIProfileDetail detail : details) {
			int posId = detail.getProfileOrderSegmentId();
			String code = detail.getPeriodCode();
			PSIOrderSegment os = getOrderSegmentWithIdAndCode(oses, posId, code);
			if(os != null) dtos.add(mapToDto(os, detail));
		}
		
		return dtos;
	}

	public OrderSegmentDto mapToDto(PSIOrderSegment os, PSIProfileDetail detail) {
		OrderSegmentDto dto = new OrderSegmentDto();
		
		dto.setProfileId(os.getProfileId());
		dto.setComplianceId(os.getComplianceId());
		dto.setDealerId(os.getDealerId());
		dto.setName(os.getName());
		dto.setPeriodCode(os.getPeriodCode());
		dto.setProfileOrderSegmentId(os.getId());
		dto.setRecMaximum(os.getRecMaximum());
		dto.setRecMinimum(os.getRecMinimum());
		dto.setRecommended(os.getRecommended());
		dto.setSort(os.getSort());
		dto.setSubSegment(os.getSubSegment());

		if(detail != null) {
			dto.setActual(detail.getRequestedQty());
			dto.setId(detail.getId());
			dto.setAdminComments(detail.getAdminComments());
			dto.setAdminQty(detail.getAdminQty());
			dto.setAdminReasonCode(detail.getAdminReasonCode());
			dto.setDealerComments(detail.getDealerComments());
			dto.setDsmComments(detail.getDsmComments());
			dto.setDsmQty(detail.getDsmQty());
			dto.setDsmReasonCode(detail.getDsmReasonCode());
			dto.setFinalQty(detail.getFinalQty());
			dto.setHeaderId(detail.getHeaderId());
			dto.setProfileOrderSegmentId(detail.getProfileOrderSegmentId());
			dto.setReasonCode(detail.getReasonCode());
			dto.setPeriodCode(detail.getPeriodCode());
		}
		
		return dto;
	}
	
	protected PSIOrderSegment getOrderSegmentWithIdAndCode(List<PSIOrderSegment> oses, Integer id, String code) {
		
		for (PSIOrderSegment orderSegment : oses) {
			if(orderSegment.getId().intValue() == id.intValue() && orderSegment.getPeriodCode().equals(code))
				return orderSegment;
		}
		
		return null;
	}
	
	protected PSIOrderSegment getOrderSegmentWithId(List<PSIOrderSegment> oses, Integer id) {
		
		for (PSIOrderSegment orderSegment : oses) {
			if(orderSegment.getId().intValue() == id.intValue()) return orderSegment;
		}
		
		return null;
	}
}
