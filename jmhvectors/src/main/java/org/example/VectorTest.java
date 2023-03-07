package org.example;

import java.util.concurrent.ThreadLocalRandom;
import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;

import jdk.incubator.vector.FloatVector;
import jdk.incubator.vector.IntVector;
import jdk.incubator.vector.VectorOperators;
import jdk.incubator.vector.VectorSpecies;
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
//单线程即可   --add-modules jdk.incubator.vector
@Fork(
        value = 1,
        jvmArgsPrepend = {
                "--enable-preview",
                "--add-modules=jdk.incubator.vector",
        }
)
//测试次数，我们测试10次
@Measurement(iterations = 10)
//定义了一个类实例的生命周期，所有测试线程共享一个实例
@State(value = Scope.Benchmark)
public class VectorTest {
    private static final VectorSpecies<Float> SPECIES =
            FloatVector.SPECIES_256;

    final int size = 1000;
    final float[] a = new float[size];
    final float[] b = new float[size];
    final float[] c = new float[size];

    public VectorTest() {
        for (int i = 0; i < size; i++) {
            a[i] = ThreadLocalRandom.current().nextFloat(0.0001f, 100.0f);
            b[i] = ThreadLocalRandom.current().nextFloat(0.0001f, 100.0f);
        }
    }

    @Benchmark
    public void testScalar(Blackhole blackhole) throws Exception {
        for (int i = 0; i < a.length; i++) {
            c[i] = (a[i] * a[i] + b[i] * b[i]) * -1.0f;
        }
    }

    @Benchmark
    public void testVector(Blackhole blackhole) {
        int i = 0;
        //高于数组长度的 SPECIES 一次处理数据长度的倍数
        int upperBound = SPECIES.loopBound(a.length);
        //每次循环处理 SPECIES.length() 这么多的数据
        for (; i < upperBound; i += SPECIES.length()) {
            // FloatVector va, vb, vc;
            var va = FloatVector.fromArray(SPECIES, a, i);
            var vb = FloatVector.fromArray(SPECIES, b, i);
            var vc = va.mul(va)
                    .add(vb.mul(vb))
                    .neg();
            vc.intoArray(c, i);
        }
        for (; i < a.length; i++) {
            c[i] = (a[i] * a[i] + b[i] * b[i]) * -1.0f;
        }
    }

    public static int[] simpleSum(int[] a, int[] b) {
        var c = new int[a.length];
        for (var i = 0; i < a.length; i++) {
            c[i] = a[i] + b[i];
        }
        return c;
    }

//int 求和
    public static int[] vectorSum(int[] a, int[] b) {
        final VectorSpecies<Integer> SPECIES1 = IntVector.SPECIES_PREFERRED;
        var c = new int[a.length];
        var upperBound = SPECIES1.loopBound(a.length);

        var i = 0;
        for (; i < upperBound; i += SPECIES1.length()) {
            var va = IntVector.fromArray(SPECIES1, a, i);
            var vb = IntVector.fromArray(SPECIES1, b, i);
            var vc = va.add(vb);
            vc.intoArray(c, i);
        }
        // Compute elements not fitting in the vector alignment.
        for (; i < a.length; i++) {
            c[i] = a[i] + b[i];
        }

        return c;

    }

    public static float vectorFMA(float[] a, float[] b){
        var upperBound = SPECIES.loopBound(a.length);
        var sum = FloatVector.zero(SPECIES);
        var i = 0;
        for (; i < upperBound; i += SPECIES.length()) {
            // FloatVector va, vb, vc
            var va = FloatVector.fromArray(SPECIES, a, i);
            var vb = FloatVector.fromArray(SPECIES, b, i);
            sum = va.fma(vb, sum);
        }
        var c = sum.reduceLanes(VectorOperators.ADD);
        for (; i < a.length; i++) { // Cleanup loop
            c += a[i] * b[i];
        }
        return c;
    }
    void scalarComputation(float[] a, float[] b, float[] c) {
        for (int i = 0; i < a.length; i++) {
            c[i] = (a[i] * a[i] + b[i] * b[i]) * -1.0f;
        }
    }



    void vectorComputation(float[] a, float[] b, float[] c) {
        //static final VectorSpecies<Float> SPECIES = FloatVector.SPECIES_PREFERRED;
        for (int i = 0; i < a.length; i += SPECIES.length()) {
            // VectorMask<Float>  m;
            var m = SPECIES.indexInRange(i, a.length);
            // FloatVector va, vb, vc;
            var va = FloatVector.fromArray(SPECIES, a, i, m);
            var vb = FloatVector.fromArray(SPECIES, b, i, m);
            var vc = va.mul(va)
                    .add(vb.mul(vb))
                    .neg();
            vc.intoArray(c, i, m);
        }
    }


    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder().include(VectorTest.class.getSimpleName()).build();
        new Runner(opt).run();
    }
}
//        Result "org.example.VectorTest.testVector":
//        6100521.531 ±(99.9%) 735629.202 ops/s [Average]
//        (min, avg, max) = (5353183.203, 6100521.531, 6865177.443), stdev = 486573.170
//        CI (99.9%): [5364892.330, 6836150.733] (assumes normal distribution)
//
//
//        # Run complete. Total time: 00:03:42
//
//        REMEMBER: The numbers below are just data. To gain reusable insights, you need to follow up on
//        why the numbers are the way they are. Use profilers (see -prof, -lprof), design factorial
//        experiments, perform baseline and negative tests that provide experimental control, make sure
//        the benchmarking environment is safe on JVM/OS/HW level, ask for reviews from the domain experts.
//        Do not assume the numbers tell you what you want them to tell.
//
//        NOTE: Current JVM experimentally supports Compiler Blackholes, and they are in use. Please exercise
//        extra caution when trusting the results, look into the generated code to check the benchmark still
//        works, and factor in a small probability of new VM bugs. Additionally, while comparisons between
//        different JVMs are already problematic, the performance difference caused by different Blackhole
//        modes can be very significant. Please make sure you use the consistent Blackhole mode for comparisons.
//
//        Benchmark               Mode  Cnt        Score         Error  Units
//        VectorTest.testScalar  thrpt   10  6660352.740 ± 1049673.269  ops/s
//        VectorTest.testVector  thrpt   10  6100521.531 ±  735629.202  ops/s