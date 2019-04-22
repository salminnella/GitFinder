package com.salmin.gitfinder.network;

import com.salmin.gitfinder.models.RepoResponse;

import java.util.List;

import dagger.Module;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Wrapper class for the Github API, creates a Retrofit instance
 * and performs the search for top repositories
 */
@Module
public class GitApiWrapper {

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
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.build();
		gitAPI = build.create(GitApiService.class);
	}

	/**
	 * callback interface to the view model to post the results
	 */
	public interface GitApiCallback {
		void onResponse (List<RepoResponse> responses);

		void onError(Throwable throwable);
	}

	public void getTopRepos(String organization, GitApiCallback callback) {
		gitAPI.getOrgRepos(organization)
				.subscribeOn(Schedulers.io())
				.observeOn(Schedulers.newThread())
				.flatMapIterable((Function<List<RepoResponse>, Iterable<RepoResponse>>)
						repoResponses -> repoResponses)
				.sorted(new RepoResponse())
				.take(3)
				.toList()
				.subscribe(callback::onResponse, callback::onError);
	}
}
