import java.io.BufferedWriter;
import java.io.FileWriter;

public class Test1 {

    public static void main(String args[]) throws Exception {
        BufferedWriter br = new BufferedWriter(new FileWriter("/home/user1/resultdata2.txt"));
        for(long i = 1; i <= 20000 ; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 1; j <= 100; j++) {
                stringBuilder.append("Test" + j + " ");
                br.write(stringBuilder.toString());
            }
        }
        br.close();
    }
}
