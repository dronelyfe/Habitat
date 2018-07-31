package habitatjavaport;

import processing.core.PApplet;
import processing.core.PVector;

public class uPoint extends uShape {
	  

	boolean isConnected;
	PApplet parent;
	
	  uPoint(float xtemp, float ytemp, float ztemp, boolean tempConnect, PApplet applet)
	  {
		parent = applet;
	    this.x = xtemp;
	    this.y = ytemp;
	    this.z = ztemp;
	    this.isConnected = tempConnect;
	    
	    this.position = new PVector(x, y, z);
	    this.direction = PVector.random3D();
	    this.direction.normalize();
	    this.speed = parent.random(0, 0.5f);
	  }

}
