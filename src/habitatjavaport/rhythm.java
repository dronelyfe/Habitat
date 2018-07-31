package habitatjavaport;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.data.IntList;

public class rhythm {
	
	PApplet parent;
	//sequences
	  IntList kickSequence = new IntList();
	  IntList snareSequence = new IntList();
	  IntList CHSequence = new IntList();
	  IntList OHSequence = new IntList();
	  IntList voice1Sequence = new IntList();
	  IntList voice2Sequence = new IntList();
	  IntList voice3Sequence = new IntList();
	  IntList voice4Sequence = new IntList();
	  IntList voice1Notes = new IntList();
	  IntList voice2Notes = new IntList();
	  IntList voice3Notes = new IntList();
	  IntList voice4Notes = new IntList();
	  
	  //pseudo EM distribution
	  int[] EM = {0, 1, 0, 0, 1, 1, 0, 1, 
	  0, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 
	  1, 1, 1, 1, 0, 1, 1, 0, 0, 1, 0, 1, 
	  0, 0, 1, 0, 0, 1, 1, 1, 0, 1, 0, 0, 
	  0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 1, 1, 
	  0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0, 
	  1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1,
	  1, 0, 0, 0, 1, 1, 1, 0, 0, 1, 0, 0, 
	  0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1};  
	  
	  //ticks in sequences
	  int kickTicks = 0;
	  int snareTicks = 0;
	  int CHTicks = 0;
	  int OHTicks = 0;
	  int voice1Ticks = 0;
	  int voice2Ticks = 0;
	  int voice3Ticks = 0;
	  int voice4Ticks = 0;
	  int size;
	  
	  //modes
	  int[] ionian = {12, 16, 19, 23, 24, 28, 31, 35};//c major 7
	  int[] aeolian = {21, 22, 24, 26, 33, 34, 36, 38, 17, 29};// a major 9
	  int[] dorian = {12, 16, 19, 21, 24, 28, 31, 33};// c major 13
	  int[] locrian = {11, 12, 14, 16, 17, 19, 21, 23};
	  int[] lydian = {14, 17, 21, 22, 24, 26, 29, 33, 34, 36};//F6 11 D
	  int[] mixolydian = {7, 9, 11, 12, 14, 16, 17, 19};
	  int[] phrygian = {14, 18, 19, 21, 23, 26, 30, 31, 33, 35};//D6 11
	  int[] melodicMinor = {17, 21, 24, 28, 29, 33, 36, 40};//f major 7
	  int[] lydianAugmented = {12, 14, 16, 18, 20, 21, 23, 24};
	  int[] lydianDominant = {12, 14, 16, 18, 19, 21, 22, 24};
	  int[] mixolydianB6 = {12, 14, 16, 17, 19, 20, 22, 24};
	  int[] halfDiminished = {12, 14, 15, 17, 18, 20, 22, 24};
	  
	  //tonal variables
	  int rootNote = 0;
	  int[]lastMode = ionian;
	  
	  rhythm(PApplet applet)
	  {
		parent = applet;  
	   this.euclideanRhythm(16, 8, 0, kickSequence, 1);
	   this.euclideanRhythm(16, 2, 0, snareSequence, 2);
	   this.euclideanRhythm(16, 16, 0, OHSequence, 3);
	   this.euclideanRhythm(16, 16, 0, CHSequence, 4);
	   this.euclideanRhythm(16, 4, 0, voice1Sequence, 5);
	   this.euclideanRhythm(16, 4, 0, voice2Sequence, 6);
	   this.euclideanRhythm(16, 4, 0, voice3Sequence, 7);
	   this.euclideanRhythm(16, 4, 0, voice4Sequence, 8);
	  }
	  
	  public IntList getKickSequence()
	  {
	    return this.kickSequence;
	  }
	  
	  public int getKickSequenceTicks()
	  {
	    return this.kickTicks;
	  }
	  
	  public void setKickSequenceTicks(int ticks)
	  {
	    this.kickTicks = ticks;
	  }
	  
	  public IntList getSnareSequence()
	  {
	    return this.snareSequence;
	  }
	  
	  public int getSnareSequenceTicks()
	  {
	    return this.snareTicks;
	  }
	  
	  public void setSnareSequenceTicks(int ticks)
	  {
	    this.snareTicks = ticks;
	  }
	  
	  public IntList getOHSequence()
	  {
	    return this.OHSequence;
	  }

	  public int getOHSequenceTicks()
	  {
	    return this.OHTicks;
	  }
	  
	  public void setOHSequenceTicks(int ticks)
	  {
	    this.OHTicks = ticks;
	  }
	  
	  public IntList getCHSequence()
	  {
	    return this.CHSequence;
	  }

	  public int getCHSequenceTicks()
	  {
	    return this.CHTicks;
	  }
	  
	  public void setCHSequenceTicks(int ticks)
	  {
	    this.CHTicks = ticks;
	  }
	  
	  public IntList getVoice1Sequence()
	  {
	    return this.voice1Sequence;
	  }

	  public int getVoice1SequenceTicks()
	  {
	    return this.voice1Ticks;
	  }
	  
	  public void setVoice1SequenceTicks(int ticks)
	  {
	    this.voice1Ticks = ticks;
	  }
	  
	  public IntList getVoice2Sequence()
	  {
	    return this.voice2Sequence;
	  }

	  public int getVoice2SequenceTicks()
	  {
	    return this.voice2Ticks;
	  }
	  
	  public void setVoice2SequenceTicks(int ticks)
	  {
	    this.voice2Ticks = ticks;
	  }
	  
	  public IntList getVoice3Sequence()
	  {
	    return this.voice3Sequence;
	  }

	  public int getVoice3SequenceTicks()
	  {
	    return this.voice3Ticks;
	  }
	  
	  public void setVoice3SequenceTicks(int ticks)
	  {
	    this.voice3Ticks = ticks;
	  }
	  
	  public IntList getVoice4Sequence()
	  {
	    return this.voice4Sequence;
	  }

	  public int getVoice4SequenceTicks()
	  {
	    return this.voice4Ticks;
	  }
	  
	  public void setVoice4SequenceTicks(int ticks)
	  {
	    this.voice4Ticks = ticks;
	  }
	  
	  public int getVoice1Note(int index)
	  {
	    return this.voice1Notes.get(index);
	  }
	  
	  public IntList getVoice1NoteSequence()
	  {
	    return this.voice1Notes;
	  }
	  
	  public int getVoice2Note(int index)
	  {
	    return this.voice2Notes.get(index);
	  }
	  
	  public IntList getVoice2NoteSequence()
	  {
	    return this.voice2Notes;
	  }  
	  
	  public int getVoice3Note(int index)
	  {
	    return this.voice3Notes.get(index);
	  }
	  
	  public IntList getVoice3NoteSequence()
	  {
	    return this.voice3Notes;
	  }  
	  
	  public int getVoice4Note(int index)
	  {
	    return this.voice4Notes.get(index);
	  }
	  
	  public IntList getVoice4NoteSequence()
	  {
	    return this.voice4Notes;
	  }
	  
	  public int[] getLastMode()
	  {
	    return this.lastMode;
	  }
	  
	  public int getRootNote()
	  {
	    return rootNote;
	  }
	  
	  public int[] getMode(String modeName)
	  {
	    if (modeName == "a major 9")
	    {
	      return aeolian;// actually a major 9
	    }
	    else if (modeName == "c major 13")
	    {
	      return dorian;//actually c major 13
	    }
	    else if (modeName == "f 6 11 d")
	    {
	      return lydian;
	    }
	    else if (modeName == "mixolydian")
	    {
	      return mixolydian;
	    }
	    else if (modeName == "locrian")
	    {
	      return locrian;
	    }
	    else if (modeName == "d6 11")
	    {
	      return phrygian;//actually d6 11
	    }
	    else if (modeName == "f major 7")
	    {
	      return melodicMinor;//actually f major 7
	    }
	    else if (modeName == "lydian augmented c")
	    {
	      return lydianAugmented;// root c
	    }
	    else if (modeName == "lydian dominant c")
	    {
	      return lydianDominant;// root C
	    }
	    else if (modeName == "mixolydian b6 c")
	    {
	      return mixolydianB6;// root C
	    }
	    else if (modeName == "half diminished c")
	    {
	      return halfDiminished;// root C
	    }
	    else if (modeName == "c major 7")
	    {
	      return ionian;//actually c major 7
	    }
	    else
	    {
	      return ionian;
	    }
	  }
	  
	  //generative functions
	  public void euclideanRhythm(int n, int k, int changeValue, IntList tempseq, int sequencerID)
	  {

	    tempseq.clear();
	    
	    k = k + changeValue;
	    int ticks = k;
	    
	    ArrayList<Boolean> euclidSequence = new ArrayList<Boolean>();
	    
	    if (k >= n || n == 1 || k == 0)
	    {
	      
	      if (k >= n)
	      {
	        for (int i = 0; i < n; i++)
	        {
	          euclidSequence.add(true);
	        }
	      }
	      
	      else if (n == 1)
	      {
	        euclidSequence.add(k == 1);
	      }
	      
	      else 
	      {
	        for (int i = 0; i < n; i++)
	        {
	          euclidSequence.add(false);
	        }
	      }
	      
	    }
	    
	    else
	    {
	      int spaces = n - k;
	      
	      if (spaces >= k)
	      {
	        int perK = PApplet.floor(spaces/k);
	        int remainder = spaces % k;
	        
	        for (int i = 0; i < k; i++)
	        {
	          euclidSequence.add(true);
	          for (int j = 0; j < perK; j++)
	          {
	            euclidSequence.add(false);
	          }
	          if (i < remainder)
	          {
	            euclidSequence.add(false);
	          }
	        }
	      }
	      else 
	      {
	        int perK = PApplet.floor((k - spaces)/spaces);
	        int remainder = (k - spaces) % spaces;
	        
	        for (int i = 0; i < spaces; i++)
	        {
	          euclidSequence.add(true);
	          euclidSequence.add(false);
	          for (int j = 0; j < perK; j++)
	          {
	            euclidSequence.add(true);
	          }
	          if (i < remainder)
	          {
	            euclidSequence.add(true);
	          }
	        }
	      }
	    }
	    
	    //print(euclidSequence);
	    //convert to IntList
	    for (int i = 0; i < euclidSequence.size(); i++)
	    {
	      tempseq.append(PApplet.parseInt(euclidSequence.get(i)));
	    }
	    
	    size = tempseq.size();
	    
	    switch(sequencerID)
	    {
	      case 1:
	        this.setKickSequenceTicks(ticks);
	        break;
	      case 2:
	        this.setSnareSequenceTicks(ticks);
	        break;
	      case 3:
	        this.setOHSequenceTicks(ticks);
	        break;
	      case 4:
	        this.setCHSequenceTicks(ticks);
	        break;
	      case 5:
	        this.setVoice1SequenceTicks(ticks);
	        break;
	      case 6:
	        this.setVoice2SequenceTicks(ticks);
	        break;
	      case 7:
	        this.setVoice3SequenceTicks(ticks);
	        break;
	      case 8:
	        this.setVoice4SequenceTicks(ticks);
	        break;
	    }
	    
	  }
	  
	  public void setModalNotes(IntList tempsequence, IntList notesequence, int[] mode, int octave)
	  {
	    int semitones= 0;
	    int lastnote = 0;
	    
	    switch(octave)
	    {
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
	    
	    for (int i = 0; i < tempsequence.size(); i++)
	    {
	       notesequence.append(tempsequence.get(i));
	    }
	    
	    for(int i = 0; i < tempsequence.size(); i++)
	    {
	      if (i == 0 && this.lastMode != mode)
	      {
	        rootNote = mode[0];
	      }
	      if (tempsequence.get(i) == 1)
	      {
	        int newnote = mode[PApplet.parseInt(parent.random(1, mode.length))];
	        if (newnote == lastnote)
	        {
	          newnote = mode[PApplet.parseInt(parent.random(1, mode.length))];
	        }
	        if (this.rootNote > 0) 
	        {
	         //if((newnote % this.rootNote)/2 == 1)
	         //{
	         //  newnote += 1;
	         //  //if((newnote % this.rootNote)/2 == 1)
	         //  //{
	         //  //  newnote = rootNote;
	         //  //}
	         //}
	         notesequence.set(i, newnote + semitones);
	         lastnote = newnote;
	         this.lastMode = mode;
	        }
	        else if (this.rootNote == 0)
	        {
	         notesequence.set(i, mode[0] + semitones);
	         rootNote = mode[0];
	        }
	      }
	    }
	    this.lastMode = mode;
	  }

}
