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
import com.polaris.psi.util.PolarisIdentity;
import com.polaris.psi.util.SplunkLogger;

/**
 * @author bericks
 *
 */
@Component
public class PSIOrderSegmentMapper {

	private static final SplunkLogger LOG = new SplunkLogger(PSIOrderSegmentMapper.class);
	
	/**
	 * @param oses
	 * @param details
	 * @param email
	 * @return
	 */
	public List<OrderSegmentDto> mapToDto(List<PSIOrderSegment> oses, List<PSIProfileDetail> details, String email) {
		LOG.methodStart(PolarisIdentity.get(), "mapToDto");
		
		List<OrderSegmentDto> dtos = new ArrayList<OrderSegmentDto>();
		
		if(oses.size() == 0) return dtos;
		
		if(details.size() == 0) {
			for (PSIOrderSegment entity : oses) {
				dtos.add(mapToDto(entity, null, null));
			}
			return dtos;
		}
		
		for (PSIOrderSegment orderSegment : oses) {
			Integer profileOrderSegmentId = orderSegment.getId();
			String periodCode = orderSegment.getPeriodCode();
			PSIProfileDetail detail = getDetailObject(details, profileOrderSegmentId, periodCode);
			dtos.add(mapToDto(orderSegment, detail, email));
		}
		
		LOG.methodEnd(PolarisIdentity.get(), "mapToDto");

		return dtos;
	}
	
	public OrderSegmentDto mapToDto(PSIOrderSegment os, PSIProfileDetail detail, String email) {
		LOG.methodStart(PolarisIdentity.get(), "mapToDto");

		OrderSegmentDto dto = new OrderSegmentDto();
		
		dto.setDealerEmail(email);

		dto.setProfileId(os.getProfileId());
		dto.setComplianceId(os.getComplianceId());
		dto.setDealerId(os.getDealerId());
		dto.setOsCode(os.getOsCode());
		dto.setPeriodCode(os.getPeriodCode());
		dto.setProfileOrderSegmentId(os.getId());
		dto.setRecMaximum(os.getRecMaximum());
		dto.setRecMinimum(os.getRecMinimum());
		dto.setRecommended(os.getRecommended());
		dto.setSort(os.getSort());
		dto.setOsName(os.getOsName());

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
		
		LOG.methodEnd(PolarisIdentity.get(), "mapToDto");

		return dto;
	}
	
	protected PSIProfileDetail getDetailObject(List<PSIProfileDetail> details, Integer profileOrderSegmentId, String periodCode) {
		LOG.methodEnd(PolarisIdentity.get(), "getDetailObject");

		for (PSIProfileDetail detail : details) {
			if(detail.getPeriodCode().equals(periodCode) 
					&& detail.getProfileOrderSegmentId().intValue() == profileOrderSegmentId.intValue()) {

				LOG.methodEnd(PolarisIdentity.get(), "getDetailObject");
				return detail;
			}
		}
		
		LOG.methodEnd(PolarisIdentity.get(), "getDetailObject");

		return null;
	}

}
