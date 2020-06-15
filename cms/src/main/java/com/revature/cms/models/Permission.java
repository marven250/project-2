package com.revature.cms.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema="", name = "permissions")
public class Permission {
	
	@Id
	  @Column(name = "permissionId")
	private Integer permissionId;
	@Column(name = "permissionType")
	private String permissionType;
	@Column(name = "permissionDescription")
	private String permissionDescription;
	
	public Permission() {
		
	}
	
	public Permission(Integer permissionId, String permissionType, String permissionDescription) {
		super();
		this.permissionId = permissionId;
		this.permissionType = permissionType;
		this.permissionDescription = permissionDescription;
	}

	public Integer getPermissionID() {
		return permissionId;
	}

	public void setPermissionID(Integer permissionID) {
		this.permissionId = permissionID;
	}

	public String getPermissionType() {
		return permissionType;
	}

	public void setPermissionType(String permissionType) {
		this.permissionType = permissionType;
	}

	public String getPermissionDescription() {
		return permissionDescription;
	}

	public void setPermissionDescription(String permissionDescription) {
		this.permissionDescription = permissionDescription;
	}
	

}

