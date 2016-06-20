package com.twitter4j.sentiment;
import java.util.ArrayList;

public class App {

    public static void main(String[] args) {
        String topic = "Restaurants";
        
        ArrayList<String> tweets = TweetManager.getTweets(topic);
        NLP.init();
        
        int total = tweets.size();
        int good = 0, bad = 0, neutral = 0;
        
        for(String tweet : tweets) {
        	int mainSentiment = NLP.findSentiment(tweet);
        	switch (mainSentiment) {
	            case 0:
	            	bad++;
	            	System.out.println(tweet + " : Very Negative");
	                break;
	            case 1:
	            	bad++;
	            	System.out.println(tweet + " : Negative");
	                break;
	            case 2:
	            	neutral++;
	            	System.out.println(tweet + " : Neutral");
	                break;
	            case 3:
	            	good++;
	            	System.out.println(tweet + " : Positive");
	                break;
	            case 4:
	            	good++;
	            	System.out.println(tweet + " : Very Positive");
	                break;
	            default:
	            	neutral++;
	            	System.out.println(tweet + " : Fail");
	                break;
            }
        }
        
        System.out.println(total + " tweets analysed.");
        System.out.println(good + " good comments.");
    	System.out.println(bad + " bad comments.");
    	System.out.println(neutral + " neutral comments.");
    }
}