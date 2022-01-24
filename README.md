# ZipcodeAPI
ZipcodeAPI

Send a GET request to 

https://www.zipcodeapi.com/rest/<api_key>/multi-distance.<format>/<zip_code>/<other_zip_codes>/<units>

Pick the first zip code from response and use it as zip_code2 in the below request
https://www.zipcodeapi.com/rest/<api_key>/distance.<format>/<zip_code1>/<zip_code2>/<units>
