package hu.ait.keyshawn.minesweeper.Model;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import hu.ait.keyshawn.minesweeper.MainActivity;
import hu.ait.keyshawn.minesweeper.View.GridCell;

import static android.support.design.R.id.snackbar_text;

/**
 * Created by mac on 3/2/17.
 */

public class MSModel {

    private static MSModel instance = null;
    private Context context;
    private LinearLayout layoutRoot;
    public static final short NUM_BOMBS = 12;
    public static final short WIDTH = 10;
    public static final short HEIGHT = 10;
    public boolean isGameOver;
    public boolean isGameStarted;

    private GridCell[][] mineSweeperCells = new GridCell[WIDTH][HEIGHT];

    public static MSModel getInstance() {
        if (instance == null) {
            instance = new MSModel();
        }

        return instance;
    }

    private MSModel() { }

    public void initializeGame(Context context, LinearLayout layoutRoot) {
        this.context = context;
        isGameOver = false;
        isGameStarted = false;
        this.layoutRoot = layoutRoot;
        short [][] generatedGrid = GridGenerator.generateGrid(NUM_BOMBS,WIDTH,HEIGHT);
        setCells(generatedGrid);
    }
    
    private void setCells(short[][]grid){
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                if(mineSweeperCells[x][y] == null) {
                    mineSweeperCells[x][y] = new GridCell(context,x,y);
                }
                mineSweeperCells[x][y].setValue(grid[x][y]);
            }
        }
    }

    public GridCell getCellAt(int position){
        int x = position % WIDTH;
        int y = position / WIDTH;
        return mineSweeperCells[x][y];
    }

    public GridCell CellAt(int x, int y){
        return mineSweeperCells[x][y];
    }

    public void executeClick(int xPos, int yPos) {
        if( xPos >= 0 && yPos >= 0 && xPos < WIDTH && yPos < HEIGHT && !CellAt(xPos,yPos).isClicked() ){
            if(!CellAt(xPos,yPos).isFlagged()) {
                CellAt(xPos, yPos).setAsClicked();
            }

            if(CellAt(xPos,yPos).getValue() == 0){
                for (int x = -1; x <= 1; x++) {
                    for(int y = -1; y <= 1; y++){
                        if(x != y){
                            executeClick(xPos+x, yPos+y);
                        }
                    }

                }
            }
            if(CellAt(xPos,yPos).isBomb() && !CellAt(xPos,yPos).isFlagged()){
                onGameLost();
                isGameOver = true;
            }

        }
        checkGameWon();
    }

    private void checkGameWon() {
        short bombNotFound = NUM_BOMBS;
        int notRevealed = WIDTH * HEIGHT;

        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                if(CellAt(x,y).isRevealed() || CellAt(x,y).isFlagged()){
                    notRevealed --;
                }

                if(CellAt(x,y).isFlagged() && CellAt(x,y).isBomb()){
                    bombNotFound--;
                }
            }
        }

        if(bombNotFound == 0 && notRevealed == 0){
            onGameWon();
            isGameOver = true;
        }
    }


    private void onGameLost() {
        showEndGameMessage("YOU LOST THE GAME", Color.RED);

        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                CellAt(x,y).switchRevealed();
            }
        }
    }

    private void onGameWon(){
        showEndGameMessage("YOU WON THE GAME", Color.GREEN);
    }

    private void showEndGameMessage(String message, int color){
        Snackbar snack = Snackbar.make(layoutRoot, message, Snackbar.LENGTH_LONG);
        View view = snack.getView();
        TextView tv = (TextView) view.findViewById(snackbar_text);
        tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tv.setTextColor(color);
        tv.setTextSize(18);
        snack.show();
    }

    public void flag(int xPos, int yPos) {
        if(!CellAt(xPos,yPos).isClicked()) {
            CellAt(xPos, yPos).switchFlagged();
            checkGameWon();
        }
    }

    public void resetGame(){
        initializeGame(context,layoutRoot);
    }
}
