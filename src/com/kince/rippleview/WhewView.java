package com.kince.rippleview;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;

/**
* 
* 
* @author 扩散的水波纹
*
*/
public class WhewView extends View {
	private Paint paint;
	private int maxWidth = 255;
	// �Ƿ�����
	private boolean isStarting = false;
	private List<String> alphaList = new ArrayList<String>();
	private List<String> startWidthList = new ArrayList<String>();
	private int startPoint;
	private int startWidth;
	
	public WhewView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		init();
	}
	public WhewView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
	}
	public WhewView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}
	private void init() {
		paint = new Paint();
		// ���ò��ĵ���ɫ
		paint.setColor(0x0059ccf5);
		
		paint.setAntiAlias(true);
		paint.setStrokeWidth((float) 5.0);
		paint.setStyle(Style.STROKE);
		paint.setStrokeCap(Cap.ROUND);
//		paint.setColor(Color.TRANSPARENT);
		
		alphaList.add("255");// Բ�ĵĲ�͸����
		startWidthList.add("0");
	}
	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		setBackgroundColor(Color.TRANSPARENT);// ��ɫ����ȫ͸��
		// ���λ��� ͬ��Բ
		for (int i = 0; i < alphaList.size(); i++) {
			int alpha = Integer.parseInt(alphaList.get(i));
			// Բ�뾶
			int startWidth = Integer.parseInt(startWidthList.get(i));
			paint.setAlpha(alpha);
			
			canvas.drawCircle(getWidth() / 2, getHeight() / 2, startWidth+ (maxWidth/2),
					paint);
			// ͬ��Բ��ɢ
			if (isStarting && alpha > 0 && startWidth < maxWidth) {
				alphaList.set(i, (alpha - 2) + "");
				startWidthList.set(i, (startWidth + 10) + "");
			}
		}
		if (isStarting
		&& Integer
		.parseInt(startWidthList.get(startWidthList.size() - 1)) >= maxWidth / 8) {
			if(startWidthList.size()<3){
				alphaList.add("255");
				startWidthList.add("0");
			}
		}
		// ͬ��Բ�����ﵽ10����ɾ��������?
//		if (isStarting && startWidthList.size() == 8) {
//			startWidthList.remove(0);
//			alphaList.remove(0);
//		}
		// ˢ�½���
		invalidate();
	}
	// ִ�ж���
	public void start() {
		isStarting = true;
	}
	// ֹͣ����
	public void stop() {
		alphaList.clear();
		startWidthList.clear();
		alphaList.add("255");// Բ�ĵĲ�͸����
		startWidthList.add("0");
		isStarting = false;
	}
	// �ж��Ƕ��ڲ���ִ��
	public boolean isStarting() {
		return isStarting;
	}
	public void setMaxWidth( int j) {
		this.maxWidth = j;
	}
}