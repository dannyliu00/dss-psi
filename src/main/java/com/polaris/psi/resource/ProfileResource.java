/**
 * 
 */
package com.polaris.psi.resource;

import groovy.util.logging.Slf4j;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.polaris.psi.resource.dto.OrderSegmentDto;
import com.polaris.psi.resource.dto.OrderSegment;
import com.polaris.psi.resource.dto.ProfileDto;
import com.polaris.psi.resource.dto.ProfilePeriodDto;
import com.polaris.psi.resource.dto.SegmentDto;
import com.polaris.psi.resource.dto.SegmentQuantityDto;

/**
 * @author bericks
 *
 */
@Slf4j
@Component
@Path("/profile")
public class ProfileResource {
	
	@GET
    @Path("/{profileId}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public ProfileDto getProfile(@PathParam("profileId") int profileId) {
		
		return buildProfile(profileId);
	}
	
	private ProfileDto buildProfile(int profileId) {
		ProfileDto profile = new ProfileDto();
		switch (profileId) {
		case 999:
			profile.setModifiedDate(new Date());
			profile.setType("motorcycle");
			profile.setName("Victory Inventory Profile 04/30/14");
			profile.setProfileId(profileId);
			profile.setStatus("Not Started");

			profile.setPeriods(buildMotorcycleProfilePeriods());
			profile.setSegments(buildMotorcycleSegments());
			break;

		case 998:
			profile.setModifiedDate(new Date());
			profile.setType("atv");
			profile.setName("ATV Inventory Profile 04/30/14");
			profile.setProfileId(profileId);
			profile.setStatus("Not Started");
			
			profile.setPeriods(buildATVProfilePeriods());
			profile.setSegments(buildATVSegments());
		default:
			break;
		}
		
		return profile;
	}
	
	private List<ProfilePeriodDto> buildATVProfilePeriods() {
		List<ProfilePeriodDto> periods = new ArrayList<ProfilePeriodDto>();
		ProfilePeriodDto july = new ProfilePeriodDto();
		july.setId(1);
		july.setName("ATV 2014 Jul");
		july.setQuantities(buildQuantities(1));
		periods.add(july);
		
		ProfilePeriodDto august = new ProfilePeriodDto();
		august.setId(1);
		august.setName("ATV 2014 Aug");
		august.setQuantities(buildQuantities(1));
		periods.add(august);
		
		ProfilePeriodDto september = new ProfilePeriodDto();
		september.setId(1);
		september.setName("ATV 2014 Sep");
		september.setQuantities(buildQuantities(1));
		periods.add(september);
		
		return periods;
	}
	
	private List<ProfilePeriodDto> buildMotorcycleProfilePeriods() {
		List<ProfilePeriodDto> periods = new ArrayList<ProfilePeriodDto>();
		ProfilePeriodDto july = new ProfilePeriodDto();
		july.setId(1);
		july.setName("VIC 2014 Jul");
		periods.add(july);
		
		return periods;
	}
		
	private List<SegmentQuantityDto> buildQuantities(int version) {
		List<SegmentQuantityDto> quantities = new ArrayList<SegmentQuantityDto>();
		
		
		switch (version) {
		case 1:
			SegmentQuantityDto qty1 = new SegmentQuantityDto();
			qty1.setActual(0);
			qty1.setMaximum(5);
			qty1.setMinimum(3);
			qty1.setRecommended(4);
			quantities.add(qty1);
			
			SegmentQuantityDto qty2 = new SegmentQuantityDto();
			qty2.setActual(0);
			qty2.setMaximum(6);
			qty2.setMinimum(4);
			qty2.setRecommended(5);
			quantities.add(qty2);

			SegmentQuantityDto qty3 = new SegmentQuantityDto();
			qty3.setActual(0);
			qty3.setMaximum(7);
			qty3.setMinimum(5);
			qty3.setRecommended(6);
			quantities.add(qty3);

			break;
			
		default:
			SegmentQuantityDto quantity = new SegmentQuantityDto();
			quantity.setActual(3);
			quantity.setMaximum(0);
			quantity.setMinimum(0);
			quantity.setRecommended(3);
			quantities.add(quantity);
		}
		
		return quantities;
	}
	
	private List<SegmentDto> buildMotorcycleSegments() {
		List<SegmentDto> segments = new ArrayList<SegmentDto>();
		SegmentDto cruiser = new SegmentDto();
		cruiser.setName("Cruiser");
		cruiser.setRecommendedQty(4);
		cruiser.setSegmentId(111);
		cruiser.setOrderSegments(buildCruiserOrderSegments());
		segments.add(cruiser);
		
		SegmentDto bagger = new SegmentDto();
		bagger.setName("Bagger");
		bagger.setRecommendedQty(4);
		bagger.setSegmentId(222);
		bagger.setOrderSegments(buildBaggerOrderSegments());
		segments.add(bagger);
		
		SegmentDto touring = new SegmentDto();
		touring.setName("Touring");
		touring.setRecommendedQty(3);
		touring.setSegmentId(333);
		touring.setOrderSegments(buildTouringOrderSegments());
		segments.add(touring);
		
		return segments;
	}
	
	private List<OrderSegmentDto> buildCruiserOrderSegments() {
		List<OrderSegmentDto> segments = new ArrayList<OrderSegmentDto>();
		OrderSegmentDto judge = new OrderSegmentDto();
		judge.setName("Judge");
		judge.setId(1);
		judge.setQuantities(buildQuantities(0));
		segments.add(judge);
		
		OrderSegmentDto vegas = new OrderSegmentDto();
		vegas.setName("Vegas 8-Ball");
		vegas.setId(2);
		vegas.setQuantities(buildQuantities(0));
		segments.add(vegas);
		
		OrderSegmentDto highBall = new OrderSegmentDto();
		highBall.setName("High-Ball");
		highBall.setId(3);
		highBall.setQuantities(buildQuantities(0));
		segments.add(highBall);
		
		OrderSegmentDto gunner = new OrderSegmentDto();
		gunner.setName("Gunner");
		gunner.setId(4);
		gunner.setQuantities(buildQuantities(0));
		segments.add(gunner);
		
		OrderSegmentDto boardWalk = new OrderSegmentDto();
		boardWalk.setName("Boardwalk");
		boardWalk.setId(5);
		boardWalk.setQuantities(buildQuantities(0));
		segments.add(boardWalk);
		
		OrderSegmentDto fatTire = new OrderSegmentDto();
		fatTire.setName("Fat Tire");
		fatTire.setId(6);
		fatTire.setQuantities(buildQuantities(0));
		segments.add(fatTire);
		
		return segments;
	}
	
	private List<OrderSegmentDto> buildBaggerOrderSegments() {
		List<OrderSegmentDto> segments = new ArrayList<OrderSegmentDto>();
		
		OrderSegmentDto xCountry = new OrderSegmentDto();
		xCountry.setName("Cross Country");
		xCountry.setId(7);
		xCountry.setQuantities(buildQuantities(0));
		segments.add(xCountry);
		
		OrderSegmentDto customXC = new OrderSegmentDto();
		customXC.setName("Custom Cross Country");
		customXC.setId(8);
		customXC.setQuantities(buildQuantities(0));
		segments.add(customXC);
		
		OrderSegmentDto value = new OrderSegmentDto();
		value.setName("Value Bagger");
		value.setId(9);
		value.setQuantities(buildQuantities(0));
		segments.add(value);
		
		OrderSegmentDto xRoads = new OrderSegmentDto();
		xRoads.setName("Cross Roads");
		xRoads.setId(10);
		xRoads.setQuantities(buildQuantities(0));
		
		return segments;
	}
	
	private List<OrderSegmentDto> buildTouringOrderSegments() {
		List<OrderSegmentDto> segments = new ArrayList<OrderSegmentDto>();
		
		OrderSegmentDto xcTour = new OrderSegmentDto();
		xcTour.setName("Cross Country Tour");
		xcTour.setId(11);
		xcTour.setQuantities(buildQuantities(0));
		segments.add(xcTour);
		
		OrderSegmentDto vision = new OrderSegmentDto();
		vision.setName("Vision");
		vision.setId(12);
		vision.setQuantities(buildQuantities(0));
		segments.add(vision);
		
		return segments;
	}
	
	private List<SegmentDto> buildATVSegments() {
		List<SegmentDto> segments = new ArrayList<SegmentDto>();
		SegmentDto rangerXP = new SegmentDto();
		rangerXP.setActualQty(0);
		rangerXP.setName("Ranger XP");
		rangerXP.setRecommendedQty(0);
		rangerXP.setSegmentId(1);
		rangerXP.setOrderSegments(buildRangerXPSegments());
		segments.add(rangerXP);
		
		return segments;
	}
	
	private List<OrderSegmentDto> buildRangerXPSegments() {
		List<OrderSegmentDto> orderSegments = new ArrayList<OrderSegmentDto>();
		
		OrderSegmentDto base = new OrderSegmentDto();
		base.setId(1);
		base.setName("Ranger XP Base");
		base.setQuantities(buildQuantities(1));
		orderSegments.add(base);
		
		OrderSegmentDto paint = new OrderSegmentDto();
		paint.setId(2);
		paint.setName("Ranger XP Paint");
		paint.setQuantities(buildQuantities(2));
		orderSegments.add(paint);
		
		OrderSegmentDto eps = new OrderSegmentDto();
		eps.setId(3);
		eps.setName("Ranger XP EPS");
		eps.setQuantities(buildQuantities(3));
		orderSegments.add(eps);
		
		return orderSegments;
	}
	
}
