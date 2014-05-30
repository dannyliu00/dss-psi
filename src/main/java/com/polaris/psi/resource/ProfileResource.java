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

import com.polaris.psi.resource.dto.ProfileDto;
import com.polaris.psi.resource.dto.OrderSegmentDto;
import com.polaris.psi.resource.dto.OrderSegmentWithRange;
import com.polaris.psi.resource.dto.SegmentDto;

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
			
			profile.setSegments(buildMotorcycleSegments());
			break;

		case 998:
			profile.setModifiedDate(new Date());
			profile.setType("atv");
			profile.setName("ATV Inventory Profile 04/30/14");
			profile.setProfileId(profileId);
			profile.setStatus("Not Started");
			
			profile.setSegments(buildATVSegments());
		default:
			break;
		}
		
		return profile;
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
		touring.setOrderSegments(buildTouringORderSegments());
		segments.add(touring);
		
		return segments;
	}
	
	private List<OrderSegmentDto> buildCruiserOrderSegments() {
		List<OrderSegmentDto> segments = new ArrayList<OrderSegmentDto>();
		OrderSegmentDto judge = new OrderSegmentDto();
		judge.setName("Judge");
		judge.setOrderSegmentId(1);
		judge.setRecommendedQty(0);
		segments.add(judge);
		
		OrderSegmentDto vegas = new OrderSegmentDto();
		vegas.setName("Vegas 8-Ball");
		vegas.setOrderSegmentId(2);
		vegas.setRecommendedQty(1);
		segments.add(vegas);
		
		OrderSegmentDto highBall = new OrderSegmentDto();
		highBall.setName("High-Ball");
		highBall.setOrderSegmentId(3);
		highBall.setRecommendedQty(1);
		segments.add(highBall);
		
		OrderSegmentDto gunner = new OrderSegmentDto();
		gunner.setName("Gunner");
		gunner.setOrderSegmentId(4);
		gunner.setRecommendedQty(0);
		segments.add(gunner);
		
		OrderSegmentDto boardWalk = new OrderSegmentDto();
		boardWalk.setName("Boardwalk");
		boardWalk.setOrderSegmentId(5);
		boardWalk.setRecommendedQty(1);
		segments.add(boardWalk);
		
		OrderSegmentDto fatTire = new OrderSegmentDto();
		fatTire.setName("Fat Tire");
		fatTire.setOrderSegmentId(6);
		fatTire.setRecommendedQty(1);
		segments.add(fatTire);
		
		return segments;
	}
	
	private List<OrderSegmentDto> buildBaggerOrderSegments() {
		List<OrderSegmentDto> segments = new ArrayList<OrderSegmentDto>();
		
		OrderSegmentDto xCountry = new OrderSegmentDto();
		xCountry.setName("Cross Country");
		xCountry.setOrderSegmentId(7);
		xCountry.setRecommendedQty(2);
		segments.add(xCountry);
		
		OrderSegmentDto customXC = new OrderSegmentDto();
		customXC.setName("Custom Cross Country");
		customXC.setOrderSegmentId(8);
		customXC.setRecommendedQty(1);
		segments.add(customXC);
		
		OrderSegmentDto value = new OrderSegmentDto();
		value.setName("Value Bagger");
		value.setOrderSegmentId(9);
		value.setRecommendedQty(1);
		segments.add(value);
		
		OrderSegmentDto xRoads = new OrderSegmentDto();
		xRoads.setName("Cross Roads");
		xRoads.setOrderSegmentId(10);
		xRoads.setRecommendedQty(0);
		
		return segments;
	}
	
	private List<OrderSegmentDto> buildTouringORderSegments() {
		List<OrderSegmentDto> segments = new ArrayList<OrderSegmentDto>();
		
		OrderSegmentDto xcTour = new OrderSegmentDto();
		xcTour.setName("Cross Country Tour");
		xcTour.setOrderSegmentId(11);
		xcTour.setRecommendedQty(2);
		segments.add(xcTour);
		
		OrderSegmentDto vision = new OrderSegmentDto();
		vision.setName("Vision");
		vision.setOrderSegmentId(12);
		vision.setRecommendedQty(1);
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
		
		OrderSegmentWithRange base = new OrderSegmentWithRange();
		base.setActualQty(0);
		base.setName("Ranger XP Base");
		base.setOrderSegmentId(1);
		base.setRecommendedQty(4);
		base.setMinRecommend(3);
		base.setMaxRecommend(5);
		orderSegments.add(base);
		
		OrderSegmentWithRange paint = new OrderSegmentWithRange();
		paint.setActualQty(0);
		paint.setMaxRecommend(8);
		paint.setMinRecommend(4);
		paint.setName("Ranger XP Paint");
		paint.setRecommendedQty(6);
		orderSegments.add(paint);
		
		OrderSegmentWithRange eps = new OrderSegmentWithRange();
		eps.setActualQty(0);
		eps.setMaxRecommend(8);
		eps.setMinRecommend(4);
		eps.setName("Ranger XP EPS");
		eps.setRecommendedQty(6);
		orderSegments.add(eps);
		
		return orderSegments;
	}
	
}
