package ru.kraz.randomfriend.data.people

import retrofit2.HttpException
import ru.kraz.randomfriend.domain.common.ErrorType
import ru.kraz.randomfriend.domain.people.RandomPersonDomain
import ru.kraz.randomfriend.domain.people.RandomPeopleRepository
import ru.kraz.randomfriend.domain.common.ResultFDS
import java.net.UnknownHostException

class RandomPeopleRepositoryImpl(
    private val service: PeopleService
) : RandomPeopleRepository {
    override suspend fun fetchPeople(): ResultFDS<List<RandomPersonDomain>> {
        return try {
            val peopleCloud = service.fetchRandomPeople()
            ResultFDS.Success(peopleCloud.map().map { it.toRandomPersonDomain() })
        } catch (e: UnknownHostException) {
            ResultFDS.Error(ErrorType.NO_CONNECTION)
        } catch (e: HttpException) {
            ResultFDS.Error(ErrorType.SERVICE_UNAVAILABLE)
        } catch (e: Exception) {
            ResultFDS.Error(ErrorType.GENERIC_ERROR)
        }
    }
}