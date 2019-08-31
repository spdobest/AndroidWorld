package spm.androidworld.all.dagger2

import spm.androidworld.all.dagger2.component.DaggerBattleComponent


object BattleOfBastards {

    @JvmStatic
    fun main(args: Array<String>) {

        //        Starks starks = new Starks();
        //        Boltons boltons = new Boltons();
        //
        //        War war = new War(starks,boltons);
        //        war.prepare();
        //        war.report();

        val component = DaggerBattleComponent.create()
        val war = component.getWar()
        war.prepare()
        war.report()

        //using cash and soldiers
        component.getCash()
        component.getSoldiers()
    }
}