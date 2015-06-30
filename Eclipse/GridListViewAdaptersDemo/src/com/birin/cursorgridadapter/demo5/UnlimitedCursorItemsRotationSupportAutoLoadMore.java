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
package com.birin.cursorgridadapter.demo5;

import android.content.Loader;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.birin.cursorgridadapter.datasetup.CursorRetainingFragment.DummyDataGeneratorCallback;
import com.birin.cursorgridadapter.demo4.FixedCursorItemsRotationSupport;
import com.birin.gridlistviewadapters.utils.OnLoadMoreRequestListener;
import com.birin.gridlistviewadaptersdemo.R;

public class UnlimitedCursorItemsRotationSupportAutoLoadMore extends
		FixedCursorItemsRotationSupport implements OnLoadMoreRequestListener,
		DummyDataGeneratorCallback {

	protected View loadMoreFooterView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		listviewAdapter.setOnLoadMoreRequestListener(this);
	}

	@Override
	public void onLoadMoreRequested() {
		dataRetainingFragment.runAddDataTaskOnLoadMoreRequest();
	}

	@Override
	protected void bindAdapterToList() {
		loadMoreFooterView = getLoadMoreView();
		if (dataRetainingFragment.canLoadMoreData(listviewAdapter.getCount()) == true) {
			listView.addFooterView(loadMoreFooterView);
			updateLoadMoreView();
		}
		super.bindAdapterToList();
	}

	protected View getLoadMoreView() {
		ProgressBar loadMoreProgress = (ProgressBar) inflater.inflate(
				R.layout.auto_load_more_footer, null);
		loadMoreProgress.setBackgroundColor(Color.LTGRAY);
		return loadMoreProgress;
	}

	@Override
	public void onLoadFinished(Loader<Cursor> arg0, Cursor data) {
		super.onLoadFinished(arg0, data);
		updateLoadMoreView();
	}

	@Override
	public void onDataGeneratorTaskExecuting() {
		updateLoadMoreView();
	}

	protected void updateLoadMoreView() {
		if (dataRetainingFragment.canLoadMoreData(listviewAdapter.getCount()) == false) {
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
