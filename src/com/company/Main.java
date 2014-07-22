package com.company;

import java.util.ArrayList;

public class Main {

    private static final int numArguments = 2;

    public static void main(String[] args) {
	    //check for input
        if(args.length != numArguments){
            System.out.println("Incorrect arguments");
            return;
        }

	    //initialise program
        KnightBoard board = new KnightBoard();
        PathFinder pathfinder = new PathFinder(board);

        //run dijkstra
        ArrayList<String> path = pathfinder.dijkstra(args[0], args[1]);

        //print result
        for(String s : path){
            if(path.size()-1 == path.indexOf(s)){
                System.out.print(s + "\n");
            } else {
                System.out.print(s + " ");
            }
        }

    }
}
