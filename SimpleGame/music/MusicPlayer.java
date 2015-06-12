package music;
import java.io.*;
import javax.sound.sampled.*;
import java.util.*;
/**
 * <p>Lets the other classes play music and stuff without dealing with the horrifically
 * irritating details of javax.sound.sampled</p>
 * 
 * <p>Credit to <a href = "http://stackoverflow.com/">stackOverflow</a>
 * for help with the sound clip information</p>
 * 
 * @author Aristocrates, barbecue chef / j̶a̶r̶g̶o̶n̶ ̶s̶p̶o̶u̶t̶i̶n̶g̶ ̶m̶a̶n̶i̶a̶c̶ 
 * part time philosopher
 * @version sin(π/2)
 */
public class MusicPlayer
{
    private static SmartClip main;
    private static final String filePathPrefix = "music/";

    public static void playAudioOneThenLoopAudioTwo(String file1, String file2)
    {
        ArrayList<String> s = new ArrayList<String>();
        s.add(file1);
        s.add(file2);
        main = consecutiveClipStream(s);
        main.setLoopEnd(true);
        main.start();
    }
    
    public static void stopMain()
    {
        main.stop();
    }

    public static SmartClip consecutiveClipStream(ArrayList<String> names)
    {
        SmartClip first = null, current = null;
        try {
            for (String name: names)
            {
                SmartClip c = new SmartClip(getClipFromFilename(name));
                if (current!=null)
                    current.setLink(c);
                current = c;
                if (first == null)
                {
                    first = c;
                    current = first;
                }
            }
            first.addLineListener(new AudioSwitcher(first.nextLink()));
        }
        catch (UnsupportedAudioFileException e){}
        catch (IOException e){}
        catch (LineUnavailableException e){}
        return first;
    }

    /**
     * Automatically opens the sound AudioInputStream for the Clip
     */
    private static Clip getClipFromFilename(String name)
    throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        File soundFile = new File(filePathPrefix+name);
        AudioInputStream sound = AudioSystem.getAudioInputStream(soundFile);
        DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());
        Clip c = (Clip)AudioSystem.getLine(info);
        c.open(sound);
        return c;
    }

    public static void playAsynchronousSound(String filename)
    {
    }

    private static class SmartClip implements Clip
    {
        //I ripped off of that one stupid gridworld file for this
        //you know, the NumberRockDisplay in the Fibonacci Bug project
        private Clip clip;
        private SmartClip nextLink;
        private FloatControl volume;
        private boolean loop, alreadyListened;
        /**
         * Apparently it isn't too smart considering it has another Clip
         * object do everything for it
         */
        public SmartClip(Clip c)
        {
            clip = c;
            alreadyListened = false;
            loop = false;
        }
        //the relatively little I added
        /**
         * For volume control
         */
        public FloatControl getMasterGain()
        {
            if (volume!=null) return volume;
            return volume = (FloatControl)getControl(FloatControl.Type.MASTER_GAIN);
        }

        public void changeClip(Clip c)
        {
            clip = c;
        }

        public SmartClip nextLink()
        {
            return nextLink;
        }

        public void setLink(SmartClip c)
        {
            nextLink = c;
        }

        /**
         * WARNING: DO NOT USE THIS METHOD IF YOU HAVE A LOOP CONFIGURED WITH THE LAST
         * SmartClip LINKED TO A SmartClip EARLIER IN THE CHAIN!!!!!!!!!!!!
         */
        public void setLoopEnd(boolean b)
        {
            if (nextLink() == null)
                loop = b;
            else
                nextLink().setLoopEnd(b);
        }
        
        public void setLoop(boolean b)
        {
            loop = b;
        }

        public boolean loop()
        {
            return loop;
        }
        
        public boolean hasLineListener()
        {
            return alreadyListened;
        }
        
        public void stop(){
            clip.stop();
            if (nextLink() != null)
                nextLink().stop();
        }
        
        /**
         * Makes the SmartClip store if a LineListener has been added
         */
        @Override
        public void addLineListener(LineListener l){
            alreadyListened = true;
            clip.addLineListener(l);
        }
        //now the majority of the class that justtells clip
        //to do everything for it
        public void loop(int c){clip.loop(c);}
        public void setLoopPoints(int start,int finish){clip.setLoopPoints(start,finish);}
        public void setMicrosecondPosition(long l){clip.setMicrosecondPosition(l);}
        public void setFramePosition(int i){clip.setFramePosition(i);}
        public long getMicrosecondLength(){return clip.getMicrosecondLength();}
        public int getFrameLength(){return clip.getFrameLength();}
        public void open(AudioInputStream au)throws LineUnavailableException,IOException{clip.open(au);}
        public void open(AudioFormat f,byte[] b,int i,int i2)throws LineUnavailableException{clip.open(f,b,i,i2);}
        public float getLevel(){return clip.getLevel();}
        public long getMicrosecondPosition(){return clip.getMicrosecondPosition();}
        public long getLongFramePosition(){return clip.getLongFramePosition();}
        public int getFramePosition(){return clip.getFramePosition();}
        public int available(){return clip.available();}
        public int getBufferSize(){return clip.getBufferSize();}
        public AudioFormat getFormat(){return clip.getFormat();}
        public boolean isActive(){return clip.isActive();}
        public boolean isRunning(){return clip.isRunning();}
        public void start(){clip.start();}
        public void flush(){clip.flush();}
        public void drain(){clip.drain();}
        public void removeLineListener(LineListener l){clip.removeLineListener(l);}
        public Control getControl(Control.Type c){return clip.getControl(c);}
        public boolean isControlSupported(Control.Type c){return clip.isControlSupported(c);}
        public Control[] getControls(){return clip.getControls();}
        public boolean isOpen(){return clip.isOpen();}
        public void close(){clip.close();}
        public void open()throws LineUnavailableException{clip.open();}
        public Line.Info getLineInfo(){return clip.getLineInfo();}
    }

    private static class AudioSwitcher implements LineListener
    {
        private SmartClip linkTwo;
        private int index;

        public AudioSwitcher(SmartClip c)
        {
            linkTwo = c;
        }

        public void update(LineEvent event) {
            if (event.getType().equals(LineEvent.Type.STOP)) {
                if (linkTwo.nextLink() == null)
                {
                    if (linkTwo.loop())
                        linkTwo.loop(Clip.LOOP_CONTINUOUSLY);
                }
                else
                {
                    if (!linkTwo.hasLineListener())
                        linkTwo.addLineListener(new AudioSwitcher(linkTwo.nextLink()));
                    linkTwo.start();
                }
            }
        }
    }
}