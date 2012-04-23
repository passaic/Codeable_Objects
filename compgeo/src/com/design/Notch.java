package com.design;

import com.datastruct.DCHalfEdge;
import com.math.CompPoint;
import com.math.Trig;

import java.util.Vector;




public class Notch extends Part{
	
	public DCHalfEdge top;
	public DCHalfEdge bottom;
	public DCHalfEdge left;
	public DCHalfEdge right;
	
	
	public Notch(double width, double height){
		super(width,height);
		
		//edges and points move in a counter clockwise direction
		this.top = new DCHalfEdge(new CompPoint(width,0),new CompPoint(0,0));
		
		this.left = new DCHalfEdge(new CompPoint(0,0),new CompPoint(0,height));
		this.bottom = new DCHalfEdge(new CompPoint(0,height),new CompPoint(width,height));
		this.right = new DCHalfEdge(new CompPoint(width,height),new CompPoint(width,0));
		top.inner=true;
		bottom.inner=true;
		right.inner=true;
		left.inner=true;
		this.addHalfEdge(top);
		this.addHalfEdge(left);
		this.addHalfEdge(bottom);
		this.addHalfEdge(right);
	}
	
	
	public void merge(Part border,int topEdgeNum,int bottomEdgeNum){
		this.top.end = Trig.findIntersectionPoint(this.top, border.edges.get(topEdgeNum));
		this.bottom.start = Trig.findIntersectionPoint(this.bottom, border.edges.get(bottomEdgeNum));
		border.addHalfEdge(top);
		border.addHalfEdge(bottom);
		border.addHalfEdge(right);
		
	}
	
	//clips the part and copies its edges into the new part
			public void merge(Part border){
				Vector <DCHalfEdge> modEdges = new Vector<DCHalfEdge>();
				Vector <DCHalfEdge> borderIntersects = new Vector<DCHalfEdge>();
				for(int i = 0; i<edges.size();i++){
					DCHalfEdge borderEdge = Trig.getIntersectedEdge(edges.get(i).start, edges.get(i).end, edges.get(i), border);
					DCHalfEdge newEdge = findMerge(edges.get(i),border);
					
					if(newEdge!=null){
						modEdges.addElement(newEdge);
						borderIntersects.addElement(borderEdge);
						newEdge.inner=true;
					}
				}
				for(int i = 0; i<modEdges.size();i++){
					
					border.addHalfEdge(modEdges.get(i));
					
				}
				//need to remove null elements
			}
	
}
