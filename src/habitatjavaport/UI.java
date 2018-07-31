package habitatjavaport;

import java.util.Arrays;

import controlP5.Button;
import controlP5.ColorPicker;
import controlP5.ControlP5;
import controlP5.DropdownList;
import controlP5.Knob;
import controlP5.Matrix;
import controlP5.Numberbox;
import controlP5.Textarea;
import controlP5.Textlabel;
import controlP5.Toggle;
import processing.core.PApplet;

public class UI extends PApplet{
	
	  rhythm rtm;
	  midiSystem mdsys;
	  Audio adsys;
	  Scene scn;
	
	 //controller object
	  ControlP5 cp5;
	  
	  //instance variables
	  String midiDevice = null;
	  String inputDevice;
	  String currentMode;
	  String[] devices; 
	  String[] devices2;
	  String[] modeList = {"a major 9", "f 6 11 d", "mixolydian", "c major 13", "d6 11", "locrian", "f major 7", "lydian augmented c", 
	                      "lydian dominant c", "mixolydian b6 c", "half diminished c", "c major 7"};
	  //various counters
	  int kickTickCounter = 0;
	  int snareTickCounter = 0;
	  int CHTickCounter = 0;
	  int OHTickCounter = 0;
	  int voice1TickCounter = 0;
	  int voice2TickCounter = 0;
	  int voice3TickCounter = 0;
	  int voice4TickCounter = 0;
	  int clock1Counter = 0;
	  int clock2Counter = 0;
	  int clock3Counter = 0;
	  int clock4Counter = 0;
	  int clock5Counter = 0;
	  int clock6Counter = 0;
	  int clock7Counter = 0;
	  int clock8Counter = 0;
	  int[][] grid = new int[16][4];
	  
	  //area labels
	  Textlabel audioControls;
	  Textlabel midiGenHarmony;
	  Textlabel midiGenRhythm;
	  Textlabel structColor;
	  Textlabel backColor;
	  Textlabel cameraControls;
	  Textlabel structControls;
	  
	  //tactile controls
	  Knob threshHold;
	  Knob movementSpeed;
	  Knob rotationSpeed;
	  Numberbox tempo;
	  Toggle boxA;
	  Toggle points;
	  Toggle kickDetection;
	  Toggle snareDetection;
	  Toggle onsetDetection;
	  Toggle rotation;
	  Button rebuild;
	  Toggle reset;
	  Toggle pause;
	  Button soundPlay;
	  Toggle soundPause;
	  Button soundReset;
	  Toggle soundMute;
	  Numberbox numBox1;
	  Numberbox numBox2;
	  Numberbox numBox3;
	  Numberbox octave1;
	  Numberbox octave2;
	  Numberbox octave3;
	  Numberbox octave4;
	  
	  
	  //rhythm sequencers
	  Matrix masterClock;
	  Matrix sequencer;
	  Matrix sequencer2;
	  Matrix sequencer3;
	  Matrix sequencer4;
	  Matrix sequencer5;
	  Matrix sequencer6;
	  Matrix sequencer7;
	  Matrix sequencer8;
	  
	  //midi device list selector
	  DropdownList deviceList;
	  
	  //audio input device selector
	  DropdownList inputList;
	  
	  //background image selector
	  DropdownList imageList;
	  
	  //musical mode selector
	  DropdownList modes;
	  
	  //sound file selector
	  DropdownList soundList;
	  
	  //midi track channel selectors
	  Numberbox channelSelect1;
	  Numberbox channelSelect2;
	  Numberbox channelSelect3;
	  Numberbox channelSelect4;
	  Numberbox channelSelect5;
	  Numberbox channelSelect6;
	  Numberbox channelSelect7;
	  Numberbox channelSelect8;
	  
	  //melodic track parameters
	  Numberbox voice1Length;
	  Numberbox voice1Notes;
	  Numberbox voice1Morph;
	  Numberbox voice2Length;
	  Numberbox voice2Notes;
	  Numberbox voice2Morph;
	  Numberbox voice3Length;
	  Numberbox voice3Notes;
	  Numberbox voice3Morph;
	  Numberbox voice4Length;
	  Numberbox voice4Notes;
	  Numberbox voice4Morph;
	  
	  
	  //drum track parameters
	  Numberbox snareLength;
	  Numberbox closedHatLength;
	  Numberbox openHatLength;
	  Numberbox snareNotes;
	  Numberbox closedHatNotes;
	  Numberbox openHatNotes;
	  Numberbox snareMorph;
	  Numberbox closedHatMorph;
	  Numberbox openHatMorph;

	  //color pickers
	  ColorPicker picker1;
	  ColorPicker picker2;
	  
	  //text console
	  Textarea console;
	  
	  UI(rhythm r, midiSystem m, Audio a, Scene s) 
	  {
		  
	    super();
	    PApplet.runSketch(new String[] {this.getClass().getSimpleName()}, this);
	    rtm = r;
		mdsys = m;
		adsys = a;
		scn = s;
	  }
	    
	  public void settings() 
	  {
	    size(500, 700, P3D);  // final int UI_WINDOW_WIDTH = 500
	    smooth(8);
	  }

	  public void setup() 
	  {
	    background(0);
	    noStroke();
	    
	    cp5 = new ControlP5(this);
	    
	    pause = cp5.addToggle("pause")
	              .setSize(40, 15)
	              .setPosition(410, 365)
	              .setValue(false)
	              .setMode(ControlP5.DEFAULT)
	              ;
	    
	    midiGenHarmony = cp5.addTextlabel("midigenharmony")
	              .setPosition(5, 289)
	              .setText("MIDI_Harmony_Generator");
	              ;
	    
	    midiGenRhythm = cp5.addTextlabel("midigenrhythm")
	              .setPosition(380, 470)
	              .setText("MIDI_Rhythm_Generator");
	              ;
	    
	    structColor = cp5.addTextlabel("structure_color")
	              .setPosition(240, 184)
	              .setText("structure_color");
	              ;
	    
	    cameraControls = cp5.addTextlabel("cameraControls")
	              .setPosition(415, 109)
	              .setText("camera_controls");
	              ;
	    
	    backColor = cp5.addTextlabel("backcolor")
	              .setPosition(240, 5)
	              .setText("background_color");
	              ;
	    
	    audioControls = cp5.addTextlabel("audio_controls")
	              .setPosition(5, 5)
	              .setText("audio_controls");
	              ;
	    
	    structControls = cp5.addTextlabel("structure_controls")
	              .setPosition(5, 184)
	              .setText("structure_controls");
	              ;
	    
	    picker2 = cp5.addColorPicker("bgcolor")
	              .setPosition(240, 40)
	              .setSize(50, 50)
	              .setColorValue(0)
	              ;   
	    
	    rotationSpeed = cp5.addKnob("rot_speed")
	              .setSize(40, 15)
	              .setPosition(244, 110)
	              .setRange(0.001f, 0.1f)
	              .setValue(0)
	              .setDragDirection(ControlP5.VERTICAL)
	              ;
	    
	    movementSpeed = cp5.addKnob("mvmt_speed")
	              .setSize(40, 15)
	              .setPosition(160, 205)
	              .setRange(0, 2)
	              .setValue(0)
	              .setDragDirection(ControlP5.VERTICAL)
	              ;
	    
	    rotation = cp5.addToggle("camera_rotation")
	              .setSize(40, 15)
	              .setPosition(300, 125)
	              .setValue(false)
	              .setMode(ControlP5.DEFAULT)
	              ;
	    
	    soundMute = cp5.addToggle("mute")
	              .setSize(40, 15)
	              .setPosition(160, 40)
	              .setValue(false)
	              .setMode(ControlP5.DEFAULT)
	              ;
	    
	    threshHold = cp5.addKnob("amp_threshhold")
	              .setPosition(85, 205)
	              .setSize(40, 40)
	              .setRange(0,1)
	              .setValue(0)
	              .setDragDirection(ControlP5.VERTICAL)
	              ;
	    
	    soundPlay = cp5.addButton("play_file")
	              .setPosition(10, 40)
	              .setSize(40, 15)
	              .activateBy(Button.PRESSED)
	              ;
	              
	    soundPause = cp5.addToggle("pause_file")
	              .setPosition(60, 40)
	              .setSize(40, 15)
	              .setValue(false)
	              .setMode(ControlP5.DEFAULT)
	              ;
	              
	    soundReset = cp5.addButton("reset_file")
	              .setPosition(110, 40)
	              .setSize(40, 15)
	              .activateBy(Button.PRESSED)
	              ;          
	    
	    rebuild = cp5.addButton("rebuild")
	              .setPosition(85, 100)
	              .setSize(40,15)
	              .activateBy(Button.PRESSED)
	              .setVisible(false)
	              ;
	    
	    kickDetection = cp5.addToggle("kickdetection")
	              .setPosition(85, 100)
	              .setSize(40,15)
	              .setValue(false)
	              .setMode(ControlP5.DEFAULT)
	              ;
	    
	    snareDetection = cp5.addToggle("snaredetection")
	              .setPosition(10, 100)
	              .setSize(40,15)
	              .setValue(false)
	              .setMode(ControlP5.DEFAULT)              
	              ;
	              
	    onsetDetection = cp5.addToggle("onsetdetection")
	              .setPosition(150, 100)
	              .setSize(40,15)
	              .setValue(false)
	              .setMode(ControlP5.DEFAULT)              
	              ;
	    
	    picker1 = cp5.addColorPicker("structurecolor")
	              .setPosition(240, 200)
	              .setSize(50, 50)
	              .setColorValue(255)
	              ;    

	   masterClock = cp5.addMatrix("master")
	            .setPosition(300, 300)
	            .setHeight(10)
	            .setGrid(4,1)
	            .set(0,0, true)
	            .set(1,0, true)            
	            .set(2,0, true)
	            .set(3,0, true)
	            //.setCells(grid)
	            .setMode(ControlP5.MULTIPLES)
	            .setVisible(false)
	            .stop();
	            ;
	    
	    sequencer = cp5.addMatrix("sequencer")
	            .setPosition(300, 300)
	            .setHeight(10)
	            .setGrid(32,1)
	            //.setCells(grid)
	            .setMode(ControlP5.MULTIPLES)
	            .setVisible(false)
	            .stop();
	            ;

	    sequencer2 = cp5.addMatrix("sequencer2")
	            .setPosition(300, 350)
	            .setHeight(10)
	            .setGrid(32,1)
	            .setMode(ControlP5.MULTIPLES)
	            .setVisible(false)
	            .stop()
	            ;

	    sequencer3 = cp5.addMatrix("sequencer3")
	            .setPosition(300, 350)
	            .setHeight(10)
	            .setGrid(32,1)
	            .setMode(ControlP5.MULTIPLES)
	            .setVisible(false)
	            .stop()
	            ;     

	    sequencer4 = cp5.addMatrix("sequencer4")
	            .setPosition(300, 350)
	            .setHeight(10)
	            .setGrid(32,1)
	            .setMode(ControlP5.MULTIPLES)
	            .setVisible(false)
	            .stop()
	            ;     
	    
	    sequencer5 = cp5.addMatrix("sequencer5")
	            .setPosition(300, 350)
	            .setHeight(10)
	            .setGrid(32,1)
	            .setMode(ControlP5.MULTIPLES)
	            .setVisible(false)
	            .stop()
	            ;

	    sequencer6 = cp5.addMatrix("sequencer6")
	            .setPosition(300, 350)
	            .setHeight(10)
	            .setGrid(32,1)
	            .setMode(ControlP5.MULTIPLES)
	            .setVisible(false)
	            .stop();
	            ;
	            
	    sequencer7 = cp5.addMatrix("sequencer7")
	            .setPosition(300, 350)
	            .setHeight(10)
	            .setGrid(32,1)
	            .setMode(ControlP5.MULTIPLES)
	            .setVisible(false)
	            .stop()
	            ;

	    sequencer8 = cp5.addMatrix("sequencer8")
	            .setPosition(300, 350)
	            .setHeight(10)
	            .setGrid(32,1)
	            .setMode(ControlP5.MULTIPLES)
	            .setVisible(false)
	            .stop()
	            ;     
	    
	    tempo = cp5.addNumberbox("tempo")
	            .setRange(50, 200)
	            .setValue(120)
	            .setPosition(410, 325)
	            ;
	    
	    numBox1 = cp5.addNumberbox("kickLength")
	            .setPosition(275, 490)
	            .setDirection(Numberbox.VERTICAL)
	            .setRange(4, 32)
	            .setValue(16)
	            ;
	    
	    numBox2 = cp5.addNumberbox("kickNotes")
	            .setPosition(350, 490)
	            .setDirection(Numberbox.VERTICAL)
	            .setRange(0, 32)
	            .setValue(8)
	            ;
	            
	    numBox3 = cp5.addNumberbox("kickMorph")
	               .setPosition(425, 490)
	               .setDirection(Numberbox.VERTICAL)
	               .setRange(0, 8)
	               .setValue(0)
	               ;
	    
	    channelSelect1 = cp5.addNumberbox("KickChan")
	               .setPosition(200, 490)
	               .setDirection(Numberbox.VERTICAL)
	               .setRange(1, 16)
	               .setValue(0)
	               ;
	   
	    
	    channelSelect2 = cp5.addNumberbox("SnareChan")
	               .setPosition(200, 530)
	               .setDirection(Numberbox.VERTICAL)
	               .setRange(1, 16)
	               .setValue(1)
	               ;
	 
	    snareLength = cp5.addNumberbox("snareLength")
	               .setPosition(275, 530)
	               .setDirection(Numberbox.VERTICAL)
	               .setRange(4, 32)
	               .setValue(16)
	               ;

	    snareNotes = cp5.addNumberbox("snareNotes")
	               .setPosition(350, 530)
	               .setDirection(Numberbox.VERTICAL)
	               .setRange(0, 32)
	               .setValue(8)
	               ;
	            
	    snareMorph = cp5.addNumberbox("snareMorph")
	               .setPosition(425, 530)
	               .setDirection(Numberbox.VERTICAL)
	               .setRange(0, 32)
	               .setValue(0)
	               ;

	    channelSelect3 = cp5.addNumberbox("OpenHatChan")
	               .setPosition(200, 570)
	               .setDirection(Numberbox.VERTICAL)
	               .setRange(1, 16)
	               .setValue(2)
	               ;
	           
	    openHatLength = cp5.addNumberbox("OHLength")
	               .setPosition(275, 570)
	               .setDirection(Numberbox.VERTICAL)
	               .setRange(4, 32)
	               .setValue(16)
	               ;
	            
	    openHatNotes = cp5.addNumberbox("OHNotes")
	               .setPosition(350, 570)
	               .setDirection(Numberbox.VERTICAL)
	               .setRange(0, 32)
	               .setValue(8)
	               ;
	            
	    openHatMorph = cp5.addNumberbox("OHMorph")
	               .setPosition(425, 570)
	               .setDirection(Numberbox.VERTICAL)
	               .setRange(0, 32)
	               .setValue(0)
	               ;
	    
	    channelSelect4 = cp5.addNumberbox("ClosedHatChan")
	               .setPosition(200, 610)
	               .setDirection(Numberbox.VERTICAL)
	               .setRange(1, 16)
	               .setValue(3)
	               ; 

	    closedHatLength = cp5.addNumberbox("CHLength")
	               .setPosition(275, 610)
	               .setDirection(Numberbox.VERTICAL)
	               .setRange(4, 32)
	               .setValue(16)
	               ;
	            
	    closedHatNotes = cp5.addNumberbox("CHNotes")
	               .setPosition(350, 610)
	               .setDirection(Numberbox.VERTICAL)
	               .setRange(0, 32)
	               .setValue(8)
	               ;
	    
	    closedHatMorph = cp5.addNumberbox("CHMorph")
	               .setPosition(425, 610)
	               .setDirection(Numberbox.VERTICAL)
	               .setRange(0, 32)
	               .setValue(0)
	               ;    
	    
	    channelSelect5 = cp5.addNumberbox("voice1Chan")
	               .setPosition(10, 325)
	               .setDirection(Numberbox.VERTICAL)
	               .setRange(1, 16)
	               .setValue(0)
	               ;
	    
	    voice1Length = cp5.addNumberbox("voice1Length")
	               .setPosition(85, 325)
	               .setDirection(Numberbox.VERTICAL)
	               .setRange(4, 32)
	               .setValue(16)
	               ;              

	    voice1Notes = cp5.addNumberbox("voice1Notes")
	               .setPosition(160, 325)
	               .setDirection(Numberbox.VERTICAL)
	               .setRange(0, 32)
	               .setValue(8)
	               ;
	               
	    voice1Morph = cp5.addNumberbox("voice1Morph")
	               .setPosition(235, 325)
	               .setDirection(Numberbox.VERTICAL)
	               .setRange(0, 32)
	               .setValue(0)
	               ;
	               
	    octave1 = cp5.addNumberbox("octave1")
	               .setPosition(310, 325)
	               .setDirection(Numberbox.VERTICAL)
	               .setRange(1, 5)
	               .setValue(3)
	               ;
	               
	    channelSelect6 = cp5.addNumberbox("voice2Chan")
	               .setPosition(10, 365)
	               .setDirection(Numberbox.VERTICAL)
	               .setRange(1, 16)
	               .setValue(0)
	               ;
	    
	    voice2Length = cp5.addNumberbox("voice2Length")
	               .setPosition(85, 365)
	               .setDirection(Numberbox.VERTICAL)
	               .setRange(4, 32)
	               .setValue(16)
	               ;

	    voice2Notes = cp5.addNumberbox("voice2Notes")
	               .setPosition(160, 365)
	               .setDirection(Numberbox.VERTICAL)
	               .setRange(0, 32)
	               .setValue(8)
	               ;
	               
	    voice2Morph = cp5.addNumberbox("voice2Morph")
	               .setPosition(235, 365)
	               .setDirection(Numberbox.VERTICAL)
	               .setRange(0, 32)
	               .setValue(0)
	               ;

	    octave2 = cp5.addNumberbox("octave2")
	               .setPosition(310, 365)
	               .setDirection(Numberbox.VERTICAL)
	               .setRange(1, 5)
	               .setValue(3)
	               ;
	    
	    channelSelect7 = cp5.addNumberbox("voice3Chan")
	               .setPosition(10, 405)
	               .setDirection(Numberbox.VERTICAL)
	               .setRange(1, 16)
	               .setValue(0)
	               ;
	    
	    voice3Length = cp5.addNumberbox("voice3Length")
	               .setPosition(85, 405)
	               .setDirection(Numberbox.VERTICAL)
	               .setRange(4, 32)
	               .setValue(16)
	               ;

	    voice3Notes = cp5.addNumberbox("voice3Notes")
	               .setPosition(160, 405)
	               .setDirection(Numberbox.VERTICAL)
	               .setRange(0, 32)
	               .setValue(8)
	               ;
	               
	    voice3Morph = cp5.addNumberbox("voice3Morph")
	               .setPosition(235, 405)
	               .setDirection(Numberbox.VERTICAL)
	               .setRange(0, 32)
	               .setValue(0)
	               ;
	               
	    octave3 = cp5.addNumberbox("octave3")
	               .setPosition(310, 405)
	               .setDirection(Numberbox.VERTICAL)
	               .setRange(1, 5)
	               .setValue(3)
	               ;
	    
	    channelSelect8 = cp5.addNumberbox("voice4Chan")
	               .setPosition(10, 445)
	               .setDirection(Numberbox.VERTICAL)
	               .setRange(1, 16)
	               .setValue(0)
	               ;
	    
	    voice4Length = cp5.addNumberbox("voice4Length")
	               .setPosition(85, 445)
	               .setDirection(Numberbox.VERTICAL)
	               .setRange(4, 32)
	               .setValue(16)
	               ;

	    voice4Notes = cp5.addNumberbox("voice4Notes")
	               .setPosition(160, 445)
	               .setDirection(Numberbox.VERTICAL)
	               .setRange(0, 32)
	               .setValue(8)
	               ;
	               
	    voice4Morph = cp5.addNumberbox("voice4Morph")
	               .setPosition(235, 445)
	               .setDirection(Numberbox.VERTICAL)
	               .setRange(0, 32)
	               .setValue(0)
	               ;
	               
	    octave4 = cp5.addNumberbox("octave4")
	               .setPosition(310, 445)
	               .setDirection(Numberbox.VERTICAL)
	               .setRange(1, 5)
	               .setValue(3)
	               ;
	    
	    boxA = cp5.addToggle("Box")
	               .setPosition(10,200)
	               .setSize(40,15)
	               .setValue(true)
	               .setMode(ControlP5.DEFAULT)
	               ;
	               
	    reset = cp5.addToggle("play_reset")
	               .setPosition(410,400)
	               .setSize(40,15)
	               .setValue(false)
	               .setMode(ControlP5.DEFAULT)
	               ;
	    
	    points = cp5.addToggle("points")
	               .setPosition(10,250)
	               .setSize(40,15)
	               .setValue(true)
	               .setMode(ControlP5.DEFAULT)
	               ;    
	    
	    deviceList = cp5.addDropdownList("MidiDevice")
	               .setPosition(340, 305)
	               .setSize(150, 200)
	               .close();
	               ;
	               
	    inputList = cp5.addDropdownList("AudioInput")
	               .setPosition(21, 20)
	               .setSize(248, 200)
	               //.addItems()
	               .close()
	               .setVisible(false)
	               ;  
	               
	    imageList = cp5.addDropdownList("BackgroundImage")
	               .setPosition(240, 20)
	               .setSize(248, 200)
	               .close();
	               ;
	    
	    soundList = cp5.addDropdownList("AudioFiles")
	               .setPosition(10, 20)
	               .setSize(224, 200)
	               .close()
	               ;
	               
	    modes = cp5.addDropdownList("modes")
	               .setPosition(10, 305)
	               .setSize(248, 200)
	               .addItems(modeList)
	               .close()
	               ;
	               
	    console = cp5.addTextarea("console")
	               .setPosition(10, 490)
	               .setSize(180, 200)
	               .setText("HABITAT.INITIALIZED")
	               .setColorBackground(20)
	               .enableColorBackground()
	               .setBorderColor(255)
	               ;
	  
	    this.populateMidiDeviceList();
	    this.populateKickSequence();
	    this.populateSnareSequence();
	    this.populateOHSequence();
	    this.populateCHSequence();
	    this.populateVoice1Sequence();
	    this.populateVoice2Sequence();
	    this.populateVoice3Sequence();
	    this.populateVoice4Sequence();
	    rtm.setModalNotes(rtm.getVoice1Sequence(), rtm.getVoice1NoteSequence(), rtm.getMode("aeolian"), 3);
	    rtm.setModalNotes(rtm.getVoice2Sequence(), rtm.getVoice2NoteSequence(), rtm.getMode("aeolian"), 3);
	    rtm.setModalNotes(rtm.getVoice3Sequence(), rtm.getVoice3NoteSequence(), rtm.getMode("aeolian"), 3);
	    rtm.setModalNotes(rtm.getVoice4Sequence(), rtm.getVoice4NoteSequence(), rtm.getMode("aeolian"), 3);
	    this.populateInputDeviceList();
	    this.populateImageList();
	    this.populateAudioFileList();
	  }

	  public void draw() 
	  {
	    background(0);
	    stroke(255);
	    strokeWeight(1);
	    line(0, 485, width, 485);
	    line(0, 285, width, 285);
	    line(237, 0, 237, 285);
	    line(0, 180, 237, 180);
	    line(237, 180, width, 180);
	    line(237, 105, width, 105);
	  }
	  
	  //controller methods
	  
	  public float getThreshHoldVal()
	  {
	    if (threshHold != null)
	    {
	      return threshHold.getValue();
	    }
	    else 
	    {
	      return 0; 
	    }
	  }
	  
	  public boolean pointToggle()
	  {
	    if (points != null)
	    {
	      return points.getBooleanValue();
	    }
	    else
	    {
	      return true;
	    }
	  }
	  
	  public boolean boxToggle()
	  {
	    if (boxA != null)
	    {
	      return boxA.getBooleanValue();
	    }
	    else
	    {
	      return true;
	    }
	  }
	  
	  public int getLengthBoxValue(Numberbox temp)
	  {
	    if (temp != null)
	    {
	      return PApplet.parseInt(temp.getValue()); 
	    }
	    else
	    {
	      return 16;
	    }
	  }
	  
	  public int getNotesBoxValue(Numberbox temp)
	  {
	    if (temp != null)
	    {
	      return PApplet.parseInt(temp.getValue()); 
	    }
	    else
	    {
	      return 0;
	    }
	  }
	  
	  public int getMorphBoxValue(Numberbox temp)
	  {
	    if (temp != null)
	    {
	      return PApplet.parseInt(temp.getValue()); 
	    }
	    else
	    {
	      return 0;
	    }
	  }
	  
	  public int getOctaveBoxValue(Numberbox temp)
	  {
	    if (temp != null)
	    {
	      return PApplet.parseInt(temp.getValue()); 
	    }
	    else
	    {
	      return 0;
	    }
	  }
	  
	  public void setKickSequenceLength(int templength)
	  {
	    if (sequencer != null)
	    {
	      sequencer.setGrid(templength,1);
	    }
	  }
	  
	  public void setSnareSequenceLength(int templength)
	  {
	    if (sequencer2 != null)
	    {
	      sequencer2.setGrid(templength,1);
	    }
	  }
	  
	  public void setOHSequenceLength(int templength)
	  {
	    if (sequencer3 != null)
	    {
	      sequencer3.setGrid(templength,1);
	    }
	  }
	  
	  public void setCHSequenceLength(int templength)
	  {
	    if (sequencer4 != null)
	    {
	      sequencer4.setGrid(templength,1);
	    }
	  }
	  
	  public void setVoice1SequenceLength(int templength)
	  {
	    if (sequencer5 != null)
	    {
	      sequencer5.setGrid(templength,1);
	    }
	  }
	  
	  public void setVoice2SequenceLength(int templength)
	  {
	    if (sequencer6 != null)
	    {
	      sequencer6.setGrid(templength,1);
	    }
	  }

	  public void setVoice3SequenceLength(int templength)
	  {
	    if (sequencer7 != null)
	    {
	      sequencer7.setGrid(templength,1);
	    }
	  }
	  
	  public void setVoice4SequenceLength(int templength)
	  {
	    if (sequencer8 != null)
	    {
	      sequencer8.setGrid(templength,1);
	    }
	  }    
	  
	  public void populateKickSequence()
	  { 
	    if (sequencer != null)
	    {
	      this.setKickSequenceLength(rtm.getKickSequence().size());
	      for (int i = 0; i < rtm.getKickSequence().size(); i++)
	      {
	        sequencer.set(i, 0, PApplet.parseBoolean(rtm.getKickSequence().get(i)));
	      }
	      int[] nums = rtm.getKickSequence().array() ;
	      
	      console.append("\n" + "kick" + Arrays.toString(nums));
	    }
	  }
	  
	  public void populateSnareSequence()
	  {
	    if (sequencer2 != null)
	    {
	      this.setSnareSequenceLength(rtm.getSnareSequence().size());
	      for (int i = 0; i < rtm.getSnareSequence().size(); i++)
	      {
	        sequencer2.set(i, 0, PApplet.parseBoolean(rtm.getSnareSequence().get(i)));
	      }
	      int[] nums = rtm.getSnareSequence().array();
	      
	      console.append("\n" + "Snare: " + Arrays.toString(nums));
	    }
	  }

	  public void populateOHSequence()
	  {
	    if (sequencer3 != null)
	    {
	      this.setOHSequenceLength(rtm.getOHSequence().size());
	      for (int i = 0; i < rtm.getOHSequence().size(); i++)
	      {
	        sequencer3.set(i, 0, PApplet.parseBoolean(rtm.getOHSequence().get(i)));
	      }
	      int[] nums = rtm.getOHSequence().array() ;
	      
	      console.append("\n" + "OH: " + Arrays.toString(nums));
	    }
	  }
	  
	  public void populateCHSequence()
	  {
	    if (sequencer4 != null)
	    {
	      this.setCHSequenceLength(rtm.getCHSequence().size());
	      for (int i = 0; i < rtm.getCHSequence().size(); i++)
	      {
	        sequencer4.set(i, 0, PApplet.parseBoolean(rtm.getCHSequence().get(i)));
	      }
	      int[] nums = rtm.getCHSequence().array();
	      
	      console.append("\n" + "CH: " + Arrays.toString(nums));
	    }
	  }
	  
	  public void populateVoice1Sequence()
	  {
	    if (sequencer5 != null)
	    {
	      this.setVoice1SequenceLength(rtm.getVoice1Sequence().size());
	      for (int i = 0; i < rtm.getVoice1Sequence().size(); i++)
	      {
	        sequencer5.set(i, 0, PApplet.parseBoolean(rtm.getVoice1Sequence().get(i)));
	        //if(sequencer5.get(i, 0))
	        //{
	        //  this.correctSequenceNotes(i);
	        //}
	      }
	      int[] nums = rtm.getVoice1Sequence().array() ;
	      
	      console.append("\n" + "Voice 1: " + Arrays.toString(nums));
	    }
	  }
	  
	  public void populateVoice2Sequence()
	  {
	    if (sequencer6 != null)
	    {
	      this.setVoice2SequenceLength(rtm.getVoice2Sequence().size());
	      for (int i = 0; i < rtm.getVoice2Sequence().size(); i++)
	      {
	        sequencer6.set(i, 0, PApplet.parseBoolean(rtm.getVoice2Sequence().get(i)));
	        //if(sequencer6.get(i, 0))
	        //{
	        //  this.correctSequenceNotes(i);
	        //}
	      }
	      int[] nums = rtm.getVoice2Sequence().array() ;
	      
	      console.append("\n" + "Voice 2: " + Arrays.toString(nums));
	    }
	  }
	  
	  public void populateVoice3Sequence()
	  {
	    if (sequencer7 != null)
	    {
	      this.setVoice3SequenceLength(rtm.getVoice3Sequence().size());
	      for (int i = 0; i < rtm.getVoice3Sequence().size(); i++)
	      {
	        sequencer7.set(i, 0, PApplet.parseBoolean(rtm.getVoice3Sequence().get(i)));
	        //if(sequencer7.get(i, 0))
	        //{
	        //  this.correctSequenceNotes(i);
	        //}
	      }
	      int[] nums = rtm.getVoice3Sequence().array() ;
	      
	      console.append("\n" + "Voice 3: " + Arrays.toString(nums));
	    }
	  }
	  
	  public void populateVoice4Sequence()
	  {
	    if (sequencer8 != null)
	    {
	      this.setVoice4SequenceLength(rtm.getVoice4Sequence().size());
	      for (int i = 0; i < rtm.getVoice4Sequence().size(); i++)
	      {
	        sequencer8.set(i, 0, PApplet.parseBoolean(rtm.getVoice4Sequence().get(i)));
	        //if(sequencer8.get(i, 0))
	        //{
	        //  this.correctSequenceNotes(i);
	        //}       
	      }
	      int[] nums = rtm.getVoice4Sequence().array() ;
	      
	      console.append("\n" + "Voice 4: " + Arrays.toString(nums));
	    }
	  }
	  
	  public void correctSequenceNotes(int x)
	  {
	    
	    int semitones = 0;
	    int diceRoll = PApplet.parseInt(random(1, 5));
	    Numberbox tempBox = octave1;
	    //println(diceRoll);
	    
	    int root = rtm.getRootNote();
	    int note1 = rtm.getVoice1Note(x);
	    int note2 = rtm.getVoice2Note(x);
	    int note3 = rtm.getVoice3Note(x);
	    int note4 = rtm.getVoice3Note(x);
	    
	    switch(diceRoll)
	    {
	      case 1:
	        tempBox = octave1;
	        break;
	      case 2:
	        tempBox = octave2;
	        break;
	      case 3:
	        tempBox = octave3;
	        break;
	      case 4:
	        tempBox = octave4;
	        break;
	    }
	    
	    switch(this.getOctaveBoxValue(tempBox))
	    {
	      case 0:
	        semitones = 24;
	        break;
	      case 1:
	        semitones = 36;
	        break;
	      case 2:
	        semitones = 48;
	        break;
	      case 3:
	        semitones = 60;
	        break;
	      case 4:
	        semitones = 72;
	         break;
	      case 5:
	        semitones = 84;
	        break;
	    }
	            
	      if (note1 > 0 && note1 != root && note2 > 0 && note2 != root && note3 > 0 && note3 != root && note4 > 0 && note4 != root)
	      {
	        switch(diceRoll)
	        {
	          case 1:
	            rtm.getVoice1NoteSequence().set(x, rtm.getRootNote() + semitones);
	            break;
	          case 2:
	            rtm.getVoice2NoteSequence().set(x, rtm.getRootNote() + semitones);
	            break;
	          case 3:
	            rtm.getVoice3NoteSequence().set(x, rtm.getRootNote() + semitones);
	            break;
	          case 4:
	            rtm.getVoice4NoteSequence().set(x, rtm.getRootNote() + semitones);
	            break;
	        }
	      }
	  }
	  
	  //dropdown list utilities
	  public String getMidiDevice()
	  {
	    return midiDevice;
	  }
	  
	  public void populateMidiDeviceList()
	  {
	    devices = mdsys.getMidiDevices();
	    deviceList.addItems(devices);
	  }
	  
	  public void updateMidiDeviceList()
	  {
	    devices = mdsys.getMidiDevices();
	    deviceList.setItems(devices);
	  }
	  
	  public void populateInputDeviceList()
	  {
	    devices2 = adsys.getInputList();
	    inputList.addItems(devices2);
	  }
	  
	  public void populateImageList()
	  {
	    imageList.addItems(scn.getBackground().getImageList());
	  }
	  
	  public void populateAudioFileList()
	  {
	    //audioSys.setAudioFileList();
	    soundList.addItems(adsys.getAudioFileList()); 
	  }
	  
	  public void updateImageList()
	  {
	    scn.getBackground().setImageList();
	    imageList.setItems(scn.getBackground().getImageList());
	  }
	  
	  //callbacks
	  public void MidiDevice(int index) 
	  {
	    midiDevice = cp5.get(DropdownList.class, "MidiDevice").getItem(index).get("name").toString();
	    mdsys.setMidiDevice(this);
	  }
	  
	  public void AudioInput(int index)
	  {
	    inputDevice = cp5.get(DropdownList.class, "AudioInput").getItem(index).get("name").toString();
	    adsys.setInputDevice(inputDevice);
	  }
	  
	  public void BackgroundImage(int index)
	  {
	    String image = cp5.get(DropdownList.class, "BackgroundImage").getItem(index).get("name").toString();
	    if (image != "no background")
	    {
	      scn.getBackground().setImage(image);
	    }
	    else
	    {
	      scn.getBackground().setHasImage(false);
	    }
	  }
	  
	  public void AudioFiles(int index)
	  {
	    adsys.loadAudioFile(cp5.get(DropdownList.class, "AudioFiles").getItem(index).get("name").toString());
	    //soundPlay.setValue(false);
	    //soundPause.setValue(false);
	    //soundReset.setValue(false);
	  }
	  
	  public void tempo(float theValue)
	  {
	    if (sequencer != null)
	    {
	      int interval = PApplet.parseInt(60/(theValue*2)*1000);
	      //theValue = theValue*2;
	      //theValue = 60.0/theValue*1000;
	      //interval = int(theValue);
	      //println(interval);
	      masterClock.setInterval(interval);
	      //println(interval);
	    }
	  }
	  
	  public void master(int theX, int theY)
	  {
	    
	    int clock1Length = rtm.getKickSequence().size();
	    int clock2Length = rtm.getSnareSequence().size();
	    int clock3Length = rtm.getOHSequence().size();
	    int clock4Length = rtm.getCHSequence().size();
	    int clock5Length = rtm.getVoice1Sequence().size();
	    int clock6Length = rtm.getVoice2Sequence().size();
	    int clock7Length = rtm.getVoice3Sequence().size();
	    int clock8Length = rtm.getVoice4Sequence().size();
	    
	    if(clock1Counter >= clock1Length)
	    {
	      clock1Counter = 0;
	    }
	    if(clock2Counter >= clock2Length)
	    {
	      clock2Counter = 0;
	    }
	    if(clock3Counter >= clock3Length)
	    {
	      clock3Counter = 0;
	    }
	    if(clock4Counter >= clock4Length)
	    {
	      clock4Counter = 0;
	    }
	    if(clock5Counter >= clock5Length)
	    {
	      clock5Counter = 0;
	    }
	    if(clock6Counter >= clock6Length)
	    {
	      clock6Counter = 0;
	    }
	    if(clock7Counter >= clock7Length)
	    {
	      clock7Counter = 0;
	    }
	    if(clock8Counter >= clock8Length)
	    {
	      clock8Counter = 0;
	    }
	    
	    this.correctSequenceNotes(clock5Counter);
	    this.correctSequenceNotes(clock6Counter);
	    this.correctSequenceNotes(clock7Counter);
	    this.correctSequenceNotes(clock8Counter);

	    
	    if(PApplet.parseInt(sequencer.get(clock1Counter, 0)) > 0)
	    {
	     this.sequencerTrig(clock1Counter); 
	    }
	    if(PApplet.parseInt(sequencer2.get(clock2Counter, 0)) > 0)
	    {
	     this.sequencer2Trig(clock2Counter); 
	    }
	    if(PApplet.parseInt(sequencer3.get(clock3Counter, 0)) > 0)
	    {
	     this.sequencer3Trig(clock3Counter); 
	    }
	    if(PApplet.parseInt(sequencer4.get(clock4Counter, 0)) > 0)
	    {
	     this.sequencer4Trig(clock4Counter); 
	    }
	    if(PApplet.parseInt(sequencer5.get(clock5Counter, 0)) > 0)
	    {
	     this.sequencer5Trig(clock5Counter); 
	    }
	    if(PApplet.parseInt(sequencer6.get(clock6Counter, 0)) > 0)
	    {
	     this.sequencer6Trig(clock6Counter); 
	    }
	    if(PApplet.parseInt(sequencer7.get(clock7Counter, 0)) > 0)
	    {
	     this.sequencer7Trig(clock7Counter); 
	    }
	    if(PApplet.parseInt(sequencer8.get(clock8Counter, 0)) > 0)
	    {
	     this.sequencer8Trig(clock8Counter); 
	    }
	    
	    clock1Counter++;
	    clock2Counter++;
	    clock3Counter++;
	    clock4Counter++;
	    clock5Counter++;
	    clock6Counter++;
	    clock7Counter++;
	    clock8Counter++;
	    
	  }
	  
	  public void sequencerTrig(int theX)
	  {
	    
	    //this.tempo(tempo.getValue());
	    kickTickCounter += 1;
	    if(midiDevice != null && this.getNotesBoxValue(numBox2) > 0)
	    {
	        mdsys.getMidiBus().sendNoteOn(0, PApplet.parseInt(channelSelect1.getValue()-1), 127);
	        mdsys.getMidiBus().sendNoteOff(0, PApplet.parseInt(channelSelect1.getValue()-1), 127);
	        //println(millis() + " seq1");
	      if (kickTickCounter >= rtm.getKickSequenceTicks()) 
	      {
	        rtm.euclideanRhythm(this.getLengthBoxValue(numBox1), this.getNotesBoxValue(numBox2),PApplet.parseInt(random(this.getMorphBoxValue(numBox3))), rtm.getKickSequence(), 1);
	        this.setKickSequenceLength(rtm.getKickSequence().size());
	        this.populateKickSequence();
	        kickTickCounter = 0;
	      }
	   }    
	    if (kickTickCounter >= rtm.getKickSequenceTicks() && this.getNotesBoxValue(numBox2) == 0)
	    {
	      kickTickCounter = 0;
	    }    
	  }
	  
	  public void sequencer2Trig(int theX)
	  {
	    //this.tempo(int(tempo.getValue()));
	    snareTickCounter += 1;
	    if(midiDevice != null && this.getNotesBoxValue(snareNotes) > 0)
	    {
	        mdsys.getMidiBus().sendNoteOn(0, PApplet.parseInt(channelSelect2.getValue()-1), 127);
	        mdsys.getMidiBus().sendNoteOff(0, PApplet.parseInt(channelSelect2.getValue()-1), 127);
	        //println(millis() + " seq2");
	      if (snareTickCounter >= rtm.getSnareSequenceTicks()) 
	      {
	        rtm.euclideanRhythm(this.getLengthBoxValue(snareLength), this.getNotesBoxValue(snareNotes),PApplet.parseInt(random(this.getMorphBoxValue(snareMorph))), rtm.getSnareSequence(), 2);
	        this.setSnareSequenceLength(rtm.getSnareSequence().size());
	        this.populateSnareSequence();
	        snareTickCounter = 0;
	      }
	   }    
	    if (snareTickCounter >= rtm.getSnareSequenceTicks() && this.getNotesBoxValue(snareNotes) == 0)
	    {
	      snareTickCounter = 0;
	    }   
	  }
	  
	  public void sequencer3Trig(int theX)
	  {
	    //this.tempo(int(tempo.getValue()));
	    OHTickCounter += 1;
	    if(midiDevice != null && this.getNotesBoxValue(openHatNotes) > 0)
	    {
	        mdsys.getMidiBus().sendNoteOn(0, PApplet.parseInt(channelSelect3.getValue()-1), 127);
	        mdsys.getMidiBus().sendNoteOff(0, PApplet.parseInt(channelSelect3.getValue()-1), 127);
	        //println(millis() + " seq3");
	      if (OHTickCounter >= rtm.getOHSequenceTicks()) 
	      {
	        rtm.euclideanRhythm(this.getLengthBoxValue(openHatLength), this.getNotesBoxValue(openHatNotes),PApplet.parseInt(random(this.getMorphBoxValue(openHatMorph))), rtm.getOHSequence(), 3);
	        this.setOHSequenceLength(rtm.getOHSequence().size());
	        this.populateOHSequence();
	        OHTickCounter = 0;
	      }
	   }    
	    if (OHTickCounter >= rtm.getOHSequenceTicks() && this.getNotesBoxValue(openHatNotes) == 0)
	    {
	      OHTickCounter = 0;
	    }
	  }
	  
	  public void sequencer4Trig(int theX)
	  {
	    //this.tempo(int(tempo.getValue()));
	    CHTickCounter += 1;
	    if(midiDevice != null && this.getNotesBoxValue(closedHatNotes) > 0)
	    {
	        mdsys.getMidiBus().sendNoteOn(0, PApplet.parseInt(channelSelect4.getValue()-1), 127);
	        mdsys.getMidiBus().sendNoteOff(0, PApplet.parseInt(channelSelect4.getValue()-1), 127);
	        //println(millis() + " seq4");
	      if (CHTickCounter >= rtm.getCHSequenceTicks()) 
	      {
	        rtm.euclideanRhythm(this.getLengthBoxValue(closedHatLength), this.getNotesBoxValue(closedHatNotes),PApplet.parseInt(random(this.getMorphBoxValue(closedHatMorph))), rtm.getCHSequence(), 4);
	        this.setCHSequenceLength(rtm.getCHSequence().size());
	        this.populateCHSequence();
	        CHTickCounter = 0;
	      }
	   }    
	    if (CHTickCounter >= rtm.getCHSequenceTicks() && this.getNotesBoxValue(closedHatNotes) == 0)
	    {
	      CHTickCounter = 0;
	    }
	  }

	  public void sequencer5Trig(int theX)
	  {
	   // this.tempo(int(tempo.getValue()));
	    voice1TickCounter += 1;
	    if(midiDevice != null && this.getNotesBoxValue(voice1Notes) > 0)
	    {
	        //this.correctSequenceNotes(theX);
	        mdsys.getMidiBus().sendNoteOn(PApplet.parseInt(channelSelect5.getValue()-1), rtm.getVoice1Note(theX), 127);
	        mdsys.getMidiBus().sendNoteOff(PApplet.parseInt(channelSelect5.getValue()-1), 0, 127);
	        //println(millis() + " seq5");
	      if (voice1TickCounter >= rtm.getVoice1SequenceTicks()) 
	      {
	        rtm.euclideanRhythm(this.getLengthBoxValue(voice1Length), this.getNotesBoxValue(voice1Notes),PApplet.parseInt(random(this.getMorphBoxValue(voice1Morph))), rtm.getVoice1Sequence(), 5);
	        this.setVoice1SequenceLength(rtm.getVoice1Sequence().size());
	        this.populateVoice1Sequence();
	        rtm.setModalNotes(rtm.getVoice1Sequence(), rtm.getVoice1NoteSequence(), rtm.getMode(currentMode), this.getOctaveBoxValue(octave1));
	        voice1TickCounter = 0;
	      }
	   }    
	    if (voice1TickCounter >= rtm.getVoice1SequenceTicks() && this.getNotesBoxValue(voice1Notes) == 0)
	    {
	      mdsys.getMidiBus().sendNoteOff(PApplet.parseInt(channelSelect5.getValue()-1), rtm.getVoice1Note(theX), 127);
	      voice1TickCounter = 0;
	    }
	  }
	  
	  public void sequencer6Trig(int theX)
	  {
	    //this.tempo(int(tempo.getValue()));
	    voice2TickCounter += 1;
	    if(midiDevice != null && this.getNotesBoxValue(voice2Notes) > 0)
	    {
	        //this.correctSequenceNotes(theX);
	        mdsys.getMidiBus().sendNoteOn(PApplet.parseInt(channelSelect6.getValue()-1), rtm.getVoice2Note(theX), 127);
	        mdsys.getMidiBus().sendNoteOff(PApplet.parseInt(channelSelect6.getValue()-1), 0, 127);
	        //println(millis() + " seq6");
	      if (voice2TickCounter >= rtm.getVoice2SequenceTicks()) 
	      {
	        rtm.euclideanRhythm(this.getLengthBoxValue(voice2Length), this.getNotesBoxValue(voice2Notes),PApplet.parseInt(random(this.getMorphBoxValue(voice2Morph))), rtm.getVoice2Sequence(), 6);
	        this.setVoice2SequenceLength(rtm.getVoice2Sequence().size());
	        this.populateVoice2Sequence();
	        rtm.setModalNotes(rtm.getVoice2Sequence(), rtm.getVoice2NoteSequence(), rtm.getMode(currentMode), this.getOctaveBoxValue(octave2));
	        voice2TickCounter = 0;
	      }
	   }    
	    if (voice2TickCounter >= rtm.getVoice2SequenceTicks() && this.getNotesBoxValue(voice2Notes) == 0)
	    {
	      mdsys.getMidiBus().sendNoteOff(PApplet.parseInt(channelSelect6.getValue()-1), rtm.getVoice2Note(theX), 127);
	      voice2TickCounter = 0;
	    }
	  }

	  public void sequencer7Trig(int theX)
	  {
	    //this.tempo(int(tempo.getValue()));
	    voice3TickCounter += 1;
	    if(midiDevice != null && this.getNotesBoxValue(voice3Notes) > 0)
	    {      
	        //this.correctSequenceNotes(theX);
	        mdsys.getMidiBus().sendNoteOn(PApplet.parseInt(channelSelect7.getValue()-1), rtm.getVoice3Note(theX), 127);
	        mdsys.getMidiBus().sendNoteOff(PApplet.parseInt(channelSelect7.getValue()-1), 0, 127);
	        //println(millis() + " seq7");
	      if (voice3TickCounter >= rtm.getVoice3SequenceTicks()) 
	      {
	        rtm.euclideanRhythm(this.getLengthBoxValue(voice3Length), this.getNotesBoxValue(voice3Notes),PApplet.parseInt(random(this.getMorphBoxValue(voice3Morph))), rtm.getVoice3Sequence(), 7);
	        this.setVoice3SequenceLength(rtm.getVoice3Sequence().size());
	        this.populateVoice3Sequence();
	        rtm.setModalNotes(rtm.getVoice3Sequence(), rtm.getVoice3NoteSequence(), rtm.getMode(currentMode), this.getOctaveBoxValue(octave3));
	        voice3TickCounter = 0;
	      }
	   }    
	    if (voice3TickCounter >= rtm.getVoice3SequenceTicks() && this.getNotesBoxValue(voice3Notes) == 0)
	    {
	      mdsys.getMidiBus().sendNoteOff(PApplet.parseInt(channelSelect7.getValue()-1), rtm.getVoice3Note(theX), 127);
	      voice3TickCounter = 0;
	    }
	  }

	  public void sequencer8Trig(int theX)
	  {
	   // this.tempo(int(tempo.getValue()));
	    voice4TickCounter += 1;
	    if(midiDevice != null && this.getNotesBoxValue(voice4Notes) > 0)
	    {
	        //this.correctSequenceNotes(theX);
	        mdsys.getMidiBus().sendNoteOn(PApplet.parseInt(channelSelect8.getValue()-1), rtm.getVoice4Note(theX), 127);
	        mdsys.getMidiBus().sendNoteOff(PApplet.parseInt(channelSelect8.getValue()-1), rtm.getVoice4Note(theX), 127);
	        //println(millis() + " seq8");
	      if (voice4TickCounter >= rtm.getVoice4SequenceTicks()) 
	      {
	        rtm.euclideanRhythm(this.getLengthBoxValue(voice4Length), this.getNotesBoxValue(voice4Notes),PApplet.parseInt(random(this.getMorphBoxValue(voice4Morph))), rtm.getVoice4Sequence(), 8);
	        this.setVoice4SequenceLength(rtm.getVoice4Sequence().size());
	        this.populateVoice4Sequence();
	        rtm.setModalNotes(rtm.getVoice4Sequence(), rtm.getVoice4NoteSequence(), rtm.getMode(currentMode), this.getOctaveBoxValue(octave4));
	        voice4TickCounter = 0;
	      }
	   }    
	    if (voice4TickCounter >= rtm.getVoice4SequenceTicks() && this.getNotesBoxValue(voice4Notes) == 0)
	    {
	      mdsys.getMidiBus().sendNoteOff(PApplet.parseInt(channelSelect8.getValue()-1), rtm.getVoice4Note(theX), 127);
	      voice4TickCounter = 0;
	    }
	  }
	  
	  public void modes(int index)
	  {
	    currentMode = cp5.get(DropdownList.class, "modes").getItem(index).get("name").toString();
	  }
	  
	  public void play_reset(boolean theFlag)
	  {
	    if (theFlag == true);
	    {
	      masterClock.stop();

	      masterClock.play();
	    }
	    if(theFlag == false)
	    {
	      masterClock.stop();
	      mdsys.getMidiBus().sendNoteOff(PApplet.parseInt(channelSelect5.getValue()-1), 0, 127);
	      mdsys.getMidiBus().sendNoteOff(PApplet.parseInt(channelSelect6.getValue()-1), 0, 127);
	      mdsys.getMidiBus().sendNoteOff(PApplet.parseInt(channelSelect7.getValue()-1), 0, 127);
	      mdsys.getMidiBus().sendNoteOff(PApplet.parseInt(channelSelect8.getValue()-1), 0, 127);
	    }  
	  }
	  
	  public void pause(boolean theFlag)
	  {
	    if (pause != null && sequencer != null && reset != null)
	    {
	      if (theFlag == true && sequencer.isPlaying() == true)
	      {
	        masterClock.pause();
	      }
	      
	      if (theFlag == false && reset.getBooleanValue() == true)
	      {
	        masterClock.play();
	      }
	    }
	  }
	  
	  public void Box(boolean theFlag)
	  {
	   if(theFlag == true && points != null)
	   {
	     this.points.setValue(false);
	     scn.setZoom(250);
	   }
	  }
	  
	  public void points(boolean theFlag)
	  {
	   if(theFlag == true && boxA != null)
	   {
	     this.boxA.setValue(false);
	     scn.setZoom(200);
	   }
	  }
	  
	  public void rebuild(int theValue)
	  {
	   if(this.points.getBooleanValue() == false && this.boxA.getBooleanValue() == true)
	   {
	     for(int i = 0; i < scn.getForeground().getBoxArraySize(); i++)
	     {
	       scn.getForeground().getBox(i).move();
	       scn.getForeground().getBox(i).restrain(); 
	     }
	   }
	   else if (this.points.getBooleanValue() == true && this.boxA.getBooleanValue() == false)
	   {
	     for(int i = 0; i < scn.getForeground().getPointArraySize(); i++)
	     {
	       scn.getForeground().getPoint(i).move();
	       scn.getForeground().getPoint(i).restrain(); 
	     }
	   }
	  }
	  
	  public void play_file(int theValue)
	  {
	    if(this.soundPlay != null)
	    {
	        if (adsys.getAudioPlayer() != null)
	        {
	          if (adsys.getAudioPlayer().isPlaying() == false)
	          {
	            adsys.getAudioPlayer().play();
	          }
	        }
	     }
	  }
	  
	  public void pause_file(boolean theFlag)
	  {
	    if(this.soundPause != null)
	    {
	      if (theFlag == true) 
	      {
	        if (adsys.getAudioPlayer() != null)
	        {
	          if (adsys.getAudioPlayer().isPlaying() == true)
	          {
	            adsys.getAudioPlayer().pause();
	          }
	        }
	      }
	      else if (theFlag == false)
	      {
	        if(adsys.getAudioPlayer() != null)
	        {
	          if(adsys.getAudioPlayer().isPlaying() == false)
	          {
	           adsys.getAudioPlayer().play(); 
	          }
	        }
	      }
	    }
	  }
	  
	  public void reset_file(int theValue)
	  {
	    if (this.soundReset != null)
	    {
	        if (adsys.getAudioPlayer() != null)
	        {
	          adsys.getAudioPlayer().pause();
	          adsys.getAudioPlayer().rewind();
	        }
	    }
	  }
	  
	  public void mute (boolean theFlag)
	  {
	    if (this.soundMute != null)
	    {
	      if (adsys.getAudioPlayer() != null)
	      {
	        if (theFlag == true)
	        {
	          adsys.getAudioPlayer().mute();
	        }
	        else if (theFlag == false)
	        {
	          adsys.getAudioPlayer().unmute();
	        }     
	      }
	    }
	  }
	  
	  public void camera_rotation (boolean theFlag)
	  {
	    if (this.rotation != null)
	    {
	      scn.setCameraRotation(theFlag);     
	    }
	  }
	  
	  public void amp_threshhold(int theValue)
	  {
	    if (this.threshHold != null)
	    {
	      scn.getForeground().setAmplitudeMultiplier(this.threshHold.getValue());
	    }
	  }
	  
	  public void mvmt_speed(int theValue)
	  {
	    if (this.movementSpeed != null)
	    {
	      scn.getForeground().setMovementSpeed(this.movementSpeed.getValue());
	    }
	  }
	  
	  public void rot_speed(int theValue)
	  {
	    if (this.rotationSpeed != null)
	    {
	      scn.setRotationSpeed(this.rotationSpeed.getValue());
	    }
	  }
	  
	  public void kickdetection(boolean theFlag)
	  {
	    if (this.kickDetection != null && this.snareDetection != null && this.onsetDetection != null && theFlag == true)
	    {
	      snareDetection.setValue(false);
	      onsetDetection.setValue(false);
	    }
	  }
	  
	  public void snaredetection(boolean theFlag)
	  {
	    if (this.kickDetection != null && this.snareDetection != null && this.onsetDetection != null && theFlag == true)
	    {
	      this.kickDetection.setValue(false);
	      this.onsetDetection.setValue(false);

	    }
	  }
	  
	  public void onsetdetection(boolean theFlag)
	  {
	    if (this.kickDetection != null && this.snareDetection != null && this.onsetDetection != null && theFlag == true)
	    {
	      this.kickDetection.setValue(false);
	      this.snareDetection.setValue(false);
	    }
	  }
	  
	  public void structurecolor(int col)
	  {
	    scn.getForeground().setStructureColor(col);
	  }
	  
	  public int getStructureColorPickerValue(int index)
	  {
	    return PApplet.parseInt(this.picker1.getArrayValue(index));
	  }
	  
	  public void bgcolor (int col)
	  {
	    scn.getBackground().setIntRGB(col);
	  }

}
