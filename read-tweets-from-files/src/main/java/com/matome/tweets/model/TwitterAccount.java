package com.matome.tweets.model;

import java.util.Set;

/**
 *
 * @author Matome
 */
public class TwitterAccount implements Comparable<TwitterAccount> {
    private String name;
    private Set<String> followers; 

    public TwitterAccount() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<String> followers) {
        this.followers = followers;
    }

    public int compareTo(TwitterAccount comparison) {
        return name.compareToIgnoreCase(comparison.name);
    }

    public int compareTo(String comparison) {
        return name.compareToIgnoreCase(comparison);
    }
}