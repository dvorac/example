package com.dvorac.android.example;

import java.util.List;

import com.dvorac.android.example.async.AsyncLoader;
import com.dvorac.android.example.async.AsyncResult;

import android.app.ListActivity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;

public class SimpleLoadingActivity extends ListActivity implements LoaderCallbacks<AsyncResult<List<String>>> {

	ArrayAdapter<String> mAdapter;
	View mLoadingView;
	View mEmptyView;
	View mErrorView;
	
	//
	// ACTIVITY LIFECYCLE
	//
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// init adapter
		mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
		setListAdapter(mAdapter);

		LayoutInflater inflater = LayoutInflater.from(this);
		
		// init loading view
		mLoadingView = inflater.inflate(R.layout.loading, null);
		mLoadingView.setVisibility(View.GONE);
		addContentView(mLoadingView, new LayoutParams(getListView().getLayoutParams()));
		
		// init empty view
		mEmptyView = inflater.inflate(R.layout.empty, null);
		mEmptyView.setVisibility(View.GONE);
		addContentView(mEmptyView, new LayoutParams(getListView().getLayoutParams()));
		
		// init error view
		mErrorView = inflater.inflate(R.layout.error, null);
		mErrorView.setVisibility(View.GONE);
		addContentView(mErrorView, new LayoutParams(getListView().getLayoutParams()));
		
		// initialize a new loader, or start an already existing one
		getLoaderManager().initLoader(0, null, this);
		
	}
	
	//
	// LOADER CALLBACKS
	//
	
	@Override
	public Loader<AsyncResult<List<String>>> onCreateLoader(int id, Bundle args) {
		// beginning a new load; show loading view
		showLoading();
		return new SimpleAsyncLoader(this);
	}

	@Override
	public void onLoadFinished(Loader<AsyncResult<List<String>>> loader, AsyncResult<List<String>> data) {
		// load complete
		if (data.getException() != null) {
			// load error; clear adapter and show error view
			mAdapter.clear();
			showError();
		} else {
			// load success; fill adapter
			mAdapter.clear();
			mAdapter.addAll(data.getData());
			if (mAdapter.isEmpty()) {
				// adapter was empty; show empty view
				showEmpty();
			}
		}
	}

	@Override
	public void onLoaderReset(Loader<AsyncResult<List<String>>> data) {
		// reseting load; flush adapter
		mAdapter.clear();
	}
	
	//
	// BUTTON HANDLERS
	//

	public void onClickRefresh(View v) {
		// attempt new load
		getLoaderManager().restartLoader(0, null, this);
	}
	
	public void onClickRetry(View v) {
		// attempt new load
		getLoaderManager().restartLoader(0, null, this);
	}

	//
	// HELPERS
	//
	
	void showEmpty() {
		mLoadingView.setVisibility(View.GONE);
		mErrorView.setVisibility(View.GONE);
		
		mEmptyView.setVisibility(View.VISIBLE);
		getListView().setEmptyView(mEmptyView);
	}
	
	void showLoading() {
		mEmptyView.setVisibility(View.GONE);
		mErrorView.setVisibility(View.GONE);
		
		mLoadingView.setVisibility(View.VISIBLE);
		getListView().setEmptyView(mLoadingView);
	}
	
	void showError() {
		mLoadingView.setVisibility(View.GONE);
		mEmptyView.setVisibility(View.GONE);
		
		mErrorView.setVisibility(View.VISIBLE);
		getListView().setEmptyView(mErrorView);
	}
}
