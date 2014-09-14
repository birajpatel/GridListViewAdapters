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
package com.birin.cursorgridadapter.demo3;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.Toast;

import com.birin.cursorgridadapter.datasetup.TestContentProviderSqlHelper;
import com.birin.cursorgridadapter.demo2.CardClickHandlingEmployeeCursorGridAdapter;
import com.birin.gridlistviewadapters.utils.ChildViewsClickHandler;
import com.birin.gridlistviewadaptersdemo.common.Constants;
import com.birin.gridlistviewadaptersdemo.common.EmployeeCardViewHolder;

public class ChildAndCardClickHandlingEmployeeCursorGridAdapter extends
		CardClickHandlingEmployeeCursorGridAdapter {

	public ChildAndCardClickHandlingEmployeeCursorGridAdapter(Context context,
			int maxCardsInaRow, Cursor c) {
		super(context, maxCardsInaRow, c);
	}

	@Override
	protected void registerChildrenViewClickEvents(
			EmployeeCardViewHolder cardViewHolder,
			ChildViewsClickHandler childViewsClickHandler) {
		childViewsClickHandler
				.registerChildViewForClickEvent(cardViewHolder.employeeName,
						Constants.TEXT_VIEW_CLICK_EVENT_ID);
	}

	@Override
	public void onChildViewClicked(View clickedChildView, Cursor cardData,
			int eventId) {
		switch (eventId) {
		case Constants.TEXT_VIEW_CLICK_EVENT_ID:
			Toast.makeText(
					getContext(),
					"Child View Clicked : "
							+ cardData.getString(cardData
									.getColumnIndex(TestContentProviderSqlHelper.EMPLOYEE_NAME)),
					Toast.LENGTH_LONG).show();
			break;
		default:
			break;
		}
	}
}
