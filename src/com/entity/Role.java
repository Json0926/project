package com.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.entity.Member;

/**
 * 角色
 */
@Entity
@Table(name = "sys_role")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	// 角色名称
	private String name;
	// 描述
	private String note;

	// 拥有菜单（叶子结点）
	private String menuIds;
	// 菜单名称
	@Column(length = 1000)
	private String menuNames;
	
	//一个角色多个用户
//		@OneToMany(mappedBy="role",targetEntity=Member.class)
//		private Set<Member> members = new HashSet<Member>();

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getNote() {
		return note;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(String menuIds) {
		this.menuIds = menuIds;
	}

	public String getMenuNames() {
		return menuNames;
	}

	public void setMenuNames(String menuNames) {
		this.menuNames = menuNames;
	}

}
