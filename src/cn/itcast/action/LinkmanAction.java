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

import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.domain.Customer;
import cn.itcast.domain.Linkman;
import cn.itcast.domain.PageBean;
import cn.itcast.service.CustomerService;
import cn.itcast.service.LinkmanService;

@Controller("linkmanAaction")
@Scope("prototype")
@ParentPackage("struts-default")
@Namespace("/")
public class LinkmanAction implements ModelDriven<Linkman>
{
	// 封装页面数据
	private Linkman linkman=new Linkman();
	@Override
	public Linkman getModel() {
		// TODO Auto-generated method stub
		return linkman;
	}
	
	
	@Autowired
	private CustomerService customerService;
	@Autowired
	private LinkmanService linkmanService;
	
	
	// 所有的客户
	private List<Customer> customerList;
	// 所有的联系人
	private List<Linkman> linkmanList;
	// 单个联系人
	private Linkman linkmanFind;
	
	// 分页数据
	private PageBean pb;
	
	public PageBean getPb() {
		return pb;
	}
	public Linkman getLinkmanFind() {
		return linkmanFind;
	}
	public List<Linkman> getLinkmanList() {
		return linkmanList;
	}
	public List<Customer> getCustomerList() {
		return customerList;
	}

	// 分页数据
	private Integer pageNumber=1;
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}


	private Integer pageSize=3;
	
	// 跳到新增页面
	@Action(value="linkman_addUI",results={@Result(name="addUI",location="/jsp/linkman/add.jsp")})
	public String addUI()
	{
		/*步骤:
		 * 1  获取所有的客户  list
		 * 2 把封装好的list放在值栈中 带到add.jsp页面显示到下拉框中
		 * */
		customerList = customerService.findAll();
		
		
		return "addUI";
	}

	// 保存
	@Action(value="linkman_save",results={@Result(name="toaction",type="redirectAction",location="linkman_list")})
	public String save()
	{
		/*步骤:
		 *  1 将modelDriver封装的对象数据进行传递
		 *  2 保存完毕,执行list方法 查询最新数据到页面展示
		 * 
		 * */
		linkmanService.save(linkman);
		
		return "toaction";
	}
	
	// 列表查询  不是分页
	/*@Action(value="linkman_list",results={@Result(name="tolist",location="/jsp/linkman/list.jsp")})
	public String list()
	{
		步骤:
		 * 1 查询所有的客户 返回的是list
		 * 2 将list放在值栈中 带到list.jsp页面显示到条件查询的下拉列表中
		 * 3 将联系人的数据全查 返回的list
		 * 4 将联系人的list放在值栈中 带到list.jsp页面展示联系人的信息
		 * 
		customerList = customerService.findAll();
		 要开始针对条件就要玩离线查询
		linkmanList=linkmanService.findAll();
		DetachedCriteria dc=DetachedCriteria.forClass(Linkman.class); // 默认语法:from Linkman
		// 添加条件  点击的是联系人列表就忽略这2个条件  点击的是筛选就加上这2个条件
		if(linkman.getLkm_name()!=null)
		{
			dc.add(Restrictions.like("lkm_name", "%"+linkman.getLkm_name()+"%"));
		}
		
		if(linkman.getCustomer()!=null && linkman.getCustomer().getCust_id()!=-1)
		{
			dc.add(Restrictions.eq("customer.cust_id", linkman.getCustomer().getCust_id()));
		}
		
		// 点击的是联系人列表做默认语法全查
		// 点击的是筛选做条件查
		linkmanList = linkmanService.findByDc(dc);
		
		
		return "tolist";
	}*/
	
	
	// 分页的
	@Action(value="linkman_list",results={@Result(name="tolist",location="/jsp/linkman/pageList.jsp")})
	public String list()
	{
		// 所有的客户
		customerList = customerService.findAll();
		 
		// 离线条件查询
		DetachedCriteria dc=DetachedCriteria.forClass(Linkman.class); // 默认语法:from Linkman
		// 添加条件  点击的是联系人列表就忽略这2个条件  点击的是筛选就加上这2个条件
		if(linkman.getLkm_name()!=null)
		{
			dc.add(Restrictions.like("lkm_name", "%"+linkman.getLkm_name()+"%"));
		}
		
		if(linkman.getCustomer()!=null && linkman.getCustomer().getCust_id()!=-1)
		{
			dc.add(Restrictions.eq("customer.cust_id", linkman.getCustomer().getCust_id()));
		}
		
		// 点击的是联系人列表做默认语法全查
		// 点击的是筛选做条件查 
		pb = linkmanService.findByDc(dc,pageNumber,pageSize);
	
		return "tolist";
	}
	
	// 跳转到修改页面
	@Action(value="linkman_editUI",results={@Result(name="editUI",location="/jsp/linkman/edit.jsp")})
	public String editUI()
	{
		/*步骤:
		 *  1 查询所有客户 返回list 带到edit页面显示
		 *  2 根据页面传递的id 将点击的联系人数据给查出来  返回的对象
		 *  3 将对象数据放在值栈中 带到edit.jsp页面回显
		 * */
		customerList = customerService.findAll();
		linkmanFind=linkmanService.findById(linkman.getLkm_id());
		
		return "editUI";
	}
	
	// 更新
	@Action(value="linkman_update",results={@Result(name="toaction",type="redirectAction",location="linkman_list")})
	public String update()
	{
		linkmanService.update(linkman);
		
		return "toaction";
	}
	
	// 删除
	@Action(value="linkman_delete",results={@Result(name="toaction",type="redirectAction",location="linkman_list")})
	public String delete()
	{
		linkmanService.delete(linkman);
		return "toaction";
	}
}
