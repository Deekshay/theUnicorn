package edu.upenn.cis573.hwk2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class GameView extends View {
	android.app.Activity parentActivity;
	private Stroke theStroke = new Stroke();
	private Image theImage;

	private boolean killed = false;
	private boolean newUnicorn = true;
	private int score = 0;
	private int yChange = 0;


	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @return the yChange
	 */
	public int getyChange() {
		return yChange;
	}

	/**
	 * @return the theImage
	 */
	public Image getTheImage() {
		return theImage;
	}

	
	public GameView(Context context) {
		super(context);
		theImage = new Image(context);
		initializeGameView();
	}

	public GameView(Context context, AttributeSet attributeSet) {
		super(context, attributeSet);
		theImage = new Image(context, attributeSet);
		initializeGameView();
	}
	
	
	/*
	 * To eliminate duplicate code in the constructors.
	 */
	public void initializeGameView(){
		setBackgroundResource(R.drawable.space);
		parentActivity = (GameActivity) getContext();
	}

	/*
	 * This method is automatically invoked when the View is displayed. It is
	 * also called after you call "invalidate" on this object.
	 */

	// fuction to draw the images
	protected void onDraw(Canvas canvas) {
		// resets the position of the unicorn if one is killed or reaches the
		// right edge
		if (newUnicorn || theImage.getImagePoint().x >= this.getWidth()) {
			Point setter = new Point(-150, (int) (Math.random() * 200 + 200));
			theImage.setImagePoint(setter);
			yChange = (int) (10 - Math.random() * 20);
			newUnicorn = false;
			killed = false;
		}

		// show the exploding image when the unicorn is killed
		if (killed) {
			canvas.drawBitmap(theImage.getExplode(), theImage.getImagePoint().x, theImage.getImagePoint().y, null);
			newUnicorn = true;
			try {
				Thread.sleep(10);
			} catch (Exception e) {
			}
			invalidate();
			return;
		}

		// draws the unicorn at the specified point
		canvas.drawBitmap(theImage.getUnicorn(), theImage.getImagePoint().x, theImage.getImagePoint().y, null);

		/*
		 * Calls the function that draws the red stroke.
		 */
		theStroke.drawStroke(canvas);

	}

	/*
	 * This method is automatically called when the user touches the screen.
	 */
	public boolean onTouchEvent(MotionEvent event) {

		if (!theStroke.strokeDirection(event)) {
			return false;
		}

		// see if the point is within the boundary of the image
		// the !killed thing here is to prevent a "double-kill" that could occur
		// while the "explosion" image is being shown
		if (!killed && theImage.unicornKilled(event.getX(), event.getY())) {
			killed = true;
			score++;
			((TextView) (((GameActivity) parentActivity).getScoreboard())).setText("" + score);
		}

		// forces a redraw of the View
		invalidate();

		return true;
	}
}
