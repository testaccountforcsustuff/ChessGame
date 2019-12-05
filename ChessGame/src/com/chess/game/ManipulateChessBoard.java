package com.chess.game;

public class ManipulateChessBoard {

	ChessBoardBlockPiece[][] chessPieceBoardArrays;

	public ManipulateChessBoard() {
		chessPieceBoardArrays = new ChessBoardBlockPiece[8][8];
	}

	public boolean checkPiaceAtBoardLocation(int row, int col) {
		return chessPieceBoardArrays[row][col] != null;
	}

	void removePieceAndUpdateLocation(ChessBoardLocation location) {
		chessPieceBoardArrays[location.getChessBoardRowIndex()][location.getChessBoardColumnIndex()] = null;
	}

	public ChessBoardBlockPiece getPieceAt(ChessBoardLocation location) {
		return chessPieceBoardArrays[location.getChessBoardRowIndex()][location.getChessBoardColumnIndex()];
	}

	public static boolean locationInBounds(ChessBoardLocation location) {
		int index = 8;
		boolean responseBound = location.getChessBoardRowIndex() >= 0 && location.getChessBoardRowIndex() < index
				&& location.getChessBoardColumnIndex() >= 0 && location.getChessBoardColumnIndex() < index;
		return responseBound;
	}

	public void piecePlaceAtBoardLocation(ChessBoardBlockPiece blockPiece, ChessBoardLocation boardLocation) {
		boolean pieceLocation = checkPiaceAtBoardLocation(boardLocation.getChessBoardRowIndex(),
				boardLocation.getChessBoardColumnIndex());
		if (pieceLocation) {
			removePieceAndUpdateLocation(boardLocation);
		}

		if (blockPiece.getCurrentChessBoardLocation() != null) {
			removePieceAndUpdateLocation(blockPiece.getCurrentChessBoardLocation());
		}

		chessPieceBoardArrays[boardLocation.getChessBoardRowIndex()][boardLocation
				.getChessBoardColumnIndex()] = blockPiece;
		blockPiece.setCurrentChessBoardLocation(boardLocation);
	}

	@Override
	public String toString() {
		int maximumRow = 8;
		int maximumColumn = 8;
		String totalRowCount = "  0 1 2 3 4 5 6 7\n";

		for (int rowNumber = 0; rowNumber < maximumRow; rowNumber++) {
			totalRowCount += rowNumber;
			for (int col = 0; col < maximumColumn; col++) {
				if (chessPieceBoardArrays[rowNumber][col] != null) {
					totalRowCount += " " + chessPieceBoardArrays[rowNumber][col].getPieceUniqueId();
				} else {
					totalRowCount += " *";
				}
			}
			totalRowCount += "\n";
		}
		return totalRowCount;
	}
}