package com.asoom.skypulse.di

import com.asoom.skypulse.data.GpsLocationProvider
import com.asoom.skypulse.data.LocationProvider
import com.asoom.skypulse.data.datasource.WeatherForecastDataSource
import com.asoom.skypulse.data.datasource.WeatherForecastDataSourceImp
import com.asoom.skypulse.data.remote.WeatherApi
import com.asoom.skypulse.data.repo.LocationRepositoryImpl
import com.asoom.skypulse.data.repo.WeatherForecastRepoImp
import com.asoom.skypulse.domain.repo.LocationRepository
import com.asoom.skypulse.domain.repo.WeatherForecastRepo
import com.asoom.skypulse.domain.usecase.GetCurrentLocationUseCase
import com.asoom.skypulse.domain.usecase.GetWeatherUseCase
import com.asoom.skypulse.presentation.viewModel.LocationViewModel
import com.asoom.skypulse.presentation.viewModel.WeatherViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {

    single {
        HttpClient() {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                    isLenient = true
                })
            }
        }
    }

    single { WeatherApi(get()) }

    single<WeatherForecastDataSource> { WeatherForecastDataSourceImp(get()) }

    single<WeatherForecastRepo> { WeatherForecastRepoImp(get()) }

    // LocationProvider
    single<LocationProvider> { GpsLocationProvider(androidContext()) }

    // LocationRepository
    single<LocationRepository> { LocationRepositoryImpl(androidContext(), get<LocationProvider>()) }

    // UseCases
    single { GetCurrentLocationUseCase(get<LocationRepository>()) }
    single { GetWeatherUseCase(get()) }

    // ViewModels
    viewModel { WeatherViewModel(get()) }
    viewModel { LocationViewModel(get()) }
}