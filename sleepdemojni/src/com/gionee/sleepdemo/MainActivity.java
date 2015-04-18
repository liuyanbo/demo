package com.gionee.sleepdemo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener; 

public class MainActivity extends Activity {
	private final static int STATE_CHECK_MEMORY = 1;
	private final static int SWEEP_ANGLE_CONSTANT = 290;
	private final static int PERCENT_CONSTANT = 100;
	private MainCircleView mCircle;
	private int mStart = 80;
	private int mEnd = 0;
	private boolean flag = true;
    private boolean isStart = true;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mCircle = (MainCircleView) findViewById(R.id.main_circle);
		mCircle.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Native.sleep(4);
				if(!isStart){
					return;
				}
				
				isStart = false;
				
				if(flag){
					new MemoryTask(STATE_CHECK_MEMORY, mStart, mEnd)
					   .executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
				}else{
					new MemoryTask(STATE_CHECK_MEMORY, mEnd, mStart)
					   .executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
				}
				flag = !flag;
				
			}
		});

		new MemoryTask(STATE_CHECK_MEMORY, mStart, mEnd)
		   .executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
	}


	private class MemoryTask extends AsyncTask<Void, Integer, Integer> {

		private int mState = 0;
		private int mStart = 0;
		private int mEnd = 0;

		public MemoryTask(int state, int start, int end) {
			mState = state;
			mStart = (int) (start * SWEEP_ANGLE_CONSTANT / PERCENT_CONSTANT);
			mEnd = (int) (end * SWEEP_ANGLE_CONSTANT / PERCENT_CONSTANT);
		}

		@Override
		protected Integer doInBackground(Void... params) {
			// TODO Auto-generated method stub
			if ((mState & STATE_CHECK_MEMORY) != 0) {
				while (mStart-- > mEnd) {
					publishProgress(mState, mStart);
					sleep(getSleepTime(mStart));
				}
				while (mStart++ < mEnd) {
					publishProgress(mState, mStart);
					sleep(getSleepTime(mStart));
				}
			}
			return 0;
		}

		@Override
		protected void onPostExecute(Integer result) {
			// TODO Auto-generated method stub
			isStart = true;
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub

			switch (values[0]) {
			case STATE_CHECK_MEMORY:
				break;
			}

			updateCircle(values[1]);
		}

		private void sleep(int milliseconds) {
			try {
				
				Log.i("sleepdemo", "sleep " + milliseconds + "ms");
                long begin = System.currentTimeMillis();
                Native.sleep(milliseconds);
                Log.i("sleepdemo", "real sleep ---> " + (System.currentTimeMillis() - begin) + "ms");				
				
			} catch (Exception ex) {
			}
		}

		private int getSleepTime(int value) {
			int duration = 4;
			return duration;
		}
	}

	private void updateCircle(int ratio) {
		mCircle.updateRatio(ratio);
		mCircle.updateViews();
	}

}
