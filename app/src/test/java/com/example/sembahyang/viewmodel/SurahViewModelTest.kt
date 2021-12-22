package com.example.sembahyang.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.sembahyang.model.ModelSurah
import org.junit.Before
import org.junit.Test
import org.mockito.junit.MockitoJUnitRunner

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock

@RunWith(MockitoJUnitRunner::class)
class SurahViewModelTest {

    private lateinit var viewModel: SurahViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()



    @Mock
    private lateinit var observer: Observer<List<ModelSurah>>


    @Before
    fun setUp() {
        viewModel = SurahViewModel()
    }

    @Test
    fun getSurah() {
        val surahModel = viewModel.getSurah()
        val surah = MutableLiveData<ArrayList<ModelSurah>>()
        assertNotNull(surahModel)
//        assertEquals(114)
    }
}