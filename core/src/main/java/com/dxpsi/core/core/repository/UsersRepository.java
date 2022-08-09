package com.dxpsi.core.core.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.dxpsi.core.core.model.Users;

@Repository
public interface UsersRepository extends MongoRepository<Users,String> {
    
}
