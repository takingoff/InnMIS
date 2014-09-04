/**
 * projectName: InnMIS
 * 
 * fileName: Test.java
 * 
 * author : tangli <tanglidehaizi@gamil.com>
 * 
 * createTime :2014 2014-4-8 下午10:58:20
 * 
 * version : V1.0
 * 
 */
package tang.li.inn.mis;

import java.util.List;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tang.li.inn.domain.service.entered.EnteredInfoService;
import tang.li.inn.domain.service.staff.StaffService;
import tang.li.inn.entity.entered.EnteredInfo;
import tang.li.inn.entity.order.Order;
import tang.li.inn.entity.room.RoomType;
import tang.li.inn.entity.staff.Staff;
import tang.li.inn.infrastructure.dao.entered.EnteredInfoDao;
import tang.li.inn.infrastructure.dao.order.OrderDao;
import tang.li.inn.infrastructure.dao.room.RoomTypeDao;
import tang.li.inn.infrastructure.dao.staff.StaffDao;
import tang.li.inn.infrastructure.exception.InnErrorsUtil;
import tang.li.inn.infrastructure.exception.InnException;
import tang.li.inn.infrastructure.util.PaginationSupport;

/**
 *<description>
 *@author tangli <tanglidehaizi@gamil.com>
 *@version V1.0 
 *@see 
 *@since
*/
public class Test extends TestCase
{

	static ApplicationContext context;
	static SessionFactory sf;
	static Logger log = Logger.getLogger(Test.class);
//	static Session s ;
	@Override
	protected void setUp() throws Exception
	{
		context = new ClassPathXmlApplicationContext("classpath*:applicationContext-*.xml");
		sf = (SessionFactory) context.getBean("sessionFactory");
	}
	@Override
	protected void tearDown() throws Exception
	{
		sf.close();
	}

	
	public void TtestAdd()
	{

		Session s = sf.openSession();
		
//		Staff m = new Staff();
//		m.setName("xisdfhonbg");
//		m.setPassword("ndssddad");
//		m.setLevel(1);
//		s.save(m);
		
		
		for(int i = 0 ;i < 165 ;i  ++){
		
		RoomType rt = new RoomType();
		rt.setDayPrice(i*10);
		rt.setHourPrice(i);
		rt.setDescription("description");
		rt.setName("name"+i);
		s.save(rt);
		
		
//		Room r = new Room();
//		r.setDescription("roomm");
//		r.setState(1);
//		r.setRoomType(rt);
//		r.setName("12");
//		s.save(r);
		}
		
		
//		EnteredInfo ei = new EnteredInfo();
//		ei.setCheckOut(false);
//		ei.setHourRoom(false);
//		ei.setDescription("descirp");
//		ei.setName("name");
//		ei.setPhoneNumber("2341341234");
//		ei.setRoom(r);
//		s.save(ei);
		
		
//		EnteredInfo ei  = (EnteredInfo) s.get(EnteredInfo.class,"986b5d1c-194a-401f-91bf-7779b7fb9335");
//		System.out.println(ei.getRoom().getRoomType().getDescription());
		
		
		
//		Order o = new Order();
//		o.setDescription("dsafdsf");
//		o.setEnteredInfo(ei);
//		o.setExtendHours(2);
//		o.setName("name");
//		o.setPhoneNumber("12341324");
//		o.setRoomType(rt);
//		o.setState(1);
//		o.setWillEnterDays(2);
//		o.setWillEnterDate("2014-6-8");
//		s.save(o);
		
		
//		BillPay bp = new BillPay();
//		bp.setBill(232.5);
//		bp.setDescription("biilldase");
//		bp.setEnteredInfo(ei);
//		bp.setPayWay(1);
//		bp.setRoom(r);
//		s.save(bp);
//		
//		BillConsume bc = new BillConsume();
//		bc.setBill(232.5);
//		bc.setDescription("biilldase");
//		bc.setEnteredInfo(ei);
//		bc.setRoom(r);
//		s.save(bc);
//		
//		BillRoom br = new BillRoom();
//		br.setBill(232.5);
//		br.setEnteredInfo(ei);
//		br.setRoom(r);
//		br.setRoomExpenseType(1);
//		s.save(br);
		
		
		s.flush();
		s.close();
	}
	
	public void TtestGet()
	{
		Session s = sf.openSession();
		Staff m = (Staff) s.get(Staff.class,Long.parseLong("1"));
		System.out.println(m.getName());
		s.close();
	}
	
	
	public static void saveStaff() throws InnException
	{
		StaffService ss = (StaffService) context.getBean("staffDao");
		ss.testTrasaction();
	}
	
	public void TtestTransaction() throws InnException
	{
		saveStaff();
		
	}
	
	
	
	
	public void TtestState()
	{
		
		Session s = sf.openSession();
		Staff m = (Staff) s.get(Staff.class,Long.parseLong("5"));
		s.delete(m);
//		s.evict(m);
		System.out.println(m.getId());
//		m.setName("tang");
		s.save(m);
		s.flush();
		s.close();
		
	}
	
	public void TtestLog() throws InnException
	{
		
		throw new InnException(InnErrorsUtil.getInnError("inn.dao.pagedFind"),null);
		
	}
	
	public void testDao() throws InnException
	{
		StaffDao s = (StaffDao)context.getBean("staffDao");
		OrderDao od = (OrderDao)context.getBean("orderDao");
		EnteredInfoDao ed = (EnteredInfoDao)context.getBean("enteredInfoDao");
//		BillRoomDao brd = (BillRoomDao)context.getBean("billRoomDao");
		
		Order o = new Order();
		o.setDescription("description");
		o.setExtendHours(1);
		o.setName("na,e");
		o.setPhoneNumber("135191834");
		o.setState(1);
		o.setWillEnterDate("2014-5-8");
		o.setWillEnterDays(2);
		RoomType ry = new RoomType();
		ry.setId("abe0bff7-c749-4f72-8c1f-f9fed903087e");
		o.setRoomType(ry);
		od.save(o);
		
		EnteredInfoService eis = (EnteredInfoService)context.getBean("enteredInfoService");
		
//		System.out.println(s.findByProperty("genTime","121"));
//		System.out.println(s.executeUpdate("update Staff set name = ? where id =  ? ","name","37726e01-3f6e-4ab4-9354-da71fd0db605"));
		
		
//		System.out.println((Double)s.executeHQLQuery(InnHQL.SUM_BILLPAY_ENTERED_SPECIFIC,"1ccf36ea-ac04-4e6e-a42f-97a8b40f25ea"));
//		System.out.println(s.executeHQLQuery(InnHQL.SUM_BILLPAY_ENTERED_SPECIFIC,"1ccf36ea-ac04-4e6ea"));
		
		
//		for(EnteredInfo ei : ed.find(InnHQL.ENTEREDINFO_NOW))
//		{
//			try
//			{
//				if(ei.getName().equals("ee"))
//				{
//					System.out.println(ei.getName()+")))))))))))))");;
//					eis.genBillRoom(ei);
//				}
//			}
//			catch (Exception e)
//			{
//				e.printStackTrace();
//			}
//			
//		}
		
//		EnteredInfo ei = eis.getById("e625210a-0264-48da-873d-d21e65372a39");
//		eis.genBillRoom(ei);
		
		
		
//		System.out.println(ed.executeHQLQuery(InnHQL.ENTEREDINFO_NOW_BILLROOM_COUNT,"0a067881-88ec-4b29-a8ae-965b11d95951"));
//		
//		for(BillRoom br: brd.findRecent(InnHQL.ENTEREDINFO_RECENT_BILLROOM,1,"0a067881-88ec-4b29-a8ae-965b11d95951"))
//		{
//			System.out.println(br.getBill());
//		}
		
	}
	
	
	public void TtestString() throws Exception
	{
		
		String s1 = "abcd";
		String s2 = "aBcD";
		if(s1.compareToIgnoreCase(s2) == 0)
		{
			System.out.println("eaula");
		}
		
		System.out.println(s2.indexOf("c"));
		System.out.println(s2.indexOf("DFSDF"));
		System.out.println(s2.substring(1,2));
//		System.out.println(s2.substring(1,-1));
		System.out.println(s2.substring(1));
		
		
		System.out.println(RoomType.class.getDeclaredField("dayPrice").getType());
		System.out.println(EnteredInfo.class.getDeclaredField("isCheckOut").getType());
		
		
		System.out.println("--------------------------");
		String type = "ma.name";
		String[] names = type.split("\\.");
		
		for(String name :names)
			System.out.println(name);
		
		
//		System.out.println(DateTimeUtil.convertNowToInnDateFormat(2));
//		System.out.println(DateTimeUtil.convertNowToInnDateTimeFormat(1,2,3));
//		System.out.println(DateTimeUtil.convertDateStringToInnDateFormat("2002-2-02",2));
//		System.out.println(DateTimeUtil.convertDateTimeStringToInnDateTimeFormat("2002-2-02 22:23:12",2,2,2));
		
		
	}
	

	public void TtestPagination() throws InnException
	{
		RoomTypeDao rtd = (RoomTypeDao)context.getBean("roomTypeDao");
		PaginationSupport<RoomType> ps =  rtd.pagedFind(8,5,"from RoomType where 1= 1 order by id ",false,RoomType.class);
		List<RoomType> list = ps.getItems();
		for(RoomType rt : list)
		{
			System.out.println(rt.getId());
		}
		
	}
	
	
	
	
	
}
