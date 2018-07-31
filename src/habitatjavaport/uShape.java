package habitatjavaport;

import processing.core.PApplet;
import processing.core.PVector;

public abstract class uShape {
	  PVector position;
	  PVector direction;
	  float speed;
	  float x;
	  float y;
	  float z; 
	  Boolean isClose = false;
	  
	  //accessors
	  public float getX()
	  {
	    return x;
	  }
	  
	  public float getY()
	  {
	    return y;
	  }
	  
	  public float getZ()
	  {
	    return z;
	  }
	  
	  //mutators
	  public void move()
	  {
	    this.position.x += this.direction.x*this.speed;
	    this.position.y += this.direction.y*this.speed;
	    this.position.z += this.direction.z*this.speed;
	    x = this.position.x;
	    y = this.position.y;
	    z = this.position.z;
	  }
	  
	  public void restrain()
	  {
	    float[] tempFloat = PApplet.reverse(this.direction.array());
	    this.direction.set(tempFloat);
	    if (this.position.x >= 20 || this.position.x <= -20)
	    {
	      this.direction.x = PVector.random3D().x;
	    }
	    if (this.position.y >= 20 || this.position.y <= -20)
	    {
	      this.direction.y = PVector.random3D().y;
	    }
	    if (this.position.z >= 20 || this.position.z <= -20)
	    {
	      this.direction.z = PVector.random3D().z;
	    }    
	  }
	  
	  public void mirror(PVector mirrorPos, PVector mirrorDir, float mirrorSpeed)
	  {
	    float[] tempFloat1 = PApplet.reverse(mirrorPos.array());
	    float[] tempFloat2 = PApplet.reverse(mirrorDir.array());
	    this.position.set(tempFloat1);
	    this.direction.set(tempFloat2);
	    this.speed = mirrorSpeed;
	  }
	  
	  public void setX(float newx)
	  {
	    x = newx;
	  }
	  
	  public void setY(float newy)
	  {
	    x = newy;
	  }
	  
	  public void setZ(float newz)
	  {
	    x = newz;
	  }
	  
	  public void setXYZ(float newx, float newy, float newz)
	  {
	    x = newx;
	    y = newy;
	    z = newz;
	  }
	  
	  public Boolean proximityFlag()
	  {
	    return isClose;
	  }
	  
	  public void setProximityFlag(Boolean flag)
	  {
	    isClose = flag;
	  }
	  
	  public void setSpeed(float newSpeed)
	  {
	    this.speed = newSpeed;
	  }
}
