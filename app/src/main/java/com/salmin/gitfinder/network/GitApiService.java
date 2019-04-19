package com.salmin.gitfinder.network;

import com.salmin.gitfinder.models.RepoResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GitApiService {

	// https://api.github.com/search/repositories?q=org:google&sort=stars&order=desc
	// https://api.github.com/orgs/google/repos?type=all

	@GET("/search/repositories")
	Call<RepoResponse> getRepositories(@Query("q") String query, @Query("sort") String sort);

	@GET("/orgs/{org}/repos?type=all")
	Call<List<RepoResponse>> getOrgRepos(@Path("org") String org);
}
