package com.salmin.gitfinder.network;

import com.salmin.gitfinder.models.RepoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GitApiService {

	// https://api.github.com/search/repositories?q=org:google&sort=stars&order=desc

	@GET("/search/repositories")
	Call<RepoResponse> getRepositories(@Query("q") String query, @Query("sort") String sort);
}
