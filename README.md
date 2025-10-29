
# Connected Insights Coding Challenge

This is the source code for the AstraZeneca coding challenge, implemented by Mauricio Velez.

## How to run locally
```
docker build -t ci-app .  
docker run -d -p 8080:8080 ci-app
 ```

## Features implemented

- Create Competitor endpoint
- Create Insights endpoint
- List/Filter insights endpoint
- Endpoint validations

## Known Limitations
- The filter endpoint is not as dynamic as I wished. If I need to create a new filter, I would need to modify the InsightSpecification class to cover the new logic.
- The summary endpoint uses two separate JPQL queries (summary dto and category summary), and then merged in Java. Ideally, the query should handle everything without having to merge the two results in code.
- Needs more test coverage
