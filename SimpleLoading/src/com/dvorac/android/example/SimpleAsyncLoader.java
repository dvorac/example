package com.dvorac.android.example;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.dvorac.android.example.async.AsyncLoader;
import com.dvorac.android.example.async.AsyncResult;

public class SimpleAsyncLoader extends AsyncLoader<List<String>> {

	public SimpleAsyncLoader(Context context) {
		super(context);
	}

	@Override
	public AsyncResult<List<String>> loadInBackground() {

		ArrayList<String> list = new ArrayList<String>();
		list.add("one");
		list.add("two");
		list.add("three");
		
		try {
			// simulate long-running fetch
			Thread.sleep(2000);
			// success
			return new AsyncResult<List<String>>(list, null);
		} catch (Exception e) {
			// error
			return new AsyncResult<List<String>>(null, e);
		}
	}

}
