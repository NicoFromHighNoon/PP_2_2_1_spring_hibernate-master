package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class UserDaoImp implements UserDao {

   private SessionFactory sessionFactory;

   @Autowired
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
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getByCar(String model, int series) {
      Query query = sessionFactory.getCurrentSession().createQuery("from Car where model=:model and series =: series");
      query.setParameter("model", model);
      query.setParameter("series", series);
      Car car = (Car) query.getSingleResult();
      Query query2 = sessionFactory.getCurrentSession().createQuery("from User where car=:car");
      query2.setParameter("car", car);
      User user = (User) query2.getSingleResult();
      return user;
   }
}
