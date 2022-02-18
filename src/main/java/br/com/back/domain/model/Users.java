package br.com.back.domain.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Users {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "users_id")
	private Long id; 
	
	@Column(name = "google_uuid")
	private String googleUuid;
	
	@Column(name = "ios_uuid")
	private String iosUuid;
	
	@Column(name = "users_email", nullable = false)
	private String userEmail;

	@Column(name = "crypted_pass", nullable = false)
	private String cryptedPass;
	
	@Column(name = "status_", nullable = false)
	private String status;
	
	@Column(name = "users_name")
	private String userName;
	
	@Column(name = "photo_url")
	private String photo;
	
	@ManyToMany
	@JoinTable(name = "user_grouping", joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "grouping_id"))
	private Set<Grouping> groupings = new HashSet<>();	

	public boolean removeGroup(Grouping grouping) {
		return getGroupings().remove(grouping);
	}
	
	public boolean addGroup(Grouping grouping) {
		return getGroupings().add(grouping);
	}
	
	public boolean isNew() {
		return getId() == null;
	}
	
}
