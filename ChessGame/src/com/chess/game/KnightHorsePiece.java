package com.chess.game;

import java.util.Scanner;

public class KnightHorsePiece extends ChessBoardBlockPiece {

	public KnightHorsePiece(String owner, ChessBoardLocation initialLocation, ChessPiecePosition game) {
		super(owner, initialLocation, game);
		if (owner.equalsIgnoreCase("White")) {
			pieceUniqueId = '\u2658';
		} else if (owner.equalsIgnoreCase("Black")) {
			pieceUniqueId = '\u265E';
		}
	}

	@Override
	public boolean moveChessBoardPiece(ChessBoardLocation location) {
		if (Math.abs(currentChessBoardLocation.getChessBoardRowIndex() - location.getChessBoardRowIndex()) == 2 && Math
				.abs(currentChessBoardLocation.getChessBoardColumnIndex() - location.getChessBoardColumnIndex()) == 1) {

			return super.moveChessBoardPiece(location);
		} else if (Math.abs(currentChessBoardLocation.getChessBoardRowIndex() - location.getChessBoardRowIndex()) == 1
				&& Math.abs(currentChessBoardLocation.getChessBoardColumnIndex()
						- location.getChessBoardColumnIndex()) == 2) {

			return super.moveChessBoardPiece(location);
		}
		return false;
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
	@Override
	protected void updateBoardPieceLocation() {
		int[] rowMoves = { -2, -1, 1, 2, -2, -1, 1, 2 };
		int[] colMoves = { 1, 2, 2, 1, -1, -2, -2, -1 };

		threateningLocations.clear();
		for (int i = 0; i < 8; i++) {
			ChessBoardLocation location = new ChessBoardLocation(rowMoves[i], colMoves[i]);
			if (ManipulateChessBoard.locationInBounds(location)) {
				ChessBoardBlockPiece piece = gameChessPiecePosition.getManipulateChessBoard().getPieceAt(location);

				if (piece != null && !piece.getPlayerNumber().equals(playerNumber)) {

					threateningLocations.add(location);
				}
			}
		}
	}
}
