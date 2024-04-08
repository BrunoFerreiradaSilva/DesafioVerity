import com.example.desafioverity.data.repository.preferences.UserPreferenceRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Interceptor
import okhttp3.Response
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date

class HeaderInterceptor(private val userPreferenceRepository: UserPreferenceRepository) :
    Interceptor {
    private val scope = CoroutineScope(Dispatchers.IO)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        val remaining = response.header("X-RateLimit-Remaining")
        val timeForReset = response.header("X-RateLimit-Reset")

        remaining?.let {
            scope.launch {
                userPreferenceRepository.saveRemaining(it)
            }
        }

        timeForReset?.let {
            scope.launch {
                userPreferenceRepository.saveTimeNewRequest(convertUnixTimestamp(it.toLong()))
            }
        }

        return response
    }

    private fun convertUnixTimestamp(timestamp: Long): String {
        val instant = Instant.ofEpochSecond(timestamp)
        val zoneId = ZoneId.of("America/Sao_Paulo")
        val localDateTime = LocalDateTime.ofInstant(instant, zoneId)
        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        return formatter.format(localDateTime)
    }
}
