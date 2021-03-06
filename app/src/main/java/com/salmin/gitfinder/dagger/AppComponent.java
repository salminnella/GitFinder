package com.salmin.gitfinder.dagger;

import android.app.Application;

import com.salmin.gitfinder.network.GitApiWrapper;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * defines the connection between the modules and the classes which need the dependency
 */

@Component(modules= {
		GitApiWrapper.class,
		ViewModelModule.class,
		ActivityModule.class,
		AndroidSupportInjectionModule.class})

@Singleton
public interface AppComponent {
	@Component.Builder
	interface Builder {

		@BindsInstance
		Builder application(Application application);

		AppComponent build();
	}


	void inject(AppController appController);
}
