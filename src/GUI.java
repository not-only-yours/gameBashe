import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Random;

public class GUI extends JFrame{
    boolean comp = true;
    private ButtonGroup group= new ButtonGroup();
    private JLabel text = new JLabel("Check hardness");
    private  JRadioButton easy= new JRadioButton("Easy");
    private  JRadioButton medium= new JRadioButton("Medium");
    private  JRadioButton hard= new JRadioButton("Hard");
    private JLabel num = new JLabel("Elements was: "+Turns.mainNumbersField);
    private JButton button = new JButton("Make step");
    private JButton buttonNewGame = new JButton("New Game");
    private JTextField input = new JTextField("",5);
    private JLabel label = new JLabel("Input from 1 to 3");
    private JLabel label1 = new JLabel("");
    private JLabel label2 = new JLabel("");
    private JLabel label3 = new JLabel("");
    private JLabel label4 = new JLabel("");
    private JLabel label5 = new JLabel("");

    public GUI() {
        super("Game");
        this.setBounds(100,100,500,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(14,1,0,0));
        this.getContentPane().add(buttonNewGame);
        buttonNewGame.addActionListener(new NewButtonEventListener());
        container.add(text);
        container.add(easy);
        container.add(medium);
        container.add(hard);
        group.add(easy);
        group.add(medium);
        group.add(hard);
        group.clearSelection();
        container.add(num);
        container.add(label);
        container.add(input);
        button.addActionListener(new AlphaBetaButtonEventListener());
        container.add(button);
        container.add(label1);
        container.add(label2);
        container.add(label3);
        container.add(label4);
        container.add(label5);



    }

    class NewButtonEventListener implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent) {
            Turns.mainNumbersField = 15;
            comp = true;
            num.setText("Elements was: "+ Turns.mainNumbersField+"");
            label1.setText("");
            label2.setText("");
            label3.setText("");
            label4.setText("");
            label5.setText("");
        }
    }

    class ButtonEventListener implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent) {

            String message = "";

            if(Integer.parseInt(input.getText())>=1 && Integer.parseInt(input.getText()) <= 3 && Integer.parseInt(input.getText())< Turns.mainNumbersField) {
                    message = Turns.BothMedium(input.getText());
                    //System.out.println(message);
                   String[] mess= message.split("\n");
                    if(mess.length==1){
                        label1.setText(mess[0]);
                        label2.setText("");
                        label3.setText("");
                        label4.setText("");
                        label5.setText("");
                    }
                    if(mess.length == 3){
                        label1.setText(mess[0]);
                        label2.setText(mess[1]);
                        label3.setText(mess[2]);
                        label4.setText("");
                        label5.setText("");
                    }
                    if(mess.length == 4){
                        label1.setText(mess[0]);
                        label2.setText(mess[1]);
                        label3.setText(mess[2]);
                        label4.setText(mess[3]);
                        label5.setText("");
                    }
                    if(mess.length == 5){
                        label1.setText(mess[0]);
                        label2.setText(mess[1]);
                        label3.setText(mess[2]);
                        label4.setText(mess[3]);
                        label5.setText(mess[4]);
                    }
            }

                else{
                label1.setText("Write correct input");
                label2.setText("");
                label3.setText("");
                label4.setText("");
                label5.setText("");
                }
            num.setText("Elements was: "+Turns.mainNumbersField+"");
        }
    }


    class AlphaBetaButtonEventListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            if (Turns.mainNumbersField != 1) {
                if (medium.isSelected()) {
                    if (Integer.parseInt(input.getText()) > 0 && Integer.parseInt(input.getText()) < 4 && Turns.mainNumbersField - Integer.parseInt(input.getText()) >= 1)
                        mediumhardness();
                    else {
                        label1.setText("Write correct input");
                        label2.setText("");
                        label3.setText("");
                        label4.setText("");
                        label5.setText("");
                    }
                } else if (hard.isSelected()) {
                    if (Integer.parseInt(input.getText()) > 0 && Integer.parseInt(input.getText()) < 4 && Turns.mainNumbersField - Integer.parseInt(input.getText()) >= 1)
                        hardMode();
                    else {
                        label1.setText("Write correct input");
                        label2.setText("");
                        label3.setText("");
                        label4.setText("");
                        label5.setText("");
                    }
                } else if (easy.isSelected()) {
                    if(Turns.mainNumbersField >= 6)
                        if (Integer.parseInt(input.getText()) > 0 && Integer.parseInt(input.getText()) < 4 && Turns.mainNumbersField - Integer.parseInt(input.getText()) >= 1)
                           easyHardness();
                        else {
                            label1.setText("Write correct input");
                            label2.setText("");
                            label3.setText("");
                            label4.setText("");
                            label5.setText("");
                        }
                    else
                    if (Integer.parseInt(input.getText()) > 0 && Integer.parseInt(input.getText()) < 4)
                        mediumhardness();
                    else {
                        label1.setText("Write correct input");
                        label2.setText("");
                        label3.setText("");
                        label4.setText("");
                        label5.setText("");
                    }
                } else {
                    label1.setText("Select hardness");
                    label2.setText("");
                    label3.setText("");
                    label4.setText("");
                    label5.setText("");
                }
            } else {
                label1.setText("Game ends");
                label2.setText("Click new game");
                label3.setText("");
                label4.setText("");
                label5.setText("");
            }
        }

        public void mediumhardness() {
            if (Turns.mainNumbersField != 1) {
                Integer player = Integer.parseInt(input.getText());

                label1.setText("Player turn was: " + player);
                Turns.mainNumbersField -= player;
                label2.setText("Current num of elements: " + Turns.mainNumbersField);

            }
            if (Turns.mainNumbersField == 1) {
                label3.setText("You win");
                label4.setText("");
            }


            if (Turns.mainNumbersField != 1) {


                ArrayList<Integer> typeOfTakes = new ArrayList<>();
                typeOfTakes.add(Tree.finder.get(16 - Turns.mainNumbersField).coeficientOfTaken);
                if (Turns.mainNumbersField > 2)
                    typeOfTakes.add(Tree.finder.get(17 - Turns.mainNumbersField).coeficientOfTaken);
                if (Turns.mainNumbersField > 3)
                    typeOfTakes.add(Tree.finder.get(18 - Turns.mainNumbersField).coeficientOfTaken);

                Integer maximum = Collections.max(typeOfTakes);
                //System.out.println(maximum);
                Integer compTurn = 0;


                for (Element q : Tree.finder) {
                    //System.out.println(q.coeficientOfTaken + " " + compTurn);
                    if (q.coeficientOfTaken.equals(maximum)) {
                        compTurn = q.prise;
                    }
                }
                //System.out.println(compTurn);

                label3.setText("Computer turn was: " + (Turns.mainNumbersField - compTurn));
                Turns.mainNumbersField = compTurn;
                label4.setText("Current num of elements: " + Turns.mainNumbersField);
                label5.setText("");
                if (Turns.mainNumbersField == 1) {
                    label5.setText("You lost");
                }
                num.setText("Elements was: " + Turns.mainNumbersField + "");
            }
        }

        public void easyHardness() {
            Integer maximum = 0;
            if (Turns.mainNumbersField != 1) {
                Integer player = Integer.parseInt(input.getText());

                label1.setText("Player turn was: " + player);
                Turns.mainNumbersField -= player;
                label2.setText("Current num of elements: " + Turns.mainNumbersField);

            }
            if (Turns.mainNumbersField == 1) {
                label3.setText("You win");
                label4.setText("");
            }


            if (Turns.mainNumbersField != 1) {


                ArrayList<Integer> typeOfTakes = new ArrayList<>();
                typeOfTakes.add(Tree.finder.get(16 - Turns.mainNumbersField).coeficientOfTaken);
                if (Turns.mainNumbersField > 2)
                    typeOfTakes.add(Tree.finder.get(17 - Turns.mainNumbersField).coeficientOfTaken);
                if (Turns.mainNumbersField > 3)
                    typeOfTakes.add(Tree.finder.get(18 - Turns.mainNumbersField).coeficientOfTaken);


                System.out.println(typeOfTakes.size());
                if (typeOfTakes.size() == 3) {
                    Random rn = new Random();
                    Integer nn = rn.nextInt(2);
                    System.out.println("nn" + nn);
                    if (nn == 0) {
                        maximum = typeOfTakes.get(0);
                    }
                    if (nn == 1) {
                        maximum = typeOfTakes.get(1);
                    }
                    if (nn == 2) {
                        maximum = typeOfTakes.get(2);
                    }

                } else {
                    Random rn = new Random(1);
                    Integer nn = rn.nextInt();
                    if (nn == 0) {
                        maximum = typeOfTakes.get(0);
                    } else {
                        maximum = typeOfTakes.get(1);
                    }
                }
                //System.out.println(maximum);
                Integer compTurn = 0;


                for (Element q : Tree.finder) {
                    //System.out.println(q.coeficientOfTaken + " " + compTurn);
                    if (q.coeficientOfTaken.equals(maximum)) {
                        compTurn = q.prise;
                    }
                }
                //System.out.println(compTurn);

                label3.setText("Computer turn was: " + (Turns.mainNumbersField - compTurn));
                Turns.mainNumbersField = compTurn;
                label4.setText("Current num of elements: " + Turns.mainNumbersField);
                label5.setText("");
                if (Turns.mainNumbersField == 1) {
                    label5.setText("You lost");
                }
                num.setText("Elements was: " + Turns.mainNumbersField + "");
            }
        }


        public void compHard() {
            if (Turns.mainNumbersField != 1) {
                ArrayList<Integer> typeOfTakes = new ArrayList<>();
                typeOfTakes.add(Tree.finder.get(16 - Turns.mainNumbersField).coeficientOfTaken);
                if (Turns.mainNumbersField > 2)
                    typeOfTakes.add(Tree.finder.get(17 - Turns.mainNumbersField).coeficientOfTaken);
                if (Turns.mainNumbersField > 3)
                    typeOfTakes.add(Tree.finder.get(18 - Turns.mainNumbersField).coeficientOfTaken);

                Integer maximum = Collections.max(typeOfTakes);
                //System.out.println(maximum);
                Integer compTurn = 0;


                for (Element q : Tree.finder) {
                    //System.out.println(q.coeficientOfTaken + " " + compTurn);
                    if (q.coeficientOfTaken.equals(maximum)) {
                        compTurn = q.prise;
                    }
                }
                //System.out.println(compTurn);

                label1.setText("Computer turn was: " + (Turns.mainNumbersField - compTurn));
                Turns.mainNumbersField = compTurn;
                label2.setText("Current num of elements: " + Turns.mainNumbersField);
                label3.setText("");
                label4.setText("");
                if (Turns.mainNumbersField == 1) {
                    label3.setText("You lost");
                    label4.setText("");
                    label5.setText("");
                }
            }
            num.setText("Elements was: " + Turns.mainNumbersField + "");
        }

        public void playerHard() {
            if (Turns.mainNumbersField != 1) {
                int player = Integer.parseInt(input.getText());

                label3.setText("Player turn was: " + player);
                Turns.mainNumbersField -= player;
                label4.setText("Current num of elements: " + Turns.mainNumbersField);

                if (Turns.mainNumbersField == 1) {
                    label5.setText("You win");
                }
            }
            num.setText("Elements was: " + Turns.mainNumbersField + "");
        }


        public void hardMode() {
            if (comp) {
                compHard();
                comp = false;
            } else {
                playerHard();
                comp = true;
            }

        }
    }
        }




