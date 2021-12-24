package com.example.sembahyang.activities

import androidx.recyclerview.widget.RecyclerView

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.sembahyang.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {
    private val surah = "Al-Fatihah"
    private val tempatTurun = "mekah"
    private val jumlahAyat = "7 Ayat"
    private val arti = "Pembukaan"

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)


    @Test
    fun loadSurah() {
        onView(withId(R.id.cv_alquran)).perform(click())
        onView(withId(R.id.rv_surah)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_surah)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                114
            )
        )
    }

    @Test
    fun loadAyah() {
        onView(withId(R.id.cv_alquran)).perform(click())
        onView(withId(R.id.rv_surah)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.tv_surah)).check(matches(withText(surah)))
        onView(withId(R.id.tv_arti)).check(matches(withText(arti)))
        onView(withId(R.id.tv_jumlah_ayat)).check(matches(withText(jumlahAyat)))
        onView(withId(R.id.tv_turun)).check(matches(withText(tempatTurun)))
        onView(withId(R.id.tv_arti)).check(matches(withText(arti)))

    }

    @Test
    fun loadJadwal() {
        onView(withId(R.id.cv_jadwal_sholat)).perform(click())
        onView(checkNotNull(withId(R.id.tv_date)))
        onView(checkNotNull(withId(R.id.rl_spin)))
        onView(checkNotNull(withId(R.id.tv_subuh)))
        onView(checkNotNull(withId(R.id.tv_dzuhur)))
        onView(checkNotNull(withId(R.id.tv_ashar)))
        onView(checkNotNull(withId(R.id.tv_maghrib)))
        onView(checkNotNull(withId(R.id.tv_isya)))
    }

    @Test
    fun loadDoa(){
        onView(withId(R.id.cv_doa)).perform(click())
        onView(withId(R.id.rv_doa)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_doa)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                37
            )
        )
    }
}