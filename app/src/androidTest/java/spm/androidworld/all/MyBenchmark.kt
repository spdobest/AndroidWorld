package spm.androidworld.all

import androidx.benchmark.junit4.BenchmarkRule
import androidx.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MyBenchmark {
    @get:Rule
    val benchmarkRule = BenchmarkRule()

    /* @Test
     fun benchmarkSomeWork() = benchmarkRule.measureRepeated {

     }*/
}