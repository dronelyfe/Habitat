package habitatjavaport;

import java.io.File;
import java.util.Arrays;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Mixer;

import ddf.minim.AudioInput;
import ddf.minim.AudioOutput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.BeatDetect;
import ddf.minim.analysis.FFT;
import processing.core.PApplet;
import processing.data.StringList;

public class Audio {
	
	PApplet parent;
	
	  Minim audioEngine;
	  Mixer.Info[] mixerInfo;
	  AudioPlayer player;
	  AudioInput audioInput;
	  AudioOutput audioOutput;
	  FFT fft;
	  BeatDetect beatDetect;
	  BeatDetect beatDetect2;
	  String[] inputDevices;
	  
	  //file IO stuff
	  File dir;
	  File[] files;
	  String[] fileNames1;
	  StringList fileNames2 = new StringList();
	  String[] fileNames3;
	  String[] dummyString = {"no files found"};
	  String mp3 = "mp3";
	  boolean detectionLoading = false;
	  
	  Audio(Minim minim, PApplet applet)
	  {
	    parent = applet;
		  
	    audioEngine = minim;
	    
	    mixerInfo = AudioSystem.getMixerInfo();
	    //println(mixerInfo);
	    
	    inputDevices = new String[mixerInfo.length];
	    
	    audioInput = audioEngine.getLineIn(Minim.STEREO);
	    
	    fft = new FFT(audioInput.bufferSize(), audioInput.sampleRate());
	    
	    beatDetect = new BeatDetect();
	    
	    beatDetect.detectMode(BeatDetect.FREQ_ENERGY);
	    
	    beatDetect.setSensitivity(10);
	    
	    beatDetect2 = new BeatDetect();
	    
	    beatDetect2.detectMode(BeatDetect.SOUND_ENERGY);
	    
	    beatDetect2.setSensitivity(10);
	    
	    this.dir = new File(parent.dataPath("E:\\eclipseworkspace\\Habitat Java Port\\src\\data"));
	    
	    this.setAudioFileList();    
	    
	  }
	  
	  //accessors
	  public AudioInput getAudioInput()
	  {
	    return this.audioInput;
	  }
	  
	  public BeatDetect getBeatDetect()
	  {
	    return this.beatDetect;
	  }
	  
	  public BeatDetect getBeatDetect2()
	  {
	    return this.beatDetect2;
	  }
	  
	  public AudioPlayer getAudioPlayer()
	  {
	    return this.player;
	  }

	  public FFT getFFT()
	  {
	    return fft;
	  }   
	  
	  public String[] getInputList()
	  {
	    for (int i = 0; i < mixerInfo.length; i++ )
	    {
	      inputDevices[i] = mixerInfo[i].getName();
	    }
	    return inputDevices;
	  }
	  
	  public void setInputDevice(String device)
	  {
	    int index = Arrays.asList(inputDevices).indexOf(device);
	    Mixer newMixer = AudioSystem.getMixer(mixerInfo[index]);
	    if (newMixer != null)
	    {
	      audioEngine.setInputMixer(newMixer);
	      audioInput = audioEngine.getLineIn(Minim.STEREO);
	    }
	  }
	  
	  public void loadAudioFile(String file)
	  {
	    this.player = this.audioEngine.loadFile(file);
	    //this.beatDetect.detect(player.mix);
	    //this.fft.forward(player.mix);
	  }
	  
	  public void setAudioFileList()
	  {
	    this.files = this.dir.listFiles();
	    this.fileNames1 = this.dir.list();
	    
	    for(int i = 0; i < this.fileNames1.length; i++)
	    {      
	      String extension = "";
	      int j = this.fileNames1[i].lastIndexOf('.');
	      
	      if (j > 0) 
	      {
	         extension = this.fileNames1[i].substring(j+1);
	      }
	      
	      if (extension.equals("mp3") || extension.equals("wav"))
	      {
	         this.fileNames2.append(this.fileNames1[i]); 
	      }
	    }  
	  }
	  
	  public String[] getAudioFileList()
	  {
	    if (fileNames2 != null)
	    {
	      return this.fileNames2.array();
	    }
	    else 
	    {
	      return this.dummyString;
	    }
	  }
	  
	  public void setDetectionLoading(boolean flag)
	  {
	    this.detectionLoading = flag;
	  }
	  
	  public boolean isDetectionLoading()
	  {
	    return this.detectionLoading;
	  }

}
