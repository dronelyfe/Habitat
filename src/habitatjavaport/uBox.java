package habitatjavaport;

import processing.core.PApplet;
import processing.core.PVector;

public class uBox extends uShape{
	
	  PApplet parent;
	  float w;
	  float h; 
	  float d;
	  float s;
	  
	  
	  uBox(float xtemp, float ytemp, float ztemp, float wtemp, float htemp, float dtemp, float stemp, PApplet applet)
	  {
		  
		parent = applet;
	    //location in 3d space
	    x = xtemp;
	    y = ytemp;
	    z = ztemp;
	    
	    //dimensions, or size
	    w = wtemp;
	    h = htemp;
	    d = dtemp;
	    s = stemp;
	    
	    this.position = new PVector(x, y, z);
	    this.direction = PVector.random3D();
	    this.direction.normalize();
	    this.speed = parent.random(0, 0.5f);
	  }
	  
	  //accessors
	  public float getWidth()
	  {
	    return w;
	  }
	  
	  public float getHeight()
	  {
	    return h;
	  }
	  
	  public float getDepth()
	  {
	    return d;
	  }
	  
	  public float getSize()
	  {
	    return s;
	  }
	  
	  //mutators
	  public void setWidth(float wtemp)
	  {
	    w = wtemp;
	  }
	  
	  public void setHeight(float htemp)
	  {
	    h = htemp;
	  }
	  
	  public void setDepth(float dtemp)
	  {
	    d = dtemp;
	  }
	  
	  public void setSize(float stemp)
	  {
	    s = stemp;
	  }
}
