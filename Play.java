import java.util.Scanner;

public class Play {

    public Play(){

    }

    public boolean positionMatch(Character a, Character b){
        if(a.getPosition_x() == b.getPosition_x() && a.getPosition_y() == b.getPosition_y()){
            return true;
        } else{
            return false;
        }
    }
    

    public static void main(String[] args) {
        Play game = new Play();
        Scanner input = new Scanner(System.in);

        System.out.println("Hello, welcome to the game!");
        System.out.println("Would you like to play? Yes to play end to end");
        String response = input.nextLine();

        while(!((response.toLowerCase()).equals("end"))){

        }


        input.close();
    }

}
