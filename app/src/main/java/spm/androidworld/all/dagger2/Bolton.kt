package spm.androidworld.all.dagger2

import javax.inject.Inject


class Bolton @Inject constructor() : House {

    override fun prepareWar() {
        //do something
        print(this.javaClass.simpleName + " prepared for war")
    }

    override fun reportWar() {
        print(this.javaClass.simpleName + " Report war")
    }
}