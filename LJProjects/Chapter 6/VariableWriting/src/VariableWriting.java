import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class VariableWriting {
    public static void main(String[] args) {
        // variables
        int v1 = 5;
        String v2 = "Learn Java is fun";
        double v3 = 1.23;
        int v4 = -4;
        boolean v5 = true;
        String v6 = "Another string type";
        try{
            PrintWriter outputFile1 = new PrintWriter(new BufferedWriter(new FileWriter("test1.txt")));
            outputFile1.print(v1);outputFile1.print(v2);
            outputFile1.print(v3);
            outputFile1.print(v4);
            outputFile1.print(v5);
            outputFile1.print(v6);
            outputFile1.flush();
            outputFile1.close();

        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        try{
            PrintWriter outputFile2 = new PrintWriter(new BufferedWriter(new FileWriter("test2.txt")));
            outputFile2.println(v1);
            outputFile2.println(v2);
            outputFile2.println(v3);
            outputFile2.println(v4);
            outputFile2.println(v5);
            outputFile2.println(v6);
            outputFile2.flush();
            outputFile2.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
