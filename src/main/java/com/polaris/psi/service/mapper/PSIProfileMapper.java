/**
 * 
 */
package com.polaris.psi.service.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.polaris.psi.Constants;
import com.polaris.psi.repository.entity.PSIProfile;
import com.polaris.psi.resource.dto.ProfileDto;
import com.polaris.psi.util.PolarisIdentity;
import com.polaris.psi.util.SplunkLogger;

/**
 * PSIProfileMapper maps a PSIProfile JPA entity to a ProfileDto object more fit for
 * use in PSI web requests.
 * 
 * @author bericks
 *
 */
@Component
public class PSIProfileMapper implements IMapper<PSIProfile, ProfileDto> {

	private static final SplunkLogger LOG = new SplunkLogger(PSIProfileMapper.class);
	
	@Autowired
	ProfileTypeMapper typeMapper;
	
	/* (non-Javadoc)
	 * @see com.polaris.psi.service.mapper.IMapper#mapToDto(java.util.List)
	 */
	@Override
	public List<ProfileDto> mapToDto(List<PSIProfile> entities) {
		LOG.methodStart(PolarisIdentity.get(), "mapToDto");

		List<ProfileDto> profiles = new ArrayList<ProfileDto>();
		
		for (PSIProfile entity : entities) {
			profiles.add(mapToDto(entity));
		}

		LOG.methodEnd(PolarisIdentity.get(), "mapToDto");

		return profiles;
	}

	/* (non-Javadoc)
	 * @see com.polaris.psi.service.mapper.IMapper#mapToDto(java.lang.Object)
	 */
	@Override
	public ProfileDto mapToDto(PSIProfile entity) {
		LOG.methodStart(PolarisIdentity.get(), "mapToDto");

		ProfileDto dto = new ProfileDto();

		dto.setName(entity.getName());
		dto.setTargetCompletionDate(entity.getTargetCompleteDate());
		dto.setModifiedDate(entity.getLastModifiedDate());
		dto.setProfileId(entity.getId());
		dto.setStatus(getStatus(entity.getStatus()));
		dto.setTypeCode(entity.getType());
		dto.setDealerEmail(entity.getEmail());
		
		typeMapper.mapTypeToProfile(entity.getType(), dto);
		
		LOG.methodEnd(PolarisIdentity.get(), "mapToDto");

		return dto;
	}
	
	/* (non-Javadoc)
	 * @see com.polaris.psi.service.mapper.IMapper#mapToDto(java.lang.Object)
	 */
	public ProfileDto mapToDtoRealStatusMessaging(PSIProfile entity) {
		LOG.methodStart(PolarisIdentity.get(), "mapToDto");

		ProfileDto dto = new ProfileDto();

		dto.setName(entity.getName());
		dto.setTargetCompletionDate(entity.getTargetCompleteDate());
		dto.setModifiedDate(entity.getLastModifiedDate());
		dto.setProfileId(entity.getId());
		dto.setStatus(entity.getStatus() == null ? Constants.DEFAULT_PROFILE_STATUS : entity.getStatus());
		dto.setTypeCode(entity.getType());
		dto.setDealerEmail(entity.getEmail());
		
		typeMapper.mapTypeToProfile(entity.getType(), dto);
		
		LOG.methodEnd(PolarisIdentity.get(), "mapToDto");

		return dto;
	}
	
	/* (non-Javadoc)
	 * @see com.polaris.psi.service.mapper.IMapper#mapToDto(java.lang.Object)
	 */
	public ProfileDto mapToDto(PSIProfile entity, boolean isDealer) {
		
		if(isDealer) return mapToDto(entity);
		else return mapToDtoRealStatusMessaging(entity);

	}
	
	/**
	 * Returns the appropriate status string based on the value passed in.  PENDING, RETURNED TO DEALER and 
	 * APPROVED NONCOMPLIANT do not change.  RETURNED TO DSM and EXCEPTION REQUESTED return a PENDING status.  
	 * APPROVED AS REQUESTED, APPROVED WITH CHANGES and APPROVED COMPLIANT return APPROVED.  A null or empty string
	 * returns NOT STARTED.
	 * 
	 * @param s
	 * @return
	 */
	protected String getStatus(String s) {
		LOG.methodStart(PolarisIdentity.get(), "getStatus");

		if (s == null) s = "";
		
		switch (s) {
			case Constants.IN_PROGRESS_STATUS:
			case Constants.PENDING_STATUS:
			case Constants.RETURNED_TO_DEALER:
			case Constants.APPROVED_NONCOMPLIANT:
				break;
		
			case Constants.RETURNED_TO_DSM:
			case Constants.EXCEPTION_REQUESTED:
				s = Constants.PENDING_STATUS;
				break;
	
			case Constants.APPROVED_AS_REQUESTED:
			case Constants.APPROVED_W_CHANGES:
			case Constants.APPROVED_COMPLIANT:
				s = Constants.APPROVED;
				break;
	
			default:
				s = Constants.DEFAULT_PROFILE_STATUS;
				break;
		}

		LOG.methodEnd(PolarisIdentity.get(), "getStatus");

		return s;
	}

}
