package com.tygame.libgdx.utility;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.FadeOut;
import com.badlogic.gdx.scenes.scene2d.actions.MoveBy;
import com.badlogic.gdx.scenes.scene2d.actions.Sequence;

/**
 * 动画工具类 Delay $ (Action action, float duration) 延迟duration秒执行action。
 * 
 * Forever $ (Action action) 一直执行action。
 * 
 * Parallel $ (Action... actions) 并行（同时）执行actions。
 * 
 * Repeat $ (Action action, int times) 重复action times次。
 * 
 * Sequence $ (Action... actions) 按顺序执行actions。
 * 
 * Remove $ () 删除所有Action。
 * 
 * @Package com.tygame.libgdx.utility
 * @FileName ActionUtility.java
 * @Author TyGame
 * @Date 2012-12-28
 */
public class ActionUtility {

	/**
	 * 保持几秒后渐渐消失的Action
	 */
	public static Action getKeepAndFadeOut(float keepDuration, float fadeoutDuration) {
		return Sequence.$(MoveBy.$(0, 0, keepDuration), FadeOut.$(fadeoutDuration));
	}
}
