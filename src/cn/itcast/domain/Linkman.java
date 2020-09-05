package cn.itcast.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//N  维护外键关系
@Entity
@Table(name="cst_linkman")
public class Linkman    
{
	  @Id
	  @Column(name="lkm_id")
	  @GeneratedValue(strategy=GenerationType.IDENTITY)
	  private Long lkm_id;// '联系人编号(主键)',
	  private String lkm_name;// '联系人姓名',
	  private String lkm_gender;// '联系人性别',
	  private String lkm_phone;// '联系人办公电话',
	  private String lkm_mobile;// '联系人手机',
	  private String lkm_email;// '联系人邮箱',
	  private String lkm_qq;// '联系人qq',
	  private String lkm_position;// '联系人职位',
	  private String lkm_memo;//'联系人备注',
	  
	  // 客户ID 外键    有一个一的一方的对象
	  //lkm_cust_id 外键
	  @ManyToOne(targetEntity=Customer.class)
	  // 注解里面针对的都是表字段名
	  @JoinColumn(name="lkm_cust_id",referencedColumnName="cust_id")
	  private Customer customer;

	public Long getLkm_id() {
		return lkm_id;
	}

	public void setLkm_id(Long lkm_id) {
		this.lkm_id = lkm_id;
	}

	public String getLkm_name() {
		return lkm_name;
	}

	public void setLkm_name(String lkm_name) {
		this.lkm_name = lkm_name;
	}

	public String getLkm_gender() {
		return lkm_gender;
	}

	public void setLkm_gender(String lkm_gender) {
		this.lkm_gender = lkm_gender;
	}

	public String getLkm_phone() {
		return lkm_phone;
	}

	public void setLkm_phone(String lkm_phone) {
		this.lkm_phone = lkm_phone;
	}

	public String getLkm_mobile() {
		return lkm_mobile;
	}

	public void setLkm_mobile(String lkm_mobile) {
		this.lkm_mobile = lkm_mobile;
	}

	public String getLkm_email() {
		return lkm_email;
	}

	public void setLkm_email(String lkm_email) {
		this.lkm_email = lkm_email;
	}

	public String getLkm_qq() {
		return lkm_qq;
	}

	public void setLkm_qq(String lkm_qq) {
		this.lkm_qq = lkm_qq;
	}

	public String getLkm_position() {
		return lkm_position;
	}

	public void setLkm_position(String lkm_position) {
		this.lkm_position = lkm_position;
	}

	public String getLkm_memo() {
		return lkm_memo;
	}

	public void setLkm_memo(String lkm_memo) {
		this.lkm_memo = lkm_memo;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	  
	  
	  
	  
	 
}
