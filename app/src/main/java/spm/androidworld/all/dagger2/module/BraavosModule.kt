package spm.androidworld.all.dagger2.module

import dagger.Module
import dagger.Provides
import spm.androidworld.all.dagger2.Cash
import spm.androidworld.all.dagger2.Soldiers


@Module
class BraavosModule {

    private lateinit var cash: Cash
    private lateinit var soldiers: Soldiers

    fun BraavosModule(cash: Cash, soldiers: Soldiers) {
        this.cash = cash
        this.soldiers = soldiers
    }

    @Provides
    fun getCash(): Cash {
        return cash
    }

    @Provides
    fun getSoldiers(): Soldiers {
        return soldiers
    }

}