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
package com.birin.cursorgridadapter.demo2;

import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import com.birin.cursorgridadapter.base.BaseEmployeeCursorGridAdapter;
import com.birin.cursorgridadapter.datasetup.TestContentProviderSqlHelper;

public class CardClickHandlingEmployeeCursorGridAdapter extends
		BaseEmployeeCursorGridAdapter {

	public CardClickHandlingEmployeeCursorGridAdapter(Context context,
			int maxCardsInaRow, Cursor c) {
		super(context, maxCardsInaRow, c);
	}

	@Override
	public void onCardClicked(Cursor cardData) {
		Toast.makeText(
				getContext(),
				"Employee card clicked "
						+ cardData.getString(cardData
								.getColumnIndex(TestContentProviderSqlHelper.EMPLOYEE_NAME)),
				Toast.LENGTH_LONG).show();
	}

}
