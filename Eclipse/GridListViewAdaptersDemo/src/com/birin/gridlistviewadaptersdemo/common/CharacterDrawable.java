/**
 * Copyright 2014-present Biraj Patel
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.birin.gridlistviewadaptersdemo.common;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.widget.ImageView;

public class CharacterDrawable extends ColorDrawable {

	private char character;
	private int color;
	private final Paint textPaint;

	public CharacterDrawable(CharacterDrawableInfo info) {
		super(info.color);
		color = info.color;
		character = info.newChar;
		textPaint = new Paint();
		textPaint.setColor(Color.BLACK);
		textPaint.setStyle(Paint.Style.FILL);
		textPaint.setTextAlign(Paint.Align.CENTER);
		textPaint.setAntiAlias(true);
		textPaint.setFakeBoldText(true);
	}

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);

		// draw text
		int width = canvas.getWidth();
		int height = canvas.getHeight();
		textPaint.setTextSize(height / 2);
		canvas.drawText(String.valueOf(character), width / 2, height / 2
				- ((textPaint.descent() + textPaint.ascent()) / 2), textPaint);
	}

	@Override
	public void setAlpha(int alpha) {
		textPaint.setAlpha(alpha);
	}

	@Override
	public void setColorFilter(ColorFilter cf) {
		textPaint.setColorFilter(cf);
	}

	@Override
	public int getOpacity() {
		return PixelFormat.TRANSLUCENT;
	}

	public void update(CharacterDrawableInfo info) {
		if (info.newChar != character || info.color != color) {
			character = info.newChar;
			this.color = info.color;
			setColor(color);
			invalidateSelf();
		}
	}

	@SuppressLint("NewApi")
	@SuppressWarnings("deprecation")
	public static void tagNewDrawableToImageView(ImageView imageview) {
		CharacterDrawable drawable = new CharacterDrawable(
				new CharacterDrawableInfo());
		imageview.setTag(drawable);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
			imageview.setBackground(drawable);
		} else {
			imageview.setBackgroundDrawable(drawable);
		}
	}

	public static void updateTaggedDrawableStateFromImageView(
			ImageView imageview, CharacterDrawableInfo info) {
		CharacterDrawable drawable = (CharacterDrawable) imageview.getTag();
		drawable.update(info);
	}

	public static class CharacterDrawableInfo {
		public int color;
		public char newChar;
	}
}