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

import com.birin.gridlistviewadaptersdemo.common.RandomInfoGenerator;

public class Employee {

	private String name;
	private final int iconChar;
	private final int iconCharColor;

	public Employee(int id) {
		name = "id:" + id;
		iconChar = RandomInfoGenerator.getRandomChar();
		iconCharColor = RandomInfoGenerator.getRandomColor();
	}

	public String getEmployeeName() {
		return name;
	}

	public void setEmployeeName(String employeeName) {
		this.name = employeeName;
	}

	public char getIconChar() {
		return (char) iconChar;
	}

	public int getIconCharColor() {
		return iconCharColor;
	}

}
