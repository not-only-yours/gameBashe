import java.util.ArrayList;
import java.util.Scanner;

public class Turns {
    public static Integer mainNumbersField = 15;

    public static Integer computerTurn(Integer curr) {
        Integer num = Integer.MIN_VALUE;
        Integer priseNum = 0;
        Tree.createTree(curr, Tree.mainElement);
        Tree.dfs(Tree.mainElement, true);
        System.out.println("Coeficient of take 1: " + Tree.mainElement.left.coeficientOfTaken);
        System.out.println("Coeficient of take 2: " + Tree.mainElement.center.coeficientOfTaken);
        System.out.println("Coeficient of take 3: " + Tree.mainElement.right.coeficientOfTaken);
        if (Tree.mainElement.left.coeficientOfTaken > num) {
            num = Tree.mainElement.left.coeficientOfTaken;
            priseNum = Tree.mainElement.left.prise;
        }
        if(Tree.mainElement.center.coeficientOfTaken > num){
            num = Tree.mainElement.center.coeficientOfTaken;
            priseNum = Tree.mainElement.center.prise;
        }
        if(Tree.mainElement.right.coeficientOfTaken > num){
            num = Tree.mainElement.right.coeficientOfTaken;
            priseNum = Tree.mainElement.right.prise;
        }
        return priseNum;
    }
    public static Integer playerTurn(){
        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }


    public static void Both(){
        Integer buff = 0;


        if(mainNumbersField!=1) {
            do {
                System.out.println("Input num from 1 to 3:");
                buff = Turns.playerTurn();
            } while (buff < 1 || buff > 3);
            mainNumbersField -= buff;
        }
        System.out.println("Current num of elements: "+mainNumbersField);



        mainNumbersField = Turns.computerTurn(mainNumbersField);
        System.out.println("Current num of elements: "+mainNumbersField);


    }


    public static String BothMedium(String a){
       String returned = "";
        if(mainNumbersField!=1) {
            mainNumbersField -= Integer.parseInt(a);
        }
        System.out.println("Current num of elements: "+mainNumbersField);
        returned+= "Player turn was: ";
        returned+=a;
        returned+="\n";
        returned+="Current num of elements: "+mainNumbersField.toString()+"\n";
        Integer buff = mainNumbersField;
        String lost = "";
        if(mainNumbersField==1){
            lost="c";
        }
        if(mainNumbersField != 1) {
            mainNumbersField = Turns.computerTurn(mainNumbersField);
            buff -= mainNumbersField;
        }

        if(mainNumbersField !=1) {
            returned += "Computer turn was: " + buff.toString() + "\n";
            returned += "Current num of elements: " + mainNumbersField.toString() + "\n";
            System.out.println("Current num of elements: " + mainNumbersField);
        }
        else {
            if(!lost.equals("c")) {
                System.out.println("Current num of elements: " + mainNumbersField);
                lost = "p";
            }
        }

        if(!lost.isEmpty()){
            if(lost.equals("p")){
                returned += "You lost";
            }else{
                returned += "You win";
            }
        }

        return returned;
    }


}
