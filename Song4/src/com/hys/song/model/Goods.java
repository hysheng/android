package com.hys.song.model;

public class Goods {

	private int goodsId;
	private String goodsName;
	private int goodsPrice;
	private int salevolume;
	private int sallerId;
	private String imageCache;
	
	public Goods() {
	
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
	public int getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(int goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public int getSalevolume() {
		return salevolume;
	}
	public void setSalevolume(int salevolume) {
		this.salevolume = salevolume;
	}
	public void setSallerId(int sallerId){
		this.sallerId=sallerId;
	}
	public int getSallerId(){
		return sallerId;
	}
	public String getImageCache() {
		return imageCache;
	}
	public void setImageCache(String imageCache) {
		this.imageCache = imageCache;
	}
	
}
