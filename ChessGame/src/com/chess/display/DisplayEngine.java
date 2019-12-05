package com.chess.display;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;

import com.chess.game.ChessBoardLocation;
import com.chess.game.EntryProgram;
import com.chess.game.ManipulateChessBoard;

public class DisplayEngine extends JFrame implements ActionListener
{
	private static final long serialVersionUID = -403289027202281894L;
	private DisplayGame gamePanel;

	public DisplayEngine(ManipulateChessBoard board)
	{
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setBounds( 100, 100, 800, 800 );
		gamePanel = new DisplayGame(this, board);
		setContentPane( gamePanel );
		this.setResizable(false);
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
	
	
	private void movePiece(String sx, String sy)
	{
		int x = Integer.parseInt(sx);
		int y = Integer.parseInt(sy);
		
		ChessBoardLocation location = new ChessBoardLocation(y,x);
		EntryProgram.i.selectPiece( location );
	}
	
	private void piecePromotion(String type)
	{
		
	}
	
	public void updateBoard(ManipulateChessBoard board)
	{
		gamePanel.updateBoard( board );
	}
	
	public void updateSelectPiece(ChessBoardLocation location)
	{
		gamePanel.updateBoard(location);
	}
	
	public void updateMovePiece(ChessBoardLocation from, ChessBoardLocation to, String turnstr, boolean check, boolean checkmate)
	{
		gamePanel.updateBoard(from, to, turnstr);
		gamePanel.updateBoard(check, checkmate);
	}
}
