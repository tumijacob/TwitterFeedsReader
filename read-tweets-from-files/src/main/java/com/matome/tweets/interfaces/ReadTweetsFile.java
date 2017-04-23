package com.matome.tweets.interfaces;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.matome.tweets.model.Tweet;


/**
 *
 * @author Matome
 */
public interface ReadTweetsFile {
    List<Tweet> process(File tweets) throws IOException;
}