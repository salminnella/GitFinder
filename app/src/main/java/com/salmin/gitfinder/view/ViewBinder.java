package com.salmin.gitfinder.view;

import android.content.Context;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;

import androidx.databinding.BindingAdapter;

/**
 * Binders to use the helper methods from the xml
 */

public class ViewBinder {
	@BindingAdapter("loadImage")
	public static void loadImage(ImageView imageView, String url) {
		Context context = imageView.getContext();
		Glide.with(context).load(url).into(imageView);
	}

	@BindingAdapter("hidden")
	public static void hidden(ProgressBar progressBar, int value) {
		progressBar.setVisibility(value);
	}

}
