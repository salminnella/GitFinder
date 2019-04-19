package com.salmin.gitfinder;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.salmin.gitfinder.models.RepoResponse;
import com.salmin.gitfinder.view.RepoListViewModel;
import com.salmin.gitfinder.view.ViewModelFactory;
import com.salmin.gitfinder.view.adapter.RepoListAdapter;

import java.util.List;

import javax.inject.Inject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dagger.android.AndroidInjection;

public class MainActivity extends AppCompatActivity {


	@Inject
	ViewModelFactory viewModelFactory;
//	private MainActivityBinding binding;
	private RepoListViewModel repoListViewModel;
	private RepoListAdapter repoListAdapter;

	private EditText searchQuery;
	private RecyclerView recyclerView;
	private RepoListAdapter adapter;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		AndroidInjection.inject(this);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initView();
		initViewModel();

		searchQuery.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
				if (i == EditorInfo.IME_ACTION_SEARCH) {
					repoListViewModel.getRepositories(searchQuery.getText().toString());
					searchQuery.setText("");
					return true;
				}
				return false;
			}
		});
	}

	private void initView() {
		searchQuery = (EditText) findViewById(R.id.search_edit_text_main);
		recyclerView = (RecyclerView) findViewById(R.id.repo_list_main);
		initAdapter();

	}

	private void initAdapter() {
		repoListAdapter = new RepoListAdapter(this);
		recyclerView.setAdapter(repoListAdapter);
		RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
		recyclerView.setLayoutManager(layoutManager);
	}

	private void initViewModel() {
		repoListViewModel = ViewModelProviders.of(this, viewModelFactory).get(RepoListViewModel.class);

		repoListViewModel.organizationRepos.observe(this, new Observer<List<RepoResponse>>() {
			@Override
			public void onChanged(List<RepoResponse> repoResponses) {
				if (repoResponses == null || repoResponses.size() == 0)
					Toast.makeText(MainActivity.this, "No Results Found!!", Toast.LENGTH_SHORT).show();
				else
					repoListAdapter.setData(repoResponses);
			}
		});

		repoListViewModel.errorEvent.observe(this, new Observer<Boolean>() {
			@Override
			public void onChanged(Boolean aBoolean) {
				Toast.makeText(MainActivity.this, "Error!!", Toast.LENGTH_SHORT).show();
			}
		});
	}
}
