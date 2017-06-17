package com.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
//固定租用衣柜
@Entity
@Table(name="t_lock")
public class Lockers {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	//供应商企业编号
	private String no;
	
	//供应商企业名
	private String realname;
	//供应商品
	private String goodsid;
	//商品名
	private String goodsname;
		

	
	
	//用户有0-N个租柜
	@ManyToOne
	@JoinColumn(name="member_id")
	private Member member;

	

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}

	public String getGoodsname() {
		return goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}

	public Integer getId() {
		return id;
	}

	public String getNo() {
		return no;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}



}
