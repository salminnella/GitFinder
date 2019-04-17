package com.salmin.gitfinder.dagger;

import com.salmin.gitfinder.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

	/**
	 * Generates the Android Injector for the activities
	 * defined in this class. Allows injection into an activity
	 * with AndroidInjection.inject(this) in onCreate
	 *
	 * @return
	 */
	@ContributesAndroidInjector
	abstract MainActivity contributeMainActivity();
}
