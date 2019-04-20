package com.salmin.gitfinder.view;

import android.app.Application;
import android.util.Log;
import android.view.View;

import com.salmin.gitfinder.models.RepoResponse;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import static com.salmin.gitfinder.network.GitApiWrapper.GitApiCallback;
import static com.salmin.gitfinder.network.GitApiWrapper.getInstance;

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


		getInstance().getTopRepos(query, new GitApiCallback() {
			@Override
			public void onResponse(List<RepoResponse> responses) {
				organizationRepos.postValue(responses);
				showProgress.postValue(View.GONE);
			}

			@Override
			public void onError() {
				errorEvent.postValue(true);
				showProgress.postValue(View.GONE);
			}
		});
	}
}
