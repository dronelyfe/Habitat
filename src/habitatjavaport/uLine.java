package habitatjavaport;

public class uLine extends uShape {
	
	 uShape shapeOne;
	 uShape shapeTwo;
	 float x2;
	 float y2;
	 float z2;
	  
	 uLine(uShape tshapeOne, uShape tshapeTwo)
	 {
	   shapeOne = tshapeOne;
	   shapeTwo = tshapeTwo;
	   
	   x = shapeOne.getX();
	   y = shapeOne.getY();
	   z = shapeOne.getZ();
	   
	   x2 = shapeTwo.getX();
	   y2 = shapeTwo.getY();
	   z2 = shapeTwo.getZ();
	   
	 }
	 
	 public void setX2(float newx2)
	 {
	   x2 = newx2;
	 }
	 
	 public void setY2(float newy2)
	 {
	   y2 = newy2;
	 }
	 
	 public void setZ2(float newz2)
	 {
	   z2 = newz2;
	 }
	 
	 public float getX2()
	 {
	   return x2;
	 }
	 
	 public float getY2()
	 {
	   return y2;
	 }
	 
	 public float getZ2()
	 {
	   return z2;
	 }
	 

}
