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

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.birin.gridlistviewadapters.Card;
import com.birin.gridlistviewadapters.ListGridAdapter;
import com.birin.gridlistviewadapters.dataholders.CardDataHolder;
import com.birin.gridlistviewadapters.utils.ChildViewsClickHandler;
import com.birin.gridlistviewadaptersdemo.R;
import com.birin.gridlistviewadaptersdemo.common.CharacterDrawable;
import com.birin.gridlistviewadaptersdemo.common.CharacterDrawable.CharacterDrawableInfo;
import com.birin.gridlistviewadaptersdemo.common.EmployeeCardViewHolder;
import com.birin.listgridadapter.datasetup.Employee;

public class BaseEmployeeListGridAdapter extends
		ListGridAdapter<Employee, EmployeeCardViewHolder> {

	public BaseEmployeeListGridAdapter(Context context, int totalItemsInRow) {
		super(context, totalItemsInRow);
	}

	@SuppressLint("InflateParams")
	@Override
	protected Card<EmployeeCardViewHolder> getNewCard(int cardwidth) {
		View cardView = getLayoutInflater().inflate(R.layout.card_layout, null);
		EmployeeCardViewHolder cardViewHolder = initViewHolderUsing(cardView,
				cardwidth);
		return new Card<EmployeeCardViewHolder>(cardView, cardViewHolder);
	}

	@Override
	protected void setCardView(CardDataHolder<Employee> cardDataHolder,
			EmployeeCardViewHolder cardViewHolder) {
		setCardViews(cardDataHolder, cardViewHolder);
	}

	@Override
	public void onCardClicked(Employee cardData) {
		// Not handling
	}

	@Override
	protected void registerChildrenViewClickEvents(
			EmployeeCardViewHolder cardViewHolder,
			ChildViewsClickHandler childViewsClickHandler) {
		// No child registered.
	}

	@Override
	public void onChildViewClicked(View clickedChildView, Employee cardData,
			int eventId) {
		// No child registered.
	}

	// Helper Methods.

	private void setCardViews(CardDataHolder<Employee> cardDataHolder,
			EmployeeCardViewHolder cardViewHolder) {
		Employee employee = cardDataHolder.getData();
		cardViewHolder.employeeName.setText(employee.getEmployeeName());
		CharacterDrawableInfo info = new CharacterDrawableInfo();
		info.color = employee.getIconCharColor();
		info.newChar = employee.getIconChar();
		CharacterDrawable.updateTaggedDrawableStateFromImageView(
				cardViewHolder.employeeImage, info);
	}

	protected EmployeeCardViewHolder initViewHolderUsing(View cardView,
			int cardwidth) {
		EmployeeCardViewHolder cardViewHolder = new EmployeeCardViewHolder();
		initNameView(cardViewHolder, cardView);
		initImage(cardViewHolder, cardView);
		initImageContainer(cardViewHolder, cardView, cardwidth);
		return cardViewHolder;
	}

	private void initNameView(EmployeeCardViewHolder cardViewHolder,
			View cardView) {
		cardViewHolder.employeeName = (TextView) cardView
				.findViewById(R.id.name);
	}

	private void initImage(EmployeeCardViewHolder cardViewHolder, View cardView) {
		cardViewHolder.employeeImage = (ImageView) cardView
				.findViewById(R.id.sample_image);
		CharacterDrawable
				.tagNewDrawableToImageView(cardViewHolder.employeeImage);
	}

	private void initImageContainer(EmployeeCardViewHolder cardViewHolder,
			View cardView, int cardwidth) {
		cardViewHolder.employeeImageContainer = (RelativeLayout) cardView
				.findViewById(R.id.image_container);
		cardViewHolder.employeeImageContainer.getLayoutParams().height = cardwidth;
	}

}
