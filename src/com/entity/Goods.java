package com.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*商品信息*/


@Entity
@Table(name="t_goods")

public class Goods implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//表id
	private Integer id;
	// 商品id
	private long goodsID;
	//商品名
	private String goodsName;
	//生产商企业id
	private long manufactureID;
	//生产商企业名
	private String manufactureName;
	//客户企业id
	private long clientID;
	//客户商企业名
	private String clientName;
	//商品供应时间
	private Date goodsDate;
	//商品供应数量
	private long goodsCount;
	
	
	public long getGoodsID() {
		return goodsID;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setGoodsID(long goodsID) {
		this.goodsID = goodsID;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public long getManufactureID() {
		return manufactureID;
	}
	public void setManufactureID(long manufactureID) {
		this.manufactureID = manufactureID;
	}
	public String getManufactureName() {
		return manufactureName;
	}
	public void setManufactureName(String manufactureName) {
		this.manufactureName = manufactureName;
	}
	public long getClientID() {
		return clientID;
	}
	public void setClientID(long clientID) {
		this.clientID = clientID;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public Date getGoodsDate() {
		return goodsDate;
	}
	public void setGoodsDate(Date goodsDate) {
		this.goodsDate = goodsDate;
	}
	public long getGoodsCount() {
		return goodsCount;
	}
	public void setGoodsCount(long goodsCount) {
		this.goodsCount = goodsCount;
	}
	
	
	
	
	
}
