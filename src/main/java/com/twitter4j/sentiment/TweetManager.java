package com.twitter4j.sentiment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import twitter4j.HashtagEntity;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.URLEntity;
import twitter4j.UserMentionEntity;

public class TweetManager {
	// Max tweets per page (100 is the ceiling!!!)
	private static final int MAX = 100;
	
	// Query language 
	private static final String LANG = "pt-br";

    public static HashMap<String, ArrayList<ArrayList<String>>> getTweets(String topic) {

        Twitter twitter = new TwitterFactory().getInstance();
        HashMap<String, ArrayList<ArrayList<String>>> tweetList =
        		new HashMap<String, ArrayList<ArrayList<String>>>();
        try {
        	// Create and configure query
            Query query = new Query(topic).count(MAX);
            query.setLang(LANG);
            query.setResultType(Query.ResultType.recent);
            
            QueryResult result;
            do {
                result = twitter.search(query);
                List<Status> tweets = result.getTweets();
                
                for (Status tweet : tweets) {
                	tweetList.put(tweet.getText(), new ArrayList<ArrayList<String>>());
                	ArrayList<ArrayList<String>> lst = tweetList.get(tweet.getText());
                	
                	// Mention list
                	lst.add(new ArrayList<String>());
                	
                	// Hashtag list
                	lst.add(new ArrayList<String>());
                	
                	// URL list
                	lst.add(new ArrayList<String>());
                	
                	for (UserMentionEntity mention : tweet.getUserMentionEntities()) {
                		lst.get(0).add(mention.getText());
                	}
                	
                	for (HashtagEntity tag : tweet.getHashtagEntities()) {
                		lst.get(1).add(tag.getText());
                	}
                	
                	for (URLEntity url : tweet.getURLEntities()) {
                		lst.get(2).add(url.getText());
                	}
                }
            } while ((query = result.nextQuery()) != null);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
        }
        return tweetList;
    }
}