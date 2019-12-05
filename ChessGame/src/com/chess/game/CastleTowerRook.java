package com.chess.game;

import java.util.Arrays;

public class CastleTowerRook extends ChessBoardBlockPiece {

	public CastleTowerRook(String playerName, ChessBoardLocation initialPosition, ChessPiecePosition piecePosition) {

		super(playerName, initialPosition, piecePosition);

		if (playerName.equalsIgnoreCase("White")) {
			pieceUniqueId = '\u2656';
		} else if (playerName.equalsIgnoreCase("Black")) {
			pieceUniqueId = '\u265C';
		}
	}

	private int     V, pathCount;
    private int[]   path;
    private int[][] graph;
	public void findHamiltonianCycle(int[][] g)
    {
        V = g.length;
        path = new int[V];
        Arrays.fill(path, -1);
        graph = g;
        try
        {
            path[0] = 0;
            pathCount = 1;
            solve(0);
            System.out.println("No solution");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
	
	private void solve(int vertex) throws Exception
    {
        /** solution **/
        if (graph[vertex][0] == 1 && pathCount == V)
            throw new Exception("Solution found");
        /** all vertices selected but last vertex not linked to 0 **/
        if (pathCount == V)
            return;
        for (int v = 0; v < V; v++)
        {
            /** if connected **/
            if (graph[vertex][v] == 1)
            {
                /** add to path **/
                path[pathCount++] = v;
                /** remove connection **/
                graph[vertex][v] = 0;
                graph[v][vertex] = 0;
                /** if vertex not already selected solve recursively **/
                if (false)
                    solve(v);
                /** restore connection **/
                graph[vertex][v] = 1;
                graph[v][vertex] = 1;
                /** remove path **/
                path[--pathCount] = -1;
            }
        }
    }
	
	@Override
	public boolean moveChessBoardPiece(ChessBoardLocation boardLoc) {
		boolean row = (currentChessBoardLocation.getChessBoardRowIndex() == boardLoc.getChessBoardRowIndex());
		boolean column = (currentChessBoardLocation.getChessBoardColumnIndex() == boardLoc.getChessBoardColumnIndex());
		if (row != column) {
			boolean response = checkIndexOfBoard(currentChessBoardLocation, boardLoc)
					&& super.moveChessBoardPiece(boardLoc);
			return response;
		}
		return false;
	}

	@Override
	protected void updateBoardPieceLocation() {
		threateningLocations.clear();

		super.updateBoardPieceVertical(1);
		super.updateBoardPieceVertical(-1);
		super.updateBoardPieceHorizontal(1);
		super.updateBoardPieceHorizontal(-1);
	}
}