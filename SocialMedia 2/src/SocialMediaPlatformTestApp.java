import socialmedia.*;
import socialmedia.SocialMedia;

/**
 * A short program to illustrate an app testing some minimal functionality of a
 * concrete implementation of the SocialMediaPlatform interface -- note you will
 * want to increase these checks, and run it on your SocialMedia class (not the
 * BadSocialMedia class).
 *
 * @author Diogo Pacheco
 * @version 1.0
 */
public class SocialMediaPlatformTestApp {

    /**
     * Test method.
     *
     * @param args not used
     */
    public static void main(String[] args) throws NotActionablePostException, PostIDNotRecognisedException {
		/*
		System.out.println("The system compiled and started the execution...");

		SocialMediaPlatform platform = new SocialMedia();

		assert (platform.getNumberOfAccounts() == 0) : "Innitial SocialMediaPlatform not empty as required.";
		assert (platform.getTotalOriginalPosts() == 0) : "Innitial SocialMediaPlatform not empty as required.";
		assert (platform.getTotalCommentPosts() == 0) : "Innitial SocialMediaPlatform not empty as required.";
		assert (platform.getTotalEndorsmentPosts() == 0) : "Innitial SocialMediaPlatform not empty as required.";

		Integer id;

		try {
			id = platform.createAccount("my_handle");
			assert (platform.getNumberOfAccounts() == 1) : "number of accounts registered in the system does not match";

			platform.removeAccount(id);
			assert (platform.getNumberOfAccounts() == 0) : "number of accounts registered in the system does not match";

		} catch (IllegalHandleException e) {
			assert (false) : "IllegalHandleException thrown incorrectly";
		} catch (InvalidHandleException e) {
			assert (false) : "InvalidHandleException thrown incorrectly";
		} catch (AccountIDNotRecognisedException e) {
			assert (false) : "AccountIDNotRecognizedException thrown incorrectly";
		}
		 */
        SocialMedia platform = new SocialMedia();

        Post post1 = new Post("a");
        Post post2 = new Post("b");
        Post post3 = new Post("c");
        Comment comment1 = new Comment("d", post1.getId());
        Comment comment2 = new Comment("f", comment1.getId());
        Endorsement endorsement = new Endorsement("e", post2.getId());
        Endorsement endorsement2 = new Endorsement("g", comment2.getId());

        System.out.println(platform.showPostChildrenDetails(post1.getId()));

    }
}
