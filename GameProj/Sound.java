import java.io.*;
import javax.sound.sampled.*;
import java.util.*;
/**
 * Sound player for the game
 * 
 * @author Nick Meyer
 * @version 1.0
 */
public class Sound
{
    private static Clip backg;

    /**
     * Loops the background music
     */
    public static void playMusic()
    {
        stopMusic();
        if (backg == null)
            try
            {
                backg = getClipFromFilename("background.wav");
            }
            catch (UnsupportedAudioFileException e) {}
            catch (IOException e) {}
            catch (LineUnavailableException e) {}
        if (backg != null)
            backg.loop(Clip.LOOP_CONTINUOUSLY);
    }

    /**
     * Stops the background music
     */
    public static void stopMusic()
    {
        if (backg == null)
            return;
        backg.stop();
        backg.flush();
        backg.setFramePosition(0);
    }

    /**
     * Plays a non-background music sound
     */
    public static void playSound(String filename)
    {
        Clip c = null;
        try{
            c = getClipFromFilename(filename);
        }
        catch (UnsupportedAudioFileException e) {
            return;
        }
        catch (IOException e) {
            return;
        }
        catch (LineUnavailableException e) {
            return;
        }
        final Clip fin = c;
        fin.addLineListener(new LineListener(){
                public void update(LineEvent e)
                {
                    if (e.getType().equals(LineEvent.Type.STOP))
                    {
                        fin.flush();
                    }
                }
            });
        c.start();
    }

    /**
     * Encapsulates the creation of sound clips
     */
    private static Clip getClipFromFilename(String name)
    throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        AudioInputStream sound = AudioSystem.getAudioInputStream(Sound.class.getResource(name));
        DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());
        Clip c = (Clip)AudioSystem.getLine(info);
        c.open(sound);
        return c;
    }
}