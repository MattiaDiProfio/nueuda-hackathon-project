
# Group 5 - Stock API

Group 5's submission for the Nueuda Hackathon. A Restful API which allows users to perform CRUD operations on a Stock database.

## How to make requests
#### 1. Using Postman web client
- Log into [Postman](https://web.postman.co/) - ensure you have the Desktop agent downloaded and running as well
- Create a new workspace and import the collections of requests found in the `Nueuda API.postman_collection.json` file.
- Customise and perform the requests as you wish.

#### 2. Using a JavaScript library
- Copy the repository contents to your machine using the command `git pull origin main`
- Travel to the `/stockapi` folder and start the API with `mvnw clean spring-boot:run`. The API should be running on `localhost:8080`.
- Setup your JavaScript client to perform requests on this address.

## API Endpoints Explained

#### 1. Get individual stock
Retrieve a stock from the database given its ticker symbol
```
  GET /stock/{stockTicker}
```
Expected response output
```
{
    "ticker": "APPL",
    "price": 45.51,
    "companyName": "Apple"
}
Status : 200 Ok
```

#### 2. Get all stocks
Retrieve all stocks currently stored in the database
```
  GET /stock/all
```
Expected response output
```
[
    {
        "ticker": "APPL",
        "price": 294.10,
        "companyName": "Apple"
    }, 
    {
        "ticker": "C",
        "price": 65.51,
        "companyName": "Citi"
    }
]
Status : 200 Ok
```

#### 3. Get all stocks between price range
Retrieve all stocks whose price falls between min and max
```
  GET /stock/filter
```
Example of payload body expected by endpoint
```
{
    "min": 60.00,
    "max": 70.00
}
```
Expected response output
```
[
    {
        "ticker": "C",
        "price": 65.51,
        "companyName": "Citi"
    }
]
Status : 200 Ok
```

#### 4. Create new stock entry
Create a new stock entry in the database
```
  POST /stock
```
Example of payload body expected by endpoint
```
{
    "ticker": "GOOG",
    "price": 183.32,
    "companyName": "Google"
}
```
Expected response output
```
{
    "ticker": "GOOG",
    "price": 183.32,
    "companyName": "Google"
}
Status : 201 Created
```

#### 5. Edit existing stock entry
Update a stock's price and/or companyName given its ticker symbol
```
  PUT /stock
```
Example of payload body expected by endpoint
```
{
    "ticker": "GOOG",
    "price": 200.45,
    "companyName": "Google"
}
```
Expected response output
```
{
    "ticker": "GOOG",
    "price": 200.45,
    "companyName": "Google"
}
Status : 201 Created
```

#### 6. Delete existing stock entry
Delete a stock from the database given its ticker symbol
```
  DELETE /stock/{stockTicker}
```
Expected response output
```
Status : 204 No Content
```


