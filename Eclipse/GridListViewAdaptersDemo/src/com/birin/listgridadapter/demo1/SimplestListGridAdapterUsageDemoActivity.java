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
package com.birin.listgridadapter.demo1;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.birin.gridlistviewadapters.Card;
import com.birin.gridlistviewadapters.ListGridAdapter;
import com.birin.gridlistviewadapters.dataholders.CardDataHolder;
import com.birin.gridlistviewadapters.utils.ChildViewsClickHandler;
import com.birin.gridlistviewadaptersdemo.R;

public class SimplestListGridAdapterUsageDemoActivity extends Activity {

	private ListView listview;
	private ArrayList<Item> dataList;
	private SimplestDemoAdadpter listadapter;

	private final int MAX_CARDS = 3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		listview = new ListView(this);
		listview.setDivider(null);
		setContentView(listview);
		generateSomeDummyDataList();
		listadapter = new SimplestDemoAdadpter(getApplicationContext(),
				MAX_CARDS);
		listadapter.addItemsInGrid(dataList);
		addHeaderFooters();
		listview.setAdapter(listadapter);
	}

	private void addHeaderFooters() {
		final int cardSpacing = listadapter.getCardSpacing();

		// Header View
		View headerView = getHeaderFooterView("HEADER", cardSpacing);
		headerView.setPadding(cardSpacing, cardSpacing, cardSpacing, 0);
		listview.addHeaderView(headerView);

		// Footer View
		View footerView = getHeaderFooterView("FOOTER", cardSpacing);
		footerView.setPadding(cardSpacing, 0, cardSpacing, cardSpacing);
		listview.addFooterView(footerView);
	}

	private View getHeaderFooterView(String text, int cardSpacing) {
		// New footer/header view.
		View view = listadapter.getLayoutInflater().inflate(
				R.layout.simplest_header_footer_layout, null);

		// Header-Footer card sizing.
		View headerFooterView = view.findViewById(R.id.card_main_parent);
		int headerFooterWidth = listadapter.getDeviceWidth()
				- (2 * cardSpacing); // Left-right so *2
		int headerFooterHeight = listadapter.getCardWidth(MAX_CARDS);
		headerFooterView.getLayoutParams().width = headerFooterWidth;
		headerFooterView.getLayoutParams().height = headerFooterHeight;

		// Setting text value
		((TextView) view.findViewById(R.id.name)).setText(text);
		return view;
	}

	private void generateSomeDummyDataList() {
		dataList = new ArrayList<Item>();
		// Generate some dummy data.
		for (int i = 0; i < 51; i++) {
			dataList.add(new Item(i));
		}
	}

}

// Data class to be used for ListGridAdapter demo.

class Item {
	private int position;

	protected Item(int position) {
		this.position = position;
	}

	public String getPositionText() {
		return Integer.toString(position);
	}
}

// Card View Holder class, these should hold all child-view references of a
// given card so that this references can be Re-used to update views (Without
// having to call findViewById each time on list scroll.)

class ViewHolder {
	TextView textView;
}

// Now that our data class & ViewHolder class are ready lets bind each data
// item to each card, for that purpose we extend
// ListGridAdapter<E,CVH>/CursorGridAdapter<CVH> & implement few easy methods.
//
// When using ListGridAdapter you need to pass your POJO in place of 'E' so that
// you can get that object back as a parameters in various methods so in this
// Example we are dealing with ArrayList<Item> so we need to
// pass 'Item' in place of 'E'
//
// CVH means CardViewHolder for our card.
//
// By passing E & CVH you bind your adapter class generically to specific
// objects so that type-cast are not needed & compile type object safety can be
// achieved, Just like using ArrayList<Integer> where only integer values only
// can be passed.

// There are 5 easy methods to be implemented in grid-adapters
//
// 1. getNewCard() here library will ask how your Card will look & child views
// you want to hold in CardViewHolder ?, return new Object of
// com.birin.gridlistviewadapters.Card<CVH> class where CVH stands for
// CardViewHolder for this Card. Card<CVH> takes two objects in constructor
// i. CardView : return new view inflating through layout or creating new View
// through Java.
// ii. CardViewHolder : ViewHolder class that should hold child views of given
// cardView, so that view-holder can be used to recycle on list scroll.
// All the card & element sizing should be done in this method.
//
// 2. setCardView() here library will tell you to fill data into your views by
// using Data & CardViewHolder.
//
// 3. onCardClicked() here library give you callback of card-click event with
// card's data.
//
// 4. registerChildrenViewClickEvents() here library will tell you to register
// children present in your CardViewHolder using ChildViewsClickHandler
// instance, registering your children will enable you to get click events in
// onChildViewClicked method, Using this is optional alternatively user can
// handle children ViewClicks in their own ways.
//
// 5. onChildViewClicked() here library will tell you to that any of registered
// child is clicked the advantage of registering is that library will provide
// data related to clicked view's row & you do not have to find your data,
// child view can be registered using ChildViewsClickHandler instance which was
// passed in registerChildrenViewClickEvents() method.
//
//

class SimplestDemoAdadpter extends ListGridAdapter<Item, ViewHolder> {

	public SimplestDemoAdadpter(Context context, int totalCardsInRow) {
		super(context, totalCardsInRow);
	}

	@Override
	protected Card<ViewHolder> getNewCard(int cardwidth) {
		// Create card through XML (can be created programmatically as well.)
		View cardView = getLayoutInflater().inflate(
				R.layout.simplest_card_layout, null);
		cardView.setMinimumHeight(cardwidth);

		// Now create card view holder.
		ViewHolder viewHolder = new ViewHolder();
		viewHolder.textView = (TextView) cardView.findViewById(R.id.name);

		return new Card<ViewHolder>(cardView, viewHolder);
	}

	@Override
	protected void setCardView(CardDataHolder<Item> cardDataHolder,
			ViewHolder cardViewHolder) {
		Item item = cardDataHolder.getData();
		cardViewHolder.textView.setText(item.getPositionText());
	}

	@Override
	protected void onCardClicked(Item cardData) {
		Toast.makeText(getContext(),
				"Card click " + cardData.getPositionText(), Toast.LENGTH_LONG)
				.show();
	}

	private final int TEXT_VIEW_CLICK_ID = 0;

	@Override
	protected void registerChildrenViewClickEvents(ViewHolder cardViewHolder,
			ChildViewsClickHandler childViewsClickHandler) {
		childViewsClickHandler.registerChildViewForClickEvent(
				cardViewHolder.textView, TEXT_VIEW_CLICK_ID);
	}

	@Override
	protected void onChildViewClicked(View clickedChildView, Item cardData,
			int eventId) {
		if (eventId == TEXT_VIEW_CLICK_ID) {
			Toast.makeText(getContext(),
					"TextView click " + cardData.getPositionText(),
					Toast.LENGTH_LONG).show();
		}
	}

	// OPTIONAL SETUP

	@Override
	public int getCardSpacing() {
		return (2 * super.getCardSpacing());
	}

	@Override
	protected void setRowView(View rowView, int arg1) {
		rowView.setBackgroundColor(getContext().getResources().getColor(
				R.color.simplest_list_background));
	}

}

//
//
//
// Feeling comfortable with basic library usage ?? Wait !! There are more demos
// in this Sample Project like,
// 1.Usage of CursorGridAdapter for handling DB data.
// 2.Usage of PositionCalculator class to maintain correct position
// after rotation.
// 3.Handling card click events.
// 4.Handling click events of child views in a card in very easy way.
// 5.Usage of Tap to Load More functionality
// 6.Using Auto load more feature of library
// 7.Limiting Auto Load More on max items reached
// 8.Binding data through ContentProvider/CursorLoader mechanism
// 9.Supporting fixed number of data items
// 10.Best approach to handle data & AysncTasks through rotation of screen.
//
// So goto AndroidManifest & start tracking different feature's demos (All the
// demos are presented considering the OOPs concept so that it becomes very
// clear what line of code needs to be added for using various features of
// library.)
//
//
//
