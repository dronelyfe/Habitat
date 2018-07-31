package habitatjavaport;


import processing.core.PApplet;
import processing.core.PVector;

public class Layer {
	
	PApplet parent;
	float fftDataInput;
	  float amplitudeMultiplier;
	  float movementSpeed;
	  uBox[] boxes;
	  uBox[] mirrorBoxes;
	  uPoint[] points;
	  uPoint[] mirrorPoints;
	  uLine[] linesBoxes;
	  uLine[] linesPoints;
	  uLine[] mirrorLinesPoints;
	  int structureColor = 255;
	  
	  Layer()
	  {

	  }
	  
	  public void init()
	  {
		    points = new uPoint[200];
		    boxes = new uBox[400];
		    mirrorPoints = new uPoint[points.length];
		    mirrorBoxes = new uBox[boxes.length];
		    linesBoxes = new uLine[100];
		    linesPoints = new uLine[100];
		    mirrorLinesPoints = new uLine[linesPoints.length];
		    this.setPointArray();
		    this.setBoxArray();
		    //this.connectBoxes();
		    this.connectPoints();
	  }
	  
	  
	  public void setParent(PApplet applet)
	  {
		this.parent = applet;  
	  }
	  
	  public void setPointArray()
	  {
	    for (int i=0; i < points.length; i++) 
	    {
	      points[i] = new uPoint(parent.random(-100, 100), parent.random(-100, 100), parent.random(-100, 100), false, parent);
	      float[] tempArray = PApplet.reverse(points[i].position.array());
	      mirrorPoints[i] = new uPoint(tempArray[0], tempArray[1], tempArray[2], false, parent);
	    } 
	  }
	  
	  public void setBoxArray()
	  {
	    for (int i=0; i < boxes.length; i++) 
	    {
	      boxes[i] = new uBox(parent.random(-100, 100), parent.random(-100, 100), parent.random(-100, 100), parent.random(-25, 25), parent.random(-25, 25), parent.random(-25, 25), parent.random(0, 10), parent);
	      float[] tempArray = PApplet.reverse(boxes[i].position.array());
	      mirrorBoxes[i] = new uBox(tempArray[0], tempArray[1], tempArray[2], boxes[i].w, boxes[i].h, boxes[i].d, boxes[i].s, parent);
	    }    
	  }
	  
	  public void connectBoxes()
	  {
	    for (int i=0, j = 0; i < linesBoxes.length; j += 2, i++)
	    {
	      if(j <= boxes.length)
	      {
	        linesBoxes[i] = new uLine(boxes[j], boxes[j+1]);
	      }
	    }
	  }
	  
	  public void connectPoints()
	  {
	    for (int i=0, j = 0; i < linesPoints.length; j += 2, i++)
	    {
	      if(j <= points.length)
	      {
	          linesPoints[i] = new uLine(points[j], points[j+1]);
	          mirrorLinesPoints[i] = new uLine(mirrorPoints[j], mirrorPoints[j + 1]);
	      }
	    }
	  }
	  
	  public void connectPoint(uPoint point)
	  {
	    for (int i=0; i < points.length; i++)
	    {
	      if (PApplet.dist(point.x, point.y, point.z, points[i].x, points[i].y, points[i].z) < 50)
	      {
	        points[i].setProximityFlag(true);
	        mirrorPoints[i].setProximityFlag(true);
	      }
	      else
	      {
	        points[i].setProximityFlag(false);
	        mirrorPoints[i].setProximityFlag(false);
	      }
	    }
	    
	  }
	  
	  public void shuffleThisBox(int i)
	  {
	    //boxes[i].setXYZ(random(-25, 25), random(-25, 25), random(-25, 25));
	    //boxes[i].position = new PVector(x, y, z);
	    PVector newPosition = new PVector(parent.random(-25, 25), parent.random(-25, 25), parent.random(-25, 25));
	    float[] tempFloat = PApplet.reverse(newPosition.array());

	    boxes[i].direction.set(tempFloat);
	    //boxes[i].direction.normalize();
	    boxes[i].speed = parent.random(1,3);
	  }
	  
	  public void shuffleThisPoint(int i)
	  {
	    PVector newPosition = new PVector(parent.random(-100, 100), parent.random(-100, 100), parent.random(-100, 100));
	    float[] tempFloat = PApplet.reverse(newPosition.array());
	    
	    points[i].position.set(tempFloat);
	    //points[i].direction.normalize();
	    points[i].speed = parent.random(9,10);
	    //points[i].setX(random(-100, 100));
	    //points[i].setY(random(-100, 100));
	    //points[i].setZ(random(-100, 100));
	  }
	  
	  //array accessors
	  public float getBoxSize(int i)
	  {    
	    return boxes[i].getSize(); 
	  }
	  
	  public float getBoxHeight(int i)
	  {
	    return boxes[i].getHeight();
	  }
	  
	  public float getBoxWidth(int i)
	  {
	    return boxes[i].getWidth();
	  }
	  
	  public float getBoxDepth(int i)
	  {
	    return boxes[i].getDepth();
	  }
	  
	  public float getBoxX(int i)
	  {
	    return boxes[i].getX();
	  }
	  
	  public float getBoxY(int i)
	  {
	    return boxes[i].getY();
	  }
	  
	  public float getBoxZ(int i)
	  {
	    return boxes[i].getZ();
	  }
	  
	  public float getPointX(int i)
	  {
	    return points[i].getX();
	  }
	  
	  public float getPointY(int i)
	  {
	    return points[i].getY();
	  }
	  
	  public float getPointZ(int i)
	  {
	    return points[i].getZ();
	  }
	  
	  public uPoint getMirrorPoint(int i)
	  {
	    return mirrorPoints[i];
	  }
	  
	  public uBox getMirrorBox(int i)
	  {
	    return mirrorBoxes[i];
	  }
	  
	  public int getBoxArraySize()
	  {
	    return boxes.length;
	  }
	  
	  public int getPointArraySize()
	  {
	    return points.length;
	  }
	  
	  public float getFFTData()
	  {
	    return fftDataInput;
	  }
	  
	  public uBox getBox(int i)
	  {
	    return boxes[i];
	  }
	  
	  public uPoint getPoint(int i)
	  {
	    return points[i];
	  }
	  
	  public uLine getBoxLine(int i)
	  {
	    if (i < linesBoxes.length)
	    {
	      return linesBoxes[i];
	    }
	    else 
	    {
	      return linesBoxes[i - 100];
	    }
	  }
	  
	  public uLine getPointLine(int i)
	  {
	    if (i < this.linesPoints.length)
	    {
	      return this.linesPoints[i];
	    }
	    else 
	    {
	      return this.linesPoints[i - 100];
	    }
	  }
	  
	  public void setFFTData(float input)
	  {
	    this.fftDataInput = input;
	  }
	  
	  public int getStructureColor()
	  {
	    return this.structureColor;
	  }
	  
	  public void setStructureColor(int newcolor)
	  {
	    this.structureColor = newcolor;
	  }
	  
	  public void setAmplitudeMultiplier(float theValue)
	  {
	    this.amplitudeMultiplier = theValue;
	  }
	  
	  public float getAmplitudeMultiplier()
	  {
	    return this.amplitudeMultiplier;
	  }
	  
	  public float getMovementSpeed()
	  {
	    return this.movementSpeed;
	  }
	  
	  public void setMovementSpeed(float newSpeed)
	  {
	    this.movementSpeed = newSpeed;
	  }
	
	
	
	
}
