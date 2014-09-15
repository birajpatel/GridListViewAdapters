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
package com.birin.listgridadapter.demo6;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.birin.gridlistviewadapters.utils.OnLoadMoreRequestListener;
import com.birin.gridlistviewadaptersdemo.R;
import com.birin.listgridadapter.datasetup.Employee;
import com.birin.listgridadapter.demo5.FixedListItemsRotationSupport;

public class UnlimitedListItemsRotationSupportAutoLoadMore extends
		FixedListItemsRotationSupport implements OnLoadMoreRequestListener {

	protected View loadMoreFooterView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		gridAdapter.setOnLoadMoreRequestListener(this);
	}

	@Override
	public void onLoadMoreRequested() {
		dataRetainingFragment.runAddDataTask();
	}

	@Override
	protected void bindAdapterToList() {
		loadMoreFooterView = getLoadMoreView();
		if (dataRetainingFragment.canLoadMoreData() == true) {
			listView.addFooterView(loadMoreFooterView);
			updateLoadMoreView();
		}
		super.bindAdapterToList();
	}

	@SuppressLint("InflateParams")
	protected View getLoadMoreView() {
		ProgressBar loadMoreProgress = (ProgressBar) inflater.inflate(
				R.layout.auto_load_more_footer, null);
		loadMoreProgress.setBackgroundColor(Color.LTGRAY);
		return loadMoreProgress;
	}

	@Override
	public void onNewDummyDataGenerated(ArrayList<Employee> newDataList) {
		super.onNewDummyDataGenerated(newDataList);
		updateLoadMoreView();
	}

	@Override
	public void onDataGeneratorTaskExecuting() {
		super.onDataGeneratorTaskExecuting();
		updateLoadMoreView();
	}

	@Override
	public void onDataGeneratorTaskCancelled() {
		super.onDataGeneratorTaskCancelled();
		updateLoadMoreView();
	}

	protected void updateLoadMoreView() {
		if (dataRetainingFragment.canLoadMoreData() == false) {
			listView.removeFooterView(loadMoreFooterView);
		} else {
			boolean isLoadingMoreData = dataRetainingFragment.isTaskRunning();
			if (isLoadingMoreData == true) {
				loadMoreFooterView.setVisibility(View.VISIBLE);
			} else {
				loadMoreFooterView.setVisibility(View.GONE);
			}
		}
	}
}
