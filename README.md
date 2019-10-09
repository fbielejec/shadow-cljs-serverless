# shadow-cljs-serverless

Demo app, developing cloud infra with [shadow-cljs](https://shadow-cljs.github.io/docs/UsersGuide.html) and [serverless](https://serverless.com/). It showcases a simple graphql endpoint running as an AWS Lambda function.

## <a name="offline"> Offline development ##

Note the flag to skip cache invalidation, or else shadow's own hot reloading will cause errors with the offline mode reload:

```bash
yarn api:watch
cd api/
sls offline --skipCacheInvalidation
```

Navigate to http://localhost:4000/graphql and run this test query

```
{
  getTodos {
    id
    name
  }
}
```

or using curl:

```bash
curl -X POST -H "Content-Type: application/json"  --data \
'{ "query": "{ getTodos { id name } }" }' \
http://localhost:4000/graphql
```
## Prerequisites

Pretty much follow the [getting-started](https://serverless.com/framework/docs/getting-started/) guidelines.
You need serverless CLI installed:

```bash
npm install --global serverless
```

Login to serverless dashboard. It will gide you through the process if you need to create an account:

```bash
serverless login
```

## <a name="deploy"> Deploy ##

Navigate to [dashboard](https://dashboard.serverless.com) and create new application called `serverless-demo`.
Deploy the application:

```bash
yarn api:release
cd api/
sls deploy
```
