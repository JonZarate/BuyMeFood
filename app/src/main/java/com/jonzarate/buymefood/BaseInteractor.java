package com.jonzarate.buymefood;

import android.os.AsyncTask;

/**
 * Created by JonZarate on 28/02/2018.
 */

public abstract class BaseInteractor extends AsyncTask<Void, Void, Void> {

    protected abstract void executeWorkerThread();
    protected abstract void executeMainThread();

    @Override
    protected Void doInBackground(Void... voids) {
        executeWorkerThread();
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        executeMainThread();
    }


}
