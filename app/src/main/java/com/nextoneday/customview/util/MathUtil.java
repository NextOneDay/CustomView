package com.nextoneday.customview.util;

/**
 * Created by sszz on 2016/12/12.
 */

public class MathUtil {
	public static float getTouchAngle(float x, float y) {
		float touchAngle = 0;
		if (x < 0 && y < 0) {  //2 象限
			touchAngle += 180;
		} else if (y < 0 && x > 0) {  //1象限
			touchAngle += 360;
		} else if (y > 0 && x < 0) {  //3象限
			touchAngle += 180;
		}
		//Math.atan(y/x) 返回正数值表示相对于 x 轴的逆时针转角，返回负数值则表示顺时针转角。
		//返回值乘以 180/π，将弧度转换为角度。
		touchAngle += Math.toDegrees(Math.atan(y / x));
		if (touchAngle < 0) {
			touchAngle = touchAngle + 360;
		}
		return touchAngle;
	}
}
