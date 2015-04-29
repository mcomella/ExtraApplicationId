package me.mcomella.applicationid;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Browser;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;;

public class ExtraApplicationId extends Activity
{

    private final String LINK_1_URL = "http://kotaku.com";
    private final String LINK_2_URL = "https://en.wikipedia.org/wiki/IU_%28singer%29";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        final View link1 = findViewById(R.id.link1);
        link1.setOnClickListener(new ApplicationIDOnClickListener(LINK_1_URL));

        final View link2 = findViewById(R.id.link2);
        link2.setOnClickListener(new ApplicationIDOnClickListener(LINK_2_URL));
    }

    private Intent createIntentWithApplicationID(String url) {
        final Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        intent.putExtra(Browser.EXTRA_APPLICATION_ID, getPackageName());
        return intent;
    }

    private class ApplicationIDOnClickListener implements OnClickListener {
        private final String url;

        public ApplicationIDOnClickListener(String url) {
            this.url = url;
        }

        @Override
        public void onClick(View v) {
            final Intent intent = createIntentWithApplicationID(url);
            startActivity(intent);
        }
    }
}
