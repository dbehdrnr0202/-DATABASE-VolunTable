package VolunEx;

import javax.xml.parsers.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.sql.*;


public class Parsing {
	//Parsing 시작, 공공데이터 API를 받아와서 파싱해준다(volTime, volInfo)
	//volTime 간단한 봉사 정보, volInfo는 봉사 상세정보
	Parsing() {
		// TODO Auto-generated method stub
		int pageIndex;
		int index = 0;
		int totalCount[] = {3555, 557};
		Connection con = null;
		Statement stmt = null;
		
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/vtex";
		String user = "user";
		String pw = "pwd";
		
		for (index = 0; index < 2; index++) {
			pageIndex = 1;
			try {
				String endPoint = "http://openapi.1365.go.kr/openapi/service/rest/VolunteerPartcptnService/";
				String operation[] = {"getVltrSearchWordList", "getVltrPartcptnItem"};
				String key = "serviceKey=XWGy58J7VSoy50ScKQ0o%2FXzqQr6FQyZ%2Fh4bWOB1aiUJA0XbW7aF77FLK2grNRu4ljMOnJlyJh%2BQugYZY5TNEPQ%3D%3D";
				String progrmBgnde[] = {"progrmBgnde=20190101", "progrmBgnde=20200101"};
				String progrmEndde[] = {"progrmEndde=20191231", "progrmEndde=20201231"};
				String numOfRows = "numOfRows=100";
				String progrmNo = "progrmRegistNo=";
				String pageNo = "pageNo=";
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Class.forName(driver);
				con = DriverManager.getConnection(url, user, pw);
				
				//여기서부터 url 연결한 후에 parsing 받음
				do {
					String urllist = endPoint+operation[0]+"?"+key+"&"+progrmBgnde[index]+"&"+progrmEndde[index]+"&"+numOfRows+"&"+pageNo+Integer.toString(pageIndex);
					Document doc = dBuilder.parse(urllist);
					doc.getDocumentElement().normalize();
					System.out.println("root element : "+doc.getDocumentElement().getNodeName());
					//여기서 들어가야하는 Tag 입력하기
					NodeList nList = doc.getElementsByTagName("item");
					//NodeList node = doc.getElementsByTagName("items");
					//totalCount = Integer.parseInt(getTagValue("totalCount", (Element)node.item(0)));
					System.out.println("num of list : "+nList.getLength());
					stmt = con.createStatement();
					int length = nList.getLength();
					for (int temp = 0;temp<length;temp++)	{
						Node nNode = nList.item(temp);
						if (nNode.getNodeType()==Node.ELEMENT_NODE)	{
							Element eElement = (Element)nNode;
							Volunteer voltime = new Volunteer();
							System.out.println("===========================");
							System.out.println("Node : "+ (temp+1));
							voltime.progrmRegistNo = Integer.parseInt(getTagValue("progrmRegistNo",eElement));
							voltime.actBeginTm = Integer.parseInt(getTagValue("actBeginTm", eElement));
							voltime.actEndTm = Integer.parseInt(getTagValue("actEndTm", eElement));
							voltime.actPlace = getTagValue("actPlace", eElement);
							voltime.actPlace = voltime.actPlace.replace("'", "\"");
							String pos = getTagValue("adultPosblAt", eElement);
							voltime.adultPosblAt = (pos.charAt(0) == 'Y')? true : false;
							pos = getTagValue("yngbgsPosblAt", eElement);
							voltime.yngbgsPosblAt = (pos.charAt(0) == 'Y')? true : false;
							voltime.gugunCd = getTagValue("gugunCd", eElement);;
							voltime.nanmmbyNm = getTagValue("nanmmbyNm", eElement);
							voltime.noticeBgnde = getTagValue("noticeBgnde", eElement);
							voltime.noticeEndde = getTagValue("noticeEndde", eElement);
							voltime.progrmBgnde = getTagValue("progrmBgnde", eElement);
							voltime.progrmEndde = getTagValue("progrmEndde", eElement);
							voltime.progrmRegistNo = Integer.parseInt(getTagValue("progrmRegistNo", eElement));
							voltime.progrmSj = getTagValue("progrmSj", eElement);
							voltime.progrmSj = voltime.progrmSj.replace("'", "\"");
							voltime.progrmSttusSe = Integer.parseInt(getTagValue("progrmSttusSe", eElement));
							voltime.sidoCd = getTagValue("sidoCd", eElement);
							voltime.url = getTagValue("url", eElement);
							System.out.println(voltime);
							String sql =insertVolTime(voltime);
							stmt.execute(sql);
							//progrmRegistNo
							try {
								String urlpgm = endPoint+operation[1]+"?"+key+"&"+progrmNo+voltime.progrmRegistNo;
								DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
								DocumentBuilder dbuilder = dbf.newDocumentBuilder();
								Document docu = dbuilder.parse(urlpgm);
								docu.getDocumentElement().normalize();
								NodeList nlist = docu.getElementsByTagName("item");
								Node node = nlist.item(0);
								//con = DriverManager.getConnection(url, user, pw);
								if (node.getNodeType()==Node.ELEMENT_NODE) {
									Element element = (Element)node;
									VolInfo volinfo = new VolInfo();
									volinfo.progrmRegistNo = getTagValue("progrmRegistNo", element);
									volinfo.actPlace = getTagValue("actPlace", element);
									volinfo.actPlace = volinfo.actPlace.replace("'", "\"");
									volinfo.actWkdy = getTagValue("actWkdy", element);
									String posible = getTagValue("adultPosblAt", element);
									volinfo.adultPosblAt = (posible.charAt(0)=='Y')? true : false;
									posible = getTagValue("yngbgsPosblAt", element);
									volinfo.yngbgsPosblAt = (posible.charAt(0)=='Y')? true : false;
									volinfo.email = getTagValue("email", element);
									volinfo.mnnstNm = getTagValue("mnnstNm", element);
									volinfo.nanmmbyNm = getTagValue("nanmmbyNm", element);
									volinfo.noticeBgnde = getTagValue("noticeBgnde", element);
									volinfo.noticeEndde = getTagValue("noticeEndde", element);
									volinfo.progrmBgnde = getTagValue("progrmBgnde", element);
									volinfo.progrmCn = getTagValue("progrmCn", element);
									volinfo.progrmCn = volinfo.progrmCn.replace("'", "\"");
									volinfo.progrmEndde = getTagValue("progrmEndde", element);
									volinfo.progrmRegistNo = getTagValue("progrmRegistNo", element);
									volinfo.progrmSj = getTagValue("progrmSj", element);
									volinfo.progrmSj = volinfo.progrmSj.replace("'", "\"");
									volinfo.progrmSttusSe = getTagValue("progrmSttusSe", element);
									volinfo.srvcClCode = getTagValue("srvcClCode", element);
									volinfo.telno = getTagValue("telno", element);
									String actbegintm = getTagValue("actBeginTm", element);
									volinfo.actBeginTm = Integer.parseInt(actbegintm);
									volinfo.actEndTm = Integer.parseInt(getTagValue("actEndTm", element));
									volinfo.rcritNmpr = Integer.parseInt(getTagValue("rcritNmpr", element));
									volinfo.appTotal = Integer.parseInt(getTagValue("appTotal", element));
									String sql2 = insertVolInfo(volinfo);
									stmt.execute(sql2);

									System.out.println(volinfo.progrmSj);
								}
							}catch(Exception e) {
								e.printStackTrace();
							}
						}
					}
					System.out.println("page number : "+pageIndex);
					pageIndex+=1;
				}while(pageIndex<=Math.ceil((double)totalCount[index]/100));
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	//volTime을 insert하는 SQL 문 return
	private static String insertVolTime(Volunteer voltime)	{
		String sql = "insert into volunteer values(";
		int adultpos = (voltime.adultPosblAt ==true)? 1:0;
		int yngpos = (voltime.yngbgsPosblAt==true)?1:0;
		sql = sql+"'"+
				voltime.progrmRegistNo+"'"+","+"'"+
				voltime.progrmSj+"'"+","+"'"+
				voltime.progrmBgnde+"'"+","+"'"+
				voltime.progrmEndde+"'"+","+"'"+
				voltime.actBeginTm+"'"+","+"'"+
				voltime.actEndTm+"'"+","+"'"+
				voltime.progrmSttusSe+"'"+","+"'"+
				voltime.sidoCd+"'"+","+"'"+
				voltime.gugunCd+"'"+","+"'"+
				voltime.noticeBgnde+"'"+","+"'"+
				voltime.noticeEndde+"'"+","+"'"+
				adultpos+"'"+","+"'"+
				yngpos+"'"+","+"'"+
				voltime.nanmmbyNm+"'"+","+"'"+
				voltime.actPlace+"'"+",";
		sql = sql +"'"+
				voltime.url+"'"+")";
		return sql;
	}
	//volInfo를 insert하는 SQL 문 return 
	private static String insertVolInfo(VolInfo volinfo)	{
		String sql = "insert into VolInfo values(";
		int adultpos = (volinfo.adultPosblAt ==true)? 1:0;
		int yngpos = (volinfo.yngbgsPosblAt==true)?1:0;
		sql = sql+"'"+
				volinfo.progrmRegistNo+"'"+","+"'"+
				volinfo.progrmSj+"'"+","+"'"+
				volinfo.progrmSttusSe+"'"+","+"'"+
				volinfo.progrmBgnde+"'"+","+"'"+
				volinfo.progrmEndde+"'"+","+
				volinfo.actBeginTm+","+//int
				volinfo.actEndTm+","+"'"+//int
				volinfo.noticeBgnde+"'"+","+"'"+
				volinfo.noticeEndde+"'"+","+
				volinfo.rcritNmpr+","+//int
				volinfo.appTotal+","+"'"+//int
				volinfo.actWkdy+"'"+","+"'"+
				volinfo.srvcClCode+"'"+","+
				adultpos+","+//boolean
				yngpos+","+"'"+//boolean
				volinfo.mnnstNm+"'"+","+"'"+
				volinfo.nanmmbyNm+"'"+","+"'"+
				volinfo.actPlace+"'"+","+"'"+
				volinfo.telno+"'"+","+"'"+
				volinfo.email+"'"+","+"'"+
				volinfo.progrmCn+"'"+")";
		return sql;
	}
	//XML 파일에서 태그에 맞는 데이터를 String형으로 return
	private static String getTagValue(String tag, Element eElement)	{
		NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
		
		Node nValue = (Node)nlList.item(0);
		if (nValue==null)
			return null;
		return nValue.getNodeValue();
	}

}