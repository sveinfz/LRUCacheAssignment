package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import rpc.LRUCache;

class LRUCacheTest {

	@Test
	void testLRUCache() {
		LRUCache cache = new LRUCache(1);
		assertNotNull(cache);
	}

	@Test
	void testGet() {
		LRUCache cache = new LRUCache(1);
		cache.put(1, 200);
		assertEquals(cache.get(1), 200);
		
		cache.put(2, 400);
		assertEquals(cache.get(2), 400);
		
		cache.put(2, 600);
		assertEquals(cache.get(2), 600);
		
		assertEquals(cache.get(5), -1);
	}

	@Test
	void testPut() {
		LRUCache cache = new LRUCache(1);
		int result1 = cache.put(1, 200);
		assertEquals(result1, -1);
		
		int result2 = cache.put(2, 400);
		assertEquals(result2, 200);
		
		int result3 = cache.put(2, 600);
		assertEquals(result3, 400);
	}

}
