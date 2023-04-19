import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class Keno extends JFrame{
    public String budgetFromUser;
    JPanel kenoPanel;
    private JLabel txt1;
    private JTextField playAmountField;
    private JLabel txt2;
    private JLabel budgetValue;
    private JRadioButton a1RadioButton;
    private JRadioButton a2RadioButton;
    private JRadioButton a3RadioButton;
    private JRadioButton a4RadioButton;
    private JRadioButton a5RadioButton;
    private JRadioButton a6RadioButton;
    private JRadioButton a7RadioButton;
    private JRadioButton a8RadioButton;
    private JRadioButton a9RadioButton;
    private JRadioButton a10RadioButton;
    private JRadioButton a11RadioButton;
    private JRadioButton a12RadioButton;
    private JRadioButton a13RadioButton;
    private JRadioButton a14RadioButton;
    private JRadioButton a15RadioButton;
    private JRadioButton a16RadioButton;
    private JRadioButton a17RadioButton;
    private JRadioButton a18RadioButton;
    private JRadioButton a19RadioButton;
    private JRadioButton a20RadioButton;
    private JRadioButton a21RadioButton;
    private JRadioButton a22RadioButton;
    private JRadioButton a23RadioButton;
    private JRadioButton a24RadioButton;
    private JRadioButton a25RadioButton;
    private JRadioButton a26RadioButton;
    private JRadioButton a27RadioButton;
    private JRadioButton a28RadioButton;
    private JRadioButton a29RadioButton;
    private JRadioButton a30RadioButton;
    private JRadioButton a31RadioButton;
    private JRadioButton a32RadioButton;
    private JRadioButton a33RadioButton;
    private JRadioButton a34RadioButton;
    private JRadioButton a35RadioButton;
    private JRadioButton a36RadioButton;
    private JRadioButton a37RadioButton;
    private JRadioButton a38RadioButton;
    private JRadioButton a39RadioButton;
    private JRadioButton a40RadioButton;
    private JLabel nChosen;
    private JButton saveMyChoiceButton;
    private JButton PLAYButton;
    private JLabel generatedTxt;
    private JLabel numbersGeneratedTxt;

    private ArrayList<JRadioButton> radioButtons;
    final int MAX_CHOICES = 10;
    ArrayList<Integer> zgjedhjet;
    ArrayList<Integer> numratRandomTeGjeneruar;
    String playAmount = playAmountField.getText();

    //Double budget = Double.parseDouble(budgetValue.getText());
    Double budget = 1000.0;

    public Keno(){
        budgetValue.setText(Double.toString(budget));
        playAmountField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePlayAmount();
                if(!checkPlayAmount(playAmount)){
                    JOptionPane.showMessageDialog(kenoPanel, "Play amount exceeds your budget!");
                }
            }
        });
        radioButtons = new ArrayList<JRadioButton>();
        radioButtons.add(a1RadioButton);
        radioButtons.add(a2RadioButton);
        radioButtons.add(a3RadioButton);
        radioButtons.add(a4RadioButton);
        radioButtons.add(a5RadioButton);
        radioButtons.add(a6RadioButton);
        radioButtons.add(a7RadioButton);
        radioButtons.add(a8RadioButton);
        radioButtons.add(a9RadioButton);
        radioButtons.add(a10RadioButton);
        radioButtons.add(a11RadioButton);
        radioButtons.add(a12RadioButton);
        radioButtons.add(a13RadioButton);
        radioButtons.add(a14RadioButton);
        radioButtons.add(a15RadioButton);
        radioButtons.add(a16RadioButton);
        radioButtons.add(a17RadioButton);
        radioButtons.add(a18RadioButton);
        radioButtons.add(a19RadioButton);
        radioButtons.add(a20RadioButton);
        radioButtons.add(a21RadioButton);
        radioButtons.add(a22RadioButton);
        radioButtons.add(a23RadioButton);
        radioButtons.add(a24RadioButton);
        radioButtons.add(a25RadioButton);
        radioButtons.add(a26RadioButton);
        radioButtons.add(a27RadioButton);
        radioButtons.add(a28RadioButton);
        radioButtons.add(a29RadioButton);
        radioButtons.add(a30RadioButton);
        radioButtons.add(a31RadioButton);
        radioButtons.add(a32RadioButton);
        radioButtons.add(a33RadioButton);
        radioButtons.add(a34RadioButton);
        radioButtons.add(a35RadioButton);
        radioButtons.add(a36RadioButton);
        radioButtons.add(a37RadioButton);
        radioButtons.add(a38RadioButton);
        radioButtons.add(a39RadioButton);
        radioButtons.add(a40RadioButton);

        saveMyChoiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int countChosen = 0; //mi ngje sa numra i ka zgedh
                for(JRadioButton rBtn : radioButtons){
                    if(rBtn.isSelected()){ // nese osht i zgedhen numri
                        countChosen++; //rrite per njo countChosen
                    }
                }
                while(countChosen > MAX_CHOICES){
                    JOptionPane.showMessageDialog(kenoPanel, "Maximum numbers you can choose is: "+MAX_CHOICES);
                    for(JRadioButton rBtn : radioButtons){
                        rBtn.setSelected(false);
                        countChosen=0;
                    }
                }
                if(countChosen == 0){
                    JOptionPane.showMessageDialog(kenoPanel, "Please select at least 1 number!");
                }
                nChosen.setText(String.valueOf(countChosen)); //teksti onash qe bohet update me vleren countChosen
                //ArrayList<Integer> userChoices = saveChoices();
                zgjedhjet = saveChoices();
            }
        });

        PLAYButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                budget -= Double.parseDouble(playAmount);
                if(budget < 0){
                    JOptionPane.showMessageDialog(kenoPanel,"Play amount exceeds your budget!");
                }
                if(playAmount == null){
                    playAmount = Double.toString(0.0);
                }
                updateBudget();
                numratRandomTeGjeneruar = generateRandomNumbers();
                generatedTxt.setText("Generating random numbers: ");
                new SwingWorker<Void, Void>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                        Thread.sleep(2000);
                        ArrayList<Integer> numbers = numratRandomTeGjeneruar;
                        boolean isFinished = false;
                        for (Integer number : numbers) {
                            if(number == numbers.get(0)){
                                numbersGeneratedTxt.setText(number.toString());
                                Thread.sleep(2000);
                            }else {
                                numbersGeneratedTxt.setText(numbersGeneratedTxt.getText() + " , " + number.toString());
                                Thread.sleep(2000);
                            }
                        }
                        Thread.sleep(2500);

                        JOptionPane.showMessageDialog(kenoPanel, "Keni qelluar: " + calculateResult() + " numra nga " + saveChoices().size() + " numrat e zgjedhur!\n"+"Ju keni fituar "+calculateMoneyWon()+" euro");
                        budget += calculateMoneyWon();
                        updateBudget();
                        return null;
                    }
                }.execute();
            }
        });
    }

    public boolean checkPlayAmount(String b){
        if(Double.parseDouble(b) > Double.parseDouble(budgetValue.getText())){
            return false;
        }
        return true;
    }

    public ArrayList<Integer> saveChoices(){ // numrat e zgedhen nga useri na kthen me ni arraylist si ints
        ArrayList<Integer> choices = new ArrayList<Integer>();
        for(JRadioButton rBtn : radioButtons){
            if(rBtn.isSelected()){
                choices.add(Integer.parseInt(rBtn.getText()));
            }
        }
        return choices;
    }

    public ArrayList<Integer> generateRandomNumbers(){ //numrat e zgedhen nga kompjuteri me ni arraylist si ints
        ArrayList<Integer> tempArray = new ArrayList<Integer>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int randomNumber = random.nextInt(40) + 1;
            while(tempArray.contains(randomNumber)){
                randomNumber = random.nextInt(40)+1;
            }
            if(!tempArray.contains(randomNumber)){
                tempArray.add(randomNumber);
            }
        }
        return tempArray;
    }

    public int calculateResult(){ //ta kthen sa numra jon qellu
        int countNumratEQelluar = 0;
        for(int i : numratRandomTeGjeneruar){
            if(saveChoices().contains(i)){
                countNumratEQelluar++;
            }
        }
        return countNumratEQelluar;
    }

    public double calculateMoneyWon(){
        int nrQellun = calculateResult();
        int nrZgedhen = zgjedhjet.size();
        double fitimi = 0;

        if(nrQellun == 1 && nrZgedhen == 1){
            fitimi = (double)Integer.parseInt(playAmount) * 2;
        }
        if(nrQellun == 1 && nrZgedhen == 2){
            fitimi = (double)Integer.parseInt(playAmount) * 1.5;
        }
        if(nrQellun == 1 && nrZgedhen == 3){
            fitimi = (double)Integer.parseInt(playAmount) * 1.25;
        }
        if(nrQellun == 1 && nrZgedhen == 4){
            fitimi = (double)Integer.parseInt(playAmount) * 1.125;
        }
        if(nrQellun == 1 && nrZgedhen == 5){
            fitimi = (double)Integer.parseInt(playAmount) * 1;
        }
        if(nrQellun == 1 && nrZgedhen == 6){
            fitimi = (double)Integer.parseInt(playAmount) * 0.8;
        }
        if(nrQellun == 1 && nrZgedhen == 7 || nrZgedhen == 8 || nrZgedhen == 9 || nrZgedhen == 10){
            fitimi = (double)Integer.parseInt(playAmount) * 0.1;
        }

        if(nrQellun == 2 && nrZgedhen == 2){
            fitimi = (double)Integer.parseInt(playAmount) * 2;
        }
        if(nrQellun == 2 && nrZgedhen == 3){
            fitimi = (double)Integer.parseInt(playAmount) * 1.9;
        }
        if(nrQellun == 2 && nrZgedhen == 4){
            fitimi = (double)Integer.parseInt(playAmount) * 1.75;
        }
        if(nrQellun == 2 && nrZgedhen == 5){
            fitimi = (double)Integer.parseInt(playAmount) * 1.5;
        }
        if(nrQellun == 2 && nrZgedhen == 6){
            fitimi = (double)Integer.parseInt(playAmount) * 1.20;
        }
        if(nrQellun == 2 && nrZgedhen == 7){
            fitimi = (double)Integer.parseInt(playAmount) * 1;
        }
        if(nrQellun == 2 && nrZgedhen == 8 || nrZgedhen == 9 || nrZgedhen == 10){
            fitimi = (double)Integer.parseInt(playAmount) * 0.1;
        }

        if(nrQellun == 3 && nrZgedhen == 3){
            fitimi = (double)Integer.parseInt(playAmount) * 3;
        }
        if(nrQellun == 3 && nrZgedhen == 4){
            fitimi = (double)Integer.parseInt(playAmount) * 2.75;
        }
        if(nrQellun == 3 && nrZgedhen == 5){
            fitimi = (double)Integer.parseInt(playAmount) * 2;
        }
        if(nrQellun == 3 && nrZgedhen == 6){
            fitimi = (double)Integer.parseInt(playAmount) * 1.25;
        }
        if(nrQellun == 3 && nrZgedhen == 7){
            fitimi = (double)Integer.parseInt(playAmount) * 1;
        }
        if(nrQellun == 3 && nrZgedhen == 8 || nrZgedhen == 9 || nrZgedhen == 10){
            fitimi = (double)Integer.parseInt(playAmount) * 0.1;
        }
        if(nrQellun == 4 && nrZgedhen == 4){
            fitimi = (double)Integer.parseInt(playAmount) * 4;
        }
        if(nrQellun >= 4 && nrZgedhen >= 5){
            fitimi = (double)Integer.parseInt(playAmount) * 100;
        }

        return fitimi;
    }

    public void updateBudget(){
        budgetValue.setText(Double.toString(budget));
    }
    public void updatePlayAmount(){
        playAmount = playAmountField.getText();
    }
}
