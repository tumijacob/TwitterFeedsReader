package com.matome.tweets.interfaces;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import com.matome.tweets.model.TwitterAccount;


/**
 *
 * @author Matome
 */
public interface ReadUsersFile {
    Set<TwitterAccount> process(File users) throws IOException;
}