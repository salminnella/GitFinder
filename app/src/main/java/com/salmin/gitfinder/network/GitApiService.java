package com.salmin.gitfinder.network;

import com.salmin.gitfinder.models.RepoResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * API endpoint Get method to retrieve Repositories based on Organization search
 * Returns an observable list to used with RxJava
 */
public interface GitApiService {

	@GET("/orgs/{org}/repos?type=all")
	Observable<List<RepoResponse>> getOrgRepos(@Path("org") String org);
}
