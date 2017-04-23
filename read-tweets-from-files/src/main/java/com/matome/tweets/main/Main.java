package com.matome.tweets.main;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.matome.tweets.interfaces.ReadTweetsFile;
import com.matome.tweets.interfaces.ReadUsersFile;
import com.matome.tweets.interfaces.ShowTweets;
import com.matome.tweets.io.ProcessTweets;
import com.matome.tweets.io.ProcessUsers;
import com.matome.tweets.model.Tweet;
import com.matome.tweets.model.TwitterAccount;
import com.matome.tweets.output.SysOutputter;

/**
 *
 * @author Matome
 */
public class Main {
    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    @Parameter(names = "--users", description = "File containing twitter users.", required = true)
    private String twitterUsersFile;
    @Parameter(names = "--tweets", description = "File containing tweets.", required = true)
    private String tweetsFile;

    @Parameter(names = "--help", help = true)
    private static boolean help;

    public static ShowTweets out = new SysOutputter();

    public Main() {
    }

    private void validate() throws RuntimeException {
        File users = new File(twitterUsersFile);
        if(!users.exists()) {
            throw new RuntimeException(String.format("Unable to find twitter user file: %s", twitterUsersFile));
        }

        File tweets = new File(tweetsFile);
        if(!tweets.exists()) {
            throw new RuntimeException(String.format("Unable to find tweets file: %s", tweetsFile));
        }
    }
    
    public void execute() {
        List<Tweet> tweets = null;
        Set<TwitterAccount> users = null;

        try {
            ReadTweetsFile tweetProc = new ProcessTweets();
            tweets = tweetProc.process(new File(tweetsFile));

            ReadUsersFile usersProc = new ProcessUsers();
            users = usersProc.process(new File(twitterUsersFile));

            for(TwitterAccount currentUser : users) {
                out.write(currentUser);

                for(Tweet currentTweet : tweets) {
                    final String owner = currentTweet.getOwner();

                    if(currentUser.getName().equals(owner) || currentUser.getFollowers().contains(owner)) {
                        out.write(currentTweet);
                    }
                }
            }
        } catch(IOException io) {
            LOG.error("Unable to process file correctly.");
        }
    }
    
    public static final void main(String[] args) {
        Main main = new Main();

        JCommander cmd = new JCommander(main, args);

        if(help) {
            cmd.usage();
        } else {
            try {
                main.validate();
                main.execute();
            } catch(RuntimeException exception) {
                LOG.error("", exception);

                throw exception;
            }
        }
    }
}