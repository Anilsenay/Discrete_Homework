import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * @author Anil Senay | 150117023
 */
public class Main {

    public static boolean output[]; //this array will keep values for output file
    public static Boolean array[][]; //this array keeps input values line by line as True and False
    public static void main(String[] args) {
        File file = new File("input.cnf");
        //Creating array from input file
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            int index = 0;
            while ((st = br.readLine()) != null) {
              String[] temp = st.split(" ");
              if(temp[0].equals("p")) // if it is first line
                  array = new Boolean[Integer.parseInt(temp[3])][Integer.parseInt(temp[2])];
              else{
                  for(int i = 0; i < temp.length; i++){
                      if(Integer.parseInt(temp[i]) == 0) //when it arrive a 0, it will stop to reading that line
                          break;
                      array[index][Math.abs(Integer.parseInt(temp[i])) - 1] = (Integer.parseInt(temp[i]) > 0);
                  }
                  index++;
              }
              System.out.println(st);
            }
        }catch(Exception e){
            System.out.println(e);
        }

        boolean test[] = new boolean[array[0].length];

        for(int i = 0; i < test.length; i++){
            if(permute(test, 0))
                break;
            test[i] = true;
        }
        if(output != null){
            String outputStr = "";
            for(int i = 0; i < output.length; i++){
                if(output[i])
                    outputStr += (i+1) + " ";
                else
                    outputStr += (-(i+1)) + " ";
            }
            System.out.println("Writing to output file: " + outputStr);
            try{
                BufferedWriter writer = new BufferedWriter(new FileWriter("output.cnf"));
                writer.write("SAT\n\n");
                writer.write(outputStr);
                writer.close();
            }catch(Exception e){ System.out.println(e); };
        }
        else{
            try{
                BufferedWriter writer = new BufferedWriter(new FileWriter("output.cnf"));
                writer.write("UNSAT");
                writer.close();
            }catch(Exception e){ System.out.println(e); };
        }
    }
    //testing each permutation in this function
    //(in output, it will only print last tried one. So output may be different from yours. But it is another true solution)
    static boolean testExpression(boolean[] testArray){

        boolean expression = true;

        for(int i = 0; i < array.length; i++){
            boolean tempExpression = false;
            for(int j = 0; j < array[0].length; j++){
                if(array[i][j] == null)
                    continue;
                else if(array[i][j] == true){
                    tempExpression = (tempExpression || testArray[j]);
                }
                else if(array[i][j] == false){
                    tempExpression = tempExpression || !testArray[j];
                }

            }
            expression = expression && tempExpression;
        }
        if(expression == true){
            System.out.println("Expression is " + expression + " for this values:");
            output = new boolean[array[0].length];
            for(int i = 0; i < testArray.length; i++){
                System.out.println(testArray[i]);
                output[i] = testArray[i];
            }
        }
        return expression;
    }

    //this function for testing all possible T/F values for variables.
    public static boolean permute(boolean[] intArray, int start) {
        for(int i = start; i < intArray.length; i++){
            boolean temp = intArray[start];
            intArray[start] = intArray[i];
            intArray[i] = temp;
            permute(intArray, start + 1);
            intArray[i] = intArray[start];
            intArray[start] = temp;
        }
        if (start == intArray.length - 1) {
            return testExpression(intArray);
        }
        return false;
    }
}
