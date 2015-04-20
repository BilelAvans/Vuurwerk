import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.Random;


public class BolletjesExplosie extends Explosie
{

	
	//* Origin van vuurwerk
	private Point2D.Double startPoint;
	
	//* Vuurwerk bestaat uit 20 bolletjes
	private Ellipse2D.Double[] vuurwerkBol =
			new Ellipse2D.Double[20];
	
	//* En die bolletjes exploderen dan ook weer in 20 bolletjes
	private Ellipse2D.Double[][] vuurwerkBollen = 
			new Ellipse2D.Double[20][20];
	
	//* Variablen voor het maken van een kleur
	private Random random = new Random();
	
	//* Loop waarin de bolletjes steeds 'x' aantal punten bewegen
	private int loops = 0;
	

	
	//* Nodig voor translatie van bolletjes
	private int newX = 0, 
				newY = 0;

	//* Tijdelijke opslag voor gebruikte kleur
	private Color kleur;	
	
	//* Schaling van de verschillende fasen
	private int
		startFirstExplode = 10,
		startSecondExplode = 25,
		stopExplosions = 55;
	
	private boolean rainbow;
	
	BolletjesExplosie(int x, int y, boolean rainbow)
	{
		super();
		new PlaySound().start();
		startPoint = new Point2D.Double(x, y);
		
		kleur = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
		this.rainbow = rainbow;
		
		fillEllipse();
		
	}

	@Override
	public void paintComponent(Graphics2D g)
	{

			g.setColor(kleur);
			
			if (rainbow == true)
			{
				g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
			}
			//* Lijn tekenen
			if (loops < startFirstExplode)
				g.drawLine((int)startPoint.getX(), (int)startPoint.getY() + 100, (int)startPoint.getX(), (int)startPoint.getY() + 100 - 10 * loops);
			else if (loops < startSecondExplode)		
			{
				for (int x = 0; x <= vuurwerkBol.length - 1; x++)
				{
						g.draw(vuurwerkBol[x]);
						g.fill(vuurwerkBol[x]);
				}
			}
			else if (loops < stopExplosions)
			{
				for (int x = 0; x <= vuurwerkBollen.length - 1; x++)
				{
					for (int y = 0; y <= vuurwerkBollen[x].length - 1; y++)
					{
							g.draw(vuurwerkBollen[x][y]);
						  	g.fill(vuurwerkBollen[x][y]);
					}
				}	
			}
	}
	
	@Override
	public void update(Graphics2D g)
	{
	if (loops < stopExplosions)
	{
		loops++;
		if (loops >= startFirstExplode && loops < startSecondExplode)
		{
			for (int x = 0; x <= vuurwerkBol.length - 1; x++)
			{
					newX = (int)(startPoint.getX() + (11 * loops) * Math.cos(((360/vuurwerkBol.length * x * Math.PI / 180))));
				  	newY = (int)(startPoint.getY() + (11 * loops) * Math.sin(((360/vuurwerkBol.length * x) * Math.PI / 180)));
				  	
				  	vuurwerkBol[x].x = newX;
				  	vuurwerkBol[x].y = newY;
			}	
			
			//* Kleur wordt bij elke paint transparanter, vanaf 13 loops.
			if (loops > 13)
				kleur = addAlpha(kleur, 10);
		
		}
		else if (loops >= startSecondExplode && loops < stopExplosions)
		{
			if (loops == startSecondExplode)
				kleur = addAlpha(kleur, 0);
			
			kleur = addAlpha(kleur, 10);
			
			for (int x = 0; x <= vuurwerkBollen.length - 1; x++)
			{
				for (int y = 0; y <= vuurwerkBollen[x].length - 1; y++)
				{
					
					newX = (int)(vuurwerkBol[x].x + loops
							* Math.cos(((360/vuurwerkBollen[x].length * y * Math.PI / 180))));
					
				  	newY = (int)(vuurwerkBol[x].y + loops
				  			* Math.sin(((360/vuurwerkBollen[x].length * y) * Math.PI / 180)));
				  	
				  	
				  	
				  	vuurwerkBollen[x][y] = new Ellipse2D.Double(newX, newY, 5, 5);
				}
			}
	}	
		paintComponent(g);

	}
	
	}
	
	private void fillEllipse()
	{
		for (int x = 0; x < vuurwerkBol.length; x++)
		{
			vuurwerkBol[x] = new Ellipse2D.Double(startPoint.getX(), startPoint.getY(), 5, 5);
		}	
	}
	
	public boolean areWeDoneYet()
	{
		if (loops < stopExplosions)
			return false;
		else
			return true;
	}
	

	
}
