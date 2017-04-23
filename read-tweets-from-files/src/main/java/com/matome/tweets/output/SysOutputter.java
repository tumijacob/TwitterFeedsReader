package com.matome.tweets.output;

import com.matome.tweets.interfaces.ShowTweets;
import com.matome.tweets.model.Tweet;
import com.matome.tweets.model.TwitterAccount;

/**
 *
 * @author Matome
 */
public class SysOutputter implements ShowTweets {

    public void write(Tweet tweet) {
        System.out.println(String.format("\t@%s: %s", tweet.getOwner(), tweet.getMessage()));
    }
    
    public void write(TwitterAccount user) {
        System.out.println(user.getName());
    }

}