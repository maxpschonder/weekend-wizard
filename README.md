# Weekend Wizard 🧙

Welcome to Weekend Wizard, a Scala HTTP service for a Kubernetes training with 3 endpoints!

## Endpoints

### `/health`

This endpoint returns the current health status of the service. It is used to check if the service is running and responding to requests.

### `/fibonacci/<int>`

This endpoint calculates and returns the nth value in the Fibonacci sequence, where `n` is an integer passed as a path parameter. For example, calling `/fibonacci/5` will return the fifth value in the Fibonacci sequence, which is `3`. This endpoint is intended to create CPU load on the system.

### `/weekend-activities`

This endpoint returns suggestions for activities to do on the weekend.

## Running the service

### Running with SBT

To run the service, you will need to have Scala and SBT (Scala build tool) installed on your machine. Once you have those, follow these steps:

1. Clone this repository to your local machine:

    ```
    git clone https://github.com/maxpschonder/weekend-wizard.git
    ```

2. Navigate to the root directory of the repository:

    ```
    cd weekend-wizard
    ```

3. Start the service using SBT:
    
    ```
    sbt run
    ```

### Running with Docker

To run the service using Docker, you will need to have Docker installed on your machine.
```
docker run -p 8080:8080 ghcr.io/maxpschonder/weekend-wizard/weekend-wizard:latest
```

The service should now be running on http://localhost:8080. You can access the endpoints using your favorite HTTP client, such as cURL or Postman.

## Contributing

We welcome contributions to Weekend Wizard! If you have an idea for a new feature or have found a bug, please open an issue on this repository. If you would like to contribute code, please fork the repository and submit a pull request.