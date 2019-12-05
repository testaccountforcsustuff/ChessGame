package com.chess.game;

public class PawnInfanty extends ChessBoardBlockPiece {

	private boolean isFirstMove;
	private int one;

	public PawnInfanty(String owner, ChessBoardLocation initialLocation, ChessPiecePosition game) {
		super(owner, initialLocation, game);
		if (owner.equalsIgnoreCase("White")) {
			pieceUniqueId = '\u2659';
			one = 1;
		} else if (owner.equalsIgnoreCase("Black")) {
			pieceUniqueId = '\u265F';
			one = -1;
		}
		isFirstMove = true;
	}

	@Override
	public boolean moveChessBoardPiece(ChessBoardLocation location) {
		if (location.getChessBoardColumnIndex() == currentChessBoardLocation.getChessBoardColumnIndex()) {
			if (location.getChessBoardRowIndex() - currentChessBoardLocation.getChessBoardRowIndex() == one) {
				if (isFirstMove) {
					isFirstMove = false;
				}
				return !gameChessPiecePosition.getManipulateChessBoard().checkPiaceAtBoardLocation(
						location.getChessBoardRowIndex(), location.getChessBoardColumnIndex())
						&& super.moveChessBoardPiece(location);
			} else if (isFirstMove && (location.getChessBoardRowIndex()
					- currentChessBoardLocation.getChessBoardRowIndex() == (one * 2))) {
				if (isFirstMove) {
					isFirstMove = false;
				}
				return !gameChessPiecePosition.getManipulateChessBoard().checkPiaceAtBoardLocation(
						location.getChessBoardRowIndex(), location.getChessBoardColumnIndex())
						&& super.moveChessBoardPiece(location);
			}
		} else if (Math
				.abs(location.getChessBoardColumnIndex() - currentChessBoardLocation.getChessBoardColumnIndex()) == 1) {
			if (gameChessPiecePosition.getManipulateChessBoard()
					.checkPiaceAtBoardLocation(location.getChessBoardRowIndex(), location.getChessBoardColumnIndex())
					&& location.getChessBoardRowIndex() - currentChessBoardLocation.getChessBoardRowIndex() == one) {

				if (isFirstMove) {
					isFirstMove = false;
				}
				return super.moveChessBoardPiece(location);
			}
		}
		return false;
	}

	@Override
	protected void updateBoardPieceLocation() {
		int one = 0;
		if (playerNumber.equalsIgnoreCase("Player1") && currentChessBoardLocation.getChessBoardRowIndex() <= 6) {
			one = 1;
		} else if (playerNumber.equalsIgnoreCase("Player2") && currentChessBoardLocation.getChessBoardRowIndex() >= 1) {
			one = -1;
		}

		threateningLocations.clear();

		if (currentChessBoardLocation.getChessBoardColumnIndex() >= 1) {
			threateningLocations.add(new ChessBoardLocation(currentChessBoardLocation.getChessBoardRowIndex() + one,
					currentChessBoardLocation.getChessBoardColumnIndex() - 1));
		}
		if (currentChessBoardLocation.getChessBoardColumnIndex() <= 6) {
			threateningLocations.add(new ChessBoardLocation(currentChessBoardLocation.getChessBoardRowIndex() + one,
					currentChessBoardLocation.getChessBoardColumnIndex() + 1));
		}
	}
}