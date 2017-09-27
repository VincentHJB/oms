package com.feifang.oms.controller.param;

/**
 * 订单参数
 */
public class OrderParam {
	private Integer id;
	private String tradeName;               //商品名称
	private Integer number;                 //数量
	private Double amount;                  //金额（订单总价）
	private Integer poNo;                   //采购单号（手动输入，唯一）
	private String imageUrl;                //图片路径
	private String remark;                  //订单备注
	private String customerNo;              //关联客户编号
	private Integer vid;                    //关联供应商ID
	private Integer uid;                    //用户ID
	private String startAt;                 //订货日（下单付保证金）
	private String endAt;                   //交货日
	private Integer paymentDay;             //付款天数
	private Double deposit;                 //保证金
	private Integer status;                 //状态 1:订单进行中，2：已完成，3：已取消

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Double getDeposit() {
		return deposit;
	}

	public void setDeposit(Double deposit) {
		this.deposit = deposit;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTradeName() {
		return tradeName;
	}

	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

//	public Double getPrice() {
//		return price;
//	}
//
//	public void setPrice(Double price) {
//		this.price = price;
//	}
//
//	public String getItemNo() {
//		return itemNo;
//	}
//
//	public void setItemNo(String itemNo) {
//		this.itemNo = itemNo;
//	}
//
//	public String getColor() {
//		return color;
//	}
//
//	public void setColor(String color) {
//		this.color = color;
//	}
//
//	public String getSize() {
//		return size;
//	}
//
//	public void setSize(String size) {
//		this.size = size;
//	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Integer getPoNo() {
		return poNo;
	}

	public void setPoNo(Integer poNo) {
		this.poNo = poNo;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	public Integer getVid() {
		return vid;
	}

	public void setVid(Integer vid) {
		this.vid = vid;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getStartAt() {
		return startAt;
	}

	public void setStartAt(String startAt) {
		this.startAt = startAt;
	}

	public String getEndAt() {
		return endAt;
	}

	public void setEndAt(String endAt) {
		this.endAt = endAt;
	}

	public Integer getPaymentDay() {
		return paymentDay;
	}

	public void setPaymentDay(Integer paymentDay) {
		this.paymentDay = paymentDay;
	}
}
