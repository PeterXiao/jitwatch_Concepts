package org.example.sonic;

import com.alibaba.fastjson2.JSON;
import jdk.incubator.vector.FloatVector;
import jdk.incubator.vector.VectorSpecies;
import org.openjdk.jmh.annotations.*;
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

    //EishayFuryTest eishayFuryTest =new EishayFuryTest();
    String str = "{\"images\": [{\n" +
            "      \"height\":768,\n" +
            "      \"size\":\"LARGE\",\n" +
            "      \"title\":\"Javaone Keynote\",\n" +
            "      \"uri\":\"http://javaone.com/keynote_large.jpg\",\n" +
            "      \"width\":1024\n" +
            "    }, {\n" +
            "      \"height\":240,\n" +
            "      \"size\":\"SMALL\",\n" +
            "      \"title\":\"Javaone Keynote\",\n" +
            "      \"uri\":\"http://javaone.com/keynote_small.jpg\",\n" +
            "      \"width\":320\n" +
            "    }\n" +
            "  ],\n" +
            "  \"media\": {\n" +
            "    \"bitrate\":262144,\n" +
            "    \"duration\":18000000,\n" +
            "    \"format\":\"video/mpg4\",\n" +
            "    \"height\":480,\n" +
            "    \"persons\": [\n" +
            "      \"Bill Gates\",\n" +
            "      \"Steve Jobs\"\n" +
            "    ],\n" +
            "    \"player\":\"JAVA\",\n" +
            "    \"size\":58982400,\n" +
            "    \"title\":\"Javaone Keynote\",\n" +
            "    \"uri\":\"http://javaone.com/keynote.mpg\",\n" +
            "    \"width\":640\n" +
            "  }\n" +
            "}";
    @Benchmark
    public void testFastjson2withVector() throws Exception {


        MediaContent mediaContent = JSON.parseObject(str, MediaContent.class);

    }



    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder().include(VectorTest.class.getSimpleName()).build();
        new Runner(opt).run();
    }
}

//      Result "org.example.sonic.VectorTest.testFastjson2withVector":
//        706349.665 ±(99.9%) 46617.538 ops/s [Average]
//        (min, avg, max) = (648374.274, 706349.665, 762424.646), stdev = 30834.615
//        CI (99.9%): [659732.127, 752967.203] (assumes normal distribution)
//
//
//        # Run complete. Total time: 00:05:33
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
//        Benchmark                                  Mode  Cnt        Score        Error  Units
//        VectorTest.testScalar                     thrpt   10  7543111.041 ± 504746.983  ops/s
//        VectorTest.testVector                     thrpt   10  7905367.453 ± 836128.984  ops/s
//        sonic.VectorTest.testFastjson2withVector  thrpt   10   706349.665 ±  46617.538  ops/s
//
//        进程已结束,退出代码0