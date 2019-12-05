package com.chess.display;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.chess.game.ChessBoardBlockPiece;
import com.chess.game.ChessBoardLocation;
import com.chess.game.ManipulateChessBoard;

import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.awt.Color;

public class DisplayGame extends JPanel
{
	
	private static final long serialVersionUID = -2700512290966573456L;
	private static final int width = 8;
	private static final Font boardFont = new Font("Monospaced", Font.PLAIN, 39);
	private static final Font piecesFont = new Font("Monospaced", Font.PLAIN, 32);
	private static final Font textFont = new Font("Monospaced", Font.PLAIN, 16);
	private static final Insets boardInsets = new Insets(0,0,0,0);
	private static final Dimension boardDimension = new Dimension(50,50);
	private static final Dimension boardPanelMinDimension = new Dimension(500,500);
	private static final Dimension verticalPanelMinDimension = new Dimension(60,500);
	private static final Dimension horizontalPanelMinDimension = new Dimension(500,60);
	private static final Insets insets = new Insets(5,5,5,5);

	private JButton[][] boardButtons;
	private JButton[] pieces;
	private JLabel[] horizontal;
	private JLabel[] vertical;
	private JLabel deadWhite;
	private JLabel deadBlack;
	private JLabel turn;
	private JLabel check;
	private JOptionPane endResult;
	private JLabel endText;
	private JButton endExit;
	private ChessBoardLocation selected;
	//"\u2659\u2659\u2659\u2659\u2659\u2659\u2659\u2659\u2658\u2658\u2657\u2657\u2656\u2656\u2655"

	/**
	 * Initializes a DisplayGame panel
	 * @param engine The actionlistener that buttons use
	 * @param board The gameboard to initialize off
	 * @author James Whitman
	 */
	DisplayGame(DisplayEngine engine, ManipulateChessBoard board)
	{
		GridBagConstraints gbConstraints = new GridBagConstraints();
		gbConstraints.insets = insets;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{60, 500, 60, 0};
		gridBagLayout.rowHeights = new int[]{53, 60, 500, 53, 53, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		//sets up the labels for holding captured pieces
		deadWhite = new JLabel();
		deadWhite.setFont(piecesFont);
		GridBagConstraints deadWhiteConstraints = new GridBagConstraints();
		deadWhiteConstraints.insets = insets;
		deadWhiteConstraints.gridx = 1;
		deadWhiteConstraints.gridy = 0;
		add(deadWhite, deadWhiteConstraints);
		
		deadBlack = new JLabel();
		deadBlack.setFont(piecesFont);
		GridBagConstraints deadBlackConstraints = new GridBagConstraints();
		deadBlackConstraints.insets = new Insets(0, 0, 5, 5);
		deadBlackConstraints.gridx = 1;
		deadBlackConstraints.gridy = 3;
		add(deadBlack, deadBlackConstraints);
		
		//sets up the label for displaying if the game is in a check state
		check = new JLabel();
		check.setFont(boardFont);
		GridBagConstraints checkConstraints = new GridBagConstraints();
		checkConstraints.insets = new Insets(5, 5, 0, 5);
		checkConstraints.gridx = 1;
		checkConstraints.gridy = 4;
		add(check, checkConstraints);
		
		//sets up the player turn label
		turn = new JLabel("<html>Turn:<br/>White</html>");
		turn.setFont(textFont);
		GridBagConstraints turnConstraints = new GridBagConstraints();
		turnConstraints.insets = insets;
		turnConstraints.gridx = 0;
		turnConstraints.gridy = 1;
		add(turn, turnConstraints);

		//sets up the grid markers above
		JPanel horizontalPanel = new JPanel();
		horizontalPanel.setMinimumSize(horizontalPanelMinDimension);
		horizontalPanel.setPreferredSize(horizontalPanelMinDimension);
		GridBagLayout horizontalLayout = new GridBagLayout();
		horizontalPanel.setLayout(horizontalLayout);
		GridBagConstraints gbc_horizontalPanel = new GridBagConstraints();
		gbc_horizontalPanel.anchor = GridBagConstraints.SOUTHEAST;
		gbc_horizontalPanel.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalPanel.gridx = 1;
		gbc_horizontalPanel.gridy = 1;
		add(horizontalPanel, gbc_horizontalPanel);
		
		//sets up the grid markers to the side
		JPanel verticalPanel = new JPanel();
		verticalPanel.setMinimumSize(verticalPanelMinDimension);
		verticalPanel.setPreferredSize(verticalPanelMinDimension);
		GridBagLayout verticalLayout = new GridBagLayout();
		verticalPanel.setLayout(verticalLayout);
		GridBagConstraints gbc_verticalPanel = new GridBagConstraints();
		gbc_verticalPanel.anchor = GridBagConstraints.NORTHEAST;
		gbc_verticalPanel.insets = new Insets(0, 0, 5, 5);
		gbc_verticalPanel.gridx = 0;
		gbc_verticalPanel.gridy = 2;
		add(verticalPanel, gbc_verticalPanel);
		
		//sets up the gameboard panel
		JPanel boardPanel = new JPanel();
		boardPanel.setMinimumSize(boardPanelMinDimension);
		boardPanel.setPreferredSize(boardPanelMinDimension);
		GridBagLayout boardLayout = new GridBagLayout();
		boardPanel.setLayout(boardLayout);
		GridBagConstraints gbc_boardPanel = new GridBagConstraints();
		gbc_boardPanel.anchor = GridBagConstraints.NORTHEAST;
		gbc_boardPanel.insets = new Insets(0, 0, 5, 5);
		gbc_boardPanel.gridx = 1;
		gbc_boardPanel.gridy = 2;
		add(boardPanel, gbc_boardPanel);
		
		//sets up the panel of pieces to promote a pawn into
		JPanel piecesPanel = new JPanel();
		piecesPanel.setMinimumSize(verticalPanelMinDimension);
		piecesPanel.setPreferredSize(verticalPanelMinDimension);
		GridBagLayout piecesLayout = new GridBagLayout();
		piecesPanel.setLayout(piecesLayout);
		GridBagConstraints gbc_piecesPanel = new GridBagConstraints();
		gbc_piecesPanel.insets = new Insets(0, 0, 5, 0);
		gbc_piecesPanel.anchor = GridBagConstraints.WEST;
		gbc_piecesPanel.gridx = 2;
		gbc_piecesPanel.gridy = 2;
		add(piecesPanel, gbc_piecesPanel);
		
		
		//inits the horizontal gridmarkers
		gbConstraints.gridy = 0;
		horizontal = new JLabel[width];
		for(int i = 0; i < width; i++)
		{
			horizontal[i] = new JLabel(String.valueOf((char)(i+'A')),JLabel.CENTER);
			horizontal[i].setFont(boardFont);
			horizontal[i].setPreferredSize(boardDimension);
			horizontal[i].setMinimumSize(boardDimension);
			gbConstraints.gridx = i;
			horizontalPanel.add(horizontal[i], gbConstraints);
		}

		//inits the vertical gridmarkers
		gbConstraints.gridx = 0;
		vertical = new JLabel[width];
		for(int i = 0; i < width; i++)
		{
			vertical[i] = new JLabel(width-i+"",JLabel.CENTER);
			vertical[i].setFont(boardFont);
			vertical[i].setPreferredSize(boardDimension);
			vertical[i].setMinimumSize(boardDimension);
			gbConstraints.gridy = i;
			verticalPanel.add(vertical[i], gbConstraints);
		}

		//inits the piece promotion buttons
		pieces = new JButton[5];
		for(int i = 0; i < 5; i++)
		{
			pieces[i] = new JButton();
			pieces[i].setFont(boardFont);
			pieces[i].setPreferredSize(boardDimension);
			pieces[i].setMinimumSize(boardDimension);
			pieces[i].setMargin(boardInsets);
			pieces[i].addActionListener( engine );
			pieces[i].setActionCommand( "piece,"+i);
			gbConstraints.gridy = i;
			piecesPanel.add(pieces[i], gbConstraints);
		} //no good way to automate the below
		pieces[0].setText("\u2659");
		pieces[0].setToolTipText("Pawn");
		pieces[1].setText("\u2658");
		pieces[1].setToolTipText("Knight");
		pieces[2].setText("\u2657");
		pieces[2].setToolTipText("Bishop");
		pieces[3].setText("\u2656");
		pieces[3].setToolTipText("Rook");
		pieces[4].setText("\u2655");
		pieces[4].setToolTipText("Queen");
		
		//inits the board buttons
		boardButtons = new JButton[width][width];
		for(int i = 0; i < width; i++)
			for(int j = 0; j < width; j++)
			{
				boardButtons[i][j] = new JButton();
				boardButtons[i][j].setFont(boardFont);
				boardButtons[i][j].setPreferredSize(boardDimension);
				boardButtons[i][j].setMinimumSize(boardDimension);
				boardButtons[i][j].setMargin(boardInsets);
				boardButtons[i][j].addActionListener( engine );
				boardButtons[i][j].setActionCommand( "board,"+i+","+j );
				gbConstraints.gridx = i;
				gbConstraints.gridy = j;
				boardPanel.add(boardButtons[i][j], gbConstraints);
			}
		
		updateBoard(board);
	}
	
	/**
	 * Updates the displays board based off the internal gamestate board
	 * @param board The board to update the display state with
	 * @author James Whitman
	 */
	protected void updateBoard(ManipulateChessBoard board)
	{
		ChessBoardLocation location = new ChessBoardLocation(0,0);
		ChessBoardBlockPiece piece;
		
		//loops through the entire gameboard and updates the text
		for(int i = 0; i < width; i++)
		{
			location.setChessBoardColumnIndex(i);
			for(int j = 0; j < width; j++)
			{
				location.setChessBoardRowIndex(j);
				piece = board.getPieceAt( location );
				if(piece != null)
					boardButtons[i][j].setText(piece.getPieceUniqueId()+"");
				else
					boardButtons[i][j].setText("");
			}
		}
	}
	
	/**
	 * Moves a piece on the displayed gameboard, adding any captured pieces to the respective Dead label
	 * @param from The location of the piece to move
	 * @param to The location to move a piece to
	 * @param turnstr The next turn the game is on
	 * @author James Whitman
	 */
	protected void updateBoard(ChessBoardLocation from, ChessBoardLocation to, String turnstr)
	{
		int xF = from.getChessBoardColumnIndex();
		int yF = from.getChessBoardRowIndex();
		int xT = to.getChessBoardColumnIndex();
		int yT = to.getChessBoardRowIndex();
		
		System.out.println(xF + ", " + yF + "    " + xT + ", " + yT);
		
		//moves the piece to its new spot
		String taken = boardButtons[xT][yT].getText();
		boardButtons[xT][yT].setText(boardButtons[xF][yF].getText());
		//clears the old spot
		boardButtons[xF][yF].setText("");
		boardButtons[xF][yF].setBackground(null);
		boardButtons[xF][yF].setContentAreaFilled(true);
		boardButtons[xF][yF].setOpaque(false);
		//swaps the turn
		turn.setText("<html>Turn:<br/>"+turnstr+"</html>");
		
		//appends the taken piece to the appropriate dead labels
		if(turnstr.equals("Black"))
			deadWhite.setText(deadWhite.getText() + taken);
		else
			deadBlack.setText(deadBlack.getText() + taken);
	}
	
	/**
	 * Highlights and dehighlights a piece on the displayed gameboard
	 * @param location The location of the piece to highlight
	 * @author James Whitman
	 */
	protected void updateBoard(ChessBoardLocation location)
	{
		int x, y;
		
		//unhighlights spot
		if(selected != null)
		{
			x = selected.getChessBoardColumnIndex();
			y = selected.getChessBoardRowIndex();
			boardButtons[x][y].setBackground(null);
			boardButtons[x][x].setContentAreaFilled(true);
			boardButtons[x][y].setOpaque(false);
			selected = null;
		}
		//highlights spot
		if(location != null)
		{
			x = location.getChessBoardColumnIndex();
			y = location.getChessBoardRowIndex();
			boardButtons[x][y].setBackground(Color.YELLOW);
			boardButtons[x][x].setContentAreaFilled(false);
			boardButtons[x][y].setOpaque(true);
			selected = location;
		}
	}
	
	/**
	 * Updates the check and checkmate label
	 * @param checkbool Whether the game is in a check state.
	 * @param checkmate Whether the game is in a checkmate state.
	 * @author James Whitman
	 */
	protected void updateBoard(boolean checkbool, boolean checkmate)
	{
		if(checkmate)
		{
			check.setText("Checkmate");
			check.setForeground(Color.RED);
		}
		else if(checkbool)
		{
			check.setText("Check");
			check.setForeground(Color.BLUE);
		}
		else
			check.setText("");
	}
}
