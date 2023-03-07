package org.example;

import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.IOException;

// 按两次 Shift 打开 Search Everywhere 对话框并输入 `show whitespaces`，
// 然后按 Enter 键。现在，您可以在代码中看到空格字符。
public class Main {
//    public static void main(String[] args) {
//        // 当文本光标位于高亮显示的文本处时按 Alt+Enter，
//        // 可查看 IntelliJ IDEA 对于如何修复该问题的建议。
//        System.out.printf("Hello and welcome!");
//
//        // 按 Shift+F10 或点击间距中的绿色按钮以运行脚本。
//        for (int i = 1; i <= 5; i++) {
//
//            // 按 Shift+F9 开始调试代码。我们已为您设置了一个断点，
//            // 但您始终可以通过按 Ctrl+F8 添加更多断点。
//            System.out.println("i = " + i);
//        }
//    }
public static void main(String[] args) throws IOException, RunnerException {
    // TODO Auto-generated method stub
    Options opts = new OptionsBuilder().include(JMH.class.getSimpleName())
            .resultFormat(ResultFormatType.JSON).build();
    // .output(new File("jmh.log").getCanonicalPath())
    new Runner(opts).run();

}
}