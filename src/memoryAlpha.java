/**
 * @(#)GraphicsApplet1.java
 *
 * GraphicsApplet1 Applet application
 *
 * @author Ben Pope
 * @Date 2/26/2010
 * @version 1.00 2009/2/11
 */
// 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class memoryAlpha extends JApplet implements ActionListener, MouseListener

{

	Canvas  canvas;
	JTextField scoreTextField;
	private final int boardSize = 400;
	private final int cardSize = 100;

	int gameScore;


	int[][] Colors;
	
	public void mouseEntered(MouseEvent evt){}
	public void mouseExited(MouseEvent evt){}
	public void mouseClicked(MouseEvent evt){}
	public void mouseMoved(MouseEvent evt){}
	public void mouseDragged(MouseEvent evt){}
	public void mouseReleased(MouseEvent evt){}
	
	private class Canvas extends JPanel {
		public void paintComponent(Graphics g){
			super.paintComponent(g);

			g.setColor(Color.BLACK);
			g.fillRect(0, 0, boardSize-1, boardSize-1);
			int xloc, yloc;
				
			for(int i=0; i <4; i++){
				for(int j=0; j<4; j++){
					g.setColor(Color.black);
					g.drawRect(i*100, j*100, 100,100);
					if (Colors[i][j] == 0)
						g.setColor(Color.blue);
					else if (Colors[i][j] == 1 )
						g.setColor(Color.yellow);
					else if (Colors[i][j] == 2 )
						g.setColor(Color.red);
					else if (Colors[i][j] == 3 )
						g.setColor(Color.green);
				g.fillRect((i*100)+1, (j*100)+1, 99, 99);	
					
				}
			}

		}         

	}

	public void actionPerformed( ActionEvent acEvent) {
		String aC = acEvent.getActionCommand();
		if (aC == "Reset"){
			resetBoard();
			repaint();}
	}

	public void mousePressed(MouseEvent evt){
		int x = evt.getX();
		int y = evt.getY();
		int col = y/100;
		int row = x/100;
		
		if(Colors[row][col]== 0)
			Colors[row][col] = 1;
		else if(Colors[row][col]== 1)
			Colors[row][col] = 2;
		else if(Colors[row][col]== 2)
			Colors[row][col] = 3;
		else if(Colors[row][col]== 3)
			Colors[row][col] = 0;
		repaint();
		scoreTextField.setText(Integer.toString(findDominantColorCount()));
	}
	 

	public void init() {
		createComponents();

		Colors = new int[4][4];
		resetBoard();

	}

	private void resetBoard(){
		for (int i = 0; i < 4; i++)
			for(int j = 0; j <4; j++)
				Colors[i][j]= 0;

		scoreTextField.setText(Integer.toString(0));
	}

	public int findDominantColorCount(){
		int colorB = 0;
		int colorY = 0;
		int colorR = 0;
		int colorG = 0;
		int DC = 0;
		
		for (int i = 0; i < 4; i++)
			for(int j = 0; j <4; j++){
				if(Colors[i][j] == 0)
					colorB++;
				else if(Colors[i][j]==1)
					colorY++;
				else if(Colors[i][j]==2)
					colorR++;
				else if(Colors[i][j]==3)
					colorG++;
			}
			if (DC < colorG)
				DC = colorG;
			if (DC < colorR)
				DC = colorR;
			if (DC < colorB)
				DC = colorB;
			if (DC < colorY)
				DC = colorY;
		return DC;
	}
	

	private void createComponents() {
		JPanel content = new JPanel();
		content.setLayout( new BorderLayout());

		JPanel uip = new JPanel();
		uip.setLayout( new FlowLayout());


		JLabel scoreLabel = new JLabel("Dominant Count");
		scoreTextField = new JTextField(6);
		uip.add(scoreLabel);
		uip.add(scoreTextField);

		JButton b1 = new JButton("Reset");
		uip.add(b1);
		b1.addActionListener(this);
		

		canvas = new Canvas();
		

		canvas.addMouseListener(this);
		setSize(boardSize, boardSize+40);

		content.add(canvas, BorderLayout.CENTER);
		content.add(uip, BorderLayout.NORTH);
		setContentPane(content);
	}



}


