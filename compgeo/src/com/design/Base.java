package com.design;

import com.datastruct.DCHalfEdge;
import com.math.CompPoint;
import com.math.Trig;

import java.util.Vector;

public class Base extends Part {
	public String type;
	public Base(double width, double height, String type){
		super(width,height);
		this.type = type;
	}
	
	public void addNotches(double notchWidth, double notchHeight, double ribNotchOffset,int ribNum){
		
		for(int i =ribNum-1;i>=0;i--){
			//double alpha = 0-((Math.PI*2/ribNum)*180/Math.PI)+360/(ribNum*2); // determines the degree position of your current point
			
			double startTheta = Trig.cartToPolar(edges.get(i).start.getX(), edges.get(i).start.getY())[1];
			double alpha = 360/ribNum;
			double theta = startTheta+(i)*alpha; //current position on circle for your intended point
			//System.out.println("startTheta="+startTheta);
			
			//System.out.println("theta="+theta);
			Notch notch = new Notch(notchWidth,notchHeight);
			
			
				notch.rotate(startTheta+180, notch.focus);
			
			int after=0;
			if(i==0){
				after=ribNum-1;
			}
			else{
				after=i-1;
			}
			this.setNotch(notch,i,after);
		}
		
		
			
	}
	
	
	private void setNotch(Notch notch, int edgeNum, int edgeNumAfter){
		
		
		notch.translate(this.edges.get(edgeNum).start.getX(),this.edges.get(edgeNum).start.getY());
		notch.merge(this,edgeNumAfter,edgeNum);
	}
	
	
	public void generateHole(double radius){
		int res = 50; // we will define the circles by a set of evenly spaced points. This variable controls the number of points in your circles 
		Vector<CompPoint> points = new Vector<CompPoint>();
	    for(int i=0;i<res;i++){ //loop over the number of points in the circle
	   
	       double alpha = Math.PI*2/res; // determines the degree position of your current point
	  
	       double theta = i*alpha; //current position on circle for your intended point
	 
	       double xPos = Math.sin(theta)*radius+focus.getX();
	       double yPos = Math.cos(theta)*radius+focus.getY();
	       
	       points.addElement(new CompPoint(xPos,yPos));

	   }
	    
	    for(int i=0;i<res;i++){ 
	    	int after=i+1;
	    	if(i==res-1){
	    		after=0;
	    	}
	    	
	    	DCHalfEdge circleEdge = new DCHalfEdge(points.get(after),points.get(i));
	    	circleEdge.inner=true;
	    	
	    	this.addHalfEdge(circleEdge);
	    }
	}
	
	
}
