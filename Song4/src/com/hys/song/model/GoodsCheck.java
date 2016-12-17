package com.hys.song.model;



public class GoodsCheck {
			private int orderId;
			private int goodsPrice;
			private int goodsId;
			private String goodsName;
			private String userName;
			private String sellerName;
			private String date;
			private int number;
			public GoodsCheck(){
				
			}
			public int getOrderId() {
				return orderId;
			}
			public void setOrderId(int orderId) {
				this.orderId = orderId;
			}
			public int getGoodsPrice() {
				return goodsPrice;
			}
			public void setGoodsPrice(int goodsPrice) {
				this.goodsPrice = goodsPrice;
			}
			public int getGoodsId() {
				return goodsId;
			}
			public void setGoodsId(int goodsId) {
				this.goodsId = goodsId;
			}
			public String getGoodsName() {
				return goodsName;
			}
			public void setGoodsName(String goodsName) {
				this.goodsName = goodsName;
			}
			public String getUserName() {
				return userName;
			}
			public void setUserName(String userName) {
				this.userName = userName;
			}
			public String getSellerName() {
				return sellerName;
			}
			public void setSellerName(String sellerName) {
				this.sellerName = sellerName;
			}
			public String getDate() {
				return date;
			}
			public void setDate(String date) {
				this.date = date;
			}
			public int getNumber() {
				return number;
			}
			public void setNumber(int number) {
				this.number = number;
			}
}
