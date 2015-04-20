import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.JCheckBox;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Explode extends JPanel implements ActionListener, MouseListener, ItemListener, MouseWheelListener
{

	//* Timer
	private Timer timer;
	
	private List<Explosie> explosies = new ArrayList<Explosie>();
	
	private boolean regenboogKleur = false;
	
	private Random random = new Random();

	public Explode()
	{
		addMouseListener(this);
		addMouseWheelListener(this);
		setBackground(Color.BLACK);

		//* Snelheid
		timer = new Timer(1000/25, this);
		timer.start();
		
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D graph = (Graphics2D) g;
		
		Iterator explosiesIterator = explosies.iterator();
		
		while (explosiesIterator.hasNext())
		{
			Explosie e = (Explosie) explosiesIterator.next();

				if (!e.areWeDoneYet())
					e.update(graph);
				else
					explosiesIterator.remove();
		}
	}

	
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		//* Gebruiker heeft op "Afspelen" in Menu gedrukt
		if (arg0.getSource() instanceof JMenuItem)
		{
			JMenuItem item = (JMenuItem)arg0.getSource();
			
			if (item.getText().equals("Afspelen"))
			{
				explosies.add(new BolletjesExplosie(random.nextInt(getWidth()), random.nextInt(getHeight()), regenboogKleur));
			}
		}
		
		repaint();
		

	}

	public void mouseClicked(MouseEvent arg0) 
	{
		
		explosies.add(new BolletjesExplosie(arg0.getX(), arg0.getY(), regenboogKleur));
	}	
	

	
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		
		JCheckBox checkbox = (JCheckBox) arg0.getSource();

		//* Regenboog kleur (Nieuwe kleur bij elke repaint)
		if (checkbox.getText().equals("Regenboog"))
		{
			if (checkbox.isSelected() == true)
				regenboogKleur = true;
			else
				regenboogKleur = false;
		}
	}

	
	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		
		//* Versnellen en Vertragen
		if (arg0.getWheelRotation() == 1)
		{
			
			if (timer.getDelay() < 1000/15)
				timer.setDelay(timer.getDelay() + 2);
		
		}
		else if (arg0.getWheelRotation() == -1)
		{
			
			if (timer.getDelay() > 1000/60)
				timer.setDelay(timer.getDelay() - 2);
		}
		
	}
	
	//* Ongebruikte Listeners
	public void mouseEntered(MouseEvent arg0) {}

	public void mouseExited(MouseEvent arg0) {}

	public void mouseReleased(MouseEvent arg0) {}

	public void mousePressed(MouseEvent e) {}
}
