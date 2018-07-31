package habitatjavaport;

import processing.core.PApplet;
import themidibus.MidiBus;

public class midiSystem {
	
	//list of midi outputs
	  String[] outDevices;
	  MidiBus bus;
	  
	  midiSystem()
	  {
	    bus =  new MidiBus(this);
	    outDevices = MidiBus.availableOutputs();
	  }
	  
	  public MidiBus getMidiBus()
	  {
	    return bus;
	  }
	  
	  //Midi device methods
	  public String getMidiDevice(int i)
	  {
	    return outDevices[i]; 
	  }
	  
	  public String[] getMidiDevices()
	  {
	    return outDevices;
	  }
	  
	  public int deviceListLength()
	  {
	    return outDevices.length;
	  }
	  
	  public void updateAvailableDevices()
	  {
	    outDevices = MidiBus.availableOutputs();
	  }
	  
	  public void setMidiDevice(UI uiwindow)
	  {
	    this.updateAvailableDevices();
	    
	    uiwindow.updateMidiDeviceList();
	    
	    String[] attachedOuts = bus.attachedOutputs();
	    
	    if (uiwindow.getMidiDevice() != null)
	    {
	      if (attachedOuts.length == 0)
	      {
	        bus.clearOutputs();
	        bus.addOutput(uiwindow.getMidiDevice());
	        PApplet.println("Device Added " + uiwindow.getMidiDevice());
	      }
	      else if (attachedOuts[0] != uiwindow.getMidiDevice()) 
	      {
	        bus.clearOutputs();
	        bus.addOutput(uiwindow.getMidiDevice());
	        PApplet.println("Device Added " + uiwindow.getMidiDevice());
	      }
	    }
	  }

}
