package spm.androidworld.all.autoAddressSearch.module

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import spm.androidworld.all.autoAddressSearch.viewmodel.AddressSearchViewModel

val addressSearchModule = module {
//    factory { SearchRepository(get()) }
    viewModel { AddressSearchViewModel() }
}