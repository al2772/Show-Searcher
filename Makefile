# Runs the Data Wrangler, Algorithm Engineer, Backend, Frontend
run: AlgorithmEngineer DataWrangler ShowSearcherBackend.class ShowSearcherFrontend.class
        java ShowSearcherFrontend


Runs the tests for each role ——> runFrontendDeveloperTests


#Run FrontendDeveloper Tests
runFrontendDeveloperTests: FrontendDeveloperTests.class
        java FrontendDeveloperTests

FrontendDeveloperTests.class: FrontendDeveloperTests.java IShowSearcherFrontend.class
        javac FrontendDeveloperTests

IShowSearcherFrontend.class: IShowSearcherFrontend.java ShowSearcherFrontend.class
        javac IShowSearcherFrontend.java

ShowSearcherFrontend.class: ShowSearcherFronend.java Show.class
        javac ShowSearcherFrontend.java
