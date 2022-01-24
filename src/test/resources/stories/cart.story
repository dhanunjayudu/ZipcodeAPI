Narrative:
RestFul Websevice: https://www.zipcodeapi.com/API

Write and execute test for the following two requests

Send a GET request to

https://www.zipcodeapi.com/rest/<api_key>/multi-distance.<format>/<zip_code>/<other_zip_codes>/<units>

Pick the first zip code from response and use it as zip_code2 in the below request
https://www.zipcodeapi.com/rest/<api_key>/distance.<format>/<zip_code1>/<zip_code2>/<units>

Scenario: positive case with valid target zip codes
!-- Finding closest zip and Distance between two zip codes
Given current zip code value
And target zip code values
When retrieving closest zip from the targetZip codes
When retrieving distance between closest zip to current zip
Then distance between current zip and target zip

Examples:
|currentZip|targetZipCodes|expectedDistance
|75074|95206,75028|36.053
|75024|78727,32801,33101,95023|306.616

Scenario: negative case with one invalid target zip codes
!-- Finding closest zip and Distance between two zip codes
Given current zip code value
And target zip code values
When retrieving closest zip from the targetZip codes
When retrieving distance between closest zip to current zip
Then distance between current zip and target zip

Examples:
|currentZip|targetZipCodes|expectedDistance
|75024|93487,75014,75029|16.963

Scenario: checking response status
!-- Finding closest zip and Distance between two zip codes
Given current zip code value
And target zip code values
When checking valid response status

Examples:
|currentZip|targetZipCodes|responseStatus
|75024|93487,75014,75029|200
