package com.tele.microsrv.platform.dal.impl;

import com.tele.microsrv.platform.dal.dummy_UserDAL;
import com.tele.microsrv.platform.model.Dummy_User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
public class dummy_UserDALImpl implements dummy_UserDAL {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Dummy_User> getAllUsers() {
        return mongoTemplate.findAll(Dummy_User.class);
    }

    @Override
    public Dummy_User getUserById(String userId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        return mongoTemplate.findOne(query, Dummy_User.class);
    }

    @Override
    public Dummy_User addNewUser(Dummy_User user) {
        mongoTemplate.save(user);
        // Now, user object will contain the ID as well
        return user;
    }

    /*@Override
    public Object getAllUserSettings(String userId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        Dummy_User user = mongoTemplate.findOne(query, Dummy_User.class);
        return user != null ? user.getUserSettings() : "User not found.";
    }

    @Override
    public String getUserSetting(String userId, String key) {
        Query query = new Query();
        query.fields().include("userSettings");
        query.addCriteria(Criteria.where("userId").is(userId).andOperator(Criteria.where("userSettings." + key).exists(true)));
        Dummy_User user = mongoTemplate.findOne(query, Dummy_User.class);
        return user != null ? user.getUserSettings().get(key) : "Not found.";
    }

    @Override
    public String addUserSetting(String userId, String key, String value) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        Dummy_User user = mongoTemplate.findOne(query, Dummy_User.class);
        if (user != null) {
            user.getUserSettings().put(key, value);
            mongoTemplate.save(user);
            return "Key added.";
        } else {
            return "User not found.";
        }
    }*/

    @Override
    public Dummy_User getEmail(String email){
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(email));
        //System.out.println(query.toString());
        return mongoTemplate.findOne(query,Dummy_User.class);
    }

}
