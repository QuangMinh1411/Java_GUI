import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Parsing {
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
                    FileReader("test1.txt"));
            myLine = inputFile.readLine();
            inputFile.close();
        }
        catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
        v1 = Integer.parseInt(myLine.substring(0, 1));
        v2 = myLine.substring(1, 18);
        v3 = Double.parseDouble(myLine.substring(18, 22));
        v4 = Integer.parseInt(myLine.substring(22, 24));
        v5 = Boolean.parseBoolean(myLine.substring(24, 28));
        v6 = myLine.substring(28);
        System.out.println("v1 = " + v1);
        System.out.println("v2 = " + v2);
        System.out.println("v3 = " + v3);
        System.out.println("v4 = " + v4);
        System.out.println("v5 = " + v5);
        System.out.println("v6 = " + v6);
    }

}

