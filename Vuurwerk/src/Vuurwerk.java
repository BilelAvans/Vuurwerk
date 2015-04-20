import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;


public class Vuurwerk extends JFrame
{

	private Explode explode;
	private JMenuBar menu;
	
	
	Vuurwerk()
	{
		super("Vuurwerk Demo");
		setSize(new Dimension(600, 600));
		
		explode = new Explode();
		menu    = new Menu(explode);
		
		setLayout(new BorderLayout());
		
		getContentPane().add(menu, BorderLayout.NORTH);
		getContentPane().add(explode, BorderLayout.CENTER);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setVisible(true);

	}
	
	public static void main(String args[])
	{
		EventQueue.invokeLater(new Runnable(){
			
			public void run()
			{
				new Vuurwerk();
			}
		});
	}
	
}
