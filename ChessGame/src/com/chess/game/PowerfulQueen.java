package com.chess.game;

public class PowerfulQueen extends ChessBoardBlockPiece {

	public PowerfulQueen(String playerMark, ChessBoardLocation pieceInitialLocation,
			ChessPiecePosition pieceInitialPosition) {
		super(playerMark, pieceInitialLocation, pieceInitialPosition);
		if (playerMark.equalsIgnoreCase("Player1")) {
			pieceUniqueId = 'Q';
		} else if (playerMark.equalsIgnoreCase("Player2")) {
			pieceUniqueId = 'q';
		}
	}

	@Override
	public boolean moveChessBoardPiece(ChessBoardLocation location) {
		boolean response = checkIndexOfBoard(currentChessBoardLocation, location)
				&& super.moveChessBoardPiece(location);
		return response;
	}

	@Override
	protected void updateBoardPieceLocation() {
		threateningLocations.clear();

		super.updateBoardPieceVertical(1);
		super.updateBoardPieceVertical(-1);

		super.updateBoardPieceHorizontal(1);
		super.updateBoardPieceHorizontal(-1);

		super.updateDiagonalIndex(1, 1);
		super.updateDiagonalIndex(-1, 1);
		super.updateDiagonalIndex(1, -1);
		super.updateDiagonalIndex(-1, -1);
	}
}