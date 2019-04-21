package com.salmin.gitfinder.view.viewmodel;

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
	}

	/**
	 * Queries the Git API to retrieve the repositories for the desired organization
	 *
	 * @param query String
	 */
	public void getRepositories(String query) {
		showProgress.setValue(View.VISIBLE);

		getInstance().getTopRepos(query, new GitApiCallback() {
			@Override
			public void onResponse(List<RepoResponse> responses) {
				showProgress.postValue(View.GONE);
				organizationRepos.postValue(responses);
			}

			@Override
			public void onError(Throwable throwable) {
				Log.e(TAG, "accept: ", throwable);
				showProgress.postValue(View.GONE);
				errorEvent.postValue(true);
			}
		});
	}
}
