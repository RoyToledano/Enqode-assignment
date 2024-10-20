# Part 2: Darkweb Scanner API

## Overview
he Dark Web Scanner is a cURL-based API that queries dark web data relevant to a specified selector (e.g., domain). 

It uses a third-party API provider, IntelligenceX, to retrieve dark web data. 

The API is built using Java and Spring Boot.

<br />

## Endpoint
**BASE URL:** `/api/darkweb`.

- **Method:**  GET

- **Endpoint:**  `/scan`

- **Description:**  Retrieves dark web data for the given selector.

- **Query Parameters:**
  - `selector` (String) – The selector to search for (see the list of supported selector types below).
  - `limit` (Integer) - The number of records to retrieve.

- **Response:**
  ```json
    {
       "records": [{
           "systemid": "f13fdfc7-2ef6-42a4-bccf-61ff57971bd9",
           "storageid": "143cd96f978d7a7d43127b65b096c99462354098db599cf0daa684a8fb69932b7f0b0420973bc99a83193ff98970a89a5b2e09bb0b4957e13427b2c0394d3407",
           "instore": true,
           "size": 4315485,
           "accesslevel": 0,
           "type": 1,
           "media": 1,
           "added": "2017-12-14T07:35:50.998926Z",
           "date": "2017-12-14T07:35:05Z",
           "name": "",
           "description": "",
           "xscore": 68,
           "simhash": 15623487490024384908,
           "bucket": "pastes",
           "keyvalues": null,
           "tags": null,
           "relations": [{
               "target": "07c11339-1066-4906-9490-9b93d2c35690",
               "relation": 0
           }],
           "accesslevelh": "Public",
           "mediah": "Paste",
           "simhashh": "d8d1c7186ba9a98c",
           "typeh": "Text",
           "tagsh": null,
           "randomid": "f2737370-e182-4707-ad7a-a8f6a3320934",
     }],
     "status": 0
  }


An explanation of the response can be found in the IntelX documentation [here](https://github.com/IntelligenceX/SDK/blob/master/Intelligence%20X%20API.pdf) on page 10.

**List of supported selector types:**

* Email address
* Domain, including wildcards like *.example.com
* URL
* IPv4 and IPv6
* CIDRv4 and CIDRv6
* Phone Number
* Bitcoin address
* MAC address
* IPFS Hash
* UUID
* Storage ID
* System ID
* Simhash
* Credit card number
* IBAN


## Deployment
### Option 1 Using DockerHub:
* You can use Docker to set up the API on your machine.
* Ensure that Docker Engine is installed on your machine.
* Open the terminal and follow these steps:
  
   **Step 1:** Pull the docker image:
  ```
  docker pull roytoledano/dark-web-scanner:1.0
  ```
   **Step 2:** Run the container:
  ```
  docker run -p 8080:8080 roytoledano/dark-web-scanner:1.0
  ```
* The API is now running and listening on port 8080.
  
### Option 2:
* You can download the release package [here](https://github.com/RoyToledano/Enqode-assignment/releases/tag/v1.0).
* Extract the directory from the zip file.
* Run the following command in the terminal inside the extracted directory:
  ```
  java -jar dark-web-scanner.jar
  ```
* The API is now running and listening on port 8080.

### Interaction:
* The API is accessible at `http://localhost:8080`.
* You can test the API by sending the following request:
  ```
  http://localhost:8080/api/darkweb/scan?selector=<selector>&limit=<number>
  ```
  For example, to scan dark web data for the domain 'google.com' and retrieve 4 records:
  ```
  http://localhost:8080/api/darkweb/scan?domain=google.com&limit=4
  ```
* You can send the request from your browser or via Postman.
* You can also send the request using CURL in the terminal:
  ```
   curl "http://localhost:8080/api/darkweb/scan?selector=<selector>&limit=<number>"
  ```


