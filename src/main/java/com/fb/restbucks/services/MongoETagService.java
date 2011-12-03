package com.fb.restbucks.services;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.fb.restbucks.exception.InvalidTagException;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

@Service
public class MongoETagService implements ETagService {

	@Autowired
	private MongoOperations mongoOps;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	public String generate(String url, Object entity) {
		String tag = createTag(entity);
		BasicDBObject dbo = new BasicDBObject().append("url", url).append("tag", tag);
		mongoOps.getCollection("etags").save(dbo);
		return tag;
	}

	public void remove(String url) {
		BasicDBObject dbo = new BasicDBObject().append("url", url);
		mongoOps.getCollection("etags").remove(dbo);
	}

	public String update(String url, Object entity) {
		String tag = createTag(entity);
		BasicDBObject dbo = new BasicDBObject().append("url", url);
		mongoOps.getCollection("etags").update(dbo, new BasicDBObject().append("$set", new BasicDBObject().append("tag", tag)));
		return tag;
	}
	
	
	public String get(String url) {
		BasicDBObject dbo = new BasicDBObject().append("url", url);
		List<DBObject> results = mongoOps.getCollection("etags").find(dbo).toArray();
		if(results.size() == 0 ){
			throw new InvalidTagException("No tag found for url: " + url);
		}
		return String.valueOf(results.get(0).get("tag"));
	}
	
	
	private String createTag(Object entity){
		StringWriter writer = new StringWriter();
		try {
			mapper.writeValue(writer, entity);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return DigestUtils.md5DigestAsHex(writer.toString().getBytes());
	}

	

}
