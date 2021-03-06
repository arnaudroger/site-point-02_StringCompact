package org.github.arnaudroger;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.nio.charset.Charset;



@State(Scope.Benchmark)
public class EncodingBenchmark {


    @Param({"LATIN1", "UTF16_LATIN1_CHAR"})
    public StrType strType;

    @Param({"ISO_8859-1", "UTF-8", "UTF-16"})
    public String encoding;

    public String value;

    private Charset charset;

    @Setup
    public void setUp() {
        value = strType.value();
        charset = Charset.forName(encoding);
    }

    @Benchmark
    public byte[] testEncoding() {
        return value.getBytes(charset);
    }

    public static void main(String[] args) {
        EncodingBenchmark encodingBenchmark = new EncodingBenchmark();
        encodingBenchmark.encoding = "UTF-16";
        encodingBenchmark.strType = StrType.UTF16_LATIN1_CHAR;
        encodingBenchmark.setUp();

        encodingBenchmark.testEncoding();
    }

}
