# testProject
=== TESTS DESCRIPTION ===

   There is a NetguruProject solution which inlcudes tests for website 'www.twitter.com'.
Tests are veryfing website from frontend perspective, not backend.Defined two main 
areas/features to particular verifying:
- search box
- opinion for public trends

   Tests flow don't contain one of main feature like add/delete/comment/like tweet posts
because these options are available after register/log in. This action require providing
private phone number for confirmation profile, so I can't do that.


=== TEST SETUP ===

   To ensure properly tests execution on each version on OS there are the following steps to do:
1. OS should have java version 14 installed
2. Path with java bin should be added to environment variable PATH
3. OS should have Chrome browser installed
4. Copy from project directory all folder content: out->artifacts->NetguruProject_jar
   to target folder
5. Open target folder with NetguruProject.jar file and src folder (includes chromedriver.exe)
6. Open terminal/command prompt from target folder location
7. Perform tests by providing command: java -jar NetguruProject.jar
8. All tests should perform and summary of execution will be diplay in terminal/command prompt

   To continue developing or changing/improving/fixing current tests there is a need to install InteliJ IDEA
framework and open project solution from file NetguruProject.iml.
