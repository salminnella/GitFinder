package com.salmin.gitfinder.models;

import com.google.gson.annotations.SerializedName;

import java.util.Comparator;

public class RepoResponse implements Comparator<RepoResponse> {

	@SerializedName("owner")
	public RepoResponse.Owner owner;

	@SerializedName("name")
	public String name;

	@SerializedName("description")
	public String description;

	@SerializedName("stargazers_count")
	public String stargazersCount;

	@SerializedName("html_url")
	public String repoUrl;

	public class Owner {
		@SerializedName("avatar_url")
		public String avatarUrl;
	}

	/**
	 * compares the stars count to sort the returned list of repos from Github api
	 *
	 * @param repoResponse RepoResponse
	 * @return int
	 */
	@Override
	public int compare(RepoResponse repoResponse, RepoResponse t1) {
		return Integer.parseInt(t1.stargazersCount) - Integer.parseInt(repoResponse.stargazersCount);
	}
}
