package com.matome.tweets.main;

import com.matome.tweets.interfaces.ShowTweets;
import com.matome.tweets.model.Tweet;
import com.matome.tweets.model.TwitterAccount;

public class TestSysOutPutter implements ShowTweets {
	private StringBuilder output = new StringBuilder(100);	
	
	public TestSysOutPutter() {

	}

	@Override
	public void write(TwitterAccount user) {
		output.append(user.getName());
        output.append('\n');	
		
	}

	@Override
	public void write(Tweet tweet) {
		output.append(String.format("\t@%s: %s", tweet.getOwner(), tweet.getMessage()));
        output.append('\n');
		
	}

    @Override
    public String toString() {
        return output.toString();
    }
}
