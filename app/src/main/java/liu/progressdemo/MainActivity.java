package liu.progressdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import liu.progressdemo.view.LineProgress;
import liu.progressdemo.view.RoundProgressBar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private static final String TAG     = "vivi";
    private LineProgress mLp;

    private int mProgress = 1;

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
          super.handleMessage(msg);
            mRoundProgressBar.setProgress(mProgress);
            mRoundProgressBar1.setProgress(mProgress);
            mLp.setProgress(mProgress);
            Log.d(TAG, "handleMessage: "+mRoundProgressBar.getProgress());
            Log.d(TAG, "handleMessage: "+mRoundProgressBar.getMax());

        }
    };
    private Button mBtnStart;
    private RoundProgressBar mRoundProgressBar;
    private RoundProgressBar mRoundProgressBar1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        initListener();
    }

    private void initListener() {
       mBtnStart.setOnClickListener(this);
    }


    private void initView() {

        mBtnStart = (Button) findViewById(R.id.btnStart);
        mRoundProgressBar = (RoundProgressBar) findViewById(R.id.roundProgress);
        mRoundProgressBar1=(RoundProgressBar) findViewById(R.id.roundProgress1);
        mLp = (LineProgress) findViewById(R.id.pg);


    }

    private void initData() {

        new Thread() {
            @Override
            public void run() {

                while (mProgress <=100) {

                    mProgress++;


                    mHandler.sendEmptyMessage(0x11);
                    SystemClock.sleep(200);
                }

            }
        }.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnStart:
                initData();
                break;
        }
    }
}
