package xyz.zohre.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.delay
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import xyz.zohre.data.model.CoolBlueResponse
import xyz.zohre.data.model.Products
import xyz.zohre.test.LiveDataTestUtil
import xyz.zohre.test.MainCoroutineRule
import xyz.zohre.test.TestData
import xyz.zohre.test.runBlockingTest
import xyz.zohre.ui.HomeViewModel
import org.mockito.Mockito.`when`




class HomeViewModelTest {

    @get:Rule
    var coroutineRule = MainCoroutineRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var viewModel: HomeViewModel

    lateinit var repository: FakeProductRepository

    @Before
    fun setup() {
        repository = FakeProductRepository(coroutineRule.testDispatcher)
        viewModel = HomeViewModel(repository)
    }

    @Test
    fun checkCacheAndRemoteWorkProperly() = coroutineRule.runBlockingTest {
        // when
        val firstEmit = TestData.testResponse
        repository.setEmits(firstEmit)
        val feature1 = firstEmit.products

        // given
        val results = LiveDataTestUtil.getValuesLater(viewModel.products)
        viewModel.searchProducts()
        delay(510)

        // then
        Assert.assertEquals(results[0], feature1)
    }

    @Test
    fun checkContinuationOfItemsWorkProperly() = coroutineRule.runBlockingTest {
        // when
        val firstEmit = TestData.testResponse
        repository.setEmits(firstEmit)
        val feature1 = firstEmit.products

        // given
        val results = LiveDataTestUtil.getValuesLater(viewModel.products)
        viewModel.searchProducts()
        delay(510)

        // then
        Assert.assertEquals(results[0], feature1)
    }

    @Test
    fun checkCancellationWorkProperly() = coroutineRule.runBlockingTest {
        // when
        val firstEmit = TestData.testResponse
        repository.setEmits(firstEmit)

        // given
        val results = LiveDataTestUtil.getValuesLater(viewModel.products)
        viewModel.searchProducts()
        viewModel.searchProducts()
        delay(600)

        // then
        Assert.assertEquals(results.size, 2)
    }

}