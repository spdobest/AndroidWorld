package spm.androidworld.all.dagger2

import javax.inject.Inject

class War {

    lateinit var stark: Stark
    lateinit var bolton: Bolton

    @Inject
    constructor(mStark: Stark, mBolton: Bolton) {
        this.stark = mStark
        this.bolton = mBolton
    }

    fun prepare() {
        stark.prepareWar()
        bolton.prepareWar()
    }

    fun report() {
        stark.reportWar()
        bolton.reportWar()
    }

}