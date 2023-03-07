https://github.com/SchrodingerZhu/SwissTableJavaVectorAPI/blob/main/build.gradle.kts



--add-modules jdk.incubator.vector

module jmhvectors {
requires jmh.core;
requires java.base;
requires jdk.incubator.vector;
}

Result "org.example.JMH.defaultComputation":
90710.517 ±(99.9%) 13632.517 us/op [Average]
(min, avg, max) = (89988.933, 90710.517, 91481.018), stdev = 747.244
CI (99.9%): [77078.000, 104343.034] (assumes normal distribution)


    # JMH version: 1.36
    # VM version: JDK 21-ea, OpenJDK 64-Bit Server VM, 21-ea+12-971
    # VM invoker: F:\openjdk-21-ea+7_windows-x64_bin\jdk-21\bin\java.exe
    # VM options: --enable-preview --add-modules=jdk.incubator.vector
    # Blackhole mode: compiler (auto-detected, use -Djmh.blackhole.autoDetect=false to disable)
    # Warmup: 3 iterations, 1 s each
    # Measurement: 3 iterations, 1 s each
    # Timeout: 10 min per iteration
    # Threads: 1 thread, will synchronize iterations
    # Benchmark mode: Average time, time/op
    # Benchmark: org.example.JMH.defaultComputation
    # Parameters: (size = 1000000000)
    
    # Run progress: 97.62% complete, ETA 00:00:08
    # Fork: 1 of 1
    WARNING: Using incubator modules: jdk.incubator.vector
    # Warmup Iteration   1: 1762934.050 us/op
    # Warmup Iteration   2: 1072362.400 us/op
    # Warmup Iteration   3: 1110248.500 us/op
    Iteration   1: 1014137.150 us/op
    Iteration   2: 1247545.750 us/op
    Iteration   3: 1414192.900 us/op
    
    
    Result "org.example.JMH.defaultComputation":
    1225291.933 ±(99.9%) 3666151.992 us/op [Average]
    (min, avg, max) = (1014137.150, 1225291.933, 1414192.900), stdev = 200954.162
    CI (99.9%): [≈ 0, 4891443.925] (assumes normal distribution)
    
    
    # Run complete. Total time: 00:06:15
    
    REMEMBER: The numbers below are just data. To gain reusable insights, you need to follow up on
    why the numbers are the way they are. Use profilers (see -prof, -lprof), design factorial
    experiments, perform baseline and negative tests that provide experimental control, make sure
    the benchmarking environment is safe on JVM/OS/HW level, ask for reviews from the domain experts.
    Do not assume the numbers tell you what you want them to tell.
    
    NOTE: Current JVM experimentally supports Compiler Blackholes, and they are in use. Please exercise
    extra caution when trusting the results, look into the generated code to check the benchmark still
    works, and factor in a small probability of new VM bugs. Additionally, while comparisons between
    different JVMs are already problematic, the performance difference caused by different Blackhole
    modes can be very significant. Please make sure you use the consistent Blackhole mode for comparisons.
    
    Benchmark                     (size)  Mode  Cnt        Score          Error  Units
    JMH.ParallelComputation          100  avgt    3     5892.085 ±   128196.288  us/op
    JMH.ParallelComputation         1000  avgt    3     1785.251 ±    11991.778  us/op
    JMH.ParallelComputation       100000  avgt    3     1342.038 ±     1470.283  us/op
    JMH.ParallelComputation      1000000  avgt    3     3482.929 ±      256.299  us/op
    JMH.ParallelComputation     10000000  avgt    3    47324.804 ±    60293.443  us/op
    JMH.ParallelComputation    100000000  avgt    3   539350.978 ±  2176997.584  us/op
    JMH.StreamComputation            100  avgt    3        3.923 ±        1.516  us/op
    JMH.StreamComputation           1000  avgt    3        5.633 ±        1.725  us/op
    JMH.StreamComputation         100000  avgt    3       59.032 ±       13.615  us/op
    JMH.StreamComputation        1000000  avgt    3      646.727 ±      436.656  us/op
    JMH.StreamComputation       10000000  avgt    3     8322.327 ±     2863.051  us/op
    JMH.StreamComputation      100000000  avgt    3    82348.450 ±    48930.824  us/op
    JMH.StreamComputation     1000000000  avgt    3   930570.650 ±   683279.038  us/op
    JMH.Vector128Computation         100  avgt    3        0.058 ±        0.113  us/op
    JMH.Vector128Computation        1000  avgt    3        0.935 ±        1.511  us/op
    JMH.Vector128Computation      100000  avgt    3       67.395 ±      221.307  us/op
    JMH.Vector128Computation     1000000  avgt    3     1064.746 ±     2747.218  us/op
    JMH.Vector128Computation    10000000  avgt    3    21560.300 ±   125917.776  us/op
    JMH.Vector128Computation   100000000  avgt    3   117416.704 ±    67945.557  us/op
    JMH.Vector128Computation  1000000000  avgt    3  1139390.467 ±  1011162.332  us/op
    JMH.Vector256Computation         100  avgt    3        0.062 ±        0.099  us/op
    JMH.Vector256Computation        1000  avgt    3        0.728 ±        1.981  us/op
    JMH.Vector256Computation      100000  avgt    3       56.176 ±      139.489  us/op
    JMH.Vector256Computation     1000000  avgt    3     1139.852 ±     4666.770  us/op
    JMH.Vector256Computation    10000000  avgt    3    10456.139 ±     4387.249  us/op
    JMH.Vector256Computation   100000000  avgt    3    87079.008 ±    47401.540  us/op
    JMH.Vector256Computation  1000000000  avgt    3  1348608.333 ±   628634.078  us/op
    JMH.Vector512Computation         100  avgt    3        1.154 ±        4.461  us/op
    JMH.Vector512Computation        1000  avgt    3       10.215 ±       21.198  us/op
    JMH.Vector512Computation      100000  avgt    3      667.681 ±      159.796  us/op
    JMH.Vector512Computation     1000000  avgt    3     7439.490 ±     5283.078  us/op
    JMH.Vector512Computation    10000000  avgt    3    72743.802 ±    80319.819  us/op
    JMH.Vector512Computation   100000000  avgt    3   730444.267 ±  1414363.819  us/op
    JMH.Vector512Computation  1000000000  avgt    3  8966646.433 ± 12318796.851  us/op
    JMH.defaultComputation           100  avgt    3        0.100 ±        0.082  us/op
    JMH.defaultComputation          1000  avgt    3        1.232 ±        4.430  us/op
    JMH.defaultComputation        100000  avgt    3       73.749 ±       74.096  us/op
    JMH.defaultComputation       1000000  avgt    3      999.983 ±     2251.866  us/op
    JMH.defaultComputation      10000000  avgt    3    12935.859 ±    26971.970  us/op
    JMH.defaultComputation     100000000  avgt    3    90710.517 ±    13632.517  us/op
    JMH.defaultComputation    1000000000  avgt    3  1225291.933 ±  3666151.992  us/op

update 

    # Run complete. Total time: 00:06:43
    
    REMEMBER: The numbers below are just data. To gain reusable insights, you need to follow up on
    why the numbers are the way they are. Use profilers (see -prof, -lprof), design factorial
    experiments, perform baseline and negative tests that provide experimental control, make sure
    the benchmarking environment is safe on JVM/OS/HW level, ask for reviews from the domain experts.
    Do not assume the numbers tell you what you want them to tell.
    
    NOTE: Current JVM experimentally supports Compiler Blackholes, and they are in use. Please exercise
    extra caution when trusting the results, look into the generated code to check the benchmark still
    works, and factor in a small probability of new VM bugs. Additionally, while comparisons between
    different JVMs are already problematic, the performance difference caused by different Blackhole
    modes can be very significant. Please make sure you use the consistent Blackhole mode for comparisons.
    
    Benchmark                     (size)  Mode  Cnt         Score          Error  Units
    JMH.ParallelComputation          100  avgt    3      2339.885 ±    28753.520  us/op
    JMH.ParallelComputation         1000  avgt    3      1918.304 ±    11898.766  us/op
    JMH.ParallelComputation       100000  avgt    3      1805.725 ±     8163.002  us/op
    JMH.ParallelComputation      1000000  avgt    3      4283.334 ±     4755.516  us/op
    JMH.ParallelComputation     10000000  avgt    3     99966.401 ±   241023.074  us/op
    JMH.ParallelComputation    100000000  avgt    3    609788.622 ±  3099690.431  us/op
    JMH.StreamComputation            100  avgt    3         3.727 ±        2.206  us/op
    JMH.StreamComputation           1000  avgt    3         5.774 ±        6.342  us/op
    JMH.StreamComputation         100000  avgt    3        72.143 ±      212.875  us/op
    JMH.StreamComputation        1000000  avgt    3       890.062 ±     2264.074  us/op
    JMH.StreamComputation       10000000  avgt    3      9768.990 ±     4117.623  us/op
    JMH.StreamComputation      100000000  avgt    3     85599.643 ±    53806.462  us/op
    JMH.StreamComputation     1000000000  avgt    3    851310.667 ±   150162.186  us/op
    JMH.Vector128Computation         100  avgt    3         0.054 ±        0.048  us/op
    JMH.Vector128Computation        1000  avgt    3         0.691 ±        0.715  us/op
    JMH.Vector128Computation      100000  avgt    3        55.724 ±      144.506  us/op
    JMH.Vector128Computation     1000000  avgt    3       727.467 ±      563.869  us/op
    JMH.Vector128Computation    10000000  avgt    3      9674.276 ±     8366.363  us/op
    JMH.Vector128Computation   100000000  avgt    3    126857.680 ±   171854.659  us/op
    JMH.Vector128Computation  1000000000  avgt    3   1861879.567 ±  1252025.145  us/op
    JMH.Vector256Computation         100  avgt    3         0.063 ±        0.156  us/op
    JMH.Vector256Computation        1000  avgt    3         0.690 ±        2.458  us/op
    JMH.Vector256Computation      100000  avgt    3        57.690 ±      199.551  us/op
    JMH.Vector256Computation     1000000  avgt    3       832.242 ±     1297.313  us/op
    JMH.Vector256Computation    10000000  avgt    3     10862.258 ±    28418.466  us/op
    JMH.Vector256Computation   100000000  avgt    3    118521.334 ±   355715.348  us/op
    JMH.Vector256Computation  1000000000  avgt    3   1222419.767 ±  3926561.301  us/op
    JMH.Vector512Computation         100  avgt    3         0.673 ±        0.507  us/op
    JMH.Vector512Computation        1000  avgt    3         7.436 ±        9.382  us/op
    JMH.Vector512Computation      100000  avgt    3       691.103 ±      358.947  us/op
    JMH.Vector512Computation     1000000  avgt    3      7097.975 ±     3766.729  us/op
    JMH.Vector512Computation    10000000  avgt    3     90736.841 ±   162418.700  us/op
    JMH.Vector512Computation   100000000  avgt    3    820478.350 ±  1025462.829  us/op
    JMH.Vector512Computation  1000000000  avgt    3  10123763.333 ± 18853763.285  us/op
    JMH.defaultComputation           100  avgt    3         0.093 ±        0.075  us/op
    JMH.defaultComputation          1000  avgt    3         1.175 ±        0.511  us/op
    JMH.defaultComputation        100000  avgt    3        99.466 ±       39.037  us/op
    JMH.defaultComputation       1000000  avgt    3      1627.613 ±     4628.692  us/op
    JMH.defaultComputation      10000000  avgt    3     26913.598 ±   286199.338  us/op
    JMH.defaultComputation     100000000  avgt    3    146577.065 ±   576260.005  us/op
    JMH.defaultComputation    1000000000  avgt    3   1122800.433 ±  1961146.250  us/op
    
    Benchmark result is saved to jmh-result.json
    
    进程已结束,退出代码0

大量使用vector的一个实例：
https://github.com/DSheirer/sdrtrunk/tree/ba7b616f88560c0fb09c38418f15f531eae79d72/src/main/java/io/github/dsheirer/vector