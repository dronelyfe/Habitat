package habitatjavaport;

import processing.core.PApplet;
import ddf.minim.*; 



public class Habitat extends PApplet {

	//class initialization
	midiSystem midiSys;
	Audio audioSys;
	Scene sc;
	UI uiWindow;
	rhythm rytm;
	Minim minim;

	public void settings()
	{
	  fullScreen(P3D);
	  smooth(12);
	}

	public void setup()
	{
	  //minim needs to be initialized in main 
	  minim = new Minim(this);
	  
	  //scene
	  sc = new Scene(this);
	  
	  //systems and utilities
	  rytm = new rhythm(this);
	  
	  midiSys = new midiSystem();
	  
	  audioSys = new Audio(minim, this);  
	  
	  uiWindow = new UI(rytm, midiSys, audioSys, sc);  

	  ////scene
	  //sc = new scene();
	  //sc.getBackground().setImage("_MG_3170.JPG");
	  
	}

	public void draw()
	{
	  if(audioSys.getAudioInput() != null)
	  {
	    if (audioSys.getAudioPlayer() == null && audioSys.getBeatDetect() == null)
	    {
	      audioSys.getFFT().forward(audioSys.getAudioInput().mix);
	      audioSys.getBeatDetect().detect(audioSys.getAudioInput().mix);
	      audioSys.getBeatDetect2().detect(audioSys.getAudioInput().mix);
	    }
	    
	    if (audioSys.getAudioPlayer() == null && audioSys.getBeatDetect() != null)
	    {
	      audioSys.getFFT().forward(audioSys.getAudioInput().mix);
	      audioSys.getBeatDetect().detect(audioSys.getAudioInput().mix);
	      audioSys.getBeatDetect2().detect(audioSys.getAudioInput().mix);
	    }
	    
	    if (audioSys.getAudioPlayer() != null && audioSys.getBeatDetect() != null)
	    {
	      if (audioSys.getAudioPlayer().isPlaying() == true)
	      {
	          audioSys.getFFT().forward(audioSys.getAudioPlayer().mix);
	          audioSys.getBeatDetect().detect(audioSys.getAudioPlayer().mix);
	          audioSys.getBeatDetect2().detect(audioSys.getAudioPlayer().mix);
	      }
	      else 
	      {
	        audioSys.getFFT().forward(audioSys.getAudioInput().mix);
	        audioSys.getBeatDetect().detect(audioSys.getAudioInput().mix);
	        audioSys.getBeatDetect2().detect(audioSys.getAudioInput().mix);
	      }
	    }
	  }
	  
	  thread("fourierTransform");
	  if(sc.getBackground().hasImage() == true)
	  {
	    if (sc.getBackground().isLoading())
	    {
	      background(0);
	    }
	    else
	    {
	      background(sc.getBackground().getImage());
	    }
	  }
	  else if (sc.getBackground().hasImage() == false)
	  {
	    background(sc.getBackground().getIntRGB());
	  }
	  //ambientLight(40, 50, 70);
	  lights();  
	  float speed = sc.getRotationSpeed() * frameCount;
	  if (sc.isCameraRotating() == false)
	  {
	    speed = 0.0f * frameCount;
	  }
	  camera(sc.getZoom() * cos(speed), sc.getZoom() * sin(speed), sc.getZoom(), 0, 0, 0, 0, 1, 0);

	 
	  if (uiWindow.boxToggle() == true && uiWindow.pointToggle() == false)
	  {
	    for(int i = 0; i < sc.getForeground().getBoxArraySize(); i++)
	    {
	      
	      int coinFlip = PApplet.parseInt(random(0, 1));
	      
	      if(coinFlip == 1)
	      {
	        sc.getForeground().getBox(i).setSpeed(sc.getForeground().getMovementSpeed());
	        sc.getForeground().getBox(i).move();
	        sc.getForeground().getBox(i).restrain();
	        sc.getForeground().getMirrorBox(i).mirror(sc.getForeground().getBox(i).position, sc.getForeground().getBox(i).direction, sc.getForeground().getBox(i).speed);
	        sc.getForeground().getMirrorBox(i).move();
	        pushMatrix();
	        translate(sc.getForeground().getMirrorBox(i).x, sc.getForeground().getMirrorBox(i).y, sc.getForeground().getMirrorBox(i).z);
	        fill(sc.getForeground().getStructureColor());
	        box(sc.getForeground().getMirrorBox(i).s+((sc.getForeground().getFFTData()*750))*sc.getForeground().getAmplitudeMultiplier());
	        popMatrix();
	        pushMatrix();
	        translate(sc.getForeground().getBoxX(i), sc.getForeground().getBoxY(i), sc.getForeground().getBoxZ(i));
	        fill(sc.getForeground().getStructureColor());
	        box(sc.getForeground().getBoxSize(i));
	        popMatrix();
	        if (uiWindow.onsetDetection.getBooleanValue() == true && audioSys.getBeatDetect2().isOnset() == true || uiWindow.kickDetection.getBooleanValue() == true && audioSys.getBeatDetect().isKick() == true
	            || uiWindow.snareDetection.getBooleanValue() == true && audioSys.getBeatDetect().isSnare() == true)
	        {
	          sc.getForeground().shuffleThisBox(PApplet.parseInt(random(200)));
	        }
	      }
	      
	      else if(coinFlip == 0)
	      {
	        sc.getForeground().getBox(i).setSpeed(sc.getForeground().getMovementSpeed());
	        sc.getForeground().getBox(i).move();
	        sc.getForeground().getBox(i).restrain();
	        sc.getForeground().getMirrorBox(i).mirror(sc.getForeground().getBox(i).position, sc.getForeground().getBox(i).direction, sc.getForeground().getBox(i).speed);
	        sc.getForeground().getMirrorBox(i).move();
	        pushMatrix();
	        translate(sc.getForeground().getMirrorBox(i).x, sc.getForeground().getMirrorBox(i).y, sc.getForeground().getMirrorBox(i).z);
	        fill(sc.getForeground().getStructureColor());
	        box(sc.getForeground().getMirrorBox(i).s+((sc.getForeground().getFFTData()*750))*sc.getForeground().getAmplitudeMultiplier());
	        popMatrix();
	        pushMatrix();
	        translate(sc.getForeground().getBoxX(i), sc.getForeground().getBoxY(i), sc.getForeground().getBoxZ(i));
	        fill(sc.getForeground().getStructureColor());
	        box(sc.getForeground().getBoxSize(i)+((sc.getForeground().getFFTData()*750))*sc.getForeground().getAmplitudeMultiplier());
	        popMatrix();
	        if (uiWindow.onsetDetection.getBooleanValue() == true && audioSys.getBeatDetect2().isOnset() == true || uiWindow.kickDetection.getBooleanValue() == true && audioSys.getBeatDetect().isKick() == true
	            || uiWindow.snareDetection.getBooleanValue() == true && audioSys.getBeatDetect().isSnare() == true)
	        {
	          sc.getForeground().shuffleThisBox(PApplet.parseInt(random(200)));
	        }
	      }
	    } 
	    
	  }
	  
	  if (uiWindow.pointToggle() == true && uiWindow.boxToggle() == false)
	  {
	    if (uiWindow.onsetDetection.getBooleanValue() == true && audioSys.getBeatDetect2().isOnset() == true || uiWindow.kickDetection.getBooleanValue() == true && audioSys.getBeatDetect().isKick() == true
	        || uiWindow.snareDetection.getBooleanValue() == true && audioSys.getBeatDetect().isSnare() == true)
	    {
	      for(int i = 0; i < sc.getForeground().getPointArraySize(); i++)
	      {
	        stroke(sc.getForeground().getStructureColor());
	        strokeWeight(4);
	        sc.getForeground().shuffleThisPoint(i);
	        sc.getForeground().getPoint(i).setSpeed(sc.getForeground().getMovementSpeed()+((sc.getForeground().getFFTData()*750)*sc.getForeground().getAmplitudeMultiplier()));
	        sc.getForeground().getPoint(i).move();
	        sc.getForeground().getPoint(i).restrain();
	        sc.getForeground().getMirrorPoint(i).mirror(sc.getForeground().getPoint(i).position, sc.getForeground().getPoint(i).direction, sc.getForeground().getPoint(i).speed);
	        sc.getForeground().getMirrorPoint(i).move();
	        point(sc.getForeground().getPoint(i).x, sc.getForeground().getPoint(i).y, sc.getForeground().getPoint(i).z);
	        sc.getForeground().connectPoint(sc.getForeground().getPoint(i));
	        point(sc.getForeground().getMirrorPoint(i).x, sc.getForeground().getMirrorPoint(i).y, sc.getForeground().getMirrorPoint(i).z);
	        
	        
	        //sc.getForeground().connectPoint(sc.getForeground().getMirrorPoint(i));
	      }
	    }
	    else
	    {
	      for(int i = 0; i < sc.getForeground().getPointArraySize(); i++)
	      {
	        stroke(sc.getForeground().getStructureColor());
	        strokeWeight(4);
	        sc.getForeground().getPoint(i).setSpeed(sc.getForeground().getMovementSpeed()+((sc.getForeground().getFFTData()*750)*sc.getForeground().getAmplitudeMultiplier()));
	        sc.getForeground().getPoint(i).move();
	        sc.getForeground().getPoint(i).restrain();
	        sc.getForeground().getMirrorPoint(i).mirror(sc.getForeground().getPoint(i).position, sc.getForeground().getPoint(i).direction, sc.getForeground().getPoint(i).speed);
	        sc.getForeground().getMirrorPoint(i).move();
	        point(sc.getForeground().getPoint(i).x, sc.getForeground().getPoint(i).y, sc.getForeground().getPoint(i).z);
	        sc.getForeground().connectPoint(sc.getForeground().getPoint(i));
	        point(sc.getForeground().getMirrorPoint(i).x, sc.getForeground().getMirrorPoint(i).y, sc.getForeground().getMirrorPoint(i).z);
	        
	        for(int j = 0; j < sc.getForeground().getPointArraySize(); j++)
	        {
	          if (sc.getForeground().getPoint(j).proximityFlag() == true)
	          {
	            stroke(sc.getForeground().getStructureColor());
	            strokeWeight(1);
	            line(sc.getForeground().getPoint(i).x, sc.getForeground().getPoint(i).y, sc.getForeground().getPoint(i).z, 
	            sc.getForeground().getPoint(j).x, sc.getForeground().getPoint(j).y, sc.getForeground().getPoint(j).z);
	            line(sc.getForeground().getMirrorPoint(i).x, sc.getForeground().getMirrorPoint(i).y, sc.getForeground().getMirrorPoint(i).z, 
	            sc.getForeground().getMirrorPoint(j).x, sc.getForeground().getMirrorPoint(j).y, sc.getForeground().getMirrorPoint(j).z);
	          }
	        }
	      }
	    }  
	  }
	}
	
	
	public void fourierTransform()
	{
	  for(int i = 0; i < audioSys.getFFT().specSize(); i++)
	  {
	    sc.getForeground().setFFTData(audioSys.getFFT().getBand(i));
	  }
	}
	      
	  static public void main(String[] passedArgs) {
	    String[] appletArgs = new String[] { "habitatjavaport.Habitat" };
	    if (passedArgs != null) {
	      PApplet.main(concat(appletArgs, passedArgs));
	    } else {
	      PApplet.main(appletArgs);
	    }
	  }
}
