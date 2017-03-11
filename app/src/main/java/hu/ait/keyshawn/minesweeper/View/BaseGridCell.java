package hu.ait.keyshawn.minesweeper.View;

import android.content.Context;
import android.view.View;

import hu.ait.keyshawn.minesweeper.MainActivity;
import hu.ait.keyshawn.minesweeper.Model.MSModel;

/**
 * Created by mac on 3/3/17.
 */

public abstract class BaseGridCell extends View {


    private int cellValue;
    private boolean isBomb;
    private boolean isRevealed;
    private boolean isClicked;
    private boolean isFlagged;

    private int x;
    private int y;

    public BaseGridCell(Context context){
        super(context);
    }

    public int getValue() {
        return cellValue;
    }

    public void setValue(int value) {
        isBomb = false;
        isRevealed = false;
        isClicked = false;
        isFlagged = false;

        if(value == -1){
            isBomb = true;
        }
        this.cellValue = value;
        invalidate();
    }

    public void setPosition(int x, int y) {

        this.x = x;
        this.y = y;
        invalidate();
    }

    public int getXPosition() {
        return x;
    }

    public int getYPosition() {
        return y;
    }

    public boolean isClicked() {
        return isClicked;
    }

    public void setAsClicked() {
        isClicked = true;
        isRevealed = true;

        invalidate();
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public void switchFlagged() {
        short num;
        if(!isFlagged){
            isFlagged = true;
            num = -1;
        }
        else{
            isFlagged = false;
            num = 1;
        }
        invalidate();

        ((MainActivity) getContext()).updateLabel(num);
    }

    public boolean isBomb() {
        return isBomb;
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    public void switchRevealed() {

        isRevealed = true;
        invalidate();
    }
}
