package com.twitter4j.sentiment;
import java.util.ArrayList;

public class SentimentAnalyser {

    public static void main(String[] args) {
        String topic = "Restaurants";
        ArrayList<String> tweets = TweetManager.getTweets(topic);
        NLP.init();
        for(String tweet : tweets) {
            System.out.println(tweet + " : " + NLP.findSentiment(tweet));
        }
    }
}