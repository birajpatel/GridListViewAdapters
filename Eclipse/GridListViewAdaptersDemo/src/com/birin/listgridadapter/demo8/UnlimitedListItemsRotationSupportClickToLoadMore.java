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
package com.birin.listgridadapter.demo8;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.birin.gridlistviewadaptersdemo.R;
import com.birin.listgridadapter.demo6.UnlimitedListItemsRotationSupportAutoLoadMore;

public class UnlimitedListItemsRotationSupportClickToLoadMore extends
		UnlimitedListItemsRotationSupportAutoLoadMore implements
		OnClickListener {

	private Button loadMoreButton;
	private ProgressBar loadingProgress;

	@Override
	public void onLoadMoreRequested() {
		updateLoadMoreView();
	}

	@SuppressLint("InflateParams")
	@Override
	protected View getLoadMoreView() {
		LinearLayout loadMoreFooter = (LinearLayout) inflater.inflate(
				R.layout.load_more_button_footer, null);
		loadMoreFooter.setBackgroundColor(Color.LTGRAY);
		loadMoreButton = (Button) loadMoreFooter
				.findViewById(R.id.load_more_btn);
		loadingProgress = (ProgressBar) loadMoreFooter
				.findViewById(R.id.loading_progress_bar);
		loadMoreButton.setOnClickListener(this);
		return loadMoreFooter;
	}

	@Override
	protected void updateLoadMoreView() {
		if (dataRetainingFragment.canLoadMoreData() == false) {
			listView.removeFooterView(loadMoreFooterView);
		} else {
			boolean isTaskRunning = dataRetainingFragment.isTaskRunning();
			loadingProgress.setVisibility(isTaskRunning ? View.VISIBLE
					: View.GONE);
			loadMoreButton
					.setVisibility((isTaskRunning == false) ? View.VISIBLE
							: View.GONE);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.load_more_btn:
			super.onLoadMoreRequested();
			break;
		}
	}

}
