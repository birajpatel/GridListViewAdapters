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

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.birin.cursorgridadapter.datasetup.TestContentProvider;
import com.birin.cursorgridadapter.demo1.FixedCursorItems;
import com.birin.cursorgridadapter.demo2.CardClickHandlingFixedCursorItems;
import com.birin.cursorgridadapter.demo3.ChildAndCardClickHandlingFixedCursorItems;
import com.birin.cursorgridadapter.demo4.FixedCursorItemsRotationSupport;
import com.birin.cursorgridadapter.demo5.UnlimitedCursorItemsRotationSupportAutoLoadMore;
import com.birin.cursorgridadapter.demo6.UnlimitedCursorItemsRotationClickToLoadMore;
import com.birin.gridlistviewadaptersdemo.common.Constants;

public class CursorDataDemos extends BaseDemoMenuList {

	public static final Demo<?>[] AVAILABLE_DEMOS = {
			// From using Cursor Loader mechanism
			new Demo<FixedCursorItems>("Demo 1 : Fixed Items from Cursor",
					FixedCursorItems.class),
			new Demo<CardClickHandlingFixedCursorItems>(
					"Demo 2 : Fixed Items + Handling card click events",
					CardClickHandlingFixedCursorItems.class),
			new Demo<ChildAndCardClickHandlingFixedCursorItems>(
					"Demo 3 : Fixed Items + Handling card clicks + Handling children view clicks present in card",
					ChildAndCardClickHandlingFixedCursorItems.class),
			new Demo<FixedCursorItemsRotationSupport>(
					"Demo 4 : Fixed Items + Rotation Support",
					FixedCursorItemsRotationSupport.class),
			new Demo<UnlimitedCursorItemsRotationSupportAutoLoadMore>(
					"Demo 5 : Unlimited Items with auto load more + Rotation Support",
					UnlimitedCursorItemsRotationSupportAutoLoadMore.class),
			new Demo<UnlimitedCursorItemsRotationClickToLoadMore>(
					"Demo 6 : Unlimited Items with click to load more + Rotation Support",
					UnlimitedCursorItemsRotationClickToLoadMore.class) };

	@Override
	protected Demo<?>[] getDemos() {
		return AVAILABLE_DEMOS;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		findViewById(R.id.clear_db).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new EmployeeDataCleanerTask().execute();
			}
		});

	}

	@Override
	protected int getLayoutId() {
		return R.layout.cursor_demo_menu_screen_layout;
	}

	class EmployeeDataCleanerTask extends AsyncTask<Void, Void, Void> {

		ProgressDialog cleanerProgress;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			cleanerProgress = new ProgressDialog(CursorDataDemos.this);
			cleanerProgress.setCancelable(false);
			cleanerProgress
					.setMessage(getString(R.string.cleaning_employee_table_dialog_msg));
			cleanerProgress.show();
		}

		@Override
		protected Void doInBackground(Void... params) {
			try {
				Thread.sleep(Constants.DUMMY_DELAY_IN_MILLIS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			getContentResolver().delete(
					TestContentProvider.CONTENT_URI_EMPLOYEE, null, null);
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			if (cleanerProgress != null && cleanerProgress.isShowing()) {
				cleanerProgress.dismiss();
			}
		}

	}

}
