package xyz.zohre.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import xyz.zohre.domain.entities.Result

/**
 * Executes business logic in its execute method and keep posting updates to the result as
 * [Result<R>].
 * Handling an exception (emit [Result.Error] to the result) is the subclasses's responsibility.
 */
interface FlowRepository<R> {
    val coroutineDispatcher: CoroutineDispatcher

    suspend operator fun invoke(): Flow<Result<R>> = execute()
        .catch { e -> emit(Result.Error(Exception(e))) }
        .flowOn(coroutineDispatcher)

    suspend fun execute(): Flow<Result<R>>
}
