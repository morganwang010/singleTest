package workspace;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.Writer;

/**
 * InputStreamReader 和 OutputStreamWriter 测试程序
 *
 * @author skywang
 */
public class StreamConverter {

    private static final String FileName = "C:/file.txt";
    private static final String CharsetName = "utf-8";
    //private static final String CharsetName = "gb2312";

    public static void main(String[] args) {
        testWrite();
        testRead();
    }

    /**
     * OutputStreamWriter 演示函数
     *
     */
    private static void testWrite() {
        try {
            // 创建文件“file.txt”对应File对象
            File file = new File(FileName);
            // 创建FileOutputStream对应OutputStreamWriter：将字节流转换为字符流，即写入out1的数据会自动由字节转换为字符。
             Writer out1 = new OutputStreamWriter(new FileOutputStream(file), CharsetName);
            // 写入10个汉字
            //long startTime=System.currentTimeMillis();
            long startTime= System.nanoTime();
            out1.write("字节流转为字符流示例");
            // 向“文件中”写入"0123456789"+换行符
            out1.write("0123456789\n");
           // long endTime=System.currentTimeMillis();
            long endTime= System.nanoTime();
            System.out.println(endTime-startTime);
            out1.close();

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * InputStreamReader 演示程序
     */
    private static void testRead() {
        try {
            // 方法1：新建FileInputStream对象
            // 新建文件“file.txt”对应File对象
            File file = new File(FileName);
            InputStreamReader in1 = new InputStreamReader(new FileInputStream(file), CharsetName);

            // 测试read()，从中读取一个字符
            char c1 = (char)in1.read();
            System.out.println("c1="+c1);

            // 测试skip(long byteCount)，跳过4个字符
            in1.skip(6);

            // 测试read(char[] cbuf, int off, int len)
            char[] buf = new char[10];
            in1.read(buf, 0, buf.length);
            System.out.println("buf="+(new String(buf)));

            in1.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}