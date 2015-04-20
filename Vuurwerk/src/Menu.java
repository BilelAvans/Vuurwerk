import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class Menu extends JMenuBar
{
	//* Menu componenten
	private JMenu vuurwerk, item2;
	private JMenuItem item1;
	private JCheckBox check1;
	
	private Explode explode;


	Menu(Explode explode)
	{
		this.explode = explode;
		
		initMenu();
	}
	
	//* Menu in elkaar flanzen
	private void initMenu()
	{
		vuurwerk 	= new JMenu		("Vuurwerk");
		item1 		= new JMenuItem	("Afspelen");
		item2		= new JMenu 	("Kleuren");
		
		check1		= new JCheckBox ("Regenboog");
		
		item2.add(check1);
		
		vuurwerk.add(item1);
		vuurwerk.add(item2);
				
		add(vuurwerk);		
		
		//* Listeners in: Explode.java
		item1.addActionListener(explode);
		check1.addItemListener(explode);
		
	}
}
