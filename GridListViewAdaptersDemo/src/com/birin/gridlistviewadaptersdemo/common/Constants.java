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

import com.birin.gridlistviewadapters.utils.MaxCardsInfo;

public class Constants {

	public static final int MAX_ITEM_IN_ROW_POTRAIT = 3;
	public static final int MAX_ITEM_IN_ROW_LANDSCAPE = 4;

	public static final int TEXT_VIEW_CLICK_EVENT_ID = 0;
	public static final int DUMMY_DELAY_IN_MILLIS = 1500;

	public static final MaxCardsInfo MAX_CARDS_INFO = new MaxCardsInfo(
			MAX_ITEM_IN_ROW_POTRAIT, MAX_ITEM_IN_ROW_LANDSCAPE);

	public static final String TAG_RETAINED_FRAGMENT = "retained_fragment";

	public static final String KEY_OLD_POSITION = "oldPosition";
	public static final String KEY_OLD_ORIENTATION = "oldOrientation";

}
