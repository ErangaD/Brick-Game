package Game;


import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;


public class GameLauncher {
	
	
	   public static void main(String[] args) {
        
    
		// Game width and height
		Dimension gameDimension = new Dimension(400, 600);
		// Create a window
		JFrame frame = new JFrame();
		
		// Set internal panel size
		JPanel panel = (JPanel) frame.getContentPane();
		panel.setPreferredSize( gameDimension );
		panel.setLayout(null);
		
		// Create our game 
		Game g = new Game(gameDimension);
		g.setBounds(0, 0, gameDimension.width, gameDimension.height);	
		// Add to the frame
		frame.add(g);
		
		// Setting for the frame
		// Set default behavior to close on exit
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		// Stop resizing
		frame.setResizable(false);
		// Set title
		frame.setTitle("Bricks Game");
                
                //adding listner
                frame.addKeyListener(g);
		// Pack the content
		frame.pack();
		// Center the frame
		frame.setLocationRelativeTo(null);		
		// Show the frame
		frame.setVisible(true);						
		// Start the game
		g.Start();
                
	}
}
