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

public class ParentDemoMenuList extends BaseDemoMenuList {

	private final Demo<?>[] AVAILABLE_DEMOS = {
			new Demo<JavaUtilListDataDemos>(
					"ListGridAdapter Demo\nUsed for java.util.List data\n("
							+ JavaUtilListDataDemos.AVAILABLE_DEMOS.length
							+ " Demos)", JavaUtilListDataDemos.class),
			new Demo<CursorDataDemos>(
					"CursorGridAdapter Demo\nUsed for android.database.Cursor data\n("
							+ CursorDataDemos.AVAILABLE_DEMOS.length
							+ " Demos)", CursorDataDemos.class),

	};

	@Override
	protected Demo<?>[] getDemos() {
		return AVAILABLE_DEMOS;
	}

}
