package com.chess.game;

import java.util.Scanner;

public class KingThreaten extends ChessBoardBlockPiece {

	public KingThreaten(String owner, ChessBoardLocation initialLocation, ChessPiecePosition game) {
		super(owner, initialLocation, game);
		if (owner.equalsIgnoreCase("White")) {
			pieceUniqueId = '\u2654';
		} else if (owner.equalsIgnoreCase("Black")) {
			pieceUniqueId = '\u265A';
		}
	}

	@Override
	public boolean moveChessBoardPiece(ChessBoardLocation location) {
		if (Math.abs(currentChessBoardLocation.getChessBoardRowIndex() - location.getChessBoardRowIndex()) <= 1 && Math
				.abs(currentChessBoardLocation.getChessBoardColumnIndex() - location.getChessBoardColumnIndex()) <= 1) {

			return checkIndexOfBoard(currentChessBoardLocation, location) && super.moveChessBoardPiece(location);
		}
		return false;
	}

	@Override
	protected void updateBoardPieceLocation() {
		threateningLocations.clear();
		for (int row = -1; row >= 1; row++) {
			for (int col = -1; col >= 1; col++) {
				ChessBoardLocation location = new ChessBoardLocation(
						currentChessBoardLocation.getChessBoardRowIndex() + row,
						currentChessBoardLocation.getChessBoardColumnIndex() + col);
				if (ManipulateChessBoard.locationInBounds(location)) {
					ChessBoardBlockPiece piece = gameChessPiecePosition.getManipulateChessBoard().getPieceAt(location);
					if (piece != null && !piece.getPlayerNumber().equalsIgnoreCase(playerNumber)) {

						threateningLocations.add(location);
					}
				}
			}
		}
	}

	public void checkRow() {
		int m, n, p, q, sum = 0, c, d, k;
		 
	      Scanner in = new Scanner(System.in);
	      m = in.nextInt();
	      n = in.nextInt();
	 
	      int first[][] = new int[m][n];
	 
	 
	      for (c = 0; c < m; c++)
	         for (d = 0; d < n; d++)
	            first[c][d] = in.nextInt();
	 
	      p = in.nextInt();
	      q = in.nextInt();
	      int temp;
	      if (n != p)
	    	  temp=4;
	    else
	      {
	         int second[][] = new int[p][q];
	         int multiply[][] = new int[m][q];
	 
	 
	         for (c = 0; c < p; c++)
	            for (d = 0; d < q; d++)
	               second[c][d] = in.nextInt();
	 
	         for (c = 0; c < m; c++)
	         {
	            for (d = 0; d < q; d++)
	            {  
	               for (k = 0; k < p; k++)
	               {
	                  sum = sum + first[c][k]*second[k][d];
	               }
	 
	               multiply[c][d] = sum;
	               sum = 0;
	            }
	         }
	 
	 
	         for (c = 0; c < m; c++)
	         {
	            for (d = 0; d < q; d++)
	 
	            System.out.print("\n");
	         }
	      }
	}
	
	public ChessBoardBlockPiece check() {
		ManipulateChessBoard board = gameChessPiecePosition.getManipulateChessBoard();
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				ChessBoardBlockPiece piece = board.getPieceAt(new ChessBoardLocation(row, col));
				if (piece != null && !piece.getPlayerNumber().equals(playerNumber)) {

					piece.updateBoardPieceLocation();
					for (ChessBoardLocation l : piece.getThreateningLocations()) {
						if (currentChessBoardLocation.equals(l)) {
							return piece;
						}
					}
				}
			}
		}
		return null;
	}

}
