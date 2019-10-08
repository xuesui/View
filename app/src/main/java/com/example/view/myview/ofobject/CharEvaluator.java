package com.example.view.myview.ofobject;

import android.animation.TypeEvaluator;

public class CharEvaluator implements TypeEvaluator<Character> {
    @Override
    public Character evaluate(float v, Character character, Character t1) {
        int startInt=(int)character;
        int endInt=(int)t1;
        int currentInt=(int)(startInt + v*(endInt-startInt));
        return (char)currentInt;
    }
}
