# LRUCache Assignment

This is a LRU Cache using memory to store and fetch key-value pairs, capacity of cache is initilaized as Tomcat start, and return http status code with result as JSON object, here are some example:

```
curl -x POST http://youraddress: port/api/v1/put/1 -d "value=400"
```

```
{
	status: 200,
	{}
}
```

```
curl -x POST http://youraddress: port/api/v1/put/2 -d "value=800"
```

```
{
	status: 200,
	{}
}
```

```
curl -x GET http://youraddress: port/api/v1/get/1
```

```
{
	status: 200,
	{
		key: 1,
		value: 400
	}
}
```

```
curl -x POST http://youraddress: port/api/v1/put/3 -d "value=1200"  //evicts key=2
```

```
{
	status: 200,
	{
		key: 2,
		value: 800
	}
}
```

```
curl -x GET http://youraddress: port/api/v1/get/2
```

```
{
	status: 404,
	{}
}
```
<br/> To run this, just deploy .war file to environment

TODO: 
<br/>Add remove funtion
<br/>Improve performance
