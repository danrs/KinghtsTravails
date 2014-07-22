package com.company;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Created by Daniel on 21/07/2014.
 *
 * This class is used for searching the chessboard for paths.
 */
public class PathFinder {
    private Board board;

    public PathFinder(Board board){
        this.board = board;
    }

    /**
     * Generates an arrayList describing the shortest path between two points using Dijkstra
     * @param start
     * @param end
     * @return ArrayList of tile code Strings describing the path, or null if no path
     */
    public ArrayList<String> dijkstra(String start, String end){
        PriorityQueue<State> openList = new PriorityQueue<State>();
        ArrayList<State> closedList = new ArrayList<State>();

        State current = new State(this.board, start);

        openList.add(current);
        while(!openList.isEmpty()){
            current = openList.poll();
            closedList.add(current);
            if(current.currentTile().equals(end)){
                //success!
                return current.getPath();
            }
            for(State s : current.getSuccessors()){
                //ignore if successor is on closed list
                if(s != null && !closedList.contains(s)){
                    System.out.println("examining " + current.currentTile());
                    if(openList.contains(s)){
                        //check if this path is better and change if so
                        for(State oldSuccessor : openList){
                            if(oldSuccessor.equals(current) && oldSuccessor.getLength() > current.getLength()){
                                openList.remove(oldSuccessor);
                                openList.add(current);
                                break;
                            }
                        }
                    } else {
                        //add to open list
                        openList.add(s);
                    }
                }
            }
        }

        return null;    //search failed (should not occur on a normal empty chessboard)
    }
}
