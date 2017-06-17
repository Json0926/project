package com.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

//原材料信息


@Entity
@Table(name="t_material")

public class Material {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//表id
	private Integer id;
	// 原材料id
	private long materialId;
	//原材料名
	
	private String materialName;
	public void setMaterialId(long materialId) {
		this.materialId = materialId;
	}
	//供应商企业id
/*@OneToOne
	@JoinColumn(name="supply_id")*/
	private long supplyId;
	//供应商企业名
	private String supplierName;
	//生产商企业id
	private long manufactureId;
	//生产商企业名
	private String manufactureName;
	//原材料供应时间
	private Date supplierDate;
	//原材料供应数量
	private long supplierCount;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	public long getMaterialId() {
		return materialId;
	}
	public void setMaterialId(Integer materialId) {
		this.materialId = materialId;
	}

	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
/*	public long getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(long supplierId) {
		this.supplierId = supplierId;
	}*/
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	
	

public long getSupplyId() {
		return supplyId;
	}
	public void setSupplyId(long supplyId) {
		this.supplyId = supplyId;
	}
/*		public Supply getSupply() {
		return supply;
	}
	public void setSupply(Supply supply) {
		this.supply = supply;
	}*/
	
	public long getManufactureId() {
		return manufactureId;
	}
	public void setManufactureId(long manufactureId) {
		this.manufactureId = manufactureId;
	}
	public String getManufactureName() {
		return manufactureName;
	}
	public void setManufactureName(String manufactureName) {
		this.manufactureName = manufactureName;
	}
	public Date getSupplierDate() {
		return supplierDate;
	}
	public void setSupplierDate(Date supplierDate) {
		this.supplierDate = supplierDate;
	}
	public long getSupplierCount() {
		return supplierCount;
	}
	public void setSupplierCount(long supplierCount) {
		this.supplierCount = supplierCount;
	}
}
