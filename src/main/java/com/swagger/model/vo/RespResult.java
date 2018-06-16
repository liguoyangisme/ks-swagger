package com.swagger.model.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;
import java.util.List;

@ApiModel(value="RespResult",description="返回结果")
public class RespResult<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	// 返回状态码：0成功，-1失败
	@ApiModelProperty(value="errcode",name="errcode")
	private Integer errcode;

	@ApiModelProperty(value="errmsg",name="errmsg")
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private String errmsg;

	@ApiModelProperty(value="result",name="result")
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private List<T> result;

	@Value( "spring.profiles.active")
	private String profiles;

	public RespResult() {
	}

	public RespResult(Integer errcode, String errmsg) {
		this.errcode = errcode;
		setErrmsg(errmsg);
	}
	
//	public RespResult(Integer errcode, String errmsg, Object result) {
//		this.errcode = errcode;
//		setErrmsg(errmsg);
//		this.result = result;
//	}
//
//	public RespResult(Integer errcode, Object result) {
//		this.errcode = errcode;
//		this.result = result;
//		setErrmsg("");
//	}

	// 此处如果errcode不是success，就会将对应的errmsg设置到对象返回给客户端
	public RespResult(Integer errcode) {
		this.errcode = errcode;
		setErrmsg("");
	}

	public Integer getErrcode() {
		return errcode;
	}

	public void setErrcode(Integer errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	public String getProfiles() {
		return profiles;
	}

	public void setProfiles(String profiles) {
		this.profiles = profiles;
	}


	//	public static void main(String[] args){
//		Map map = new HashMap<String,Object>();
//		map.put("abc", "bcccd");
//		PageInfo pageinfo=new PageInfo<StoryDomain>();
//		List<StoryDomain> list=new ArrayList<StoryDomain>();
//		StoryDomain s=new StoryDomain();
//		s.setStoryid("123");
//		s.setStoryname("445455");
//		s.setRepeatcount("1323");
//		list.add(s);
//		pageinfo.setList(list);
//		RespResult res = new RespResult(RespCodeConstants.SUCCESS,new PageBean(pageinfo));
//		System.err.println(JsonMapper.getInstance().toJson(res));
//	}
}
