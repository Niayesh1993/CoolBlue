package xyz.zohre.ui.main

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import xyz.zohre.data.discovery.ProductRepository
import xyz.zohre.data.model.CoolBlueResponse
import xyz.zohre.domain.entities.Result

class FakeProductRepository(override val coroutineDispatcher: CoroutineDispatcher): ProductRepository {

    private lateinit var firstEmit:CoolBlueResponse

    fun setEmits(firstEmit: CoolBlueResponse) {
        this.firstEmit = firstEmit
    }
    override suspend fun execute(): Flow<Result<CoolBlueResponse>> {
        return flow {
            emit(Result.Success(firstEmit))
        }
    }
}