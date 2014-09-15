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
package com.birin.listgridadapter.datasetup;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;

/**
 * Helper fragment class which will be used to hold data across configuration
 * changes of activity life-cycle as mentioned in below link
 */
// http://www.androiddesignpatterns.com/2013/04/retaining-objects-across-config-changes.html
public class RetainedDataFragment extends Fragment {

	private DummyDataGeneratorCallback mCallbacks;
	private DummyTask mTask;
	private ArrayList<Employee> listData = new ArrayList<Employee>();
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
		mCallbacks = (DummyDataGeneratorCallback) activity;
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
		runAddDataTask();
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

	public ArrayList<Employee> getListData() {
		return listData;
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

	public void runAddDataTask() {
		if (isTaskRunning() == false && canLoadMoreData()) {
			if (null != mTask) {
				mTask.cancel(true);
				mTask = null;
			}
			// Create and execute the background task.
			mTask = new DummyTask();
			mTask.execute();
		}
	}

	public boolean canLoadMoreData() {
		return listData.size() < getMaxAllowedItems();
	}

	/**
	 * A dummy task that performs some (dumb) background work and proxies
	 * progress updates and results back to the Activity.
	 * 
	 * Note that we need to check if the callbacks are null in each method in
	 * case they are invoked after the Activity's and Fragment's onDestroy()
	 * method have been called.
	 */
	private class DummyTask extends
			AsyncTask<Void, Integer, ArrayList<Employee>> {

		@Override
		protected void onPreExecute() {
			isTaskRunning = true;
			if (mCallbacks != null) {
				mCallbacks.onDataGeneratorTaskExecuting();
			}
		}

		/**
		 * Note that we do NOT call the callback object's methods directly from
		 * the background thread, as this could result in a race condition.
		 */
		@Override
		protected ArrayList<Employee> doInBackground(Void... ignore) {
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return generateNewDummyDataAndAddToList();
		}

		@Override
		protected void onCancelled() {
			isTaskRunning = false;
			if (mCallbacks != null) {
				mCallbacks.onDataGeneratorTaskCancelled();
			}
		}

		@Override
		protected void onPostExecute(ArrayList<Employee> newDataList) {
			isTaskRunning = false;
			if (mCallbacks != null) {
				mCallbacks.onNewDummyDataGenerated(newDataList);
			}
		}

		private ArrayList<Employee> generateNewDummyDataAndAddToList() {
			int currentSize = listData.size();
			ArrayList<Employee> newDummyData = new ArrayList<Employee>();
			for (int i = currentSize; i < (currentSize + 20); i++) {
				newDummyData.add(new Employee(i));
			}
			listData.addAll(newDummyData);
			return newDummyData;
		}

	}

	/**
	 * Callback interface through which the fragment will report the task's
	 * progress and results back to the Activity.
	 */
	public interface DummyDataGeneratorCallback {

		public void onDataGeneratorTaskExecuting();

		public void onDataGeneratorTaskCancelled();

		public void onNewDummyDataGenerated(ArrayList<Employee> newDataList);

	}

}
