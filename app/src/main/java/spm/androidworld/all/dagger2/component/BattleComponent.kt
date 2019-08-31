package spm.androidworld.all.dagger2.component

import dagger.Component
import spm.androidworld.all.dagger2.Cash
import spm.androidworld.all.dagger2.Soldiers
import spm.androidworld.all.dagger2.War
import spm.androidworld.all.dagger2.module.BraavosModule


@Component(modules = [BraavosModule::class])
internal interface BattleComponent {
    fun getWar(): War
    fun getCash(): Cash
    fun getSoldiers(): Soldiers
}