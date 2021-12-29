package xyz.zohre.data.discovery

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import xyz.zohre.data.model.CoolBlueResponse
import xyz.zohre.domain.DefaultDispatcher
import xyz.zohre.domain.IoDispatcher
import xyz.zohre.domain.entities.Result
import xyz.zohre.domain.exeption.RemoteCallException
import javax.inject.Inject


class ProductRepositoryImpl @Inject constructor(
    private val remote: ProductDataSource,
    @DefaultDispatcher
    override val coroutineDispatcher: CoroutineDispatcher,
    @IoDispatcher
    val ioDispatcher: CoroutineDispatcher
) : ProductRepository {

    override suspend fun execute(): Flow<Result<CoolBlueResponse>> {
        var remoteResponse: Response<CoolBlueResponse>? = null
        return flow {
            // start async request so we could use network while we loading from cache
            val remoteDeferred = CoroutineScope((ioDispatcher)).async {
                remoteResponse = remote.searchProduct()
            }
            // wait for remote call to complete and emit result
            remoteDeferred.await()
            if (remoteResponse?.isSuccessful == false) {
                emit(Result.Error(RemoteCallException(remoteResponse!!.errorBody()?.string())))
                return@flow
            }
            val remoteVenues = remoteResponse?.body()
            remoteVenues?.let {
                emit(Result.Success(it))
            }
        }
    }

}