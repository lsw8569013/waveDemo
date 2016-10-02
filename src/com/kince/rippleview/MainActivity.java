package com.kince.rippleview;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

public class MainActivity extends Activity {
	
private Handler handler = new Handler() {
		
		
		
		@Override
		public void handleMessage(Message msg) {
			
				wv.stop();
				SystemClock.sleep(100);
				wv.start();
				wv.setVisibility(View.VISIBLE);
				handler.sendEmptyMessageDelayed(0,10000);
			super.handleMessage(msg);
		}

	};
	
	private RippleView mRippleView;
	private WaterWaveView mWaterWaveView;
	private WhewView wv;
	private int width;
	private int height;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frag_work);

//        mRippleView=(RippleView) findViewById(R.id.rippleview);
//        mRippleView.stratRipple();
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        width = display.getWidth();
        height = display.getHeight();
        
        mWaterWaveView = (WaterWaveView) findViewById(R.id.wave_view);
		mWaterWaveView.setMax(10);  
		mWaterWaveView.setProgressSync(5);
		
		
		wv = (WhewView) findViewById(R.id.wv);
		wv.setMaxWidth(width/2);
		
		//À©Èö¶¯»­Ñ­»·
		wv.start();
		wv.setVisibility(View.VISIBLE);
		handler.sendEmptyMessageDelayed(0,10000);
    }


   

}
