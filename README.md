# marfeel

This is a web crawler developed in Spring MVC that exposes a REST API that can receive a set of site URLs in JSON format i.e. {
        "url": "leasing4business.co.uk",
        "rank": 435035
    }
    
It uses jSoup to crawl the HTML and check if the <title> tag contains "news" or "noticias". It then saves the result in a database using SpringData.
The business logic is parallelized (using Threads) to increase performance. 
Unit and integration tests available.
