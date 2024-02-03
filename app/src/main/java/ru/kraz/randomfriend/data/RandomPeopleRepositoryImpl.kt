package ru.kraz.randomfriend.data

import retrofit2.HttpException
import ru.kraz.randomfriend.domain.ErrorType
import ru.kraz.randomfriend.domain.RandomPeopleDomain
import ru.kraz.randomfriend.domain.RandomPeopleRepository
import ru.kraz.randomfriend.domain.ResultFDS
import java.net.UnknownHostException

class RandomPeopleRepositoryImpl(
    private val service: PeopleService
) : RandomPeopleRepository {
    override suspend fun fetchPeople(): ResultFDS<List<RandomPeopleDomain>> {
        return try {
            val peopleCloud = service.fetchRandomPeople()
            ResultFDS.Success(peopleCloud.map().map { it.map() })
        } catch (e: UnknownHostException) {
            ResultFDS.Error(ErrorType.NO_CONNECTION)
        } catch (e: HttpException) {
            ResultFDS.Error(ErrorType.SERVICE_UNAVAILABLE)
        } catch (e: Exception) {
            ResultFDS.Error(ErrorType.GENERIC_ERROR)
        }
    }
}