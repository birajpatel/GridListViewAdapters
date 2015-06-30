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
package com.birin.cursorgridadapter.datasetup;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TestContentProviderSqlHelper extends SQLiteOpenHelper {

	public static final String DATABASE_NAME = "TestDb";
	public static final int DATABASE_VERSION = 1;

	public static final String EMPLOYEE_TABLE_NAME = "Employee";
	public static final String INDEX = "_id";
	public static final String EMPLOYEE_NAME = "name";
	public static final String EMPLOYEE_COLUMN_CHAR = "char";
	public static final String EMPLOYEE_COLUMN_CHAR_COLOR = "char_color";
	public static final int EMPLOYEE_TABLE_IDENTIFIER = 1;

	public TestContentProviderSqlHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + EMPLOYEE_TABLE_NAME + " " + "(" + INDEX
				+ " INTEGER PRIMARY KEY AUTOINCREMENT," + EMPLOYEE_NAME
				+ " TEXT" + "," + EMPLOYEE_COLUMN_CHAR + " TEXT  DEFAULT "
				+ Character.toString('a') + "," + EMPLOYEE_COLUMN_CHAR_COLOR
				+ " INTEGER  DEFAULT " + Integer.toString(0) + ");");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + EMPLOYEE_TABLE_NAME);
		onCreate(db);
	}

}
