package com.blade7co.jungleproblem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView energyLevelTv;
    private TextView resultsTv;

    private TextView tigerCount;
    private TextView monkeyCount;
    private TextView snakeCount;

    private ImageView tigerBtn;
    private ImageView monkeyBtn;
    private ImageView snakeBtn;

    private String [] food = {  "meat",
                                "fish",
                                "bugs",
                                "grain" };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        energyLevelTv = findViewById(R.id.tv_energy_level);
        resultsTv = findViewById(R.id.tv_results);

        tigerCount = findViewById(R.id.tv_tiger_counter);
        monkeyCount = findViewById(R.id.tv_monkey_counter);
        snakeCount = findViewById(R.id.tv_snake_counter);

        tigerBtn = findViewById(R.id.iv_tiger);
        monkeyBtn = findViewById(R.id.iv_monkey);
        snakeBtn = findViewById(R.id.iv_snake);

        tigerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String results = "";
                Tigers tiger = new Tigers();

                for(int i = 0; i < 10; i++){
                    results = TigerAction(tiger, results, i);
                    resultsTv.setText(results);
                }

                energyLevelTv.setText("Energy Level: " + tiger.getEnergyLevel());
                tigerCount.setText("Tiger count: " + String.valueOf(tiger.getTigersCount()));
            }
        });

        monkeyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String results = "";
                Monkeys monkey = new Monkeys();

                for(int i = 0; i < 10; i++){
                    results = MonkeyAction(monkey, results, i);
                    resultsTv.setText(results);
                }

                energyLevelTv.setText("Energy Level: " + monkey.getEnergyLevel());
                monkeyCount.setText("Monkey count: " + String.valueOf(monkey.getMonkeysCount()));
            }
        });

        snakeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String results = "";
                Snakes snake = new Snakes();

                for(int i = 0; i < 10; i++){
                    results = SnakeAction(snake, results, i);
                    resultsTv.setText(results);
                }

                energyLevelTv.setText("Energy Level: " + snake.getEnergyLevel());
                snakeCount.setText("Snake count: " + String.valueOf(snake.getSnakesCount()));
            }
        });

    }

    private String TigerAction(Tigers tiger, String results, int i) {
        switch (new Random().nextInt(3)){
            case 0:
                tiger.makeSound();
                results += "(" + (i+1) + ") Tiger is making noises (-3)\n";
                //resultsTv.setText(results);
                break;
            case 1:
                Random randFood = new Random();
                int pointer = randFood.nextInt(4);
                if (pointer == 3){
                    results += "(" + (i+1) + ") Tiger eats grain and gets \n      a stomach ache\n";
                } else {
                    tiger.eatFood(food, pointer);
                    results += "(" + (i+1) + ") Tiger is eating " + food[pointer] + " (+5)\n";
                }
                //resultsTv.setText(results);
                break;
            case 2:
                tiger.sleep();
                results += "(" + (i+1) + ") Tiger is sleeping (+5)\n";
                //resultsTv.setText(results);
                break;
            default:
                results += "Nothing\n";
               // resultsTv.setText(results);
        }
        return results;
    }

    private String MonkeyAction(Monkeys monkey, String results, int i) {
        switch (new Random().nextInt(4)){
            case 0:
                monkey.makeSound();
                results += "(" + (i+1) + ") Monkey is making noises (-4)\n";
                break;
            case 1:
                Random randFood = new Random();
                int pointer = randFood.nextInt(4);
                results += "(" + (i+1) + ") Monkey is eating " + food[pointer] + " (+2)\n";
                break;
            case 2:
                monkey.sleep();
                results += "(" + (i+1) + ") Monkey is sleeping (+10)\n";
                break;
            case 3:
                monkey.play();
                if (monkey.getEnergyLevel()>= 8){
                    results += "(" + (i+1) + ") Monekey is playing \n      \"Oooo Oooo Oooo\" (-8)\n";
                } else {
                    results += "(" + (i+1) + ") Monkey is too tired to play (0)\n";
                }
                break;
            default:
                results += "Nothing\n";
        }
        return results;
    }

    private String SnakeAction(Snakes snake, String results, int i) {
        Random rand = new Random();
        int randNumber = rand.nextInt(100);

        randNumber = randomizeNumberOne(randNumber);

        switch (randNumber){
            case 0:
                snake.makeSound();
                results += "(" + (i+1) + ") Snake is making noises (-3)\n";
                //resultsTv.setText(results);
                break;
            case 1:
                Random randFood = new Random();
                int pointer = randFood.nextInt(4);
                results += "(" + (i+1) + ") Snake is eating " + food[pointer] + " (+5)\n";
                break;
            case 2:
                snake.sleep();
                results += "(" + (i+1) + ") Snake is sleeping (+10)\n";
                break;
            default:
                results += "Nothing\n";
        }
        return results;
    }

    private int randomizeNumberOne(int randNumber) {
        if (randNumber < 33){
            randNumber = 0;
        } else if (randNumber >= 33 && randNumber < 66){
            randNumber = 1;
        } else {
            randNumber = 2;
        }
        return randNumber;
    }

    static class Animals{

        private int energyLevel = 0;
        private static int tigersCount = 0;
        private static int monkeysCount = 0;
        private static int snakesCount = 0;

        public void makeSound(){
            energyLevel -= 3;
        }

        public void eatFood(String [] food, int pointer){
            energyLevel += 5;
        }

        public void sleep(){
            energyLevel += 10;
        }

        public void setEnergyLevel(int energyLevel) {
            this.energyLevel = energyLevel;
        }

        public int getEnergyLevel() {
            return energyLevel;
        }

        public void setTigersCount(int tigersCount) {
            this.tigersCount = tigersCount;
        }

        public void setMonkeysCount(int monkeysCount) {
            this.monkeysCount = monkeysCount;
        }

        public void setSnakesCount(int snakesCount) {
           this.snakesCount = snakesCount;
        }

        public int getTigersCount() {
            return tigersCount;
        }

        public int getMonkeysCount() {
            return monkeysCount;
        }

        public int getSnakesCount() {
            return snakesCount;
        }
    }

    class Tigers extends Animals{

        public Tigers(){
            setTigersCount(getTigersCount() + 1);
        }

        @Override
        public void sleep() {
            setEnergyLevel(getEnergyLevel() + 5);
        }

        @Override
        public void eatFood(String [] food, int pointer) {
            if (food[pointer] != "grain"){
                setEnergyLevel(getEnergyLevel() + 5);
            } else {
                System.out.println("Tiger can't eat grain");
            }
        }
    }

    class Monkeys extends Animals{

        public Monkeys() {
            setMonkeysCount(getMonkeysCount() + 1);
        }

        @Override
        public void eatFood(String [] food, int pointer) {
            setEnergyLevel(getEnergyLevel() + 2);
        }

        @Override
        public void makeSound() {
            setEnergyLevel(getEnergyLevel() - 4);
        }

        public void play(){
            if (getEnergyLevel()>= 8){
                System.out.println("Oooo Oooo Oooo");
                setEnergyLevel(getEnergyLevel() - 8);
            } else {
                System.out.println("Monkey is too tired");
            }

        }
    }

    class Snakes extends Animals{

        public Snakes() {
            setSnakesCount(getSnakesCount() + 1);
        }
    }
}