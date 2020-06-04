import java.awt.Frame;
import java.awt.Panel;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Button;
import java.awt.TextArea;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;



public class StoppingDistances
{
	public static void main(String[] args )
	{
		FrameAndPanels frame = new FrameAndPanels();
		frame.setTitle("Stopping Distances");
		frame.setVisible( true );
	}
	
}

class FrameAndPanels extends Frame implements ActionListener
{
	public FrameAndPanels()
	{
		final int DEFAULT_FRAME_WIDTH = 250;
		final int DEFAULT_FRAME_HEIGHT = 200;
		setSize( DEFAULT_FRAME_WIDTH, DEFAULT_FRAME_HEIGHT  );
		//Panel 1:
		panel1 = new Panel();
		panel1.setLayout( new GridLayout( 5, 2) );
		
			//start:
			panel1.add( new Label( "Start", Label.CENTER));
			start = new TextField();
			panel1.add( start );
			
			//end:
			panel1.add( new Label( "End", Label.CENTER));
			end = new TextField();
			panel1.add( end );
			
			//increment:
			panel1.add( new Label( "Increment", Label.CENTER));
			increment = new TextField();
			panel1.add( increment );
		
			//clear:
			clear = new Button("Clear");
			clear.addActionListener( this );
			panel1.add(clear);
			
			//table:
			display = new Button ("Table");
			display.addActionListener( this );
			panel1.add( display );
		
			//done:
			done = new Button( "Exit");
			done.addActionListener( this );
			panel1.add( done );
			
		
		//panel 2
		panel2 = new Panel();
		panel2.setLayout( new GridLayout( 1, 1));
			
			//text area:
			text = new TextArea( "", 0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY );
			panel2.add( text );
			text.setEditable(false);
		
		// Stuff
		setLayout( new GridLayout( 2, 1) );
		add( panel1 ); 
		add( panel2);
	}
	
	public void actionPerformed( ActionEvent e )
	{
		text.setText("");
		text.setFont(new Font("Monospaced", Font.PLAIN, 10) );
		if( e.getSource() == display)
		{
			
			String startString = start.getText();
			String endString = end.getText();
			String incrementString = increment.getText();
			String regex = "^\\s+";
			String startStringNoLeadSpace = startString.replaceAll(regex, "");
			String endStringNoLeadSpace = endString.replaceAll(regex, "");
			String incrementStringNoLeadSpace = incrementString.replaceAll(regex, "");
			
			
			
			if (startString.length() > 0 && endString.length() > 0 && incrementString.length() > 0 )
				
			{
				try
				{
					float startNum = Float.parseFloat(startString);
					float endNum = Float.parseFloat(endString);
					float incrementNum = Float.parseFloat(incrementString);
					if ( endNum == 0 )
					{
						text.append("You have a zero end speed. A stationary object has a stopping distance of zero seconds.");
					}
					else if (incrementNum == 0)
					{
						text.append("Please add a non-zero increment.");
					}
					
					else if (startNum < 0 || endNum < 0 || incrementNum < 0)
					{
						text.append("No negative numbers please");
					}
					//else if (startNum > endNum)
					//{
						//text.append("You cannot put an end that is smaller than your start.");
					//}
					
					else if ( startString.length() > 7 || endString.length() > 7 || incrementString.length() > 7)
					{
						text.append("You are allowed a maximum of 7 characters in the fields.");
					}
					
					
					else if (startStringNoLeadSpace.length() != startString.length() || endStringNoLeadSpace.length() != endString.length() ||incrementStringNoLeadSpace.length() != incrementString.length())
					{
						text.append("Make sure you haven't accidentally added a space into the fields.");
					}
					else
					{
						text.append("***************************" + "\n");
						text.append("*Speed(mph)*Distance(feet)*" + "\n");
						text.append("***************************" + "\n");
						
						if (endNum >= startNum)
						{
							for (float i = startNum; i <= endNum; i = i + incrementNum)
							{
								
								float distance = ((i*i)/20 + i);
								String iString = String.valueOf((int) Math.round(i));
								String distanceString = String.valueOf((int) distance);
								String formattedString = String.format("*%1$6s%3$5s%2$7s%3$8s\n", iString, distanceString,"*");
								text.append(formattedString);
								
							}	
						}
						else 
						{
							for (float i = startNum; i >= endNum; i = i - incrementNum)
							{
								
								float distance = ((i*i)/20 + i);
								String iString = String.valueOf((int) Math.round(i));
								String distanceString = String.valueOf((int) distance);
								String formattedString = String.format("*%1$6s%3$5s%2$7s%3$8s\n", iString, distanceString,"*");
								text.append(formattedString);
								
							}
						}
						
						text.append("***************************" + "\n");
					}
						
					
				}
				
				catch(NumberFormatException ex)
				{
					text.append("Please check that you have entered one integer into each field.");
				}
					
					
			}
				
				
			else
			{
				text.append("Please make sure you have entered numbers into all three fields.");
			}
					
				
		}
		
		else if( e.getSource() == clear)
		{
			start.setText( "" );
			end.setText( "" );
			increment.setText( "" );
			text.setText("");
		}
		
		else
		{
			System.exit(0);
		}
	}
	
	
	private TextField start, end, increment;
	private Button clear, display, done;
	private TextArea text;
	private Panel panel1, panel2;
}