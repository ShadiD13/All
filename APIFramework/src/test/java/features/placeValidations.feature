Feature: Validating Place API's
@AddPlace
Scenario Outline: Verify if Place is being Successfully add using AddPlaceAPI
	Given Add Place Payload with "<name>" "<language>" "<address>"
	When user calls "AddPlaceAPI" with "POST" http request
	Then the API call is success with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And verify place Id created maps to "<name>" using "getPlaceAPI"
	
	Examples:
		|name  | language |address           |
		|Ahouse| English  |World cross Center|
#		|Shadi | Arabic   |Amman             |
@DeletePlace
Scenario: Verify if Delete Place functionality is working
	Given DeletePlace Payload
	When user calls "deletePlaceAPI" with "POST" http request
	Then the API call is success with status code 200
	And "status" in response body is "OK"