# Description
RESTful API demo that calculates statistics for transactions in the last 60 seconds


## Run
```
./gradlew bootRun
```

## Usage 

* ### POST /api/v1/transactions

Creates a new transaction

Body content: 
```
{
 "amount": "1.253",
 "timestamp": "2018-07-17T09:59:51.312Z"
}
```

* ### GET /api/v1/statistics

Returns statistics for transactions within last 60 seconds

Body response example:
```
{
    "sum": 2.46,
    "avg": 1.23,
    "max": 1.23,
    "min": 1.23,
    "count": 2
}
```


* ### DELETE /api/v1/transactions

Deletes all transactions

## Test 
```
./gradlew test
```

