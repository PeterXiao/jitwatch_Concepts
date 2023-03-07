package org.example;


import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

//测试指标为吞吐量
@BenchmarkMode(Mode.Throughput)
//需要预热，排除 jit 即时编译以及 JVM 采集各种指标带来的影响，由于我们单次循环很多次，所以预热一次就行
@Warmup(iterations = 1)
//线程个数
@Threads(10)
@Fork(1)
//测试次数，我们测试50次
@Measurement(iterations = 50)
//定义了一个类实例的生命周期，所有测试线程共享一个实例
@State(value = Scope.Benchmark)
public class TestRandomGenerator {
    @Param({
            "Random", "SecureRandom", "SplittableRandom", "Xoroshiro128PlusPlus", "Xoshiro256PlusPlus", "L64X256MixRandom",
            "L64X128StarStarRandom", "L64X128MixRandom", "L64X1024MixRandom", "L32X64MixRandom", "L128X256MixRandom",
            "L128X128MixRandom", "L128X1024MixRandom"
    })
    private String name;
    ThreadLocal<RandomGenerator> randomGenerator;
    @Setup
    public void setup() {
        final String finalName = this.name;
        randomGenerator = ThreadLocal.withInitial(() -> RandomGeneratorFactory.of(finalName).create());
    }

    @Benchmark
    public void testRandomInt(Blackhole blackhole) throws Exception {
        blackhole.consume(randomGenerator.get().nextInt());
    }

    @Benchmark
    public void testRandomIntWithBound(Blackhole blackhole) throws Exception {
        //注意不取 2^n 这种数字，因为这种数字一般不会作为实际应用的范围，但是底层针对这种数字有优化
        blackhole.consume(randomGenerator.get().nextInt(1, 100));
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder().include(TestRandomGenerator.class.getSimpleName()).build();
        new Runner(opt).run();
    }
}