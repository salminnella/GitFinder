package com.salmin.gitfinder.network;

import android.util.Log;

import com.salmin.gitfinder.models.RepoResponse;

import java.util.List;

import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Wrapper class for the Github API, creates a Retrofit instance
 * and performs the search for top repositories
 */
public class GitApiWrapper {

	// TODO may have to convert this to a module and @provides the wrapper
	private static final String TAG = "GitApitWrapper";
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

	public interface GitApiCallback {
		void onResponse (List<RepoResponse> responses);

		void onError();
	}

	public void getTopRepos(String organization, GitApiCallback callback) {
//		CompositeDisposable compositeDisposable = new CompositeDisposable();
//		Disposable disposable =
				gitAPI.getOrgRepos(organization)
				.subscribeOn(Schedulers.io())
				.observeOn(Schedulers.newThread())
				.flatMapIterable(new Function<List<RepoResponse>, Iterable<RepoResponse>>() {
					@Override
					public Iterable<RepoResponse> apply(List<RepoResponse> repoResponses) throws Exception {
						Log.d(TAG, "flatmap iterable");
						return repoResponses;
					}
				})
				.sorted(new RepoResponse())
				.take(3)
				.toList()
				.subscribe(new Consumer<List<RepoResponse>>() {
					@Override
					public void accept(List<RepoResponse> repoResponses) throws Exception {
						callback.onResponse(repoResponses);
					}
				}, new Consumer<Throwable>() {
					@Override
					public void accept(Throwable throwable) throws Exception {
						callback.onError();
						Log.e(TAG, "accept: ", throwable);
					}
				});
	}
}
