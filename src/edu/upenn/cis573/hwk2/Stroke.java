package edu.upenn.cis573.hwk2;

import java.util.ArrayList;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;

public class Stroke {
	
	private ArrayList<Point> points = new ArrayList<Point>();
	private static final int lineColor = Color.RED;
	private static final int lineWidth = 10;
	
	/*
	 * Invoked when the user touches the screen
	 */
	boolean strokeDirection(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
			Point action = new Point((int) event.getX(), (int) event.getY());
			points.add(action);
		} else if (event.getAction() == MotionEvent.ACTION_UP) {
			points.clear();
		} else {
			return false;
		}
		return true;
	}

	/*
	 * Method that draws the red stroke, based on the motions of the finger
	 */
	void drawStroke(Canvas canvas) {
		if (points.size() > 1) {
			for (int i = 0; i < points.size() - 1; i++) {
				Point start = points.get(i);
				Point stop = points.get(i + 1);
				Paint paint = new Paint();
				paint.setColor(lineColor);
				paint.setStrokeWidth(lineWidth);
				canvas.drawLine(start.x, start.y, stop.x, stop.y, paint);
			}
		}

	}
	


}
