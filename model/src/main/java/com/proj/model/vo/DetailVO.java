package com.proj.model.vo;

import lombok.Data;

/**
 * 详情VO
 */
@Data
public class DetailVO {
	private String zzbbh;//指标编号
	private String zzbmc;//指标名称
	private String zzbywsm;//指标业务说明
	private String zzbljsm;//指标逻辑说明
	private String zlyyy;
}
