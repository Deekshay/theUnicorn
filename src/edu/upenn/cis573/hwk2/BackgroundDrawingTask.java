package edu.upenn.cis573.hwk2;

import android.graphics.Point;
import android.os.AsyncTask;

class BackgroundDrawingTask extends AsyncTask<GameView, Void, GameView> {


	// this method gets run in the background
	protected GameView doInBackground(GameView... args) {
		try { 
			// note: you can change these values to make the unicorn go faster/slower
			Thread.sleep(10); 
			Point pointsInBackground = new Point(args[0].getTheImage().getImagePoint().x + 10, args[0].getTheImage().getImagePoint().y + args[0].getyChange());
			args[0].getTheImage().setImagePoint(pointsInBackground);
		} 
		catch (Exception e) { }
		// the return value is passed to "onPostExecute" but isn't actually used here
		return args[0]; 
	}
	
	// this method gets run in the UI thread
	protected void onPostExecute(GameView result) {
		// redraw the View
		result.invalidate();
		if (result.getScore() < 10) {
			// need to start a new thread to make the unicorn keep moving
			BackgroundDrawingTask task = new BackgroundDrawingTask();
			task.execute(result);
		}
		else {
			// game over, man!
			((GameActivity)(result.parentActivity)).endTime = System.currentTimeMillis();
			// these methods are deprecated but it's okay to use them... probably.
			result.parentActivity.removeDialog(1);
			result.parentActivity.showDialog(1);
		}
	}    	
}

