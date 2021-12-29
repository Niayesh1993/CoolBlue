package xyz.zohre.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import xyz.zohre.domain.entities.Result


interface Repository<in P, R> {
    val coroutineDispatcher: CoroutineDispatcher

    /** Executes the Repository asynchronously and returns a Result.
     *
     * @return a Result.
     *
     * @param parameters the input parameters to run the use case with
     */
    suspend operator fun invoke(parameters: P): Result<R> {
        return try {
            withContext(coroutineDispatcher) {
                execute(parameters).let {
                    Result.Success(it)
                }
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    /**
     * Override this to set the code to be executed.
     */
    @Throws(RuntimeException::class)
    suspend fun execute(parameters: P): R
}
