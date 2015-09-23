package com.business.hien.billiardsboard;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.ProgressBar;

public class BilliardsBoardActivity extends Activity {

    NumberPicker numPicker1 = null;
    NumberPicker numPicker2 = null;
    protected static final int TIMER_RUNTIME = 10000;
    protected boolean mbActive;
    protected ProgressBar progressBar1;
    protected ProgressBar progressBar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_billiards_board);
        //Number Picker
        numPicker1 = (NumberPicker)findViewById(R.id.numberPicker1);
        numPicker1.setMaxValue(999);
        numPicker1.setMinValue(0);
        numPicker1.setWrapSelectorWheel(false);

        numPicker2 = (NumberPicker)findViewById(R.id.numberPicker2);
        numPicker2.setMaxValue(999);
        numPicker2.setMinValue(0);
        numPicker2.setWrapSelectorWheel(false);

        progressBar1 = (ProgressBar)findViewById(R.id.progressBar1);
    }

    public void updateProgress(final int timePassed){
        if(null != progressBar1){
            final int progress = progressBar1.getMax() * timePassed/TIMER_RUNTIME;
            progressBar1.setProgress(progress);
        }
    }

    public void onContinue(){
        Log.d("abc", "Completed!");
    }

    public void btnClick(View v){
        Log.d("abc", "Button Clicked");
        final Thread timerThread = new Thread(){
            @Override
            public void run(){
                mbActive = true;
                try{
                    int waited = 0;
                    while(mbActive && (waited < TIMER_RUNTIME)){
                        sleep(200);
                        if(mbActive){
                            waited += 200;
                            updateProgress(waited);
                        }
                    }
                }catch (InterruptedException e){
                    //do something
                }finally {
                    onContinue();
                }
            }

        };
        timerThread.start();
    }
}
