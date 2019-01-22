package com.stackroute.newsapp.repository;

import com.stackroute.newsapp.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class UserRepoTest {

  @Autowired
  private transient UserRepository userRepository;

  private User user;

  public void setUserRepository(final UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Before
  public void setUp() {
    this.user = new User("FirstName", "1234", "FullName", "LastName", new Date());
  }

  @Test
  public void testRegistrationSuccess() throws Exception {
    userRepository.save(this.user);
    Optional<User> user = userRepository.findById(this.user.getUserId());
    assertThat(user.equals(this.user));
  }
}
