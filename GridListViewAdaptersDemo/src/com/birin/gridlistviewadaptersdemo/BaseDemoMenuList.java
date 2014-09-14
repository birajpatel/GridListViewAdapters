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

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public abstract class BaseDemoMenuList extends Activity implements
		OnItemClickListener {

	private ListView demoMenuListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getLayoutId());
		demoMenuListView = (ListView) findViewById(R.id.list_view);
		demoMenuListView.setAdapter(new DemoMenuListAdapter(
				getApplicationContext()));
		demoMenuListView.setOnItemClickListener(this);
	}

	protected int getLayoutId() {
		return R.layout.demo_menu_screen_layout;
	}

	private class DemoMenuListAdapter extends ArrayAdapter<Demo<?>> {

		public DemoMenuListAdapter(Context context) {
			super(context, R.layout.demo_menu_listrow_layout, R.id.demo_name,
					getDemos());
		}
	}

	protected abstract Demo<?>[] getDemos();

	protected static class Demo<T extends Activity> {

		public Demo(String menuName, Class<T> activity) {
			this.menuName = menuName;
			this.activity = activity;
		}

		public String toString() {
			return menuName;
		};

		Class<T> activity;
		String menuName;

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View arg1, int position,
			long id) {
		if (position < getDemos().length) {
			this.startActivity(new Intent(this, getDemos()[position].activity));
		}
	}

}
