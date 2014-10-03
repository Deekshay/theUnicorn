package edu.upenn.cis573.hwk2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;


public class Image extends View {
    private Bitmap unicorn;
    private Bitmap explode;
    private Point imagePoint;
    
    public Image(Context context){
		super(context);
		initializeImage();
    }
    
    public Image(Context context, AttributeSet attributeSet){
		super(context, attributeSet);
		initializeImage();
    }
    
    
    void initializeImage() {
		imagePoint = new Point(-150,100);
		unicorn = BitmapFactory.decodeResource(getResources(), R.drawable.unicorn);
		unicorn = Bitmap.createScaledBitmap(unicorn, 150, 150, false);
		explode = BitmapFactory.decodeResource(getResources(), R.drawable.explosion); 
		explode = Bitmap.createScaledBitmap(explode, 150, 150, false);
    }
	/**
	 * @return the image
	 */
    
	public Bitmap getUnicorn() {
		return unicorn;
	}

	/**
	 * @return the explode
	 */
	public Bitmap getExplode() {
		return explode;
	}

	/**
	 * @return the imagePoint
	 */
	public Point getImagePoint() {
		return imagePoint;
	}
	/**
	 * @param imagePoint the imagePoint to set
	 */
	public void setImagePoint(Point imagePoint) {
		this.imagePoint = imagePoint;
	}
	
	
	/* This method determines if the line drawn lies within 
	 * the unicorn and helps decide if it needs to be killed
	 */ 
	public boolean unicornKilled(float x, float y) {
    	if (x > imagePoint.x && x < imagePoint.x + unicorn.getWidth() && y > imagePoint.y && y < imagePoint.y + unicorn.getHeight()) {
    		return true;
    	}
    	return false;
	}
	


}
