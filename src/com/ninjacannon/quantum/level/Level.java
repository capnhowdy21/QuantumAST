
package com.ninjacannon.quantum.level;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * @author Dan Cannon
 */
public class Level 
{
    private static ArrayList<String> easy = 
            new ArrayList<String>(Arrays.asList(new File("res/waves/easy").list()));
    private static ArrayList<String> med = 
            new ArrayList<String>(Arrays.asList(new File("res/waves/med").list()));
    private static ArrayList<String> hard = 
            new ArrayList<String>(Arrays.asList(new File("res/waves/hard").list()));
    
    private ArrayList<String> level;
    private int nextWave;
    
    public Level(int difficulty, int length)
    {
        nextWave = 0;
        level = new ArrayList<String>();
        switch(difficulty){
            case 1: GenerateEasyLevel(difficulty * length * 50);
                break;
            case 2: GenerateMedLevel(difficulty * length * 50);
                break;
            case 3: GenerateHardLevel(difficulty * length * 50);
                break;
        }
    }
    
    public String nextWave(){
        String wave;
        if(nextWave < level.size()){
            wave = level.get(nextWave++);
        } else {
            wave = null;
        }
        System.out.println(wave);
        return wave;
    }
    
    private void GenerateEasyLevel(int rating)
    {
        Random random = new Random();
        while(rating > 0){
            if(Math.random() > .50){
                level.add("res/waves/med/" + med.get(random.nextInt(med.size())));
                rating -= 2;
            } else {
                level.add("res/waves/easy/" + easy.get(random.nextInt(easy.size())));
                rating -= 1;
            }
        }
    }
    
    private void GenerateMedLevel(int rating)
    {
        Random random = new Random();
        while(rating > 0){
            double num = Math.random();
            if(num > .85){
                level.add("res/waves/hard/" + hard.get(random.nextInt(hard.size()-1)));
                rating -= 3;
            } else if (num < .15){
                level.add("res/waves/easy/" + easy.get(random.nextInt(med.size()-1)));
                rating -= 1;
            } else {
                level.add("res/waves/med/"  + med.get(random.nextInt(easy.size()-1)));
                rating -=2;
            }
        }
    }
    
    private void GenerateHardLevel(int rating)
    {
        Random random = new Random();
        while(rating > 0){
            if(Math.random() > .75){
                level.add("res/waves/med/" + med.get(random.nextInt(med.size()-1)));
                rating -= 2;
            } else {
                level.add("res/waves/hard/" + hard.get(random.nextInt(hard.size()-1)));
                rating -= 3;
            }
        }
    }
}