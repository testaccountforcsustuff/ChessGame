package com.chess.display;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;

import com.chess.game.ChessBoardLocation;
import com.chess.game.EntryProgram;
import com.chess.game.ManipulateChessBoard;

/**
 * The Frame and ActionListener for the UI at large
 * @author James Whitman
 *
 */
public class DisplayEngine extends JFrame implements ActionListener
{
	private static final long serialVersionUID = -403289027202281894L;
	private DisplayGame gamePanel;

	/**
	 * Initializes a DisplayEngine object, producing the visual UI
	 * @param board The board that the DisplayEngine is initialized with
	 */
	public DisplayEngine(ManipulateChessBoard board)
	{
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setBounds( 100, 100, 650, 740 );
		gamePanel = new DisplayGame(this, board);
		setContentPane( gamePanel );
		this.setResizable(false);
	}
	
	/**
	 * Handler for the entire UI, uses ActionCommand strings to determine the action to be taken.
	 * @param action The ActionEvent that contains details on a UI event.
	 * @author James Whitman
	 */
	@Override
	public void actionPerformed( ActionEvent action )
	{
		List<String> tokens = Arrays.asList(action.getActionCommand().split(","));
		
		if(tokens.isEmpty())
			return;
		
		//action, arg0, arg1, ...
		switch(tokens.get(0))
		{
		case "board": //x, y
			movePiece(tokens.get(1), tokens.get(2));
			break;
		case "piece": //piece type
			piecePromotion(tokens.get(1));
			break;
		}
		
		System.out.println(tokens);
	}
	
	/**
	 * Handles when a button is pressed on the UI to move or select a piece.
	 * @param sx String representing the X axis coordinate.
	 * @param sy String representing the Y axis coordinate.
	 * @author James Whitman
	 */
	private void movePiece(String sx, String sy)
	{
		int x = Integer.parseInt(sx);
		int y = Integer.parseInt(sy);
		
		ChessBoardLocation location = new ChessBoardLocation(y,x);
		//the game engine mixes up column and rows so y, x
		EntryProgram.i.selectPiece( location );
	}
	
	/**
	 * Handles when a piece promotion button is pressed.
	 * Unfortunately the engine isn't capable of handling promotion.
	 * @param type
	 * @author James Whitman
	 */
	private void piecePromotion(String type){}
	
	/**
	 * Updates the game display using the provided board.
	 * @param board Holds the game state.
	 * @author James Whitman
	 */
	public void updateBoard(ManipulateChessBoard board)
	{
		gamePanel.updateBoard( board );
	}
	
	/**
	 * Updates the game display by highlighting a specific location.
	 * @param location The location to highlight.
	 * @author James Whitman
	 */
	public void updateSelectPiece(ChessBoardLocation location)
	{
		gamePanel.updateBoard(location);
	}
	
	/**
	 * Updates the game display when a move is made by the engine.
	 * @param from Location of the piece to move.
	 * @param to Location to move the piece to.
	 * @param turnstr The current turn.
	 * @param check Whether the game is in a check state or not.
	 * @param checkmate Whether the game is in a checkmate state or not.
	 */
	public void updateMovePiece(ChessBoardLocation from, ChessBoardLocation to, String turnstr, boolean check, boolean checkmate)
	{
		gamePanel.updateBoard(from, to, turnstr);
		gamePanel.updateBoard(check, checkmate);
	}
}
