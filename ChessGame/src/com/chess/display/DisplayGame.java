package com.chess.display;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;

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

	JButton[][] boardButtons;
	JButton[] pieces;
	JLabel[] horizontal;
	JLabel[] vertical;
	JLabel deadWhite;
	JLabel deadBlack;
	JLabel turn;
	JLabel check;
	JOptionPane endResult;
	JLabel endText;
	JButton endExit;
	//"\u2659\u2659\u2659\u2659\u2659\u2659\u2659\u2659\u2658\u2658\u2657\u2657\u2656\u2656\u2655"
	
	/**
	 * Create the panel.
	 */
	public DisplayGame(DisplayEngine engine)
	{
		GridBagConstraints gbConstraints = new GridBagConstraints();
		gbConstraints.insets = insets;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{60, 500, 60, 0};
		gridBagLayout.rowHeights = new int[]{48, 60, 500, 48, 52, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
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
		
		check = new JLabel();
		check.setFont(boardFont);
		GridBagConstraints checkConstraints = new GridBagConstraints();
		checkConstraints.insets = new Insets(5, 5, 0, 5);
		checkConstraints.gridx = 1;
		checkConstraints.gridy = 4;
		add(check, checkConstraints);
		
		turn = new JLabel("<html>Turn:<br/>White</html>");
		turn.setFont(textFont);
		GridBagConstraints turnConstraints = new GridBagConstraints();
		turnConstraints.insets = insets;
		turnConstraints.gridx = 0;
		turnConstraints.gridy = 1;
		add(turn, turnConstraints);


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
		}
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
	}
	
	public void updateBoard()
	{
		JButton btnNewButton = new JButton();
		btnNewButton.setText("\u265B");
		btnNewButton.setToolTipText("Queen");
	}
}