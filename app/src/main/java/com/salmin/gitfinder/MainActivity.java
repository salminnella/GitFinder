package com.salmin.gitfinder;

import android.os.Bundle;

import com.salmin.gitfinder.view.RepoListViewModel;
import com.salmin.gitfinder.view.ViewModelFactory;

import javax.inject.Inject;

import androidx.appcompat.app.AppCompatActivity;
import dagger.android.AndroidInjection;

public class MainActivity extends AppCompatActivity {


	@Inject
	ViewModelFactory viewModelFactory;
//	private MainActivityBinding binding;
	private RepoListViewModel repoListViewModel;
//	private RepoListAdapter repoListAdapter;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		AndroidInjection.inject(this);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initView();
		initViewModel();
	}

	private void initView() {

	}

	private void initViewModel() {

	}
}
