import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Tokens {
    public static void main(String[] args) {
        int v1;
        String v2;
        double v3;
        int v4;
        boolean v5;
        String v6;
        String myLine = "";
        // open file
        try
        {
            BufferedReader inputFile = new BufferedReader(new
                    FileReader("test1mod.txt"));
            myLine = inputFile.readLine();
            inputFile.close();
        }
        catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
        StringTokenizer myLineToken = new StringTokenizer(myLine, "!");
        v1 = Integer.parseInt(myLineToken.nextToken());
        v2 = myLineToken.nextToken();
        v3 = Double.parseDouble(myLineToken.nextToken());
        v4 = Integer.parseInt(myLineToken.nextToken());
        v5 = Boolean.parseBoolean(myLineToken.nextToken());
        v6 = myLineToken.nextToken();
        System.out.println("v1 = " + v1);
        System.out.println("v2 = " + v2);
        System.out.println("v3 = " + v3);
        System.out.println("v4 = " + v4);
        System.out.println("v5 = " + v5);
        System.out.println("v6 = " + v6);
    }
}
