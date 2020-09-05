package cn.itcast.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.domain.BaseDict;
import cn.itcast.domain.Customer;
import cn.itcast.service.CustomerService;

@Controller("customerAction")
@Scope("prototype")
@ParentPackage("struts-default")
@Namespace("/")
public class CustomerAction extends ActionSupport implements ModelDriven<Customer>
{
	@Autowired
	private CustomerService customerService;
	
	// 封装页面数据
	private Customer customer=new Customer();
	@Override
	public Customer getModel() {
		// TODO Auto-generated method stub
		return customer;
	}

	// 以下代码是客户功能实现代码
	// 查询的所有的级别数据
	private List<BaseDict> levelList;
	// 查询的所有的来源数据
	private List<BaseDict> sourceList;
	// 查询的所有的行业数据
	private List<BaseDict> industryList;
	// 查询的所有客户数据
	private List<Customer> customerList;
	// 单个客户的数据
	private Customer customerFind;
	
	public Customer getCustomerFind() {
		return customerFind;
	}
	public List<Customer> getCustomerList() {
		return customerList;
	}
	public List<BaseDict> getLevelList() {
		return levelList;
	}
	public List<BaseDict> getSourceList() {
		return sourceList;
	}
	public List<BaseDict> getIndustryList() {
		return industryList;
	}



	// 新增客户页面功能
	@Action(value="customer_addUI",results={@Result(name="addUI",location="/jsp/customer/add.jsp")})	
	public String addUI()
	{
		/*步骤分析:
		 *  1 查询所有客户级别数据 006  返回的是list
		 *  2 查询所有信息来源数据 002  返回的是list
		 *  3 查询所有所属行业数据 001 返回的是list
		 *  4 把查询的三个list放在值栈中 到add.jsp页面把数据显示到下拉框中
		 * */
		
		//1 查询所有客户级别数据 006  返回的是list
		levelList=customerService.findLevel("006");
		//2 查询所有信息来源数据 002  返回的是list
		sourceList=customerService.findSource("002");
		//3 查询所有所属行业数据 001 返回的是list
		industryList=customerService.findIndustry("001");
		//4 放在值栈中   1  成员属性  2 ValueStack的api:push()
		
		return "addUI";
	}
	
	// 保存客户
	@Action(value="customer_save",results={@Result(name="toaction",type="redirectAction",location="customer_list")})
	public String save()
	{
		
		/* 步骤:
		 *  1 将页面的数据封装给modelDriver 
		 *  2 将封装好的对象进行传递保存
		 *  3 保存完毕,需要执行查询操作 将最新数据查询出来到页面展示
		 * */
		customerService.save(customer);
		return "toaction";
	}
	
	// 客户列表
	@Action(value="customer_list",results={@Result(name="tolist",location="/jsp/customer/list.jsp")})
	public String list()
	{
		/*步骤:
		 * 上集:
		 *  1 将条件中的客户级别数据查询出来 
		 *  2 将条件中的客户来源数据查询出来
		 *  3 将条件中的客户行业数据查询出来
		 *  4 把查询的数据放在值栈中,带到list.jsp页面显示数据在条件里的下拉框中
		 * 
		 * 下集:
		 *   1 将客户数据从数据库中全部查询出来  list
		 *   2 将查询出来的list数据放在值栈中,带到list.jsp页面显示所有客户的数据
		 * */
		
		//1 查询所有客户级别数据 006  返回的是list
		levelList=customerService.findLevel("006");
		//2 查询所有信息来源数据 002  返回的是list
		sourceList=customerService.findSource("002");
		//3 查询所有所属行业数据 001 返回的是list
		industryList=customerService.findIndustry("001");
		
		// 客户表的全查
		customerList=customerService.findAll();
		
		
		return "tolist";
	}
	
	// 条件查询
	@Action(value="customer_conditionFind",results={@Result(name="tolist",location="/jsp/customer/list.jsp")})
	public String conditionFind()
	{
		/*步骤:
		 *  1 将页面的数据封装给modelDriver的customer对象中
		 *  2 离线条件查询 (抛弃了传统的方式 使用hibernate提供的离线对象来完成条件查询)
		 * 
		 * */
		
		// 获取一个hibernate提供的离线对象
		DetachedCriteria dc = DetachedCriteria.forClass(Customer.class); // 默认语法:from Customer
		// 做条件  where cust_name like ?
		dc.add(Restrictions.like("cust_name", "%"+customer.getCust_name()+"%"));
		// 当没有请选择的时候,才添加条件
		if(customer.getCust_level().getDict_id()!=-1)
		{
			dc.add(Restrictions.eq("cust_level.dict_id", customer.getCust_level().getDict_id()));
		}
		// 当没有请选择的时候,才添加条件
		if(customer.getCust_source().getDict_id()!=-1)
		{
			dc.add(Restrictions.eq("cust_source.dict_id", customer.getCust_source().getDict_id()));
		}
		// 当没有请选择的时候,才添加条件
		if(customer.getCust_industry().getDict_id()!=-1)
		{
			dc.add(Restrictions.eq("cust_industry.dict_id", customer.getCust_industry().getDict_id()));
		}
		
		// 传递dc对象
		customerList=customerService.conditionFind(dc);
		
		// 客户级别,客户来源,客户所属行业的信息 再查一遍
		//1 查询所有客户级别数据 006  返回的是list
		levelList=customerService.findLevel("006");
		//2 查询所有信息来源数据 002  返回的是list
		sourceList=customerService.findSource("002");
		//3 查询所有所属行业数据 001 返回的是list
		industryList=customerService.findIndustry("001");
		
		return "tolist"; 
	}
	
	// 到修改页面
	@Action(value="customer_editUI",results={@Result(name="editUI",location="/jsp/customer/edit.jsp")})
	public String editUI()
	{
		/*步骤:
		 *  1 将来源,行业,级别查出来 带到edit.jsp的下拉框中显示
		 *  2 根据点击客户传递的id 查询该客户的数据 返回的是对象
		 *  3 需要将对象放在值栈中 带到edit.jsp页面进行数据的回显
		 * */
		
		// 客户级别,客户来源,客户所属行业的信息 再查一遍
		//1 查询所有客户级别数据 006  返回的是list
		levelList=customerService.findLevel("006");
		//2 查询所有信息来源数据 002  返回的是list
		sourceList=customerService.findSource("002");
		//3 查询所有所属行业数据 001 返回的是list
		industryList=customerService.findIndustry("001");
		
		// 根据点击客户传递的id 查询该客户的数据
		customerFind=customerService.findById(customer.getCust_id());
		return "editUI";
	}
	
	// 确定修改
	@Action(value="customer_update",results={@Result(name="toaction",type="redirectAction",location="customer_list")})
	public String update()
	{
		customerService.update(customer);
		
		return "toaction";
	}
	
	@Action(value="customer_delete",results={@Result(name="toaction",type="redirectAction",location="customer_list")})
	public String delete()
	{
		customerService.delete(customer);
		
		return "toaction";
	}
}
