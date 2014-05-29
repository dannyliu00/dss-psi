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

import com.polaris.psi.model.InventoryProfile;
import com.polaris.psi.model.OrderSegment;
import com.polaris.psi.model.Segment;

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
	public InventoryProfile getProfile(@PathParam("profileId") int profileId) {
		
		return buildProfile(profileId);
	}
	
	private InventoryProfile buildProfile(int profileId) {
		InventoryProfile profile = new InventoryProfile();
		profile.setModifiedDate(new Date());
		profile.setType("MOTORYCYCLE");
		profile.setName("Victory Inventory Profile 04/30/14");
		profile.setProfileId(profileId);
		profile.setStatus("Not Started");
		
		profile.setSegments(buildSegments());
		
		return profile;
	}
	
	private List<Segment> buildSegments() {
		List<Segment> segments = new ArrayList<Segment>();
		Segment cruiser = new Segment();
		cruiser.setName("Cruiser");
		cruiser.setRecommendedQty(4);
		cruiser.setSegmentId(111);
		cruiser.setOrderSegments(buildCruiserOrderSegments());
		segments.add(cruiser);
		
		Segment bagger = new Segment();
		bagger.setName("Bagger");
		bagger.setRecommendedQty(4);
		bagger.setSegmentId(222);
		bagger.setOrderSegments(buildBaggerOrderSegments());
		segments.add(bagger);
		
		Segment touring = new Segment();
		touring.setName("Touring");
		touring.setRecommendedQty(3);
		touring.setSegmentId(333);
		touring.setOrderSegments(buildTouringORderSegments());
		segments.add(touring);
		
		return segments;
	}
	
	private List<OrderSegment> buildCruiserOrderSegments() {
		List<OrderSegment> segments = new ArrayList<OrderSegment>();
		OrderSegment judge = new OrderSegment();
		judge.setName("Judge");
		judge.setOrderSegmentId(1);
		judge.setRecommendedQty(0);
		segments.add(judge);
		
		OrderSegment vegas = new OrderSegment();
		vegas.setName("Vegas 8-Ball");
		vegas.setOrderSegmentId(2);
		vegas.setRecommendedQty(1);
		segments.add(vegas);
		
		OrderSegment highBall = new OrderSegment();
		highBall.setName("High-Ball");
		highBall.setOrderSegmentId(3);
		highBall.setRecommendedQty(1);
		segments.add(highBall);
		
		OrderSegment gunner = new OrderSegment();
		gunner.setName("Gunner");
		gunner.setOrderSegmentId(4);
		gunner.setRecommendedQty(0);
		segments.add(gunner);
		
		OrderSegment boardWalk = new OrderSegment();
		boardWalk.setName("Boardwalk");
		boardWalk.setOrderSegmentId(5);
		boardWalk.setRecommendedQty(1);
		segments.add(boardWalk);
		
		OrderSegment fatTire = new OrderSegment();
		fatTire.setName("Fat Tire");
		fatTire.setOrderSegmentId(6);
		fatTire.setRecommendedQty(1);
		segments.add(fatTire);
		
		return segments;
	}
	
	private List<OrderSegment> buildBaggerOrderSegments() {
		List<OrderSegment> segments = new ArrayList<OrderSegment>();
		
		OrderSegment xCountry = new OrderSegment();
		xCountry.setName("Cross Country");
		xCountry.setOrderSegmentId(7);
		xCountry.setRecommendedQty(2);
		segments.add(xCountry);
		
		OrderSegment customXC = new OrderSegment();
		customXC.setName("Custom Cross Country");
		customXC.setOrderSegmentId(8);
		customXC.setRecommendedQty(1);
		segments.add(customXC);
		
		OrderSegment value = new OrderSegment();
		value.setName("Value Bagger");
		value.setOrderSegmentId(9);
		value.setRecommendedQty(1);
		segments.add(value);
		
		OrderSegment xRoads = new OrderSegment();
		xRoads.setName("Cross Roads");
		xRoads.setOrderSegmentId(10);
		xRoads.setRecommendedQty(0);
		
		return segments;
	}
	
	private List<OrderSegment> buildTouringORderSegments() {
		List<OrderSegment> segments = new ArrayList<OrderSegment>();
		
		OrderSegment xcTour = new OrderSegment();
		xcTour.setName("Cross Country Tour");
		xcTour.setOrderSegmentId(11);
		xcTour.setRecommendedQty(2);
		segments.add(xcTour);
		
		OrderSegment vision = new OrderSegment();
		vision.setName("Vision");
		vision.setOrderSegmentId(12);
		vision.setRecommendedQty(1);
		segments.add(vision);
		
		return segments;
	}
	
}
