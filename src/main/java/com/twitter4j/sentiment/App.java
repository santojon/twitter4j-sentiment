package com.twitter4j.sentiment;
import java.util.ArrayList;
import java.util.HashMap;

public class App {

    public static void main(String[] args) {
        String topic = "Restaurants";
        
        HashMap<String, ArrayList<ArrayList<String>>> tweets = TweetManager.getTweets(topic);
        NLP.init();
        
        int total = tweets.size();
        int good = 0, bad = 0, neutral = 0;
        
        for(String tweet : tweets.keySet()) {
        	System.out.println();
        	System.out.println("-------------------------- TWEET --------------------------");
        	System.out.println(tweet);
        	System.out.println();
        	
        	// Extra info (mentions, hashtags, urls)
        	ArrayList<ArrayList<String>> info = tweets.get(tweet);
        	
        	// Mentions
        	if (info.get(0).size() > 0) {
        		System.out.println("---------- MENTIONS ---------");
        		for (String mention : info.get(0)) {
        			System.out.println(mention);
        		}
        		System.out.println();
        	}
        	
        	// Hastags
        	if (info.get(1).size() > 0) {
        		System.out.println("---------- HASTAGS ---------");
        		for (String tag : info.get(1)) {
        			System.out.println(tag);
        		}
        		System.out.println();
        	}
        	
        	// URLs
        	if (info.get(2).size() > 0) {
        		System.out.println("---------- URLS ---------");
        		for (String url : info.get(2)) {
        			System.out.println(url);
        		}
        		System.out.println();
        	}
        	
        	int mainSentiment = NLP.findSentiment(tweet);
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