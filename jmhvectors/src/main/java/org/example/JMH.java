/**
 * Project Name:Java18 File Name:JMH.java Package Name:com.devops.tools.utils.perf.vectors
 * Date:2023年3月5日上午9:09:51 Copyright (c) 2023, Luozi All Rights Reserved.
 * 
 */
/*
 * Copyright (c) 2023, Luozi.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License. Project Name:Java18 File Name:JMH.java Package
 * Name:com.devops.tools.utils.perf.vectors Date:2023年3月5日上午9:09:51 Copyright (c) 2023, Luozi All
 * Rights Reserved.
 * 
 */


package org.example;

import jdk.incubator.vector.IntVector;
import jdk.incubator.vector.VectorSpecies;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * ClassName:JMH <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2023年3月5日 上午9:09:51 <br/>
 * 
 * @author xiaoy
 * @version
 * @since JDK 1.8
 * @see
 */

/**
 * @author Luozi
 * @since 2023年3月5日
 */
@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(
        value = 1,
        jvmArgsPrepend = {
                "--enable-preview",
                "--add-modules=jdk.incubator.vector",
        }
)
public class JMH {

  private int[] nums;
  // , "2500000000" , "2500000000"

  @Param({"100", "1000", "100000", "1000000", "10000000", "100000000", "1000000000"})
  int size;

  @Setup
  public void setup() {
    nums = new int[size];
  }

  @Benchmark
  public int[] StreamComputation() {
    int[] result = new int[size];
    Arrays.parallelSetAll(result, i -> (nums[i] * i + nums[i] * nums[i]) * -1);
    return result;
  }

  @Benchmark
  public int[] ParallelComputation() {
    int[] result = new int[size];
    CountDownLatch count = new CountDownLatch(8);
    for (int i = 0; i <= 8; i++) {
      final int start = i;
      new Thread(() -> {
        for (int j = start; j < nums.length; j += 8) {
          result[j] = (nums[j] * j + nums[j] * nums[j]) * -1;
        }
      }).start();
    }
    // Arrays.parallelSetAll(result, i -> (nums[i] * i + nums[i] * nums[i]));
    return result;
  }

  @Benchmark
  public int[] Vector128Computation() {
    int[] result = new int[size];
    VectorSpecies<Integer> species = IntVector.SPECIES_128;
    int loop = species.loopBound(nums.length);
    int i = 0;
    for (; i < loop; i += species.length()) {
      IntVector va = IntVector.fromArray(species, nums, i);
      IntVector vb = IntVector.fromArray(species, nums, i);
      IntVector vc = va.mul(va).add(vb.mul(vb)).neg();
      vc.intoArray(result, i);

    }
    for (; i < nums.length; i++) {
      result[i] = nums[i] * i + nums[i] * nums[i] * (-1);
    }

    return result;
  }

  @Benchmark
  public int[] Vector256Computation() {
    int[] result = new int[size];
    VectorSpecies<Integer> species = IntVector.SPECIES_256;
    int loop = species.loopBound(nums.length);
    int i = 0;
    for (; i < loop; i += species.length()) {
      IntVector va = IntVector.fromArray(species, nums, i);
      IntVector vb = IntVector.fromArray(species, nums, i);
      IntVector vc = va.mul(va).add(vb.mul(vb)).neg();
      vc.intoArray(result, i);

    }
    for (; i < nums.length; i++) {
      result[i] = nums[i] * i + nums[i] * nums[i] * (-1);
    }

    return result;
  }

  @Benchmark
  public int[] Vector512Computation() {
    int[] result = new int[size];
    VectorSpecies<Integer> species = IntVector.SPECIES_512;
    int loop = species.loopBound(nums.length);
    int i = 0;
    for (; i < loop; i += species.length()) {
      IntVector va = IntVector.fromArray(species, nums, i);
      IntVector vb = IntVector.fromArray(species, nums, i);
      IntVector vc = va.mul(va).add(vb.mul(vb)).neg();
      vc.intoArray(result, i);

    }
    for (; i < nums.length; i++) {
      result[i] = nums[i] * i + nums[i] * nums[i] * (-1);
    }

    return result;
  }

  @Benchmark
  public int[] defaultComputation() {
    int[] result = new int[size];
    for (int i = 0; i < nums.length; i++) {
      result[i] = (nums[i] * i + nums[i] * nums[i]) * -1;

    }
    return result;
  }

  /**
   * @author Luozi
   * @since 2023年3月5日
   * @param args
   * @throws IOException
   * @throws RunnerException
   */
  public static void main(String[] args) throws IOException, RunnerException {
    // TODO Auto-generated method stub
    Options opts = new OptionsBuilder().include(JMH.class.getSimpleName())
        .resultFormat(ResultFormatType.JSON).build();
    // .output(new File("jmh.log").getCanonicalPath())
    new Runner(opts).run();

  }

}
