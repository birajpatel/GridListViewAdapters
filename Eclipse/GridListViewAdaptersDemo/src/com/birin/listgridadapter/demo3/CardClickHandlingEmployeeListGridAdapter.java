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
package com.birin.listgridadapter.demo3;

import android.content.Context;
import android.widget.Toast;

import com.birin.listgridadapter.base.BaseEmployeeListGridAdapter;
import com.birin.listgridadapter.datasetup.Employee;

public class CardClickHandlingEmployeeListGridAdapter extends
		BaseEmployeeListGridAdapter {

	public CardClickHandlingEmployeeListGridAdapter(Context context,
			int totalItemsInRow) {
		super(context, totalItemsInRow);
	}

	@Override
	public void onCardClicked(Employee cardData) {
		Toast.makeText(getContext(),
				"Employee card clicked " + cardData.getEmployeeName(),
				Toast.LENGTH_LONG).show();
	}

}
