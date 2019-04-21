package com.salmin.gitfinder.dagger;

import com.salmin.gitfinder.view.viewmodel.RepoListViewModel;
import com.salmin.gitfinder.view.viewmodel.ViewModelFactory;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * used to provide a map of view models through dagger that is used by the ViewModelFactory class
 */

@Module
public abstract class ViewModelModule {

	@Binds
	abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

	@Binds
	@IntoMap
	@ViewModelKey(RepoListViewModel.class)
	protected abstract ViewModel repoListViewModel(RepoListViewModel repoListViewModel);
}
