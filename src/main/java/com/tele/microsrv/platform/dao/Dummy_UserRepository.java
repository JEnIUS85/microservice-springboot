package com.tele.microsrv.platform.dao;

import com.tele.microsrv.platform.model.Dummy_User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface Dummy_UserRepository extends MongoRepository<Dummy_User, String> {
}
