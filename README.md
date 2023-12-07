This repo connects to the GitHub API with retrofit in Java using both the default and RxJava adapters.

### Key tools
* Springboot
* RxJava
* Retrofit
* GitHub/GitHub Access token

### Setup
To set up the demo, you need a GitHub personal access token. Follow the following steps to get one
* Login to GitHub, click on top right avatar 
* Go to Settings -> Developer Settings -> Personal Access Token
* Generate a token, copy to a safe place
* Add the token as value for the GITHUB_ACCESS_TOKEN variable in the .env.sample file in the root directory and rename the file to .env   

### Testing 
You can test the endpoints with postman or run the tests in the application