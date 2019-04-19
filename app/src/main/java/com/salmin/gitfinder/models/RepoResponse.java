package com.salmin.gitfinder.models;

import com.google.gson.annotations.SerializedName;

public class RepoResponse {

//	@SerializedName("items")
//	public List<Repository> items;


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
}
