package com.learn;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class MontyHallProblem {
    final int MAX_ITERATIONS = 10000;
    final int capacity;
    List<Boolean> list;
    int pickUpIndex = 0;
    boolean indexPickedUp = false;
    final Strategy pickUpStrategy;
    final static Random rnd = new Random();

    public MontyHallProblem(int capacity, int takeItemsNumber, Strategy pickUpStrategy) throws Exception {
        this.capacity = capacity;
        this.list = new ArrayList<>(capacity);
        this.pickUpStrategy = pickUpStrategy;
        fillTheList();
        pickUpAnIndex();
        takeAnyNonTrue(takeItemsNumber);
    }

    public void takeAnyNonTrue(int n) throws Exception {
        for(int i=0;i<n;i++) {
            takeAnyNonTrueIndex();
        }
    }

    public int pickUpAnIndex(){
        this.pickUpIndex = pickUpStrategy.get(this).apply(indexPickedUp);
        indexPickedUp = true;
        return this.pickUpIndex;
    }

    public boolean checkResult(){
        return list.get(pickUpIndex);
    }

    private void takeAnyNonTrueIndex() throws Exception {
        int iterations = 0;
        while(true) {
            int index = rnd.nextInt(list.size());
            if(index == pickUpIndex) {
                continue;
            }
            if(list.get(index) != null && !list.get(index)){
                list.set(index, null);
                break;
            }
            if(iterations++ > MAX_ITERATIONS){
                throw new Exception(String.format("takeAnyNonTrueIndex: number of iterations %d exceded the permited max number %d", iterations, MAX_ITERATIONS));
            }
        }
    }

    private void fillTheList(){
        int trueIndex = rnd.nextInt(capacity);
        for(int i=0;i<capacity;i++){
            list.add(i, false);
        }
        list.set(trueIndex, true);
    }
}
