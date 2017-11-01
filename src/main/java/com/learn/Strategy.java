package com.learn;

import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Function;

public enum Strategy {
    GET_ALWAYS_FIRST_CHOICE(0),
    GET_ALWAYS_DIFFERENT_FROM_FIRST_CHOICE(1);
    private int code;
    final static Random rnd = new Random();

    Strategy(int n){
        code = n;
    }

    public Function<Boolean, Integer> get(MontyHallProblem obj){
        if(code==0){
            return strategyGetAlwaysFirstChoice(obj);
        } else if(code==1){
            return strategyGetAlwaysDifferentFromTheFirstChoice(obj);
        }
        return null;
    }

    public Function<Boolean, Integer> strategyGetAlwaysFirstChoice(MontyHallProblem problem) {
        return new Function<Boolean, Integer>() {
            @Override
            public Integer apply(Boolean aBoolean) {
                if(!aBoolean){
                    return rnd.nextInt(problem.list.size());
                }
                return problem.pickUpIndex;
            }
        };
    }

    public Function<Boolean, Integer> strategyGetAlwaysDifferentFromTheFirstChoice(MontyHallProblem problem) {
        return new Function<Boolean, Integer>() {
            @Override
            public Integer apply(Boolean aBoolean) {
                int iterations = 0;
                while(true) {
                    int index = rnd.nextInt(problem.list.size());
                    if(problem.list.get(index) == null || index == problem.pickUpIndex) {
                        continue;
                    }
                    if(iterations++ > problem.MAX_ITERATIONS){
                        try {
                            throw new Exception(String.format("strategyGetAlwaysDifferentFromTheFirstChoice: number of iterations %d exceded the permited max number %d", iterations, problem.MAX_ITERATIONS));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    return index;
                }
            }
        };
    }
}
