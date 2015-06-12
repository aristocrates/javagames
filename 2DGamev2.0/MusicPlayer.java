import java.io.*;
import javax.sound.sampled.*;
import javax.swing.*;
import java.net.URL;
/**
 * Write a description of class MusicPlayer here.
 * 
 * @author Nick Meyer
 * @version 2.0
 */
public class MusicPlayer extends JFrame
{
    Clip clip;
    public MusicPlayer()
    {
        setVisible(false);
    }
    public void playSound(String musicFile)
    {
        if (clip!=null)
            clip.stop();
        playBriefSound(musicFile,Clip.LOOP_CONTINUOUSLY);
    }
    public void playBriefSound(String musicFile, int numTimes)
    {
        try {
            // Open an audio input stream.
            URL url = this.getClass().getClassLoader().getResource(musicFile);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            // Get a sound clip resource.
            clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioIn);
            clip.loop(numTimes);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    public void stop()
    {
        clip.stop();
    }
}