package habitatjavaport;

import java.io.File;

import processing.core.PImage;
import processing.data.StringList;

public class backGround extends Layer {
	
	
	//background properties
	  PImage img;
	  float r = 0;
	  float g = 0;
	  float b = 0;
	  int rgb = 0;
	  boolean hasImage = false;
	  boolean isLoading = false;
	  File dir;
	  File[] files;
	  String[] fileNames;
	  StringList fileNames2;
	  
	  backGround()
	  {
		  super();
	      //this.dir = new File(sketchPath("/data"));
	  }
	  
	  //accessors
	 
	  @Override public void init()
	  {
		  super.init();
		  dir = new File(parent.dataPath("E:\\eclipseworkspace\\Habitat Java Port\\src\\data"));
		  files = dir.listFiles();
		  fileNames = dir.list();
		  fileNames2 = new StringList();
		  this.setImageList();
	  }
	 
	  public int getIntRGB()
	  {
	    return this.rgb;
	  }
	  
	  public float getR()
	  {
	    return this.r;
	  }
	  
	  public float getG()
	  {
	    return this.g;
	  }
	  
	  public float getB()
	  {
	    return this.b;
	  }
	  
	  public PImage getImage()
	  {
	    return this.img;
	  }
	  
	  public String[] getImageList()
	  {
	    return this.fileNames2.array();
	  }
	  
	  public boolean hasImage()
	  {
	    return hasImage;
	  }
	  
	  public boolean isLoading()
	  {
	    return isLoading;
	  }
	  
	  public void setHasImage(boolean flag)
	  {
	    hasImage = flag;
	  }
	  
	  //mutators
	  public void setImage(String imgPath)
	  {
	    isLoading = true;
	    img = parent.loadImage(imgPath);
	    img.resize(parent.width, parent.height);
	    isLoading = false;
	    hasImage = true;
	  }
	  
	  public void setIntRGB(int newrgb)
	  {
	    rgb = newrgb;
	  }
	  
	  public void setR(float newr)
	  {
	    r = newr;
	  }
	  
	  public void setG(float newg)
	  {
	    g = newg;
	  }
	  
	  public void setB(float newb)
	  {
	    b = newb;
	  }
	  
	  public void setImageList()
	  {
	    //files = dir.listFiles();
	    //fileNames = dir.list();
	    
	    for(int i = 0; i < fileNames.length; i++)
	    {      
	      String extension = "";
	      int j = fileNames[i].lastIndexOf('.');
	      
	      if (j > 0) 
	      {
	         extension = this.fileNames[i].substring(j+1);
	      }
	      
	      if (extension.equals("png") || extension.equals("jpg") || extension.equals("gif") || extension.equals("JPG") || extension.equals("PNG") || extension.equals("GIF") )
	      {
	         this.fileNames2.append(fileNames[i]); 
	      }
	    }
	    
	    this.fileNames2.append("no background");
	    
	  }

}
