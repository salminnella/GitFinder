package com.salmin.gitfinder.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RepoResponse {

	@SerializedName("items")
	public List<Repository> items;
}
