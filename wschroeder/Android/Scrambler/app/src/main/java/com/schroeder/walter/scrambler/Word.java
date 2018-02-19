package com.schroeder.walter.scrambler;

/**
 * Created by wschroeder on 18/02/2018.
 */

import java.util.concurrent.ThreadLocalRandom;

public final class Word {

    public String scramble (String word){

        String mMissingLetters;
        String mScrambledWord;
        int mRdm;

        mScrambledWord = String.valueOf(word.charAt(0));
        mMissingLetters = word.substring(1,word.length());

        for (int i=1; mMissingLetters.length()>1; i++){
            mRdm = ThreadLocalRandom.current().nextInt(0, mMissingLetters.length()-1);
            mScrambledWord = mScrambledWord + String.valueOf(mMissingLetters.charAt(mRdm));
            if(mRdm == 0)
                mMissingLetters = mMissingLetters.substring(1, mMissingLetters.length());
            else
                mMissingLetters = mMissingLetters.substring(0,mRdm) + mMissingLetters.substring(mRdm+1, mMissingLetters.length());
        }

        mScrambledWord = mScrambledWord + mMissingLetters;

        return mScrambledWord;
    }
}