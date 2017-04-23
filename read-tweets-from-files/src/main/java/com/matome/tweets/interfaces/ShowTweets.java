package com.matome.tweets.interfaces;

import com.matome.tweets.model.Tweet;
import com.matome.tweets.model.TwitterAccount;

/**
 *
 * @author Matome
 */
public interface ShowTweets {
	
    void write(TwitterAccount user);
    
    void write(Tweet tweet);
}