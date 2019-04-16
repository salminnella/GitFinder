package com.salmin.gitfinder.view;

import android.app.Application;
import android.view.View;

import com.salmin.gitfinder.models.RepoResponse;
import com.salmin.gitfinder.models.Repository;
import com.salmin.gitfinder.network.GitApiWrapper;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepoListViewModel extends BaseViewModel {

	public MutableLiveData<List<Repository>> organizationRepos = new MutableLiveData<>();
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
		showProgress.setValue(View.VISIBLE);
		GitApiWrapper.getInstance().searchForOrganizations(query, new Callback<RepoResponse>() {
			@Override
			public void onResponse(Call<RepoResponse> call, Response<RepoResponse> response) {
				if (response.isSuccessful()) {
					assert response.body() != null;
					organizationRepos.setValue(response.body().items);
				} else {
					errorEvent.setValue(true);
					showProgress.setValue(View.GONE);
				}
			}

			@Override
			public void onFailure(Call<RepoResponse> call, Throwable t) {
				errorEvent.setValue(true);
				showProgress.setValue(View.GONE);
			}
		});
	}
}
