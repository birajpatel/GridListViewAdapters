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

import android.app.Activity;
import android.app.Fragment;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;

import com.birin.gridlistviewadaptersdemo.common.Constants;
import com.birin.gridlistviewadaptersdemo.common.RandomInfoGenerator;

/**
 * Helper fragment class which will be used to hold data across configuration
 * changes of activity life-cycle as mentioned in below link
 */
// http://www.androiddesignpatterns.com/2013/04/retaining-objects-across-config-changes.html
public class CursorRetainingFragment extends Fragment {

	private DummyDataGeneratorCallback mCallbacks;
	private DummyTask mTask;
	private boolean isTaskRunning;
	public static final String KEY_MAX_ITEMS = "KEY_MAX_ITEMS";

	/**
	 * Hold a reference to the parent Activity so we can report the task's
	 * current progress and results. The Android framework will pass us a
	 * reference to the newly created Activity after each configuration change.
	 */
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if (activity instanceof DummyDataGeneratorCallback) {
			mCallbacks = (DummyDataGeneratorCallback) activity;
		}
	}

	/**
	 * This method will only be called once when the retained Fragment is first
	 * created.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Retain this fragment across configuration changes.
		setRetainInstance(true);
		runAddDataTaskToEnsureMinimumData();
	}

	/**
	 * Set the callback to null so we don't accidentally leak the Activity
	 * instance.
	 */
	@Override
	public void onDetach() {
		super.onDetach();
		mCallbacks = null;
	}

	public boolean isTaskRunning() {
		return isTaskRunning;
	}

	private int getMaxAllowedItems() {
		int maxAllowed = Integer.MAX_VALUE;
		Bundle extras = getArguments();
		if (null != extras && extras.containsKey(KEY_MAX_ITEMS)) {
			maxAllowed = extras.getInt(KEY_MAX_ITEMS);
		}
		return maxAllowed;
	}

	private void runAddDataTaskToEnsureMinimumData() {
		runAddDataTaskIfNotRunning(true);
	}

	public void runAddDataTaskOnLoadMoreRequest() {
		runAddDataTaskIfNotRunning(false);
	}

	private void runAddDataTaskIfNotRunning(boolean isEnsureRun) {
		if (isTaskRunning() == false) {
			if (null != mTask) {
				mTask.cancel(true);
				mTask = null;
			}
			// Create and execute the background task.
			mTask = new DummyTask(isEnsureRun);
			mTask.execute();
		}
	}

	public boolean canLoadMoreData(int currentDataSize) {
		return currentDataSize < getMaxAllowedItems();
	}

	/**
	 * A dummy task that performs some (dumb) background work and proxies
	 * progress updates and results back to the Activity.
	 * 
	 */
	private class DummyTask extends AsyncTask<Void, Integer, Void> {

		private boolean isEnsureRun;

		public DummyTask(boolean isEnsureRun) {
			this.isEnsureRun = isEnsureRun;
		}

		@Override
		protected void onPreExecute() {
			isTaskRunning = true;
			if (mCallbacks != null) {
				mCallbacks.onDataGeneratorTaskExecuting();
			}
		}

		@Override
		protected Void doInBackground(Void... ignore) {
			Activity activity = getActivity();
			if (null != activity) {
				ContentResolver resolver = activity.getContentResolver();
				Cursor existingData = resolver.query(
						TestContentProvider.CONTENT_URI_EMPLOYEE, null, null,
						null, null);
				int currentSize = existingData == null ? 0 : existingData
						.getCount();
				if (isEnsureRun == false || currentSize <= 0) {
					generateNewDummyDataIfCanLoadMoreData(currentSize, resolver);
				}
			}
			return null;
		}

		private void generateNewDummyDataIfCanLoadMoreData(int currentSize,
				ContentResolver resolver) {
			if (canLoadMoreData(currentSize) == true) {
				generateNewDummyDataAndAddToDB(currentSize, resolver);
			}
		}

		@Override
		protected void onCancelled() {
			isTaskRunning = false;
		}

		@Override
		protected void onPostExecute(Void result) {
			isTaskRunning = false;
		}

	}

	private void generateNewDummyDataAndAddToDB(int currentSize,
			ContentResolver resolver) {
		try {
			Thread.sleep(Constants.DUMMY_DELAY_IN_MILLIS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ContentValues valuesToInsert = new ContentValues();
		for (int i = currentSize; i < (currentSize + 20); i++) {
			valuesToInsert.clear();
			updateValuesToInsert(valuesToInsert, i);
			resolver.insert(TestContentProvider.CONTENT_URI_EMPLOYEE,
					valuesToInsert);
		}
	}

	private void updateValuesToInsert(ContentValues valuesToInsert, int i) {
		valuesToInsert.put(TestContentProviderSqlHelper.EMPLOYEE_NAME, "id:"
				+ i);
		valuesToInsert.put(TestContentProviderSqlHelper.EMPLOYEE_COLUMN_CHAR,
				Character.toString(RandomInfoGenerator.getRandomChar()));
		valuesToInsert.put(
				TestContentProviderSqlHelper.EMPLOYEE_COLUMN_CHAR_COLOR,
				RandomInfoGenerator.getRandomColor());

	}

	public interface DummyDataGeneratorCallback {

		public void onDataGeneratorTaskExecuting();
	}

}
