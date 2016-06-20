package com.twitter4j.sentiment;
import java.util.ArrayList;

public class App {

    public static void main(String[] args) {
        String topic = "Restaurants";
        ArrayList<String> tweets = TweetManager.getTweets(topic);
        NLP.init();
        for(String tweet : tweets) {
        	int mainSentiment = NLP.findSentiment(tweet);
        	switch (mainSentiment) {
            case 0:
            	System.out.println(tweet + " : Very Negative");
                break;
            case 1:
            	System.out.println(tweet + " : Negative");
                break;
            case 2:
            	System.out.println(tweet + " : Neutral");
                break;
            case 3:
            	System.out.println(tweet + " : Positive");
                break;
            case 4:
            	System.out.println(tweet + " : Very Positive");
                break;
            default:
            	System.out.println(tweet + " : Fail");
                break;
            }
        }
    }
}