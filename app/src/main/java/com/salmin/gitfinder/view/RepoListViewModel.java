package com.salmin.gitfinder.view;

import android.app.Application;
import android.util.Log;
import android.view.View;

import com.salmin.gitfinder.models.RepoResponse;
import com.salmin.gitfinder.network.GitApiWrapper;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

public class RepoListViewModel extends BaseViewModel {

	private static final String TAG = "RepoListVM";
	public MutableLiveData<List<RepoResponse>> organizationRepos = new MutableLiveData<>();
	public MutableLiveData<Boolean> errorEvent = new MutableLiveData<>();
	public MutableLiveData<Integer> showProgress = new MutableLiveData<>();

	@Inject
	public RepoListViewModel(@NonNull Application application) {
		super(application);
		showProgress.setValue(View.GONE);
	}

	/**
	 * Queries the Git API to retrieve the repositories for the desired organization
	 *
	 * @param query String
	 */
	public void getRepositories(String query) {
		Log.d(TAG, "getRepositories: was called");
		showProgress.setValue(View.VISIBLE);


		List<RepoResponse> repos = GitApiWrapper.getInstance().getTopRepos(query);
		organizationRepos.setValue(repos);


		Log.d(TAG, "getRepositories: " + GitApiWrapper.getInstance().getTopRepos(query));

//		GitApiWrapper.getInstance().searchForOrganizations(query, new Callback<List<RepoResponse>>() {
//			@Override
//			public void onResponse(Call<List<RepoResponse>> call, Response<List<RepoResponse>> response) {
//				Log.d(TAG, "onResponse: " + response.isSuccessful());
//				if (response.isSuccessful()) {
//					assert response.body() != null;
//					Log.d(TAG, "name: " + response.body().get(0).name +
//							"stars: " + response.body().get(0).stargazersCount);
//					organizationRepos.setValue(response.body());
//				} else {
//					errorEvent.setValue(true);
//					showProgress.setValue(View.GONE);
//				}
//			}
//
//			@Override
//			public void onFailure(Call<List<RepoResponse>> call, Throwable t) {
//				Log.e(TAG, "onFailure: ", t);
//				errorEvent.setValue(true);
//				showProgress.setValue(View.GONE);
//			}
//		});
	}
}
