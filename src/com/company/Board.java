package com.company;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Daniel on 21/07/2014.
 *
 * This class describes a chess board and the valid neighbour tiles for a single piece.
 * Extend this class to implement generateNeighbours for the behaviour of your chosen piece.
 */
public abstract class Board {
    protected HashMap<String, ArrayList<String>> tiles;
    protected final int boardWidth = 8;
    private final String validChars = "ABCDEFGH12345678";


    /**
     * Constructor
     * Populates the tiles on the board and adds valid neighbours for each tile
     */
    public Board(){
        //populate tiles
        tiles = new HashMap<String, ArrayList<String>>();
        String current;
        for(int i=0; i<boardWidth; i++){
            for(int j = 0; j<boardWidth; j++){
                current = tileCode(i,j);
                //create tile
                tiles.put(current, new ArrayList<String>());
                //add connections
                tiles.get(current).addAll(generateNeighbours(current));
            }
        }
    }

    /**
     * Generates all valid neighbours for a piece on the current tile. This is called by the constructor and not needed
     * after construction - getNeighbours() should be used instead.
     * @param tile - String describing the tile to have neighbours generated
     * @return ArrayList of Strings containing tile codes
     * @precondition isValid(tile) == true
     */
    protected abstract ArrayList<String> generateNeighbours(String tile);

    /**
     * Returns the list of all valid neighbours (or moves) for a piece at tile
     * @param tile - String describing the tile in question
     * @return ArrayList of Strings describing tile codes for valid moves
     * @precondition isValid(tile) == true
     */
    public ArrayList<String> getNeighbours(String tile){
        return tiles.get(tile);
    }

    /**
     * Determine if a tile lies on the chessboard
     * @param tile - String describing tile being examined
     * @return true if tile is on board, else false
     */
    public boolean isValid(String tile){
        if (tile == null){
            return false;
        }

        return (tile.length() == 2 && validChars.indexOf(tile.charAt(0)) != -1 && validChars.indexOf(tile.charAt(1)) != -1);

    }

    /**
     * Returns a string describing the tile location
     * @param row - int describing tile row
     * @param column - int describing tile column
     * @return String describing tile code
     * @precondition row > 0 && row < boardWidth
     * @precondition column >= 0 && column < boardWidth
     */
    protected String tileCode(int row, int column){
        //use ascii value to generate character
        return ((char) (column + 'A')) + Integer.toString(row + 1);
    }
}