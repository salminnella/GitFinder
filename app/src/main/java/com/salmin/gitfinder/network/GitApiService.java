package com.salmin.gitfinder.network;

import com.salmin.gitfinder.models.RepoResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitApiService {

	// https://api.github.com/orgs/google/repos?type=all
	@GET("/orgs/{org}/repos?type=all")
	Observable<List<RepoResponse>> getOrgRepos(@Path("org") String org);
}
