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
package com.birin.gridlistviewadaptersdemo;

import com.birin.listgridadapter.demo1.SimplestListGridAdapterUsageDemoActivity;
import com.birin.listgridadapter.demo2.FixedListItems;
import com.birin.listgridadapter.demo3.CardClickHandlingFixedListItems;
import com.birin.listgridadapter.demo4.ChildAndCardClickHandlingFixedListItems;
import com.birin.listgridadapter.demo5.FixedListItemsRotationSupport;
import com.birin.listgridadapter.demo6.UnlimitedListItemsRotationSupportAutoLoadMore;
import com.birin.listgridadapter.demo7.UnlimitedListItemsRotationSupportAutoLoadMoreMax100Items;
import com.birin.listgridadapter.demo8.UnlimitedListItemsRotationSupportClickToLoadMore;

public class JavaUtilListDataDemos extends BaseDemoMenuList {

	public static final Demo<?>[] AVAILABLE_DEMOS = {
			// From java.util.List
			new Demo<SimplestListGridAdapterUsageDemoActivity>(
					"Demo 1 : Step by step guide for basic usage of ListGridAdapter + supporting header&footers"
							+ "\n(Recommended for beginner users of GridListViewAdapters lib )",
					SimplestListGridAdapterUsageDemoActivity.class),
			new Demo<FixedListItems>("Demo 2 : Fixed Items from List",
					FixedListItems.class),
			new Demo<CardClickHandlingFixedListItems>(
					"Demo 3 : Fixed Items from List + Handling card clicks",
					CardClickHandlingFixedListItems.class),
			new Demo<ChildAndCardClickHandlingFixedListItems>(
					"Demo 4 : Fixed Items from List + Handling card clicks + Handling children view clicks present in card",
					ChildAndCardClickHandlingFixedListItems.class),
			new Demo<FixedListItemsRotationSupport>(
					"Demo 5 : Fixed Items from List + Rotation Support",
					FixedListItemsRotationSupport.class),
			new Demo<UnlimitedListItemsRotationSupportAutoLoadMore>(
					"Demo 6 : Unlimited Items List with auto load more + Rotation Support",
					UnlimitedListItemsRotationSupportAutoLoadMore.class),
			new Demo<UnlimitedListItemsRotationSupportClickToLoadMore>(
					"Demo 7 : Unlimited Items List with click to load more + Rotation Support",
					UnlimitedListItemsRotationSupportClickToLoadMore.class),
			new Demo<UnlimitedListItemsRotationSupportAutoLoadMoreMax100Items>(
					"Demo 8 : 100 Items List with auto load more + Rotation Support",
					UnlimitedListItemsRotationSupportAutoLoadMoreMax100Items.class) };

	@Override
	protected Demo<?>[] getDemos() {
		return AVAILABLE_DEMOS;
	}

}
