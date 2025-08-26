import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class VariableReading {
    public static void main(String[] args) {
        int v1;
        String v2;
        double v3;
        int v4;
        boolean v5;
        String v6;
        try{
            BufferedReader inputFile = new BufferedReader(new
                    FileReader("test2.txt"));
            v1 = Integer.parseInt(inputFile.readLine());
            v2 = inputFile.readLine();
            v3 = Double.parseDouble(inputFile.readLine());
            v4 = Integer.parseInt(inputFile.readLine());
            v5 = Boolean.parseBoolean(inputFile.readLine());
            v6 = inputFile.readLine();
            System.out.println("v1 = " + v1);
            System.out.println("v2 = " + v2);
            System.out.println("v3 = " + v3);
            System.out.println("v4 = " + v4);
            System.out.println("v5 = " + v5);
            System.out.println("v6 = " + v6);
            inputFile.close();
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
