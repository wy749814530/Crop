package com.wang.crop;

/**
 * @WYU-WIN
 * @date 2020/7/23 0023.
 * description：
 */

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

abstract class MonitoredActivity extends Activity {

    private final ArrayList<LifeCycleListener> listeners = new ArrayList<LifeCycleListener>();

    public static interface LifeCycleListener {
        public void onActivityCreated(MonitoredActivity activity);

        public void onActivityDestroyed(MonitoredActivity activity);

        public void onActivityStarted(MonitoredActivity activity);

        public void onActivityStopped(MonitoredActivity activity);
    }

    public static class LifeCycleAdapter implements LifeCycleListener {
        public void onActivityCreated(MonitoredActivity activity) {
        }

        public void onActivityDestroyed(MonitoredActivity activity) {
        }

        public void onActivityStarted(MonitoredActivity activity) {
        }

        public void onActivityStopped(MonitoredActivity activity) {
        }
    }

    public void addLifeCycleListener(LifeCycleListener listener) {
        if (listeners.contains(listener)) return;
        listeners.add(listener);
    }

    public void removeLifeCycleListener(LifeCycleListener listener) {
        listeners.remove(listener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        for (LifeCycleListener listener : listeners) {
            listener.onActivityCreated(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        for (LifeCycleListener listener : listeners) {
            listener.onActivityDestroyed(this);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        for (LifeCycleListener listener : listeners) {
            listener.onActivityStarted(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        for (LifeCycleListener listener : listeners) {
            listener.onActivityStopped(this);
        }
    }

}