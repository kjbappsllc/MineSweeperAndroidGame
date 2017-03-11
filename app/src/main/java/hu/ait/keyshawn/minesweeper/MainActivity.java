package hu.ait.keyshawn.minesweeper;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.TextView;

import hu.ait.keyshawn.minesweeper.Model.MSModel;

public class MainActivity extends AppCompatActivity {

    private short bombCount = MSModel.NUM_BOMBS;
    private Chronometer chronTimer;
    private TextView tvBombCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout layoutRoot = (LinearLayout) findViewById(R.id.activity_main);

        MSModel.getInstance().initializeGame(this, layoutRoot);

        Button fbButton = (Button) findViewById(R.id.fbReset);
        tvBombCount = (TextView) findViewById(R.id.tvBombCount);
        chronTimer = (Chronometer) findViewById(R.id.chronTimer);

        tvBombCount.setText(Short.toString(bombCount));

        fbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MSModel.getInstance().resetGame();
                bombCount = MSModel.NUM_BOMBS;
                tvBombCount.setText(Short.toString(bombCount));
                chronTimer.setBase(SystemClock.elapsedRealtime());

            }
        });
    }

    public void startTimer(){
        chronTimer.setBase(SystemClock.elapsedRealtime());
        chronTimer.start();
    }

    public void stopTimer(){
        chronTimer.stop();
    }

    public void updateLabel(short num){
        bombCount+=num;

        tvBombCount.setText(Short.toString(bombCount));
    }
}
