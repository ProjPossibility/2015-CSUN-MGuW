package ss12.com.lightsout;

import android.content.Context;
import android.speech.tts.TextToSpeech;

import java.util.Locale;

/**
 * Created by Alex on 2/25/2015.
 */
public class testToSpeechLogic extends MobileMain
{
    public static TextToSpeech ttobj;
    // Allows for another class to set a context to the Text to speech and create a object, also sets the type of voice accent.
    public static void textToSpeechObj(Context context)
    {
        ttobj = new TextToSpeech(context,
                new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status != TextToSpeech.ERROR) {
                            ttobj.setLanguage(Locale.UK);
                        }
                    }
                });
    }

    // Takes in a word through a string and the Class using the function for Text To Speech
    public static void sayWords(String word) {
       // textToSpeech(main, word);
        //  soundEng.playSound(this, "fail");
     //   Toast.makeText(main, word,
       //         Toast.LENGTH_SHORT).show();
        testToSpeechLogic.ttobj.speak(word, TextToSpeech.QUEUE_FLUSH, null);

    }
   // public static void textToSpeech(MobileMain main, String toSpeak)
   // {


   // }

    //Required method to use the text to speech class

    public void onInit(int status) {

    }
}

