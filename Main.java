
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author b1gb4ng
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static Boolean array[][];
    public static void main(String[] args) {
        File file = new File("src/main/java/input.cnf"); 
        try{
            BufferedReader br = new BufferedReader(new FileReader(file)); 
            String st; 
            int index = 0;
            while ((st = br.readLine()) != null) {
              String[] temp = st.split(" ");
              if(temp[0].equals("p"))
                  array = new Boolean[Integer.parseInt(temp[3])][Integer.parseInt(temp[2])];
              else{
                  for(int i = 0; i < temp.length; i++){
                      if(Integer.parseInt(temp[i]) == 0)
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

        boolean testArray[] = {true,false,false};
        for(int i = 0; i < testArray.length; i++){
            System.out.print(testArray[i]+", ");
        }
        System.out.println();
        boolean expression = true;
        
        for(int i = 0; i < array.length; i++){
            boolean tempExpression = false;
            for(int j = 0; j < array[0].length; j++){
                System.out.println(i+","+j+"->"+array[i][j]);
                if(array[i][j] == null)
                    continue;
                else if(array[i][j] == true){
                    tempExpression = (tempExpression || testArray[j]);
                }
                else if(array[i][j] == false){
                    tempExpression = tempExpression || !testArray[j];
                }

            }
            System.out.println("--->" + tempExpression);
            expression = expression && tempExpression;
        }
        
        System.out.println(expression);
        

    }
    
}
