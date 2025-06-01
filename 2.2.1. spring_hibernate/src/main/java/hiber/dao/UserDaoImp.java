package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   public UserDaoImp(SessionFactory sessionFactory) {
      this.sessionFactory = sessionFactory;
   }


   @Override
   public void add(User user) {

      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
String hql = "from User";
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(hql, User.class);
      return query.getResultList();
   }

   @Override
   @SuppressWarnings("unchecked")
   public User getUserByCar(String model, int series) {
      String hql = "SELECT u FROM User u JOIN u.car c WHERE c.model = :model AND c.series = :series";
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(hql, User.class);
      query.setParameter("model", model)
              .setParameter("series", series);
      return query.setMaxResults(1).getSingleResult();
   }
}
