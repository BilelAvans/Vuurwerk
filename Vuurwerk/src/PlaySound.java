import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class PlaySound extends Thread
{
	
	private AudioInputStream audioInputStream;
	private Clip 			 clip;
	
	public static void main(String args[])
	{
		new PlaySound().start();
	}

	@Override
	public void run() {

	    try 
	    {
	    	audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource("firework_1.wav"));
	        clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	    } 
	    catch(Exception e) 
	    {
	        System.out.println("Geluid werkt niet ofzo");
	        e.printStackTrace();
	    }
		
	}
	
}