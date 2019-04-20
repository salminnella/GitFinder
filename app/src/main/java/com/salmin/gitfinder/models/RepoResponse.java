package com.salmin.gitfinder.models;

import com.google.gson.annotations.SerializedName;

public class RepoResponse implements Comparable<RepoResponse>{

	@SerializedName("owner")
	public RepoResponse.Owner owner;

	@SerializedName("name")
	public String name;

	@SerializedName("description")
	public String description;

	@SerializedName("stargazers_count")
	public String stargazersCount;

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
	public int compareTo(RepoResponse repoResponse) {
		int comparedStarCount = Integer.parseInt(repoResponse.stargazersCount);
		return comparedStarCount - Integer.parseInt(this.stargazersCount);
	}
}
