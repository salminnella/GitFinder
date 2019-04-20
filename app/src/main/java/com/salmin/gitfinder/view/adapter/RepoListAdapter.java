package com.salmin.gitfinder.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.salmin.gitfinder.BR;
import com.salmin.gitfinder.R;
import com.salmin.gitfinder.databinding.RepoItemsBinding;
import com.salmin.gitfinder.models.RepoResponse;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class RepoListAdapter extends RecyclerView.Adapter<RepoListAdapter.ViewHolder> {

	private final LayoutInflater inflater;
	private final List<RepoResponse> data;

	public RepoListAdapter(Context context) {
		inflater = LayoutInflater.from(context);
		data = new ArrayList<>();
	}

	public void setData(List<RepoResponse> data) {
		if (data == null || data.size() == 0) {
			return;
		}
		this.data.clear();
		this.data.addAll(data);
		notifyDataSetChanged();
	}

	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
		RepoItemsBinding repoBinding = DataBindingUtil.inflate(inflater, R.layout.repo_items, viewGroup, false);
		return new ViewHolder(repoBinding);
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
		viewHolder.binding.setVariable(BR.repository, data.get(i));
		viewHolder.binding.setVariable(BR.imageAvatar, data.get(i).owner.avatarUrl);
	}

	@Override
	public int getItemCount() {
		return data.size();
	}

	class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

		private final RepoItemsBinding binding;

		public ViewHolder(@NonNull RepoItemsBinding binding) {
			super(binding.getRoot());
			this.binding = binding;

			binding.getRoot().setOnClickListener(this);
		}

		@Override
		public void onClick(View view) {
			Log.d("Adapter", "onClick: was called ");
		}
	}
}
