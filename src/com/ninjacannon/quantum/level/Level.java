
package com.ninjacannon.quantum.level;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import org.newdawn.slick.util.ResourceLoader;

/**
 * @author Dan Cannon
 */
public class Level 
{   
    private static ArrayList<String> easy;
    private static ArrayList<String> med;
    private static ArrayList<String> hard;
    private static ArrayList<String> test;
    
    private ArrayList<String> level;
    private int nextWave;
    
    public Level(int difficulty, int length)
    {
        if(easy == null){
            init();
        }
        nextWave = 0;
        level = new ArrayList<String>();
        switch(difficulty){
            case 1: GenerateEasyLevel(difficulty * length * 25);
                break;
            case 2: GenerateMedLevel(difficulty * length * 25);
                break;
            case 3: GenerateHardLevel(difficulty * length * 25);
                break;
            case 4: GenerateTestLevel(25);
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
        return wave;
    }
    
    private void GenerateEasyLevel(int rating)
    {
        Random random = new Random();
        while(rating > 0){
            if(Math.random() > .70){
                level.add("waves/med/"+ med.get(random.nextInt(med.size())));
                rating -= 2;
            } else {
                level.add("waves/easy/"+ easy.get(random.nextInt(easy.size())));
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
                level.add("waves/hard/"+ hard.get(random.nextInt(hard.size()-1)));
                rating -= 3;
            } else if (num < .15){
                level.add("waves/easy/"+ easy.get(random.nextInt(easy.size()-1)));
                rating -= 1;
            } else {
                level.add("waves/med/"+ med.get(random.nextInt(med.size()-1)));
                rating -=2;
            }
        }
    }
    
    private void GenerateHardLevel(int rating)
    {
        Random random = new Random();
        while(rating > 0){
            if(Math.random() > .75){
                level.add("waves/med/" + med.get(random.nextInt(med.size()-1)));
                rating -= 2;
            } else {
                level.add("waves/hard/"+hard.get(random.nextInt(hard.size()-1)));
                rating -= 3;
            }
        }
    }
    
    private void GenerateTestLevel(int rating)
    {
        int i = 0;
        while(rating > 0){
            level.add("waves/test/" + test.get(i));
            i++;
            rating--;
            if(i >= test.size()){
                i = 0;
            }
        }
    }
    
    public final void init()
    {
        
        Scanner sc = new Scanner(ResourceLoader.getResourceAsStream("waves/easy/waves.txt"));
        easy = new ArrayList<String>();
        while(sc.hasNextLine()){
            easy.add(sc.nextLine());
        }

        sc = new Scanner(ResourceLoader.getResourceAsStream("waves/med/waves.txt"));
        med = new ArrayList<String>();
        while(sc.hasNextLine()){
            med.add(sc.nextLine());
        }

        sc = new Scanner(ResourceLoader.getResourceAsStream("waves/hard/waves.txt"));
        hard = new ArrayList<String>();
        while(sc.hasNextLine()){
            hard.add(sc.nextLine());
        }
        
        sc = new Scanner(ResourceLoader.getResourceAsStream("waves/test/waves.txt"));
        test = new ArrayList<String>();
        while(sc.hasNextLine()){
            test.add(sc.nextLine());
        }
    }
}
