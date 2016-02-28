package com.shrikant.mytwitter;

import com.shrikant.mytwitter.adapters.TweetsPagerAdapter;
import com.shrikant.mytwitter.tweetmodels.Me;
import com.shrikant.mytwitter.tweetmodels.Tweet;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TimelineActivity extends AppCompatActivity {
    private final int REQUEST_CODE = 200;

    public static Me me;


    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.sliding_tabs) TabLayout mTabLayout;
    //@Bind(R.id.pager_header) PagerTabStrip mPagerTabStrip;
    @Bind(R.id.viewpager) ViewPager vpPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setLogo(R.mipmap.ic_twitter_logo);

        vpPager.setAdapter(new TweetsPagerAdapter(getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(vpPager);

        //mTweets = new ArrayList<>();

    }

    //Send an API request to get the timeline json
    // Fill the view by creating the tweet objects from the json

    //[] == JsonArray


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.timeline, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            //case R.id.miCompose:
                //composeTweet();
                //getMyInfo();
                //launchComposeDialog();

        }
        return super.onOptionsItemSelected(item);
    }

    public void composeTweet() {

        //Toast.makeText(this, "Composed clicked", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, ComposeActivity.class);
        startActivityForResult(i, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i("TimelineActivity", "Back to onactivity result");
        // REQUEST_CODE is defined above
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            // Extract name value from result extras
            Tweet tweet = (Tweet) data.getExtras().get("tweet");
            int code = data.getExtras().getInt("code", 0);
            // Toast the name to display temporarily on screen
            Log.i("TimelineActivity", tweet.getText().toString());
            Toast.makeText(this, tweet.getText().toString(), Toast.LENGTH_SHORT).show();

//            mTweets.addFirst(tweet);
//            //int curSize = mComplexRecyclerViewArticleAdapter.getItemCount();
//            mComplexRecyclerViewArticleAdapter.notifyItemRangeInserted(0,
//                    1);
//            layoutManager.scrollToPosition(0);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("TimelineActivity", "Saving to DB");
        //clearDB();
        //saveToDB();
    }



    public void launchComposeDialog() {

        ComposeFragment composeFragment = new ComposeFragment();
        FragmentManager fm = getSupportFragmentManager();
        composeFragment.show(fm, "compose");
    }

    public void onProfileClick(MenuItem item) {
        Intent i = new Intent(this, ProfileActivity.class);
        startActivity(i);
    }

    public void showProfile(View view) {
        Intent i = new Intent(this, ProfileActivity.class);
        Log.i("TimelineActivity", "Clicked user profile is: " + (String)view.getTag());
        i.putExtra("screen_name", (String)view.getTag());
        startActivity(i);
    }

//    void getMyInfo() {
//        mTwitterClient.getUserTimeline(
//            new TextHttpResponseHandler() {
//                @Override
//                public void onSuccess(int statusCode, Header[] headers, String responseString) {
//                    List<Tweet> fetchedTweets = new ArrayList<Tweet>();
//                    if (responseString != null) {
//                        Log.i("ComposeFragment", responseString);
//                        try {
//                            Gson gson = new GsonBuilder().create();
//                            JsonArray jsonArray = gson.fromJson(responseString, JsonArray.class);
//                            if (jsonArray != null) {
//                                for (int i = 0; i < jsonArray.size(); i++) {
//                                    JsonObject jsonTweetObject = jsonArray.get(i).getAsJsonObject();
//
//                                    if (jsonTweetObject.has("user")) {
//                                        JsonObject jsonObject = jsonTweetObject.get("user")
//                                                .getAsJsonObject();
//                                        me = Me.fromJsonObjectToMe(jsonObject);
//                                        if (!TextUtils.isEmpty(me.getMyName())
//                                                && !TextUtils.isEmpty(me.getMyProfileImageUrl())
//                                                && !TextUtils.isEmpty(me.getMyTwitterHandle())) {
//                                            break;
//                                        }
//                                    }
//
//                                }
//                            }
//                        } catch (JsonParseException e) {
//                            Log.d("Async onSuccess", "Json parsing error:" + e.getMessage(), e);
//                        }
//                    }
//                    launchComposeDialog();
//                }
//
//                @Override
//                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//                    Log.w("AsyncHttpClient", "HTTP Request failure: " + statusCode + " " +
//                            throwable.getMessage());
//                }
//            });
//    }


}
