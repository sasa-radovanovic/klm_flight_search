# klm_flight_search (IT test case for KLM recruitment process)
This application is coded on December 4th in purpose of KLM recruitment process for position of Full Stack Developer. Work on this application was done according to guidelines - in under 4 hours (I started coding at 13:57 and finished everything at 17:42). I am not aware if this 4 hour time limit is just a guideline for the test - but I wanted to follow it, so that you guys at KLM can understand my expertise level and what can I  do in 4 hour time.

This is neither the prettiest or the best code I've written - I know what I would change if I had more time.

### Build and deploy

Although you guys used gradle for build, package and deploy, I wanted to differ a little bit - so I went on with maven. Besides regular stuff there, I used additional [frontend plugin](https://github.com/eirslett/frontend-maven-plugin) for packaging artifacts before application deploy. 

The procedure to start and test application is (from application root):
```
mvn clean install
```
This will take same time, since plugin installs local node, npm, gulp, bower, etc... On my laptop it took 7min 30sec. Could be a little bit more or a little bit less. After succcessful build, just a regulat startup procedure:
```
mvn spring-boot:run
```
And frontend should be available at the application root (i.e. http://localhost:9000 or whatever port you deploy it).

NOTE: [Application properties](https://github.com/sasa-radovanovic/klm_flight_search/blob/master/src/main/resources/application.properties) holds endpoint settings - deployment port and address of  [simple-travel-api-mock](https://bitbucket.org/afklmdevnet/simple-travel-api-mock) deployment.

### Software features

Unfortunately, I did not use as much of Java 8 as I would have liked to. I am a very big fan of Java 8 (especially lambdas and Stream API) and I would have used Stream API for some "in-memory cache" analysis (for example - which airport was searched most often and similar).

Under the hood - It's just basic stuff. Spring Boot with it's features and modules working it. 

On the frontend level - Angular1 with Bootstrap Material. I managed to implement language-changing and some other features, but in limited timeframe I understand if you do not find these pages visually appealing.

### Walkthrough screenshots

If you do not want to deploy this application to see it in action, I have put around few screenshots just for you to get a grip on it. You can  see them on this [link].  

### Contributors

I am the sole contributor to this small application which was implemented after a call for test from Roger Roelofs. 

Sasa Radovanovic
- sasa1kg@yahoo.com
- sasa.radovanovic@live.com


