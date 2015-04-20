import java.awt.Color;
import java.awt.Graphics2D;


public abstract class Explosie 
{

	Explosie()
	{
		
	}
	
	//* Update methode roept paintComponent aan om te tekenen
	//* In update worden de nieuwe punten berekent, 
	//* waarna ze getekent worden met paintComponent.
	
	//* areWeDoneYet kijkt of we klaar zijn met de hele animatie
	public abstract void 	paintComponent(Graphics2D g);
	public abstract void 	update(Graphics2D g);
	public abstract boolean areWeDoneYet();
	
	//* Alpha toevoegen aan een kleur, 0 als hoeveelheid = alpha verwijderen
	protected static Color addAlpha(Color c, int hoeveelheid)
	{
		int alpha = c.getAlpha();
		
		if (alpha < hoeveelheid)
			alpha = 0;
		else if (hoeveelheid == 0)
			alpha = 255;
		else
			alpha -= hoeveelheid;
			
		return new Color(c.getRed(), c.getGreen(), c.getBlue(), alpha);
	}
	
}
