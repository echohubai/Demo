package hubai.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import hubai.mapper.PermissionMapper;
import hubai.mapper.RoleMapper;
import hubai.mapper.UserMapper;
import hubai.mapper.UserRoleMapper;
import hubai.pojo.*;
import hubai.utils.RedisCache;
import hubai.utils.RespBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserMapper mockUserMapper;
    @Mock
    private AuthenticationManager mockAuthenticationManager;
    @Mock
    private RedisCache mockRedisCache;
    @Mock
    private PasswordEncoder mockPasswordEncoder;
    @Mock
    private UserRoleMapper mockUserRoleMapper;
    @Mock
    private RoleMapper mockRoleMapper;
    @Mock
    private PermissionMapper mockPermissionMapper;

    @InjectMocks
    private UserServiceImpl userServiceImplUnderTest;

    @Test
    public void testRegister() {
        // Setup
        final User user = new User();
        user.setId(0);
        user.setUsername("username");
        user.setPassword("password");
        user.setEmail("email");
        user.setNickname("nickname");

        final RespBean expectedResult = new RespBean(0L, "message", "object");
        when(mockPasswordEncoder.encode("password")).thenReturn("password");

        // Configure UserMapper.selectOne(...).
        final User user1 = new User();
        user1.setId(0);
        user1.setUsername("username");
        user1.setPassword("password");
        user1.setEmail("email");
        user1.setNickname("nickname");
        when(mockUserMapper.selectOne(any(QueryWrapper.class))).thenReturn(user1);

        // Run the test
        final RespBean result = userServiceImplUnderTest.register(user);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testRegister_UserMapperSelectOneReturnsNull() {
        // Setup
        final User user = new User();
        user.setId(0);
        user.setUsername("username");
        user.setPassword("password");
        user.setEmail("email");
        user.setNickname("nickname");

        final RespBean expectedResult = new RespBean(0L, "message", "object");
        when(mockPasswordEncoder.encode("password")).thenReturn("password");
        when(mockUserMapper.selectOne(any(QueryWrapper.class))).thenReturn(null);

        // Run the test
        final RespBean result = userServiceImplUnderTest.register(user);

        // Verify the results
        assertEquals(expectedResult, result);

        // Confirm UserMapper.insert(...).
        final User entity = new User();
        entity.setId(0);
        entity.setUsername("username");
        entity.setPassword("password");
        entity.setEmail("email");
        entity.setNickname("nickname");
        verify(mockUserMapper).insert(entity);
        verify(mockUserRoleMapper).setRole(0);
    }

    @Test
    public void testLogin() {
        // Setup
        final User user = new User();
        user.setId(0);
        user.setUsername("username");
        user.setPassword("password");
        user.setEmail("email");
        user.setNickname("nickname");

        final RespBean expectedResult = new RespBean(0L, "message", "object");

        // Configure AuthenticationManager.authenticate(...).
        final Authentication authentication = new TestingAuthenticationToken("user", "pass", "ROLE_USER");
        when(mockAuthenticationManager.authenticate(
                new TestingAuthenticationToken("user", "pass", "ROLE_USER"))).thenReturn(authentication);

        // Configure PermissionMapper.queryPermissionByUserId(...).
        final Permission permission = new Permission();
        permission.setId(0);
        permission.setUrl("url");
        permission.setName("name");
        permission.setComponent("component");
        permission.setIcon("icon");
        final List<Permission> permissions = List.of(permission);
        when(mockPermissionMapper.queryPermissionByUserId(0)).thenReturn(permissions);

        // Run the test
        final RespBean result = userServiceImplUnderTest.login(user);

        // Verify the results
        assertEquals(expectedResult, result);

        // Confirm RedisCache.setCacheObject(...).
        final User user1 = new User();
        user1.setId(0);
        user1.setUsername("username");
        user1.setPassword("password");
        user1.setEmail("email");
        user1.setNickname("nickname");
        final LoginUser value = new LoginUser(user1, List.of("value"));
        verify(mockRedisCache).setCacheObject("key", value);
    }

    @Test
    public void testLogin_AuthenticationManagerReturnsNull() {
        // Setup
        final User user = new User();
        user.setId(0);
        user.setUsername("username");
        user.setPassword("password");
        user.setEmail("email");
        user.setNickname("nickname");

        when(mockAuthenticationManager.authenticate(
                new TestingAuthenticationToken("user", "pass", "ROLE_USER"))).thenReturn(null);

        // Run the test
        assertThrows(RuntimeException.class, () -> userServiceImplUnderTest.login(user));
    }

    @Test
    public void testLogin_AuthenticationManagerThrowsAuthenticationException() {
        // Setup
        final User user = new User();
        user.setId(0);
        user.setUsername("username");
        user.setPassword("password");
        user.setEmail("email");
        user.setNickname("nickname");

        when(mockAuthenticationManager.authenticate(
                new TestingAuthenticationToken("user", "pass", "ROLE_USER"))).thenThrow(AuthenticationException.class);

        // Run the test
        assertThrows(AuthenticationException.class, () -> userServiceImplUnderTest.login(user));
    }

    @Test
    public void testLogin_PermissionMapperReturnsNoItems() {
        // Setup
        final User user = new User();
        user.setId(0);
        user.setUsername("username");
        user.setPassword("password");
        user.setEmail("email");
        user.setNickname("nickname");

        final RespBean expectedResult = new RespBean(0L, "message", "object");

        // Configure AuthenticationManager.authenticate(...).
        final Authentication authentication = new TestingAuthenticationToken("user", "pass", "ROLE_USER");
        when(mockAuthenticationManager.authenticate(
                new TestingAuthenticationToken("user", "pass", "ROLE_USER"))).thenReturn(authentication);

        when(mockPermissionMapper.queryPermissionByUserId(0)).thenReturn(Collections.emptyList());

        // Run the test
        final RespBean result = userServiceImplUnderTest.login(user);

        // Verify the results
        assertEquals(expectedResult, result);

        // Confirm RedisCache.setCacheObject(...).
        final User user1 = new User();
        user1.setId(0);
        user1.setUsername("username");
        user1.setPassword("password");
        user1.setEmail("email");
        user1.setNickname("nickname");
        final LoginUser value = new LoginUser(user1, List.of("value"));
        verify(mockRedisCache).setCacheObject("key", value);
    }

    @Test
    public void testLogout() {
        // Setup
        final RespBean expectedResult = new RespBean(0L, "message", "object");

        // Run the test
        final RespBean result = userServiceImplUnderTest.logout();

        // Verify the results
        assertEquals(expectedResult, result);
        verify(mockRedisCache).deleteObject("key");
    }

    @Test
    public void testUpdateRoleById() {
        // Setup
        // Configure RoleMapper.getRoleByEqualName(...).
        final Role role = new Role();
        role.setId(0);
        role.setName("name");
        role.setContent("content");
        when(mockRoleMapper.getRoleByEqualName("name")).thenReturn(role);

        // Run the test
        userServiceImplUnderTest.updateRoleById(0, new String[]{"roles"});

        // Verify the results
        verify(mockUserRoleMapper).deleteByUserId(0);

        // Confirm UserRoleMapper.insert(...).
        final UserRole entity = new UserRole();
        entity.setId(0);
        entity.setUid(0);
        entity.setRid(0);
        verify(mockUserRoleMapper).insert(entity);
    }

    @Test
    public void testQueryAll() {
        // Setup
        final RespBean expectedResult = new RespBean(0L, "message", "object");

        // Configure UserMapper.queryByLikeUsername(...).
        final User user = new User();
        user.setId(0);
        user.setUsername("username");
        user.setPassword("password");
        user.setEmail("email");
        user.setNickname("nickname");
        final List<User> users = List.of(user);
        when(mockUserMapper.queryByLikeUsername("username")).thenReturn(users);

        when(mockUserRoleMapper.queryByUserId(0)).thenReturn(List.of("value"));

        // Run the test
        final RespBean result = userServiceImplUnderTest.queryAll("username", 0);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testQueryAll_UserMapperReturnsNoItems() {
        // Setup
        final RespBean expectedResult = new RespBean(0L, "message", "object");
        when(mockUserMapper.queryByLikeUsername("username")).thenReturn(Collections.emptyList());

        // Run the test
        final RespBean result = userServiceImplUnderTest.queryAll("username", 0);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testQueryAll_UserRoleMapperReturnsNoItems() {
        // Setup
        final RespBean expectedResult = new RespBean(0L, "message", "object");

        // Configure UserMapper.queryByLikeUsername(...).
        final User user = new User();
        user.setId(0);
        user.setUsername("username");
        user.setPassword("password");
        user.setEmail("email");
        user.setNickname("nickname");
        final List<User> users = List.of(user);
        when(mockUserMapper.queryByLikeUsername("username")).thenReturn(users);

        when(mockUserRoleMapper.queryByUserId(0)).thenReturn(Collections.emptyList());

        // Run the test
        final RespBean result = userServiceImplUnderTest.queryAll("username", 0);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testUpdatePassword() {
        // Setup
        final RespBean expectedResult = new RespBean(0L, "message", "object");

        // Configure UserMapper.selectOne(...).
        final User user = new User();
        user.setId(0);
        user.setUsername("username");
        user.setPassword("password");
        user.setEmail("email");
        user.setNickname("nickname");
        when(mockUserMapper.selectOne(any(QueryWrapper.class))).thenReturn(user);

        when(mockPasswordEncoder.encode("lastPassword")).thenReturn("result");

        // Run the test
        final RespBean result = userServiceImplUnderTest.updatePassword("username", "lastPassword", "newPassword");

        // Verify the results
        assertEquals(expectedResult, result);

        // Confirm UserMapper.update(...).
        final User entity = new User();
        entity.setId(0);
        entity.setUsername("username");
        entity.setPassword("password");
        entity.setEmail("email");
        entity.setNickname("nickname");
        verify(mockUserMapper).update(eq(entity), any(QueryWrapper.class));
    }

    @Test
    public void testUpdatePassword_UserMapperSelectOneReturnsNull() {
        // Setup
        final RespBean expectedResult = new RespBean(0L, "message", "object");
        when(mockUserMapper.selectOne(any(QueryWrapper.class))).thenReturn(null);

        // Run the test
        final RespBean result = userServiceImplUnderTest.updatePassword("username", "lastPassword", "newPassword");

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
