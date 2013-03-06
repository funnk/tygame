package com.tygame.android.utility;

import java.util.Random;

import android.app.Activity;
import android.util.SparseIntArray;

import com.tygame.android.R;

/**
 * Android原生界面切换动画
 * 
 * @Package com.tygame.loverss.util
 * @FileName ActivityAnimation.java
 * @Author TyGame
 * @Date 2012-12-10
 */
public class AnimationUtility {

	/**
	 * 播放随机动画
	 */
	public static void doAnimation(Activity activity) {
		SparseIntArray anims = new SparseIntArray();
		anims.put(R.anim.fade, R.anim.hold);
		anims.put(R.anim.my_scale_action, R.anim.my_alpha_action);
		anims.put(R.anim.scale_rotate, R.anim.my_alpha_action);
		anims.put(R.anim.scale_translate_rotate, R.anim.my_alpha_action);
		anims.put(R.anim.scale_translate, R.anim.my_alpha_action);
		anims.put(R.anim.hyperspace_in, R.anim.hyperspace_out);
		anims.put(R.anim.push_left_in, R.anim.push_left_out);
		anims.put(R.anim.push_up_in, R.anim.push_up_out);
		anims.put(R.anim.slide_left, R.anim.slide_right);
		anims.put(R.anim.wave_scale, R.anim.my_alpha_action);
		anims.put(R.anim.zoom_enter, R.anim.zoom_exit);
		anims.put(R.anim.slide_up_in, R.anim.slide_down_out);

		int index = new Random().nextInt(anims.size() - 1);
		
		activity.overridePendingTransition(anims.keyAt(index), anims.valueAt(index));
	}
}
