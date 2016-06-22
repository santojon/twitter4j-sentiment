package com.twitter4j.sentiment;
import java.util.ArrayList;
import java.util.HashMap;

public class App {
	
	// All Twitter info we get
	private static ArrayList<TweetRetrievedInfo> allInfo = new ArrayList<TweetRetrievedInfo>();

    public static void main(String[] args) {
        String topic = "Restaurants";
        
        // Get info from Twitter
        HashMap<String, ArrayList<ArrayList<String>>> tweets = TweetManager.getTweets(topic);
        for(String tweet : tweets.keySet()) {
        	ArrayList<ArrayList<String>> info = tweets.get(tweet);
        	allInfo.add(new TweetRetrievedInfo(tweet, info));
        }
        
        // Initialize Classifier
        NLP.init();
        
        int total = allInfo.size();
        int good = 0, bad = 0, neutral = 0;
        
        for(TweetRetrievedInfo tweet : allInfo) {
        	System.out.println(tweet);
        	
        	int mainSentiment = NLP.findSentiment(tweet.getText());
        	switch (mainSentiment) {
	            case 0:
	            	bad++;
	            	System.out.println("-------------------------- Very Negative -> Negative --------------------------");
	                break;
	            case 1:
	            	bad++;
	            	System.out.println("-------------------------- Negative --------------------------");
	                break;
	            case 2:
	            	neutral++;
	            	System.out.println("-------------------------- Neutral --------------------------");
	                break;
	            case 3:
	            	good++;
	            	System.out.println("-------------------------- Positive --------------------------");
	                break;
	            case 4:
	            	good++;
	            	System.out.println("-------------------------- Very Positive -> Positive --------------------------");
	                break;
	            default:
	            	neutral++;
	            	System.out.println("-------------------------- Failure -> Neutral --------------------------");
	                break;
            }
        	System.out.println();
        }
        
        System.out.println();
        System.out.println();
        System.out.println("---------- RESULTS ---------");
        System.out.println(total + " tweets analysed.");
        System.out.println(good + " good comments.");
    	System.out.println(bad + " bad comments.");
    	System.out.println(neutral + " neutral comments.");
    	System.out.println("----------------------------");
    }
}