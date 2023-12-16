# simple spring boot web app with MySQL and Redis(as a cache)

#endpoints:

1: buy a grocery(shop/buy/{quantity}/{grocery}) -> cachePut

2: get remaining groceries(shop/remaining/{grocery}) -> cachable

3: get all groceries(shop/get/all)

4: get info for a particular grocery(shop/info/{grocery}) -> cachable

