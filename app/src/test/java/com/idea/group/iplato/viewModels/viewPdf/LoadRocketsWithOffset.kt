package com.idea.group.iplato.viewModels.viewPdf
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mockito.*

class LoadRocketsWithOffset : BaseRocketViewModelTest() {

    @Test
    fun `Should tell view to show loading on loading rockets`() {
        val captor = ArgumentCaptor.forClass(Boolean::class.java)

        viewModel?.showLoading?.observeForever(loadingNetworkDataObserver)
        viewModel?.getItems(1)

        captor.run {
            verify(loadingNetworkDataObserver, times(1)).onChanged(capture())
            assertEquals(true, value)
        }
    }

    @Test
    fun `Should call network on a new card to get data`() {
        viewModel?.getItems(1)
        verify(rocketRepo, times(1)).getRocketList(1)
    }
}