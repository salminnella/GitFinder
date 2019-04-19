package com.salmin.gitfinder.network;

import com.salmin.gitfinder.models.RepoResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class GitApiWrapper {

	// TODO may have to convert this to a module and @provides the wrapper
	private static final String BASE_URL = "https://api.github.com/";
	private static GitApiWrapper API_WRAPPER;
	private final GitApiService gitAPI;

	public static GitApiWrapper getInstance() {
		if (API_WRAPPER == null) {
			API_WRAPPER = new GitApiWrapper();
		}

		return API_WRAPPER;
	}

	private GitApiWrapper() {
		Retrofit build = new Retrofit.Builder().baseUrl(BASE_URL)
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		gitAPI = build.create(GitApiService.class);
	}

	public Call<List<RepoResponse>> searchForOrganizations(String query, Callback<List<RepoResponse>> callback) {
//		Call<RepoResponse> call = gitAPI.getRepositories(query, "stars");
		Call<List<RepoResponse>> call = gitAPI.getOrgRepos(query);
		call.enqueue(callback);

		return call;
	}

}
