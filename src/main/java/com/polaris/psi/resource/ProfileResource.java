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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.polaris.psi.resource.dto.IBaseDto;
import com.polaris.psi.resource.dto.OrderSegmentDto;
import com.polaris.psi.resource.dto.ProfileDto;
import com.polaris.psi.resource.dto.ProfilePeriodDto;
import com.polaris.psi.resource.dto.SegmentDto;
import com.polaris.psi.resource.dto.SegmentQuantityDto;
import com.polaris.psi.resource.dto.util.ProfilePeriodTotalsCalculator;
import com.polaris.psi.resource.dto.util.TotalsCalculator;
import com.polaris.psi.service.ProfileService;

/**
 * @author bericks
 *
 */
@Slf4j
@Component
@Path("/profile")
public class ProfileResource {
	
	@Autowired
	ProfileService service;
	
	@GET
    @Path("/{profileId}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public ProfileDto getProfile(@PathParam("profileId") int profileId) {
		
		return service.getDealerProfile(profileId);
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

//			profile.setPeriods(buildMotorcycleProfilePeriods());
//			profile.setSegments(buildMotorcycleSegments());
			break;

		case 998:
			profile.setModifiedDate(new Date());
			profile.setType("atv");
			profile.setName("ATV Inventory Profile 04/30/14");
			profile.setProfileId(profileId);
			profile.setStatus("Not Started");
			
//			profile.setPeriods(buildATVProfilePeriods());
//			profile.setSegments(buildATVSegments());
		default:
			break;
		}
		
		ProfilePeriodTotalsCalculator ppCalc = new ProfilePeriodTotalsCalculator();
		ppCalc.calculateProfilePeriodTotals(profile);

		TotalsCalculator calculator = new TotalsCalculator();
//		calculator.calculateTotals(profile, profile.getSegments());
		if(profile.getType().equals("motorcycle")) {
			profile.setRecMinimum(profile.getRecommended() - 1);
			profile.setRecMaximum(profile.getRecommended() + 2);
		}
		
		return profile;
	}
	
	private List<IBaseDto> buildATVProfilePeriods() {
		List<IBaseDto> periods = new ArrayList<IBaseDto>();
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
	
	private List<IBaseDto> buildMotorcycleProfilePeriods() {
		TotalsCalculator calculator = new TotalsCalculator();
		
		List<IBaseDto> periods = new ArrayList<IBaseDto>();
		ProfilePeriodDto july = new ProfilePeriodDto();
		july.setId(1);
		july.setName("VIC 2014 Jul");
		july.setQuantities(buildQuantities(0));
		
		calculator.calculateTotals(july, july.getQuantities());
		periods.add(july);
		
		return periods;
	}
		
	private List<IBaseDto> buildQuantities(int version) {
		List<IBaseDto> quantities = new ArrayList<IBaseDto>();
		
		
		switch (version) {
		case 1:
			SegmentQuantityDto qty1 = new SegmentQuantityDto();
			qty1.setActual(0);
			qty1.setRecMaximum(5);
			qty1.setRecMinimum(3);
			qty1.setRecommended(4);
			quantities.add(qty1);
			
			SegmentQuantityDto qty2 = new SegmentQuantityDto();
			qty2.setActual(0);
			qty2.setRecMaximum(6);
			qty2.setRecMinimum(4);
			qty2.setRecommended(5);
			quantities.add(qty2);

			SegmentQuantityDto qty3 = new SegmentQuantityDto();
			qty3.setActual(0);
			qty3.setRecMaximum(7);
			qty3.setRecMinimum(5);
			qty3.setRecommended(6);
			quantities.add(qty3);

			break;
			
		default:
			SegmentQuantityDto quantity = new SegmentQuantityDto();
			quantity.setActual(0);
			quantity.setRecMaximum(0);
			quantity.setRecMinimum(0);
			quantity.setRecommended(3);
			quantities.add(quantity);
		}
		
		return quantities;
	}
	
	private List<IBaseDto> buildMotorcycleSegments() {
		TotalsCalculator calculator = new TotalsCalculator();
		
		List<IBaseDto> segments = new ArrayList<IBaseDto>();
		SegmentDto cruiser = new SegmentDto();
		cruiser.setRecMinimum(17);
		cruiser.setName("Cruiser");
		cruiser.setRecommended(18);
		cruiser.setSegmentId(111);
		cruiser.setOrderSegments(buildCruiserOrderSegments());
		calculator.calculateTotals(cruiser, cruiser.getOrderSegments());
		segments.add(cruiser);
		
		SegmentDto bagger = new SegmentDto();
		bagger.setRecMinimum(8);
		bagger.setName("Bagger");
		bagger.setRecommended(9);
		bagger.setSegmentId(222);
		bagger.setOrderSegments(buildBaggerOrderSegments());
		calculator.calculateTotals(bagger, bagger.getOrderSegments());
		segments.add(bagger);
		
		SegmentDto touring = new SegmentDto();
		touring.setRecMinimum(5);
		touring.setName("Touring");
		touring.setRecommended(6);
		touring.setSegmentId(333);
		touring.setOrderSegments(buildTouringOrderSegments());
		calculator.calculateTotals(touring, touring.getOrderSegments());
		segments.add(touring);
		
		return segments;
	}
	
	private List<IBaseDto> buildCruiserOrderSegments() {
		TotalsCalculator calculator = new TotalsCalculator();
		
		List<IBaseDto> segments = new ArrayList<IBaseDto>();
		OrderSegmentDto judge = new OrderSegmentDto();
		judge.setName("Judge");
		judge.setId(1);
		judge.setQuantities(buildQuantities(0));
		calculator.calculateTotals(judge, judge.getQuantities());
		segments.add(judge);
		
		OrderSegmentDto vegas = new OrderSegmentDto();
		vegas.setName("Vegas 8-Ball");
		vegas.setId(2);
		vegas.setQuantities(buildQuantities(0));
		calculator.calculateTotals(vegas, vegas.getQuantities());
		segments.add(vegas);
		
		OrderSegmentDto highBall = new OrderSegmentDto();
		highBall.setName("High-Ball");
		highBall.setId(3);
		highBall.setQuantities(buildQuantities(0));
		calculator.calculateTotals(highBall, highBall.getQuantities());
		segments.add(highBall);
		
		OrderSegmentDto gunner = new OrderSegmentDto();
		gunner.setName("Gunner");
		gunner.setId(4);
		gunner.setQuantities(buildQuantities(0));
		calculator.calculateTotals(gunner, gunner.getQuantities());
		segments.add(gunner);
		
		OrderSegmentDto boardWalk = new OrderSegmentDto();
		boardWalk.setName("Boardwalk");
		boardWalk.setId(5);
		boardWalk.setQuantities(buildQuantities(0));
		calculator.calculateTotals(boardWalk, boardWalk.getQuantities());
		segments.add(boardWalk);
		
		OrderSegmentDto fatTire = new OrderSegmentDto();
		fatTire.setName("Fat Tire");
		fatTire.setId(6);
		fatTire.setQuantities(buildQuantities(0));
		calculator.calculateTotals(fatTire, fatTire.getQuantities());
		segments.add(fatTire);
		
		return segments;
	}
	
	private List<IBaseDto> buildBaggerOrderSegments() {
		TotalsCalculator calculator = new TotalsCalculator();
		
		List<IBaseDto> segments = new ArrayList<IBaseDto>();
		
		OrderSegmentDto xCountry = new OrderSegmentDto();
		xCountry.setName("Cross Country");
		xCountry.setId(7);
		xCountry.setQuantities(buildQuantities(0));
		calculator.calculateTotals(xCountry, xCountry.getQuantities());
		segments.add(xCountry);
		
		OrderSegmentDto customXC = new OrderSegmentDto();
		customXC.setName("Custom Cross Country");
		customXC.setId(8);
		customXC.setQuantities(buildQuantities(0));
		calculator.calculateTotals(customXC, customXC.getQuantities());
		segments.add(customXC);
		
		OrderSegmentDto value = new OrderSegmentDto();
		value.setName("Value Bagger");
		value.setId(9);
		value.setQuantities(buildQuantities(0));
		calculator.calculateTotals(value, value.getQuantities());
		segments.add(value);
		
		OrderSegmentDto xRoads = new OrderSegmentDto();
		xRoads.setName("Cross Roads");
		xRoads.setId(10);
		xRoads.setQuantities(buildQuantities(0));
		calculator.calculateTotals(xRoads, xRoads.getQuantities());
		segments.add(xRoads);
		
		return segments;
	}
	
	private List<IBaseDto> buildTouringOrderSegments() {
		TotalsCalculator calculator = new TotalsCalculator();
		
		List<IBaseDto> segments = new ArrayList<IBaseDto>();
		
		OrderSegmentDto xcTour = new OrderSegmentDto();
		xcTour.setName("Cross Country Tour");
		xcTour.setId(11);
		xcTour.setQuantities(buildQuantities(0));
		calculator.calculateTotals(xcTour, xcTour.getQuantities());
		segments.add(xcTour);
		
		OrderSegmentDto vision = new OrderSegmentDto();
		vision.setName("Vision");
		vision.setId(12);
		vision.setQuantities(buildQuantities(0));
		calculator.calculateTotals(vision, vision.getQuantities());
		segments.add(vision);
		
		return segments;
	}
	
	private List<IBaseDto> buildATVSegments() {
		List<IBaseDto> segments = new ArrayList<IBaseDto>();
		SegmentDto rangerXP = new SegmentDto();
		rangerXP.setActual(0);
		rangerXP.setName("Ranger XP");
		rangerXP.setRecommended(0);
		rangerXP.setSegmentId(1);
		rangerXP.setOrderSegments(buildRangerXPSegments());
		segments.add(rangerXP);
		
		return segments;
	}
	
	private List<IBaseDto> buildRangerXPSegments() {
		List<IBaseDto> orderSegments = new ArrayList<IBaseDto>();
		
		OrderSegmentDto base = new OrderSegmentDto();
		base.setId(1);
		base.setName("Ranger XP Base");
		base.setQuantities(buildQuantities(1));
		orderSegments.add(base);
		
		OrderSegmentDto paint = new OrderSegmentDto();
		paint.setId(2);
		paint.setName("Ranger XP Paint");
		paint.setQuantities(buildQuantities(1));
		orderSegments.add(paint);
		
		OrderSegmentDto eps = new OrderSegmentDto();
		eps.setId(3);
		eps.setName("Ranger XP EPS");
		eps.setQuantities(buildQuantities(1));
		orderSegments.add(eps);
		
		return orderSegments;
	}
	
}
