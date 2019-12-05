package com.chess.game;

public class ChessBoardLocation {

	int chessBoardRowIndex;
	int chessBoardColumnIndex;

	public ChessBoardLocation(int row, int col) {
		this.chessBoardRowIndex = row;
		this.chessBoardColumnIndex = col;
	}

	@Override
	public boolean equals(Object superClassObject) {
		if (superClassObject != null && superClassObject instanceof ChessBoardLocation) {
			ChessBoardLocation location = (ChessBoardLocation) superClassObject;
			return (chessBoardRowIndex == location.getChessBoardRowIndex()
					&& chessBoardColumnIndex == location.getChessBoardColumnIndex());
		}
		return false;
	}

	public int getChessBoardRowIndex() {
		return chessBoardRowIndex;
	}

	public void setChessBoardRowIndex(int chessBoardRowIndex) {
		this.chessBoardRowIndex = chessBoardRowIndex;
	}

	public int getChessBoardColumnIndex() {
		return chessBoardColumnIndex;
	}

	public void setChessBoardColumnIndex(int chessBoardColumnIndex) {
		this.chessBoardColumnIndex = chessBoardColumnIndex;
	}

}
