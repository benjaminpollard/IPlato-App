package com.idea.group.iplato.viewModels.viewPdf

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.idea.group.iplato.repositories.RocketRepo
import com.idea.group.iplato.testingUtils.mock
import com.idea.group.iplato.viewModels.RocketsViewModel
import com.idea.group.iplato.repositories.DatabaseRepository
import com.idea.group.iplato.models.responce.RocketModel
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit

open class BaseRocketViewModelTest {
    @Rule
    @JvmField
    var mockitoRule = MockitoJUnit.rule()

    @Rule
    @JvmField
    var instantExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    protected var viewModel: RocketsViewModel? = null

    @Mock
    protected val rocketRepo: RocketRepo = mock()

    @Mock
    protected val database: DatabaseRepository = mock()

    @Mock
    protected val loadingNetworkDataObserver: Observer<Boolean> = mock()

    @Mock
    protected val itemsObserver: Observer<List<RocketModel>> = mock()

    @Before
    open fun setUp() {
        viewModel = RocketsViewModel(rocketRepo, database)
    }

}