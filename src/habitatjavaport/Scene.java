package habitatjavaport;

import java.util.ArrayList;

import processing.core.PApplet;

public class Scene {
	
	PApplet parent;
	Layer fore;
	  backGround back; 
	  ArrayList<Layer> layers = new ArrayList<Layer>();
	  boolean cameraRotation = false;
	  float zoom = 500;
	  float rotationSpeed = 0.001f;
	  
	  Scene(PApplet applet)
	  {
		this.parent = applet;
		fore = new Layer();  
		fore.setParent(parent);
		fore.init();
		back = new backGround();
		back.setParent(parent);
		back.init();
	    layers.add(fore);
	    layers.add(back);
	  }
	  
	  public backGround getBackground()
	  {
	    return back;
	  }
	  
	  public Layer getForeground()
	  {
	    return fore;
	  }
	  
	  public boolean isCameraRotating()
	  {
	    return this.cameraRotation;
	  }
	  
	  public void setCameraRotation(boolean theFlag)
	  {
	    cameraRotation = theFlag;
	  }
	  
	  public void setZoom(float newZoom)
	  {
	    zoom = newZoom; 
	  }
	  
	  public float getZoom()
	  {
	    return this.zoom;
	  }
	  
	  public void setRotationSpeed(float newSpeed)
	  {
	    rotationSpeed = newSpeed;
	  }
	  
	  public float getRotationSpeed()
	  {
	    return this.rotationSpeed;
	  }
	}
	


