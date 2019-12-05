package com.chess.game;

public class ChessPiecePosition {

	ManipulateChessBoard manipulateChessBoard;
	KingThreaten player1KingPiece;
	KingThreaten player2KingPiece;

	public ChessPiecePosition() {
		manipulateChessBoard = new ManipulateChessBoard();
		placePieceOnBoard(0, "White");
		placePieceOnBoard(7, "Black");
	}

	void placePieceOnBoard(int chessBoardIndex, String playerName) {
		int piecePosition;
		int pieceIncInColumn = 0;
		if (chessBoardIndex > 0)
			piecePosition = -1;
		else
			piecePosition = 1;
		new CastleTowerRook(playerName, new ChessBoardLocation(chessBoardIndex, pieceIncInColumn), this);
		new CastleTowerRook(playerName, new ChessBoardLocation(chessBoardIndex, 7 - pieceIncInColumn), this);
		pieceIncInColumn += 1;

		new KnightHorsePiece(playerName, new ChessBoardLocation(chessBoardIndex, pieceIncInColumn), this);
		new KnightHorsePiece(playerName, new ChessBoardLocation(chessBoardIndex, 7 - pieceIncInColumn), this);
		pieceIncInColumn += 1;

		new BishopChessPiece(playerName, new ChessBoardLocation(chessBoardIndex, pieceIncInColumn), this);
		new BishopChessPiece(playerName, new ChessBoardLocation(chessBoardIndex, 7 - pieceIncInColumn), this);
		pieceIncInColumn += 1;

		if (playerName.equalsIgnoreCase("White")) {
			player1KingPiece = new KingThreaten(playerName, new ChessBoardLocation(chessBoardIndex, pieceIncInColumn), this);
		} else {
			player2KingPiece = new KingThreaten(playerName, new ChessBoardLocation(chessBoardIndex, pieceIncInColumn), this);
		}

		new PowerfulQueen(playerName, new ChessBoardLocation(chessBoardIndex, 7 - pieceIncInColumn), this);

		int maxIndex = 8;
		for (int index = 0; index < maxIndex; index++) {
			new PawnInfanty(playerName, new ChessBoardLocation(chessBoardIndex + piecePosition, index), this);
		}
	}

	public void setManipulateChessBoard(ManipulateChessBoard manipulateChessBoard) {
		this.manipulateChessBoard = manipulateChessBoard;
	}

	public ManipulateChessBoard getManipulateChessBoard() {
		return manipulateChessBoard;
	}

	public void setPlayer1KingPiece(KingThreaten player1KingPiece) {
		this.player1KingPiece = player1KingPiece;
	}

	public void setPlayer2KingPiece(KingThreaten player2KingPiece) {
		this.player2KingPiece = player2KingPiece;
	}

	public KingThreaten getPlayer1KingPiece() {
		return player1KingPiece;
	}

	public KingThreaten getPlayer2KingPiece() {
		return player2KingPiece;
	}
}