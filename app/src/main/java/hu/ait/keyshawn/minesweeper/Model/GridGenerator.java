package hu.ait.keyshawn.minesweeper.Model;

import java.util.Random;

/**
 * Created by mac on 3/2/17.
 */

public class GridGenerator {

    public static short[][] generateGrid(short numBombs, short width, short height){

        short[][] grid = new short[width][height];

        while(numBombs > 0) {
            int x = new Random().nextInt(width);
            int y = new Random().nextInt(height);

            if(grid[x][y] != -1) {
                grid[x][y] = -1;
                numBombs--;
            }
        }

        grid = calcuateNearbyCellNumbers(grid,width,height);
        return grid;
    }

    private static short[][] calcuateNearbyCellNumbers(short[][]grid, int width, int height) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                grid[x][y] = getNearbyCellNumber(grid, x, y, width, height);
            }
        }

        return grid;
    }

    //Sorry about number of params
    private static short getNearbyCellNumber(short [][]grid, int x, int y, int width, int height) {
        if(grid[x][y] == -1){
            return -1;
        }

        short count = 0;

        if(isBombAt(grid,x-1 ,y ,width,height)) count++;
        if(isBombAt(grid,x  ,y+1  ,width,height)) count++;
        if(isBombAt(grid,x  ,y-1  ,width,height)) count++;
        if(isBombAt(grid,x+1  ,y  ,width,height)) count++;
        if(isBombAt(grid,x+1  ,y+1 ,width,height)) count++;
        if(isBombAt(grid,x-1  ,y-1    ,width,height)) count++;
        if(isBombAt(grid,x+1  ,y-1    ,width,height)) count++;
        if(isBombAt(grid,x-1  ,y+1    ,width,height)) count++;

        return count;

    }

    private static boolean isBombAt(short[][]grid, int x, int y, int width, int height) {
        if(x >= 0 && y >= 0 && x < width && y < height ) {
            if (grid[x][y] == -1){
                return true;
            }
        }
        return false;
    }
}
