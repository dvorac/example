package com.dvorac.android.example.async;

import android.content.AsyncTaskLoader;
import android.content.Context;

public abstract class AsyncLoader<T> extends AsyncTaskLoader<AsyncResult<T>> {

	AsyncResult<T> result;
	
	public AsyncLoader(Context context) {
		super(context);
	}
	
	@Override
	protected void onStartLoading() {
		if (result != null) {
			deliverResult(result);
		}

		if (takeContentChanged() || result == null) {
			forceLoad();
		}
	}
	
	@Override
	public abstract AsyncResult<T> loadInBackground();

	@Override
	public void deliverResult(AsyncResult<T> data) {
		if (isReset()) {
			// a query came while the loader was stopped.
			return;
		}
		result = data;
		super.deliverResult(data);
	}

	@Override
	protected void onStopLoading() {
		cancelLoad();
	}

	@Override
	protected void onReset() {
		super.onReset();
		onStopLoading();
		result = null;
	}

}
