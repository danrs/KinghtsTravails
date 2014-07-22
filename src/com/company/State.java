package com.company;

import java.util.*;

/**
 * Created by Daniel on 21/07/2014.
 *
 * Describes a state in the search on the board, necessary for searching out the least number of moves. A state has both
 * a current location and a list of previous locations, known as the path. The current location is the first entry
 * in the path list.
 */
public class State implements Comparable<State> {
    private ArrayDeque<String> path;
    private Board board;

    public State(Board board, String tile){
        path = new ArrayDeque<String>(10);
        path.addFirst(tile);
        this.board = board;
    }

    public State(Board board, String tile, ArrayDeque<String> path){
        this.path = new ArrayDeque<String>(path);
        this.path.addFirst(tile);
        this.board = board;
    }

    /**
     * Generate a new successor state with the path of the current state and one additional tile added.
     * @param tile
     * @return State succeeding this one
     */
    private State successor(String tile){
        return new State(this.board, tile, this.path);
    }

    /**
     * Generate all successors of current state. Successors are squares which can be reached from the current tile
     * and are not in the path
     * @return ArrayList containing successor states
     */
    public ArrayList<State> getSuccessors(){
        ArrayList<State> successors = new ArrayList<State>();
        //get a list of valid moves from the board, and make successors for all those not in the path already
        for(String s : board.getNeighbours(path.getFirst())){
            if(!path.contains(s)){
                successors.add(successor(s));
            }
        }
        return successors;
    }

    @Override
    /**
     * States are compared by path length
     */
    public int compareTo(State s){
        if(this.getLength() > s.getLength()){
            return 1;
        } else if(this.getLength() < s.getLength()){
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    /**
     * Two states are considered equal if they have the same current square, as they are thus equally close to any other square on the board.
     * Note that the states are not necessarily identical, as the path is not considered.
     */
    public boolean equals(Object o){
        if(!(o instanceof State)){
            return false;
        }
        State s = (State) o;
        return (s.currentTile().equals(this.currentTile()));
    }

    /**
     * Get the current tile of a state
     * @return String describing current tile
     */
    public String currentTile(){
        return path.getFirst();
    }

    /**
     * Get a list of all tiles travelled by this state in order (ending with the most recent)
     * The first (start) tile is not included
     * @return ArrayList of Strings describing tile codes
     */
    public ArrayList<String> getPath(){
        ArrayList<String> tiles = new ArrayList<String>();
        for(String s : path){
            tiles.add(s);
        }
        Collections.reverse(tiles); //check if this line makes or breaks this method
        tiles.remove(0);    //remove start tile
        return tiles;
    }

    /**
     * Determine the number of steps taken by this state
     * @return int describing number of steps
     */
    public int getLength(){
        return path.size();
    }
}
