package spm.androidworld.all.coroutines

import spm.androidworld.all.network.ApiServiceInterface
import spm.androidworld.all.network.model.Result


/**
 * Created by Sibaprasad Mohanty on 26/05/20.
 * Spm Limited
 * sp.dobest@gmail.com
 */

class CoroutineRepository {

    suspend fun getTopMovies(): List<Result> =
        ApiServiceInterface.create(ApiServiceInterface::class.java)
            .getTrendingMovies().results

}