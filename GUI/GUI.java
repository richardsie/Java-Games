import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.*;
import javax.swing.Timer;
import java.util.*;
import javax.sound.sampled.*;
import javax.sound.midi.*;
import java.lang.Character;
import javax.imageio.*;
public class GUI{
    
    public static double Speed = 1 / 1;
    public static int X = 800;
    public static int Y = 600;
    public static int TankImageType = BufferedImage.TYPE_INT_RGB;
    public static int TankStartingLevel;
    public static boolean Time;
    public static boolean Border;
    
    

    public static void close(String Message, String Title){
        JOptionPane.showMessageDialog(null, Message, Title, JOptionPane.PLAIN_MESSAGE); 
        System.exit(0);
    }
    public static void playSound(boolean Play, String filename, int LoopCount, int multiplier){
        Clip Clip = null;
        for(int i = 0; i < multiplier; i++){
        
        try{
                AudioInputStream source = AudioSystem.getAudioInputStream(new File(System.getProperty("user.dir"), "Sounds\\" + filename));
                DataLine.Info clipInfo = new DataLine.Info(Clip.class, source.getFormat());
                Clip = (Clip)AudioSystem.getLine(clipInfo);
                Clip.open(source);
            }catch(Exception e){}
            if(Clip == null){
                return;
            }
            if(Play){
            
                Clip.loop(LoopCount);    
            
            }
        }
    }
    public static void playSound(String filename){
        try {
        Sequence sequence = MidiSystem.getSequence(new File(System.getProperty("user.dir"), "Sounds\\" + filename));   
        Sequencer sequencer = MidiSystem.getSequencer();
        sequencer.open();
        sequencer.setSequence(sequence);
        sequencer.start();
        }catch(Exception e){}
    }
    public static Image getImage(String filename){
        Image picture;
        try{
            picture = Toolkit.getDefaultToolkit().getImage("Pictures\\" + filename);
        }catch(Exception e){System.out.println("error"); return null;}
        return picture;
    }
    public static int randomInt(int INTA, int INTB){
        return ((int)Math.ceil(Math.random() * (Math.max(INTA, INTB) - Math.min(INTA, INTB) + 1) + Math.min(INTA, INTB) - 1));
    }   
    public static double difference(double DoubleA, double DoubleB){
        return Math.abs(Math.max(DoubleA,  DoubleB) - Math.min(DoubleA, DoubleB));
    }
    public static int numpadY(int INTY, int SWITCH){
        switch(SWITCH){
            case 1: case 2: case 3: INTY = INTY + 1; break;
            case 4: case 5: case 6: INTY = INTY;     break;
            case 7: case 8: case 9: INTY = INTY - 1; break;
        }
        return INTY;
    }
    public static int numpadX(int INTX, int SWITCH){
        switch(SWITCH){
            case 1: case 4: case 7: INTX = INTX - 1; break;
            case 2: case 5: case 8: INTX = INTX;     break;
            case 3: case 6: case 9: INTX = INTX + 1; break;
        }
        return INTX;
    }
    public static int numpadRotate(int FACING, int SWITCH){
        for(int i = 0; i < Math.abs(SWITCH); i++){
            switch(FACING){
            case 7: return (SWITCH < 0 ? 8 : 4); case 8: return (SWITCH < 0 ? 9 : 7); case 9: return (SWITCH < 0 ? 6 : 8);
            
            case 4: return (SWITCH < 0 ? 7 : 1);                                      case 6: return (SWITCH < 0 ? 3 : 9);
        
            case 1: return (SWITCH < 0 ? 4 : 2); case 2: return (SWITCH < 0 ? 1 : 3); case 3: return (SWITCH < 0 ? 2 : 6);
            default: return FACING;
        }
        }
        return FACING;
    }
    public static double square(double INPUT){
        return INPUT * INPUT;
    }
    public static double trigCap(double Current){
        if(Current < -1){
            return -1;
        }
        if(Current > 1){
            return 1;
        }
        return Current;
    }
    public static double cap(double Min, double Current, double Max){
        if(Current < Min){
            return Min;
        }
        
        if(Current > Max){
            return Max;
        }
        return Current;
    }
    public static int sign(double DoubleA){
        if(DoubleA < 0){
            return -1;
        }else if(DoubleA > 0){
            return 1;
        }else{
            return 0;
        }
    }
    public static String returnBlanks(int Count){
        String Blanks = "";
        for(int i = 0; i < Count; i++){
            Blanks += " ";
        }
        return Blanks;
    }
    public static String processNumber(int LB, double Count, int RB){
        String A = "" + Count;
        A = A.replace('.', '/');
        String[] Bits = A.split("/");
        if(Bits.length > 0){
            return returnBlanks(LB - Bits[0].length()) + Count + returnBlanks(RB - Bits[1].length());
        }else{
            return returnBlanks(LB + RB);
        }
    }
    public static String processNumber(int LB, String Count, int RB){
        String A = Count;
        A = A.replace('.', '/');
        String[] Bits = A.split("/");
        if(Bits.length > 0){
            return returnBlanks(LB - Bits[0].length()) + Count + returnBlanks(RB - Bits[1].length());
        }else{
            return returnBlanks(LB + RB);
        }
    }    
    
    public static double simplify(double Angle){
        while(Angle < 0 || Angle > 360){
            if(Angle < 0){Angle = 360 + Angle;}
            if(Angle > 360){Angle = Angle - 360;}
        }
        return Angle;
    }
    public static double moveToward(double StartValue, double Increment, double Target){
        if(StartValue == Target){
            
        }else if(StartValue > Target){
            StartValue = StartValue - Increment;
            if(StartValue < Target){
                StartValue = Target;
            }
            
        }else if(StartValue < Target){
            StartValue = StartValue + Increment;
            if(StartValue > Target){
                StartValue = Target;
            }
        }
        return StartValue;
    }
    public static double angleDiff(double AngleA, double AngleB){
        while(AngleA < 0 || AngleA > 360){
            if(AngleA < 0){AngleA = 360 + AngleA ;}
            if(AngleA > 360){AngleA = AngleA - 360;}
        }
        while(AngleB < 0 || AngleB > 360){
            if(AngleB < 0){AngleB = 360 + AngleB ;}
            if(AngleB > 360){AngleB = AngleB - 360;}
        }
            double ArcA = Math.max(AngleA, AngleB) - Math.min(AngleA, AngleB);
            double ArcB = 360 - Math.max(AngleA, AngleB) + Math.min(AngleA, AngleB);
                
                if(ArcA > ArcB){
                    if(AngleB < AngleA){
                        return ArcB;
                    }else{
                        return -ArcB;
                    }
                }
                if(ArcA < ArcB){
                    if(AngleB < AngleA){
                        return -ArcA;
                    }else{
                        return ArcA;
                    }
                }
            
            return ArcB;
    }
    
    public static Color blackenColor(Color OldColor, double PercentFading){
        PercentFading = Math.max(0, Math.min(1, PercentFading));
        return new Color((int)(OldColor.getRed() * PercentFading), (int)(OldColor.getGreen() * PercentFading), (int)(OldColor.getBlue() * PercentFading));
    }
    
    
    public static Color fadeColor(Color OldColor, double PercentFading){
        PercentFading = Math.max(0, Math.min(1, PercentFading));
        return new Color(OldColor.getRed(), OldColor.getGreen(), OldColor.getBlue(), (int)(PercentFading * 255));
    }
    
    public static Color mix(Color ColorA, Color ColorB, double PercentA){
        PercentA = Math.max(Math.min(PercentA, 1), 0);
        double Red = (ColorA.getRed() * PercentA) + ColorB.getRed() * (1 - PercentA);
        double Green = (ColorA.getGreen() * PercentA) + ColorB.getGreen() * (1 - PercentA);
        double Blue = (ColorA.getBlue() * PercentA) + ColorB.getBlue() * (1 - PercentA);
        return new Color((int)Red, (int)Green, (int)Blue);
    }
    public static double log(int Base, double Value){
        return Math.log(Value) / Math.log(Base);
        
    }
    public static AffineTransform inverse(AffineTransform Transformation){
        try{
            return Transformation.createInverse();
        }catch(NoninvertibleTransformException e){
            return Transformation;
        }
    }
    public static double round(double Value, double Divisor){
        Value = (int)(Value * Divisor) / Divisor;
        return Value;
    }
    
}

class WaveSound{
    Clip Clip;
    FloatControl VolumeControl;
    public WaveSound(String filename){
        try{
            AudioInputStream source = AudioSystem.getAudioInputStream(new File(System.getProperty("user.dir"), "Sounds\\" + filename));
            DataLine.Info clipInfo = new DataLine.Info(Clip.class, source.getFormat());
            Clip = (Clip)AudioSystem.getLine(clipInfo);
            Clip.open(source);
            VolumeControl = (FloatControl)Clip.getControl(FloatControl.Type.MASTER_GAIN);
        }catch(Exception e){
            System.out.println("Sound Initialization Error: " + e);
        }
    }
    public void play(double Volume){
        VolumeControl.setValue((float)(Volume * (VolumeControl.getMaximum() - VolumeControl.getMinimum()) + VolumeControl.getMinimum()));
        Clip.loop(0);
    }
}
class LoopingMidi implements ActionListener{
    Sequencer sequencer;
    Sequence[] sequences;
    Timer restarter = new Timer(100, this);
    boolean Repeat = true;
    int RestartDelay;
    int RestartCounter;
    char PlaySequence;
    int CurrentTrack;
    public LoopingMidi(String filename, float trestartdelay){
        RestartDelay = (int)(trestartdelay * 1000);
        try{
            sequences = new Sequence[1];
            sequences[0] = MidiSystem.getSequence(new File(System.getProperty("user.dir"), "Sounds\\" +filename));   
            sequencer = MidiSystem.getSequencer();
        }catch(Exception e){}
        play(PlaySequence);
    }
    public LoopingMidi(String[] filenames, float trestartdelay){
        RestartDelay = (int)(trestartdelay * 1000);
        sequences = new Sequence[filenames.length];
        for(int i = 0; i < sequences.length; i++){
            try{
                sequences[i] = MidiSystem.getSequence(new File(System.getProperty("user.dir"), "Sounds\\" +filenames[i]));   
                
            }catch(Exception e){}
        }
        
        try{
            sequencer = MidiSystem.getSequencer();
            play(PlaySequence);
        }catch(Exception e){}
    }
    public void actionPerformed(ActionEvent e){
        if(sequencer.isRunning() == false && Repeat == true){
            RestartCounter = RestartCounter + 100;
            if(RestartCounter > RestartDelay){
                play(PlaySequence);
                RestartCounter = 0;
            }
        }
    }
    public void stop(){
        sequencer.stop();
        restarter.stop();
        Repeat = false;
    }
    public void lastTrack(){
        Repeat = false;
        restarter.stop();
    }
    
    public void play(char tPlaySequence){
        PlaySequence = tPlaySequence;
        try{
            int INTA = 0;
            if(PlaySequence == 'r'){
                INTA = 0;
                do{
                    INTA = GUI.randomInt(0, sequences.length);
                }while(INTA == CurrentTrack && sequences.length > 1);
                
            }else if(PlaySequence == 's'){
                INTA = CurrentTrack + 1;
                if(INTA == sequences.length){
                    INTA = 0;
                }
            }
            sequencer.open();
            restarter.start();
            sequencer.setSequence(sequences[INTA]);
            CurrentTrack = INTA;
            sequencer.start();
            Repeat = true;
        }catch(Exception e){}
    }
    
}