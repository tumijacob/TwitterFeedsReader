package com.matome.tweets.main;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.core.io.ClassPathResource;

import com.beust.jcommander.JCommander;
import com.matome.tweets.interfaces.ShowTweets;

public class MainTests {
	
	@Test
	public void testMainWithValidFiles_shouldPrintCorrectOutput() throws IOException{
	 
		// Set up fixtures
	    ClassPathResource tweetFileRes = new ClassPathResource("valid-tweets.txt");
	    ClassPathResource usersFileRes = new ClassPathResource("valid-users.txt");
	    ShowTweets capturer = new TestSysOutPutter();
	    Main.out = capturer;
	    String path = "/home/matome/workspace/read-tweets-from-files/resources/expectedOutput.txt";
	    String expectedOutput = fileToString(path);

	    Main.main(new String[] {"--tweets", tweetFileRes.getFile().getPath(), 
	                               "--users" , usersFileRes.getFile().getPath()});

        // Assertions
	    Assert.assertEquals(expectedOutput, capturer.toString());
	}
	
	@Test
	public void testMainWithNonExistentUser_shouldPrintInCorrectOutput() throws IOException{
	 
		// Set up fixtures
		String expectedOutput = "Alan\nMartin\nWard\n";
	    ClassPathResource invalidTweetsFile = new ClassPathResource("tweets-with-non-existant-user.txt");
	    ClassPathResource usersFileRes = new ClassPathResource("valid-users.txt");
	    ShowTweets capturer = new TestSysOutPutter();
	    Main.out = capturer;
	   
	    // Test
	    Main.main(new String[] {"--tweets", invalidTweetsFile.getFile().getPath(), 
	                               "--users" , usersFileRes.getFile().getPath()});

        // Assertions
	    Assert.assertEquals(expectedOutput, capturer.toString());
	}
	 
	@Test(expected = RuntimeException.class)
	public void testMainWithInvalidFileEncoding_shouldThrowError() throws IOException {     
	    ClassPathResource invalidEncodingTweetsFile = new ClassPathResource("invalid-file-encoding.txt");
	    ClassPathResource usersFileRes = new ClassPathResource("valid-users.txt");
	    
	    // Test
	    Main.main(new String[] {"--tweets", invalidEncodingTweetsFile.getFile().getPath(), 
                "--users" , usersFileRes.getFile().getPath()});
     
        // Assertions
        Assert.fail(); 
	 }
	
	@Test(expected = FileNotFoundException.class)
    public void testMainWithIrrelevantTweetsFile_shouldThrowException() throws IOException {
		
		// Set up fixture
	    ClassPathResource irrelevantTweetsFile = new ClassPathResource("non-existant-tweet-file.txt");
        ClassPathResource usersFileRes = new ClassPathResource("valid-users.txt");
    	String[] args = {"--tweets", irrelevantTweetsFile.getFile().getPath(), 
                             "--users" , usersFileRes.getFile().getPath()};
    	
        Main mainMock = Mockito.mock(Main.class);
    	Main main = new Main();
    		
    	JCommander.newBuilder()
            .addObject(main)
    		.build()
    		
        // Test
    		.parse(args);
    	
    	// Assertions
    	Assert.fail();
    	verify(mainMock, times(0)).execute();
    		
     }
	
	@Test(expected = FileNotFoundException.class)
    public void testMainWithIrrelevantUsersFile_shouldThrowException() throws IOException {
		
		// Set up fixture
	    ClassPathResource tweetsFile = new ClassPathResource("valid-tweets.txt");
        ClassPathResource irrelevantUsersFile = new ClassPathResource("irrelaventUsers-users.txt");
    	String[] args = {"--tweets", tweetsFile.getFile().getPath(), 
                             "--users" , irrelevantUsersFile.getFile().getPath()};
    	
        Main mainMock = Mockito.mock(Main.class);
    	Main main = new Main();
    		
    	JCommander.newBuilder()
            .addObject(main)
    		.build()
    		
        // Test
    		.parse(args);
    	
    	// Assertions
    	verify(mainMock, times(0)).execute();   
    }
	
	@Test(expected = RuntimeException.class)
    public void testMainWithMalformedTweetsFile_shouldThrowException() throws IOException {
		
		// Set up fixture
        ClassPathResource malformedTweetsFile = new ClassPathResource("malformed-tweet-file.txt");
        ClassPathResource usersFileRes = new ClassPathResource("valid-users.txt");
        
        // Test
        Main.main(new String[] {"--tweets", malformedTweetsFile.getFile().getPath(), 
                "--users" , usersFileRes.getFile().getPath()});
     
        // Assertions
        Assert.fail(); 
     
    }
	
	@Test(expected = RuntimeException.class)
    public void testMainWithMalformedUserFile_shouldThrowExcetion() throws IOException {
        ClassPathResource tweetFileRes = new ClassPathResource("valid-tweets.txt");
        ClassPathResource usersFileRes = new ClassPathResource("malformed-user-file.txt");

        // Test
        Main.main(new String[] {"--tweets", tweetFileRes.getFile().getPath(), 
                    "--users" , usersFileRes.getFile().getPath()});
         
        // Assertions
        Assert.fail(); 
    }
			 
	private String fileToString(String path) throws IOException {
        File file = new File(path);
	    BufferedReader br = new BufferedReader(new FileReader(file));
	     try {
	         StringBuilder sb = new StringBuilder();
	         String line = br.readLine();

	         while (line != null) {
	             sb.append(line);
	             sb.append("\n");
	             line = br.readLine();
	         }
	         return sb.toString();
	     } finally {
	         br.close();
	     }
	 } 
}
