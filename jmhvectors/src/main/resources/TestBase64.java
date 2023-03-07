package org.example;


import org.openjdk.jmh.annotations.*;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;


//测试指标为吞吐量
@BenchmarkMode(Mode.Throughput)
//需要预热，排除 jit 即时编译以及 JVM 采集各种指标带来的影响，由于我们单次循环很多次，所以预热一次就行
@Warmup(iterations = 1)
//线程个数
@Threads(4)
@Fork(
        value = 1,
        jvmArgsPrepend = {
                "--enable-preview",
                "--add-modules=java.base",
                "--add-modules=jdk.incubator.vector",
        }
)
//测试次数，我们测试50次
@Measurement(iterations = 50)
//定义了一个类实例的生命周期，所有测试线程共享一个实例
@State(value = Scope.Benchmark)
public class TestBase64 {
    @Param({
            "Random", "SecureRandom", "SplittableRandom", "Xoroshiro128PlusPlus", "Xoshiro256PlusPlus", "L64X256MixRandom",
            "L64X128StarStarRandom", "L64X128MixRandom", "L64X1024MixRandom", "L32X64MixRandom", "L128X256MixRandom",
            "L128X128MixRandom", "L128X1024MixRandom"
    })
    private String name;

    @Setup
    public void setup() {
        final String finalName = this.name;

    }

    @Benchmark
    public void testBase64decode() throws Exception {
       Base64.getDecoder().decode(name);
    }
    @Benchmark
    public void testBase64encode() throws Exception {
        byte[] byname =Base64.getDecoder().decode(name);
        Base64.getEncoder().encode(byname);
    }


    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder().include(TestBase64.class.getSimpleName()).build();
        new Runner(opt).run();
    }
}