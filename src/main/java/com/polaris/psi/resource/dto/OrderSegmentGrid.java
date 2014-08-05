package com.polaris.psi.resource.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * Arrange OrderSegment data in a grid like structure used for the email templates.
 * 
 * @author pceder
 */
public class OrderSegmentGrid {

	private List<String> columns;
	private List<List<String>> rows;
	
	private OrderSegmentGrid() {
		setColumns(new ArrayList<String>());
		setRows(new ArrayList<List<String>>());
	}
	
	
	public static OrderSegmentGrid create(ProfileDto profile) {
		OrderSegmentGrid grid = new OrderSegmentGrid();
		
		boolean haveSuperSegments = profile.getSegments().size()>0;
		
		if(haveSuperSegments) {
			grid.getColumns().add("Segment");
		}
		grid.getColumns().add("Order Segment");
		
		// Iterate over the periods
		for(ProfilePeriodDto period: profile.getPeriods()) {
			grid.getColumns().add(period.getName());
		}
		
		int columnCount = grid.getColumns().size();
		String currentSegment=null;
		List<String> row=null;
		
		Collections.sort(profile.getOrderSegments(),new OrderSegmentComparator());
		
		// Build the rows
		for(OrderSegmentDto ordSeg:profile.getOrderSegments()) {
			
			if(currentSegment==null || currentSegment.equals(ordSeg.getName())==false) {
				row = new ArrayList<String>(columnCount);
				grid.getRows().add(row);
				
				// First column depends on product line.
				if(haveSuperSegments) {
					SegmentDto supSeg = getSuperSegment(profile, ordSeg);
					if(supSeg!=null) {
						row.add(getSuperSegment(profile, ordSeg).getName());
					}
				}
				
				// Segment
				row.add(ordSeg.getName());
				currentSegment = ordSeg.getName();
			}
			// Display the periods. Note that we rely on sort order here.
			row.add(String.valueOf(ordSeg.getActual()));
		}
		
		return grid;
	}
	private static SegmentDto getSuperSegment(ProfileDto profile, OrderSegmentDto ordSeg) {
		for(SegmentDto supSeg: profile.getSegments()) {
			
			for(String subSeg: supSeg.getSubSegments()) {
				if(subSeg.equals(ordSeg.getSubSegment())) {
					return supSeg;
				}
			}
		}
		return null;
	}


	/**
	 * @return the columns
	 */
	public List<String> getColumns() {
		return columns;
	}


	/**
	 * @param columns the columns to set
	 */
	public void setColumns(List<String> columns) {
		this.columns = columns;
	}


	/**
	 * @return the rows
	 */
	public List<List<String>> getRows() {
		return rows;
	}


	/**
	 * @param rows the rows to set
	 */
	public void setRows(List<List<String>> rows) {
		this.rows = rows;
	}
	
}
