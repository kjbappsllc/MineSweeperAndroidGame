package hu.ait.keyshawn.minesweeper.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import hu.ait.keyshawn.minesweeper.Model.MSModel;

/**
 * Created by mac on 3/3/17.
 */

public class GridGameView extends GridView {

    public GridGameView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setNumColumns(MSModel.WIDTH);
        setAdapter(new GridAdaptor());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private class GridAdaptor extends BaseAdapter {

        @Override
        public int getCount() {
            return MSModel.WIDTH * MSModel.HEIGHT;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return MSModel.getInstance().getCellAt(position);
        }
    }
}
