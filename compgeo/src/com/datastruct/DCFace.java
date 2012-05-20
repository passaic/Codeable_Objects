/*
 * This file is part of the Codeable Objects Framework.
 *
 *     Codeable Objects is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     Codeable Objects is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with Codeable Objects.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.datastruct;

import java.util.Vector;

public class DCFace implements Comparable <DCFace> {
	
	public Vector<DCHalfEdge> outerComponents;
	public Vector<DCHalfEdge> innerComponents;
	
	public DCFace(){
		outerComponents = new Vector<DCHalfEdge>(0);
		innerComponents = new Vector<DCHalfEdge>(0);
		
	}
	
	public void addOuterComponent(DCHalfEdge edge){
		outerComponents.addElement(edge);
		
	}
	
	public void addInnerComponent(DCHalfEdge edge){
		innerComponents.addElement(edge);
		
	}

	
	public int compareTo(DCFace f) {
		return this.outerComponents.get(0).compareTo(f.outerComponents.get(0));
	}

}
