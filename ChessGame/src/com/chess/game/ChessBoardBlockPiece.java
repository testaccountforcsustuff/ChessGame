package com.chess.game;

import java.util.ArrayList;

public abstract class ChessBoardBlockPiece {

	protected ChessPiecePosition gameChessPiecePosition;
	protected String playerNumber;
	protected ChessBoardLocation currentChessBoardLocation;
	protected char pieceUniqueId;
	protected ArrayList<ChessBoardLocation> threateningLocations;

	protected abstract void updateBoardPieceLocation();

	public ChessBoardBlockPiece(String owner, ChessBoardLocation initialLocation, ChessPiecePosition game) {
		this.playerNumber = owner;
		currentChessBoardLocation = null;
		gameChessPiecePosition = game;
		threateningLocations = new ArrayList<>();
		gameChessPiecePosition.getManipulateChessBoard().piecePlaceAtBoardLocation(this, initialLocation);
	}

	protected boolean checkIndexOfBoard(ChessBoardLocation startLocation, ChessBoardLocation endLocation) {
// Vertical
		if (startLocation.getChessBoardColumnIndex() == endLocation.getChessBoardColumnIndex()) {
			int startrowIndex = startLocation.getChessBoardRowIndex();
			int startEndIndex = endLocation.getChessBoardRowIndex();
			int startAndEnd = startrowIndex - startEndIndex;

			int verticalIndex = (startAndEnd < 0) ? 1 : -1;
			for (int row = startLocation.getChessBoardRowIndex() + verticalIndex; row < endLocation
					.getChessBoardRowIndex(); row += verticalIndex) {
				if (gameChessPiecePosition.getManipulateChessBoard().checkPiaceAtBoardLocation(row,
						startLocation.getChessBoardColumnIndex())) {
					return false;
				}
			}
			return true;
		}

// Horizontal
		if (startLocation.getChessBoardRowIndex() == endLocation.getChessBoardRowIndex()) {
			int startrowIndex = startLocation.getChessBoardColumnIndex();
			int startEndIndex = endLocation.getChessBoardColumnIndex();
			int startAndEnd = startrowIndex - startEndIndex;

			int horizontalIndex = (startAndEnd < 0) ? 1 : -1;
			for (int col = startLocation.getChessBoardColumnIndex() + horizontalIndex; col < endLocation
					.getChessBoardColumnIndex(); col += horizontalIndex) {
				if (gameChessPiecePosition.getManipulateChessBoard()
						.checkPiaceAtBoardLocation(startLocation.getChessBoardRowIndex(), col)) {
					return false;
				}
			}
			return true;
		}

// Diagonal
		if (startLocation.getChessBoardColumnIndex()
				- endLocation.getChessBoardColumnIndex() == startLocation.getChessBoardRowIndex()
						- endLocation.getChessBoardRowIndex()) {

			int startrowIndex = startLocation.getChessBoardRowIndex();
			int startEndIndex = endLocation.getChessBoardRowIndex();
			int startAndEnd = startrowIndex - startEndIndex;

			int one = (startAndEnd < 0) ? 1 : -1;
			for (int inc = one; Math.abs(inc) < Math
					.abs(startLocation.getChessBoardRowIndex() - endLocation.getChessBoardRowIndex()); inc += one) {
				if (gameChessPiecePosition.getManipulateChessBoard().checkPiaceAtBoardLocation(
						startLocation.getChessBoardRowIndex() + inc, startLocation.getChessBoardColumnIndex() + inc)) {
					return false;
				}
			}
			return true;
		} else if (startLocation.getChessBoardColumnIndex()
				- endLocation.getChessBoardColumnIndex() * -1 == startLocation.getChessBoardRowIndex()
						- endLocation.getChessBoardColumnIndex()) {
			int startrowIndex = startLocation.getChessBoardRowIndex();
			int startEndIndex = endLocation.getChessBoardRowIndex();
			int startAndEnd = startrowIndex - startEndIndex;

			int one = (startAndEnd < 0) ? 1 : -1;
			int columnByOne = one * -1;

			for (int incByOne = one; Math.abs(incByOne) < Math.abs(
					startLocation.getChessBoardRowIndex() - endLocation.getChessBoardRowIndex()); incByOne += one) {
				if (gameChessPiecePosition.getManipulateChessBoard().checkPiaceAtBoardLocation(
						startLocation.getChessBoardRowIndex() + incByOne,
						startLocation.getChessBoardColumnIndex() + (incByOne * columnByOne))) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	protected void updateBoardPieceVertical(int updateVertical) {
		ChessBoardLocation boardLocation = new ChessBoardLocation(
				currentChessBoardLocation.getChessBoardRowIndex() + updateVertical,
				currentChessBoardLocation.getChessBoardColumnIndex());

		while (ManipulateChessBoard.locationInBounds(boardLocation)) {
			ChessBoardBlockPiece verticalPiece = gameChessPiecePosition.getManipulateChessBoard()
					.getPieceAt(boardLocation);

			if (verticalPiece != null) {
				if (!verticalPiece.getPlayerNumber().equalsIgnoreCase(playerNumber)) {
					threateningLocations.add(boardLocation);
					return;
				} else if (!currentChessBoardLocation.equals(boardLocation)) {
					threateningLocations
							.add(new ChessBoardLocation(boardLocation.getChessBoardRowIndex() - updateVertical,
									boardLocation.getChessBoardColumnIndex()));
					return;
				}
			} else {
				boardLocation = new ChessBoardLocation(boardLocation.getChessBoardRowIndex() + updateVertical,
						boardLocation.getChessBoardColumnIndex());
			}
		}
	}

	protected void updateBoardPieceHorizontal(int one) {
		ChessBoardLocation horizontalLocation = new ChessBoardLocation(
				currentChessBoardLocation.getChessBoardRowIndex(),
				currentChessBoardLocation.getChessBoardColumnIndex() + one);

		while (ManipulateChessBoard.locationInBounds(horizontalLocation)) {
			ChessBoardBlockPiece horizontalPiece = gameChessPiecePosition.getManipulateChessBoard()
					.getPieceAt(horizontalLocation);
			if (horizontalPiece != null) {
				if (!horizontalPiece.getPlayerNumber().equalsIgnoreCase(playerNumber)) {
					threateningLocations.add(horizontalLocation);
					return;
				} else if (!currentChessBoardLocation.equals(horizontalLocation)) {
					threateningLocations.add(new ChessBoardLocation(horizontalLocation.getChessBoardRowIndex(),
							horizontalLocation.getChessBoardColumnIndex() - one));
					return;
				}
			} else {
				horizontalLocation = new ChessBoardLocation(horizontalLocation.getChessBoardRowIndex(),
						horizontalLocation.getChessBoardColumnIndex() + one);
			}
		}
	}

	protected void updateDiagonalIndex(int diagonalRowOne, int diagonalColumn) {
		ChessBoardLocation diagonalLocation = new ChessBoardLocation(
				currentChessBoardLocation.getChessBoardRowIndex() + diagonalRowOne,
				currentChessBoardLocation.getChessBoardColumnIndex() + diagonalColumn);

		while (ManipulateChessBoard.locationInBounds(diagonalLocation)) {
			ChessBoardBlockPiece boardPiece = gameChessPiecePosition.getManipulateChessBoard()
					.getPieceAt(diagonalLocation);

			if (boardPiece != null) {
				if (!boardPiece.getPlayerNumber().equalsIgnoreCase(playerNumber)) {
					threateningLocations.add(diagonalLocation);
					return;
				} else if (!currentChessBoardLocation.equals(diagonalLocation)) {
					threateningLocations
							.add(new ChessBoardLocation(diagonalLocation.getChessBoardRowIndex() - diagonalRowOne,
									diagonalLocation.getChessBoardColumnIndex() - diagonalColumn));
					return;
				}
			} else {
				diagonalLocation = new ChessBoardLocation(diagonalLocation.getChessBoardRowIndex() + diagonalRowOne,
						diagonalLocation.getChessBoardColumnIndex() + diagonalColumn);
			}
		}
	}

	public boolean moveChessBoardPiece(ChessBoardLocation newLocation) {
		ManipulateChessBoard manipulateBoard = gameChessPiecePosition.getManipulateChessBoard();
		ChessBoardBlockPiece previousBoardPiece = manipulateBoard.getPieceAt(newLocation);

		if (previousBoardPiece == null || previousBoardPiece.getPlayerNumber() != playerNumber) {

			manipulateBoard.piecePlaceAtBoardLocation(this, newLocation);
			return true;
		}
		return false;
	}

	public ChessBoardLocation getCurrentChessBoardLocation() {
		return currentChessBoardLocation;
	}

	public void setCurrentChessBoardLocation(ChessBoardLocation location) {
		currentChessBoardLocation = location;
	}

	public String getPlayerNumber() {
		return playerNumber;
	}

	public void setPlayerNumber(String playerNumber) {
		this.playerNumber = playerNumber;
	}

	public void setPieceUniqueId(char pieceUniqueId) {
		this.pieceUniqueId = pieceUniqueId;
	}

	public char getPieceUniqueId() {
		return pieceUniqueId;
	}

	public ArrayList<ChessBoardLocation> getThreateningLocations() {
		return threateningLocations;
	}
}