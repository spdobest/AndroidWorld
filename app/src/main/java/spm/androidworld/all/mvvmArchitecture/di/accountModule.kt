package spm.androidworld.all.mvvmArchitecture.di

import org.koin.dsl.module
import retrofit2.Retrofit
import spm.androidworld.all.network.BookApiService

val accountModule = module {
    single {
        val retrofit: Retrofit = get()
        retrofit.create(BookApiService::class.java)
    }
}