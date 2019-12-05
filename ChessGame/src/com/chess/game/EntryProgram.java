package com.chess.game;
import java.util.Scanner;

import com.chess.display.DisplayEngine;

public class EntryProgram {
	public static EntryProgram i;
	public ChessPiecePosition chessPiecePosition;
	DisplayEngine display;
	public boolean currentTurn;
	public boolean check;
	public boolean checkmate;
	
	
	public static void main(String[] args) {
		i = new EntryProgram();
		i.OldGameLoop();
	}
	
	
	
	EntryProgram()
	{
		chessPiecePosition = new ChessPiecePosition();
		currentTurn = false; //white is false, black is true
		boolean check = false;
		boolean checkmate = false;
		DisplayEngine display = new DisplayEngine();
		display.setVisible( true );
	}
	
	
	void OldGameLoop()
	{
		ChessBoardLocation chessBoardNewLocation;
		ChessBoardBlockPiece currentPiece;
		KingThreaten kingCheckMate;
		Scanner scanner = new Scanner(System.in);
		String currentPlayer = "Player1";
		String playerInputChoice;
		boolean isGameFinish = false;

		while (!isGameFinish) {
			try {
				System.out.println(chessPiecePosition.getManipulateChessBoard().toString());
				System.out.println(currentPlayer + "'s play the game:");
				System.out.println("1. Move a piece(M) \n2. Quit game(Q) \n3. Game reset (R)");

				playerInputChoice = scanner.nextLine();

				if (playerInputChoice.equalsIgnoreCase("M")) {

					if (currentPlayer.equals("Player1")) {
						kingCheckMate = chessPiecePosition.getPlayer1KingPiece();
					} else {
						kingCheckMate = chessPiecePosition.getPlayer2KingPiece();
					}

					ChessBoardBlockPiece checkKingCheckmate = kingCheckMate.check();
					if (checkKingCheckmate != null) {
						System.out.println("King Checkmate...!");
					}

					currentPiece = getCurrentPiecePosition(chessPiecePosition, currentPlayer);
					chessBoardNewLocation = getNewLocation();

					if (currentPiece.moveChessBoardPiece(chessBoardNewLocation)) {
						currentPlayer = (currentPlayer.equalsIgnoreCase("Player1")) ? "Player2" : "Player1";
					} else {
						System.out.println("Move was invalid, try again.");
					}
				} else if (playerInputChoice.equalsIgnoreCase("Q")) {
					isGameFinish = true;
					System.out.println("Close the game");
					continue;

				} else if (playerInputChoice.equalsIgnoreCase("R")) {
					chessPiecePosition = new ChessPiecePosition();
					System.out.println("GAME RESET...");
					System.out.println("Start new game");
					continue;
				}
			} catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
				System.out.println("Couldn't parse input.");
				e.printStackTrace();
			} catch (NullPointerException e) {
				System.out.println("NullPointerException :(, GL Debugging");
				e.printStackTrace();
			}
		}
	}
	
	

	ChessBoardBlockPiece getCurrentPiecePosition(ChessPiecePosition chessGame, String currentPlayer) {
		Scanner scanner = new Scanner(System.in);
		String input;
		ChessBoardLocation currentLocation;
		ChessBoardBlockPiece currentPiece;

		while (true) {
			System.out.println("Move piece in chess board, Enter Row, Column: ");
			input = scanner.nextLine();
			currentLocation = splitRowAndColumn(input);
			if (!ManipulateChessBoard.locationInBounds(currentLocation)) {
				System.out.println("Invalid location, Location not on board. Please try again.");
				continue;
			}
			currentPiece = chessGame.getManipulateChessBoard().getPieceAt(currentLocation);
			if (currentPiece == null) {
				System.out.println("Invalid piece selection.");
			} else if (currentPiece.getPlayerNumber().equalsIgnoreCase(currentPlayer)) {
				return currentPiece;
			} else {
				System.out.println("Invalid piece selected, Please select your Piece.");
			}
		}
	}

	ChessBoardLocation getNewLocation() {
		Scanner scanner = new Scanner(System.in);
		String rowAndColumnInput;

		ChessBoardLocation newLocation;

		while (true) {
			System.out.println("Move piece in Chess board, Enter Row, Column: ");
			rowAndColumnInput = scanner.nextLine();
			newLocation = splitRowAndColumn(rowAndColumnInput);

			if (!ManipulateChessBoard.locationInBounds(newLocation)) {
				System.out.println("Invalid location selected, Please select valid location.");
			} else {
				return newLocation;
			}
		}
	}

	ChessBoardLocation splitRowAndColumn(String rowAndColumnInput) {

		int chessRowLocation = Integer.parseInt(rowAndColumnInput.split(",")[0].trim());
		int chessColumnLocation = Integer.parseInt(rowAndColumnInput.split(",")[1].trim());

		return new ChessBoardLocation(chessRowLocation, chessColumnLocation);
	}
}