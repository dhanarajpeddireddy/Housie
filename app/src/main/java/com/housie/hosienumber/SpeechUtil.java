package com.housie.hosienumber;

import android.content.Context;
import android.speech.tts.TextToSpeech;

public class SpeechUtil {

    TextToSpeech toSpeech;

    SpeechUtil(Context context,TextToSpeech.OnInitListener listener)
    {
        toSpeech=new TextToSpeech(context, listener);
    }

    public void speak(String text)
    {
        toSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null);
    }
}
