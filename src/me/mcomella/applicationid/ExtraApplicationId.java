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
    private final String LINK_3_URL = "http://indefinitelywild.gizmodo.com/";
    private final String LINK_4_URL = "https://mozilla.org";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        final View link1 = findViewById(R.id.link1);
        link1.setOnClickListener(new IntentOnClickListener(LINK_1_URL, true));

        final View link2 = findViewById(R.id.link2);
        link2.setOnClickListener(new IntentOnClickListener(LINK_2_URL, true));

        final View link3 = findViewById(R.id.link3);
        link3.setOnClickListener(new IntentOnClickListener(LINK_3_URL, false));

        final View link4 = findViewById(R.id.link4);
        link4.setOnClickListener(new IntentOnClickListener(LINK_4_URL, false));
    }


    private Intent createIntent(String url, boolean withApplicationId) {
        final Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));

        if (withApplicationId) {
            intent.putExtra(Browser.EXTRA_APPLICATION_ID, getPackageName());
        }

        return intent;
    }

    private class IntentOnClickListener implements OnClickListener {
        private final String url;
        private final boolean withApplicationId;

        public IntentOnClickListener(String url, boolean withApplicationId) {
            this.url = url;
            this.withApplicationId = withApplicationId;
        }

        @Override
        public void onClick(View v) {
            final Intent intent = createIntent(url, withApplicationId);
            startActivity(intent);
        }
    }
}
