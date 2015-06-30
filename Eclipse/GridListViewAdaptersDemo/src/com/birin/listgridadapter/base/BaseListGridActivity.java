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
package com.birin.listgridadapter.base;

import java.util.ArrayList;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.birin.gridlistviewadapters.utils.PositionCalculator;
import com.birin.gridlistviewadaptersdemo.R;
import com.birin.gridlistviewadaptersdemo.common.Constants;
import com.birin.listgridadapter.datasetup.Employee;
import com.birin.listgridadapter.datasetup.RetainedDataFragment;
import com.birin.listgridadapter.datasetup.RetainedDataFragment.DummyDataGeneratorCallback;

public class BaseListGridActivity extends Activity implements
		DummyDataGeneratorCallback {

	protected ListView listView;
	protected BaseEmployeeListGridAdapter gridAdapter;
	protected RetainedDataFragment dataRetainingFragment;
	protected LayoutInflater inflater;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		inflater = LayoutInflater.from(getApplicationContext());
		setContentView(R.layout.activity_main);
		loadRetainedFragment();
		listView = (ListView) findViewById(R.id.list_view);
		gridAdapter = getListAdapter();
		bindAdapterToList();
		addItemsInGrid(dataRetainingFragment.getListData());
	}

	protected BaseEmployeeListGridAdapter getListAdapter() {
		return new BaseEmployeeListGridAdapter(getApplicationContext(),
				getMaxCardsInRow());
	}

	protected void bindAdapterToList() {
		listView.setAdapter(gridAdapter);
	}

	private void loadRetainedFragment() {
		FragmentManager fm = getFragmentManager();
		dataRetainingFragment = (RetainedDataFragment) fm
				.findFragmentByTag(Constants.TAG_RETAINED_FRAGMENT);

		// If the Fragment is non-null, then it is currently being
		// retained across a configuration
		// changgenerateSomeDummyDataAndAddToList();e.
		if (dataRetainingFragment == null) {
			dataRetainingFragment = getRetainedDataFragment();
			fm.beginTransaction()
					.add(dataRetainingFragment, Constants.TAG_RETAINED_FRAGMENT)
					.commit();
		}
	}

	protected RetainedDataFragment getRetainedDataFragment() {
		return new RetainedDataFragment();
	}

	protected int getMaxCardsInRow() {
		return PositionCalculator.getMaxCardsFor(getCurrentOrientation(),
				Constants.MAX_CARDS_INFO);
	}

	protected int getCurrentOrientation() {
		return getResources().getConfiguration().orientation;
	}

	@Override
	public void onNewDummyDataGenerated(ArrayList<Employee> newDataList) {
		addItemsInGrid(newDataList);
	}

	protected void addItemsInGrid(ArrayList<Employee> newDataList) {
		if (null != newDataList && newDataList.isEmpty() == false) {
			findViewById(R.id.place_holder_text).setVisibility(View.GONE);
			listView.setVisibility(View.VISIBLE);
			gridAdapter.addItemsInGrid(newDataList);
		}
	}

	@Override
	public void onDataGeneratorTaskExecuting() {

	}

	@Override
	public void onDataGeneratorTaskCancelled() {

	}

}
