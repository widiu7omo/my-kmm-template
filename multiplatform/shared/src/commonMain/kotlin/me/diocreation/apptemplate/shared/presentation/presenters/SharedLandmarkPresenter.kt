package me.diocreation.apptemplate.shared.presentation.presenters

import com.rickclephas.kmp.nativecoroutines.NativeCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import me.diocreation.apptemplate.shared.data.data_sources.network.utils.NetworkResult
import me.diocreation.apptemplate.shared.domain.models.LandmarkData
import me.diocreation.apptemplate.shared.domain.repositories.LandmarksRepository
import org.koin.core.component.KoinComponent

class SharedLandmarkPresenter constructor(private val landmarkRepository: LandmarksRepository) :
    KoinComponent {
    @NativeCoroutineScope
    private val viewModelScope = CoroutineScope(Dispatchers.Default)
    private val supervisorJob = MutableStateFlow<Job?>(null)

    private val _error = MutableStateFlow<String?>(null)
    val error get() = _error.asStateFlow()

    private val _landmarks = MutableStateFlow<List<LandmarkData>?>(null)
    val landmarks get() = _landmarks.asStateFlow()

    init {
        fetchAllData()
    }

    fun fetchAllData() {
        val job = viewModelScope.launch {
            getLandmarks()
        }
        supervisorJob.value = job
        job.invokeOnCompletion {
            supervisorJob.value?.cancel()
            supervisorJob.value = null
        }
    }

    private suspend fun getLandmarks() {
        try {
            landmarkRepository.fetchLandmarks().collectLatest {
                when (it) {
                    is NetworkResult.Success -> _landmarks.value = it.data
                    is NetworkResult.Error -> _error.value = it.errorMessage
                }
            }
        } catch (e: Exception) {
            _error.value = e.message
        }
    }
}