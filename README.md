# shadow-cljs-serverless

Demo app, developing cloud infra with [shadow-cljs](https://shadow-cljs.github.io/docs/UsersGuide.html) and [serverless](https://serverless.com/).

## <a name="offline"> Offline development ##

```bash
yarn api:watch
cd api/
sls offline
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
## <a name="deploy"> Deploy ##

```bash
yarn api:release
cd api/
sls deploy
```
