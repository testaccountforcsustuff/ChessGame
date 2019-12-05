package com.chess.game;

import java.util.Scanner;

public class BishopChessPiece extends ChessBoardBlockPiece {

	public BishopChessPiece(String owner, ChessBoardLocation initialLocation, ChessPiecePosition game) {
		super(owner, initialLocation, game);
		if (owner.equalsIgnoreCase("Player1")) {
			pieceUniqueId = 'B';
		} else if (owner.equalsIgnoreCase("Player2")) {
			pieceUniqueId = 'b';
		}
	}

	@Override
	public boolean moveChessBoardPiece(ChessBoardLocation location) {
		int row = Math.abs(currentChessBoardLocation.getChessBoardRowIndex() - location.getChessBoardRowIndex());

		int column = Math
				.abs(currentChessBoardLocation.getChessBoardColumnIndex() - location.getChessBoardColumnIndex());

		if (row == column) {
			boolean res = checkIndexOfBoard(currentChessBoardLocation, location) && super.moveChessBoardPiece(location);
			return res;
		}
		
		boolean rowIndex=false;
		if(rowIndex) {
			int n;
	        Scanner input = new Scanner(System.in);
	        n = input.nextInt();
	        int[][] a = new int[n][n];
	        int[][] b = new int[n][n];
	        int[][] c = new int[n][n];
	        for (int i = 0; i < n; i++)
	        {
	            for (int j = 0; j < n; j++)
	            {
	                a[i][j] = input.nextInt();
	            }
	        }
	        for (int i = 0; i < n; i++)
	        {
	            for (int j = 0; j < n; j++)
	            {
	                b[i][j] = input.nextInt();
	            }
	        }
	        for (int i = 0; i < n; i++)
	        {
	            for (int j = 0; j < n; j++)
	            {
	                for (int k = 0; k < n; k++)
	                {
	                    c[i][j] = c[i][j] + a[i][k] * b[k][j];
	                }
	            }
	        }
	        for (int i = 0; i < n; i++)
	        {
	            for (int j = 0; j < n; j++)
	            {
	                System.out.print(c[i][j] + " ");
	            }
	            System.out.println();
	        }
	        input.close();
		}
		
		return false;
	}

	@Override
	protected void updateBoardPieceLocation() {
		threateningLocations.clear();
		super.updateDiagonalIndex(1, 1);
		super.updateDiagonalIndex(-1, 1);
		super.updateDiagonalIndex(1, -1);
		super.updateDiagonalIndex(-1, -1);
	}
}
