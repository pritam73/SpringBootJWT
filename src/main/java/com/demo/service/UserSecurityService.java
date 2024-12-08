package com.demo.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.demo.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserSecurityService implements UserDetails {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String uuid;
	private String userName;
	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;

	public UserSecurityService(Long id, String uuid, String userName, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.uuid = uuid;
		this.userName = userName;
		this.password = password;
		this.authorities = authorities;
	}

	public static UserSecurityService build(User user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(user.getRoleId().getRoleName()));
		return new UserSecurityService(user.getId(), user.getUuid(), user.getEmail(), user.getPassword(), authorities);
	}

	public Long getId() {
		return id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		UserSecurityService user = (UserSecurityService) obj;
		return Objects.equals(id, user.id);
	}

}
