package com.zheng.feature.activity;

import android.support.test.rule.ActivityTestRule;

import com.zheng.feature.detail.DetailActivity;

import org.junit.Rule;
import org.junit.Test;

import zheng.com.naiqingapp.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.not;

/**
 * Created by Administrator on 9/26/17.
 */
public class DetailActivityTest {
    @Rule
    public ActivityTestRule<DetailActivity> rule = new ActivityTestRule<DetailActivity>(DetailActivity.class, true, false);

    @Test
    public void eventNotFound(){
        rule.launchActivity(null);
        onView(withId(R.id.tv_date)).check(matches(not(isDisplayed())));
    }
}