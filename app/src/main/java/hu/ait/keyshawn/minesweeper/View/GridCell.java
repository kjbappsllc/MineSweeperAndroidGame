package hu.ait.keyshawn.minesweeper.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;

import hu.ait.keyshawn.minesweeper.MainActivity;
import hu.ait.keyshawn.minesweeper.Model.MSModel;
import hu.ait.keyshawn.minesweeper.R;

/**
 * Created by mac on 3/3/17.
 */

public class GridCell extends BaseGridCell implements View.OnClickListener, View.OnLongClickListener{

    public GridCell(Context context, int x, int y){
        super(context);
        setOnClickListener(this);
        setOnLongClickListener(this);
        setPosition(x,y);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    @Override
    public void onClick(View v) {
        if(!MSModel.getInstance().isGameOver) {
            MSModel.getInstance().executeClick(getXPosition(), getYPosition());
        }
        if(!MSModel.getInstance().isGameStarted){
            MSModel.getInstance().isGameStarted = true;
            ((MainActivity)getContext()).startTimer();
        }
    }

    @Override
    public boolean onLongClick(View v) {
        MSModel.getInstance().flag(getXPosition(),getYPosition());
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(isFlagged()){
            drawFlag(canvas);
        }else if(isRevealed() && isBomb() && !isClicked()){
            drawBomb(canvas);
        } else {
            if( isClicked()){
                if(getValue() == -1) {
                    drawBombExplode(canvas);
                    ((MainActivity)getContext()).stopTimer();
                } else{
                    drawNumber(canvas);
                }
            } else {
                drawCellButton(canvas);
            }
        }
    }

    private void drawCellButton(Canvas canvas) {
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.mines_button);
        drawable.setBounds(0,0, getWidth(), getHeight());
        drawable.draw(canvas);
    }

    private void drawBombExplode(Canvas canvas) {
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.exploded_mine_bomb);
        drawable.setBounds(0,0, getWidth(), getHeight());
        drawable.draw(canvas);
    }

    private void drawBomb(Canvas canvas) {
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.mine_bomb);
        drawable.setBounds(0,0, getWidth(), getHeight());
        drawable.draw(canvas);
    }

    private void drawFlag(Canvas canvas){
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.flag_button);
        drawable.setBounds(0,0, getWidth(), getHeight());
        drawable.draw(canvas);
    }

    private void drawNumber(Canvas canvas){
        Drawable drawable = null;

        switch (getValue()){
            case 0:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_0);
                break;
            case 1:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.mnumbers_1);
                break;
            case 2:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.mnumbers_2);
                break;
            case 3:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.mnumbers_3);
                break;
            case 4:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.mnumbers_4);
                break;
            case 5:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.mnumbers_5);
                break;
            case 6:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.mnumbers_6);
                break;
            case 7:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.mnumbers_7);
                break;
            case 8:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.mnumbers_8);
                break;
        }

        drawable.setBounds(0,0, getWidth(), getHeight());
        drawable.draw(canvas);
    }
}
