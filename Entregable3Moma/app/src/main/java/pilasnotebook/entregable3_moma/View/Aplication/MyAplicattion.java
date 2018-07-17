package pilasnotebook.entregable3_moma.View.Aplication;

import android.app.Application;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

public class MyAplicattion extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
    }

    //lo dejo en activity por un tema de orden pero no es una activity
}
