package com.twitter4j.sentiment;

import java.util.ArrayList;

/**
 * Used to make a simpler toString()
 */
enum Headers {
	TWEET("-------------------------- TWEET --------------------------\n"),
	MENTIONS("---------- MENTIONS ---------\n"),
	HASHTAGS("---------- HASHTAGS ---------\n"),
	URLS("---------- URLS ---------\n");
	
	private String name;

    private Headers(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    @Override
    public String toString() {
        return name;
    }
}

/**
 * Used to organize information from Twitter
 */
public class TweetRetrievedInfo {
	// Internal!!!
	
	private String text;
	
	private ArrayList<String> mentions;
	private ArrayList<String> tags;
	private ArrayList<String> urls;
	
	/**
	 * Constructor
	 */
	public TweetRetrievedInfo(String key, ArrayList<ArrayList<String>> value) {
		setText(key);
		setMentions(value.get(0));
		setTags(value.get(1));
		setUrls(value.get(2));
	}
	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * @return the mentions
	 */
	public ArrayList<String> getMentions() {
		return mentions;
	}
	/**
	 * @param mentions the mentions to set
	 */
	public void setMentions(ArrayList<String> mentions) {
		this.mentions = mentions;
	}
	/**
	 * @return the tags
	 */
	public ArrayList<String> getTags() {
		return tags;
	}
	/**
	 * @param tags the tags to set
	 */
	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}
	/**
	 * @return the urls
	 */
	public ArrayList<String> getUrls() {
		return urls;
	}
	/**
	 * @param urls the urls to set
	 */
	public void setUrls(ArrayList<String> urls) {
		this.urls = urls;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String result = "\n" + Headers.TWEET + text + "\n";
		
    	// Mentions
    	if (mentions.size() > 0) {
    		result+= Headers.MENTIONS;
    		for (String mention : mentions) {
    			result+= mention + "\n";
    		}
    		result+= "\n";
    	}
    	
    	// Hastags
    	if (tags.size() > 0) {
    		result+= Headers.HASHTAGS;
    		for (String tag : tags) {
    			result+= tag + "\n";
    		}
    		result+= "\n";
    	}
    	
    	// URLs
    	if (urls.size() > 0) {
    		result+= Headers.URLS;
    		for (String url : urls) {
    			result+= url + "\n";
    		}
    		result+= "\n";
    	}
		
		return result;
	}
}
