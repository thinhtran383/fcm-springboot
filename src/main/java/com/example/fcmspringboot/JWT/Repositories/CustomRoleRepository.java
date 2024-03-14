package com.example.fcmspringboot.JWT.Repositories;

import com.example.fcmspringboot.JWT.Entity.Role;
import com.example.fcmspringboot.JWT.Entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomRoleRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Role> getRole(User user){
        StringBuilder sql = new StringBuilder().append("select r.name as name from users u join users_role ur on u.userId = ur.users_id join roles r on r.id = ur.roles_id");

        sql.append(" Where 1=1 ");

        if(user.getUsername() != null){
            sql.append(" and username = :username");
        }

        NativeQuery<Role> query = ((Session) entityManager.getDelegate()).createNativeQuery(sql.toString());

        if(user.getUsername() != null){
            query.setParameter("username", user.getUsername());
        }

        query.addScalar("name", StandardBasicTypes.STRING);
        query.setResultTransformer(Transformers.aliasToBean(Role.class));

        return query.list();
    }
}
