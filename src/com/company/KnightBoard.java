package com.company;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Daniel on 21/07/2014.
 *
 * Describes the chess board with valid tiles for a KNIGHT.
 */
public class KnightBoard extends Board {
    private final int knightMoves = 8;

    /**
     * Generates all valid neighbours for a knight on the current tile
     * @param tile
     * @return ArrayList of Strings containing tile codes
     * @precondition isValid(tile) == true
     */
    public ArrayList<String> generateNeighbours(String tile) {
        ArrayList<String> neighbouringTiles = new ArrayList<String>();
        String current;
        int row = (int) (tile.charAt(0)-'A');
        int column = (int) (tile.charAt(1)-'1');
        int swap;

        int rowOffset = 1;
        int colOffset = 2;

        for(int i=0; i<knightMoves; i++) {
            //generate new location
            current = tileCode(column + colOffset, row + rowOffset);

            //add tile to list if valid
            if (isValid(current)) {
                neighbouringTiles.add(current);
            }


            //rotate to next location
            if (i%2 == 1) {
                swap = rowOffset;
                rowOffset = colOffset;
                colOffset = swap;
            } else {
                rowOffset = -rowOffset;
            }
        }

        return neighbouringTiles;
    }

}