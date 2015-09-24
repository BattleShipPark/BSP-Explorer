package com.battleshippark.bsp_explorer;

import android.os.Bundle;

import com.squareup.otto.Bus;

import org.androidannotations.annotations.EBean;

import rx.subjects.PublishSubject;


@EBean
public class EventModel {
	public Bus bus = new Bus();

	public enum ActivityOnCreate {
		EVENT
	}

	public static class ActivityOnRestoreInstanceState {
		private final Bundle savedInstanceState;

		private ActivityOnRestoreInstanceState(Bundle savedInstanceState) {
			this.savedInstanceState = savedInstanceState;
		}

		public static ActivityOnRestoreInstanceState from(Bundle savedInstanceState) {
			return new ActivityOnRestoreInstanceState(savedInstanceState);
		}

		public Bundle getSavedInstanceState() {
			return savedInstanceState;
		}
	}

	public enum ActivityOnResume {
		EVENT
	}

	public static class ActivityOnSaveInstanceState {
		private final Bundle outState;

		private ActivityOnSaveInstanceState(Bundle outState) {
			this.outState = outState;
		}

		public static ActivityOnSaveInstanceState from(Bundle outState) {
			return new ActivityOnSaveInstanceState(outState);
		}

		public Bundle getOutState() {
			return outState;
		}
	}

	public enum ActivityOnDestory {
		EVENT
	}

	public static class ActivityObservable {
		public PublishSubject<Bundle> restoreInstanceState = PublishSubject.create();
	}

	public ActivityObservable activityObservable = new ActivityObservable();
}
