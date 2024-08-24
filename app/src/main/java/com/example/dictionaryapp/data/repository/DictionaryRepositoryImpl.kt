package com.example.dictionaryapp.data.repository

import android.app.Application
import com.example.dictionaryapp.R
import com.example.dictionaryapp.data.api.WordApi
import com.example.dictionaryapp.data.dto.toWordItem
import com.example.dictionaryapp.domain.model.WordItem
import com.example.dictionaryapp.domain.repository.DictionaryRepository
import com.example.dictionaryapp.util.Results
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class DictionaryRepositoryImpl @Inject constructor(
    private val dictionaryApi: WordApi,
    private val application: Application// used for fetching the string resources
) : DictionaryRepository {
    override suspend fun getWordResult(
        word: String
    ): Flow<Results<WordItem>> // it is used to provide the data with success or error state the flow asynchronous tasks without blocking the main thread.
    // blocking the main thread will make the ui unresponsive thus bad user experience
    {
        return flow {// the flow gives asynchorous data
            emit(Results.Loading(true))
           // emit is used to send messages to the ui screen
            val remoteWordResultDto = try {
                dictionaryApi.getWordResult(word)
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Results.Error(application.getString(R.string.can_t_get_result)))
                emit(Results.Loading(false))
                return@flow//Exits the flow builder early, stopping further execution within the flow.
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Results.Error(application.getString(R.string.can_t_get_result)))
                emit(Results.Loading(false))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Results.Error(application.getString(R.string.can_t_get_result)))
                emit(Results.Loading(false))
                return@flow
            }

            remoteWordResultDto?.let { wordResultDto ->
                wordResultDto[0]?.let { wordItemDto -> // here the list contain only one word thus [0] is used.
                    emit(Results.Success(wordItemDto.toWordItem()))
                    emit(Results.Loading(false))
                    return@flow
                }
            }

            emit(Results.Error(application.getString(R.string.can_t_get_result)))
            emit(Results.Loading(false))
        }
    }
}
