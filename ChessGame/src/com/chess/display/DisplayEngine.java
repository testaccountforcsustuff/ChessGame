package com.chess.display;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;

import com.chess.game.ChessBoardLocation;

public class DisplayEngine extends JFrame implements ActionListener
{
	private static final long serialVersionUID = -403289027202281894L;
	private DisplayGame gamePanel;

	public DisplayEngine()
	{
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setBounds( 100, 100, 800, 800 );
		gamePanel = new DisplayGame(this);
		setContentPane( gamePanel );
	}
	
	@Override
	public void actionPerformed( ActionEvent action )
	{
		List<String> tokens = Arrays.asList(action.getActionCommand().split(","));
		
		if(tokens.isEmpty())
			return;
		
		switch(tokens.get(0))
		{
		case "board":
			movePiece(tokens.get(1), tokens.get(2));
			break;
		case "piece":
			piecePromotion(tokens.get(1));
			break;
		}
		
		System.out.println(tokens);
	}
	
	
	void movePiece(String sx, String sy)
	{
		int x = Integer.parseInt(sx);
		int y = Integer.parseInt(sy);
		
		ChessBoardLocation location = new ChessBoardLocation(x,y);
	}
	
	void piecePromotion(String type)
	{
		
	}
}
