package com.mustafa.artbookhilttesting.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.MediumTest
import com.google.common.truth.Truth.assertThat
import com.mustafa.artbookhilttesting.fragments.ArtDetailsFragment
import com.mustafa.artbookhilttesting.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import javax.inject.Inject
import com.mustafa.artbookhilttesting.R
import com.mustafa.artbookhilttesting.fragments.ArtDetailsFragmentDirections
import com.mustafa.artbookhilttesting.getOrAwaitValue
import com.mustafa.artbookhilttesting.model.ArtModel
import com.mustafa.artbookhilttesting.repository.FakeArtRepository
import com.mustafa.artbookhilttesting.viewmodel.ArtDetailViewModel
import com.mustafa.artbookhilttesting.viewmodel.ArtsViewModel


@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class ArtDetailFragmentTest {


    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()



    @Inject
    lateinit var fragmentFactory: ArtFragmentFactory

    @Before
    fun setup(){
        hiltRule.inject()
    }

    @Test
    fun testNavigationFromArtDetailsToImageApi(){
        val navController = Mockito.mock(NavController::class.java)

        launchFragmentInHiltContainer<ArtDetailsFragment>(
            factory = fragmentFactory
        ){
            Navigation.setViewNavController(requireView(),navController)
        }

        Espresso.onView(ViewMatchers.withId(com.mustafa.artbookhilttesting.R.id.addImage)).perform(click())

        Mockito.verify(navController).navigate(
            ArtDetailsFragmentDirections.actionArtDetailsFragmentToImageApiFragment()
        )

    }

    @Test
    fun onBackPress(){
        val navController = Mockito.mock(NavController::class.java)

        launchFragmentInHiltContainer<ArtDetailsFragment>(
            factory = fragmentFactory
        ){
            Navigation.setViewNavController(requireView(),navController)
        }

        pressBack()

        Mockito.verify(navController).popBackStack()

    }

    @Test
    fun testSave(){
        val testViewmodel = ArtDetailViewModel(FakeArtRepository())
        val artTestViewModel = ArtsViewModel(FakeArtRepository())
        launchFragmentInHiltContainer<ArtDetailsFragment>(
            factory = fragmentFactory
        ){
            viewmodell = testViewmodel
        }

        Espresso.onView(ViewMatchers.withId(com.mustafa.artbookhilttesting.R.id.editArtNamee)).perform(
            replaceText("Mona Lisa")
        )
        Espresso.onView(ViewMatchers.withId(com.mustafa.artbookhilttesting.R.id.editArtArtist)).perform(
            replaceText("Da Vinci")
        )
        Espresso.onView(ViewMatchers.withId(com.mustafa.artbookhilttesting.R.id.editArtYear)).perform(
            replaceText("1700")
        )
        Espresso.onView(ViewMatchers.withId(com.mustafa.artbookhilttesting.R.id.btnSave)).perform(
            click()
        )

        assertThat(artTestViewModel.allData.getOrAwaitValue()).contains(
            ArtModel("","Mona Lisa","Da Vinci","1700")
        )


    }



}