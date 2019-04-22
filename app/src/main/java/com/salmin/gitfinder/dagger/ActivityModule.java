package com.salmin.gitfinder.dagger;

import com.salmin.gitfinder.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

	/**
	 * Generates the Android Injector for activities.
	 *
	 * @return
	 */
	@ContributesAndroidInjector
	abstract MainActivity contributeMainActivity();
}
