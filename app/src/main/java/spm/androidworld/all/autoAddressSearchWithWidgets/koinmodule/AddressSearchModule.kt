package spm.androidworld.all.autoAddressSearchWithWidgets.koinmodule

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import spm.androidworld.all.autoAddressSearchWithWidgets.repository.AddressSearchRepository
import spm.androidworld.all.autoAddressSearchWithWidgets.viewmodel.AddressSearchViewModel

val AddressSearchModule = module {
    factory { AddressSearchRepository() }
    viewModel { AddressSearchViewModel(get()) }
}