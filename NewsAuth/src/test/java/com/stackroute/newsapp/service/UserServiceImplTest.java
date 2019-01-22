package com.stackroute.newsapp.service;

import com.stackroute.newsapp.domain.User;
import com.stackroute.newsapp.exception.UserAlreadyExistException;
import com.stackroute.newsapp.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {
  @Mock
  private transient UserRepository userRepo;

  private transient User user;

  @InjectMocks
  private transient UserServiceImpl userServiceImpl;

  transient Optional<User> options;

  @Before
  public void setupMock() {
    MockitoAnnotations.initMocks(this);
    this.user = new User("", "", "", "", new Date());
    options = Optional.of(user);
  }

  @Test
  public void testMockCreation() {
    assertNotNull("JPA Repository injection fails", userRepo);
  }

  @Test
  public void testRegisterationSuccess() throws UserAlreadyExistException {
    when(userRepo.save(user)).thenReturn(user);

    final boolean isSave = userServiceImpl.saveUser(user);
    assertEquals("Registered", true, isSave);
    verify(userRepo, times(1)).save(user);
  }

  @Test(expected = UserAlreadyExistException.class)
  public void testRegisterationFailure() throws UserAlreadyExistException {
    when(userRepo.findById(user.getUserId())).thenReturn(options);
    when(userRepo.save(user)).thenReturn(user);
    final boolean isSave = userServiceImpl.saveUser(user);
    assertEquals("Registered", false, isSave);
  }

  @Test
  public void testLoginSuccess() throws UserAlreadyExistException {
    when(userRepo.findByUserIdAndPassword(user.getUserId(), user.getPassword())).thenReturn(user);
    User userResult = userRepo.findByUserIdAndPassword(user.getUserId(), user.getPassword());
    assertNotNull(userResult);
//		assertEquals(userResult.getUserId(), "FirstName");
    when(userRepo.save(user)).thenReturn(user);
    verify(userRepo, times(1)).findByUserIdAndPassword(user.getUserId(), user.getPassword());
  }

  @Test
  public void testLoginFailure() throws UserAlreadyExistException {
    when(userRepo.findById("FirstNames")).thenReturn(null);
    User userResult = userRepo.findByUserIdAndPassword("Nisahs", "12");
    assertNull(userResult);
  }
}
