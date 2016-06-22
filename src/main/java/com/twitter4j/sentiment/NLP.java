package com.twitter4j.sentiment;

import java.util.Properties;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;

/**
 * Sentiment classifier base in Standford coreNLP algorithms implementation
 */
public class NLP {
	// Basic needed properties
	public static Properties props = new Properties();
	public static StanfordCoreNLP pipeline;

	/**
	 * Responsible to configure the classifier
	 */
    public static void init() {
    	props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref, depparse, natlog, openie, sentiment");
    	pipeline = new StanfordCoreNLP(props);
    }

    /**
     * Classification itself
     * Make the sentiment analysis of a tweet
     * 
     * @param tweet
     * @return an quantified integer sentiment (greater is better)
     */
    public static int findSentiment(String tweet) {
    	// default sentiment value
        int mainSentiment = 0;
        
        if (tweet != null && tweet.length() > 0) {
            int longest = 0;
            
            // Generate a tokenized version of tweet
            Annotation annotation = pipeline.process(tweet);
            
            // Parse the text as a tree
            for (CoreMap sentence : annotation
                    .get(CoreAnnotations.SentencesAnnotation.class)) {
            	
            	// Analyze each sentence in tweet
                Tree tree = sentence
                        .get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);
                
                // Classification itself (get sentiment 'level')
                int sentiment = RNNCoreAnnotations.getPredictedClass(tree);
                
                // Checks if tree ends here
                String partText = sentence.toString();
                if (partText.length() > longest) {
                    mainSentiment = sentiment;
                    longest = partText.length();
                }
            }
        }
        return mainSentiment;
    }
}