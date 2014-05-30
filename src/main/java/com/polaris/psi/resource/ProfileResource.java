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

import com.polaris.psi.resource.dto.InventoryProfileDTO;
import com.polaris.psi.resource.dto.OrderSegmentDTO;
import com.polaris.psi.resource.dto.OrderSegmentWithRange;
import com.polaris.psi.resource.dto.SegmentDTO;

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
	public InventoryProfileDTO getProfile(@PathParam("profileId") int profileId) {
		
		return buildProfile(profileId);
	}
	
	private InventoryProfileDTO buildProfile(int profileId) {
		InventoryProfileDTO profile = new InventoryProfileDTO();
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
	
	private List<SegmentDTO> buildMotorcycleSegments() {
		List<SegmentDTO> segments = new ArrayList<SegmentDTO>();
		SegmentDTO cruiser = new SegmentDTO();
		cruiser.setName("Cruiser");
		cruiser.setRecommendedQty(4);
		cruiser.setSegmentId(111);
		cruiser.setOrderSegments(buildCruiserOrderSegments());
		segments.add(cruiser);
		
		SegmentDTO bagger = new SegmentDTO();
		bagger.setName("Bagger");
		bagger.setRecommendedQty(4);
		bagger.setSegmentId(222);
		bagger.setOrderSegments(buildBaggerOrderSegments());
		segments.add(bagger);
		
		SegmentDTO touring = new SegmentDTO();
		touring.setName("Touring");
		touring.setRecommendedQty(3);
		touring.setSegmentId(333);
		touring.setOrderSegments(buildTouringORderSegments());
		segments.add(touring);
		
		return segments;
	}
	
	private List<OrderSegmentDTO> buildCruiserOrderSegments() {
		List<OrderSegmentDTO> segments = new ArrayList<OrderSegmentDTO>();
		OrderSegmentDTO judge = new OrderSegmentDTO();
		judge.setName("Judge");
		judge.setOrderSegmentId(1);
		judge.setRecommendedQty(0);
		segments.add(judge);
		
		OrderSegmentDTO vegas = new OrderSegmentDTO();
		vegas.setName("Vegas 8-Ball");
		vegas.setOrderSegmentId(2);
		vegas.setRecommendedQty(1);
		segments.add(vegas);
		
		OrderSegmentDTO highBall = new OrderSegmentDTO();
		highBall.setName("High-Ball");
		highBall.setOrderSegmentId(3);
		highBall.setRecommendedQty(1);
		segments.add(highBall);
		
		OrderSegmentDTO gunner = new OrderSegmentDTO();
		gunner.setName("Gunner");
		gunner.setOrderSegmentId(4);
		gunner.setRecommendedQty(0);
		segments.add(gunner);
		
		OrderSegmentDTO boardWalk = new OrderSegmentDTO();
		boardWalk.setName("Boardwalk");
		boardWalk.setOrderSegmentId(5);
		boardWalk.setRecommendedQty(1);
		segments.add(boardWalk);
		
		OrderSegmentDTO fatTire = new OrderSegmentDTO();
		fatTire.setName("Fat Tire");
		fatTire.setOrderSegmentId(6);
		fatTire.setRecommendedQty(1);
		segments.add(fatTire);
		
		return segments;
	}
	
	private List<OrderSegmentDTO> buildBaggerOrderSegments() {
		List<OrderSegmentDTO> segments = new ArrayList<OrderSegmentDTO>();
		
		OrderSegmentDTO xCountry = new OrderSegmentDTO();
		xCountry.setName("Cross Country");
		xCountry.setOrderSegmentId(7);
		xCountry.setRecommendedQty(2);
		segments.add(xCountry);
		
		OrderSegmentDTO customXC = new OrderSegmentDTO();
		customXC.setName("Custom Cross Country");
		customXC.setOrderSegmentId(8);
		customXC.setRecommendedQty(1);
		segments.add(customXC);
		
		OrderSegmentDTO value = new OrderSegmentDTO();
		value.setName("Value Bagger");
		value.setOrderSegmentId(9);
		value.setRecommendedQty(1);
		segments.add(value);
		
		OrderSegmentDTO xRoads = new OrderSegmentDTO();
		xRoads.setName("Cross Roads");
		xRoads.setOrderSegmentId(10);
		xRoads.setRecommendedQty(0);
		
		return segments;
	}
	
	private List<OrderSegmentDTO> buildTouringORderSegments() {
		List<OrderSegmentDTO> segments = new ArrayList<OrderSegmentDTO>();
		
		OrderSegmentDTO xcTour = new OrderSegmentDTO();
		xcTour.setName("Cross Country Tour");
		xcTour.setOrderSegmentId(11);
		xcTour.setRecommendedQty(2);
		segments.add(xcTour);
		
		OrderSegmentDTO vision = new OrderSegmentDTO();
		vision.setName("Vision");
		vision.setOrderSegmentId(12);
		vision.setRecommendedQty(1);
		segments.add(vision);
		
		return segments;
	}
	
	private List<SegmentDTO> buildATVSegments() {
		List<SegmentDTO> segments = new ArrayList<SegmentDTO>();
		SegmentDTO rangerXP = new SegmentDTO();
		rangerXP.setActualQty(0);
		rangerXP.setName("Ranger XP");
		rangerXP.setRecommendedQty(0);
		rangerXP.setSegmentId(1);
		rangerXP.setOrderSegments(buildRangerXPSegments());
		segments.add(rangerXP);
		
		return segments;
	}
	
	private List<OrderSegmentDTO> buildRangerXPSegments() {
		List<OrderSegmentDTO> orderSegments = new ArrayList<OrderSegmentDTO>();
		
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
