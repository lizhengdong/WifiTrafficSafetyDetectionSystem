package buct.fjm.analyse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import buct.fjm.model.Pkg;
import buct.fjm.model.Pkguri;
import buct.fjm.model.Tpkg;
import buct.fjm.model.Tpkguri;
import buct.fjm.model.Uri;

/**
 * ���ļ�����
 * @author fjm
 *
 */
public class FileAnalyse {
	Map<String,Pkg> pkgMap;
	Map<String,Uri> uriMap;
	Map<String,Tpkg> tpkgMap;
	Map<String,List<String>> pkgUriMap;
	Map<String,List<String>> tpkgUriMap;
	HibernateUtil insertHibernate;
	HibernateUtil searchHibernate;
	
	public FileAnalyse(){
		pkgMap=new HashMap<String,Pkg>();
		uriMap=new HashMap<String,Uri>();
		tpkgMap=new HashMap<String,Tpkg>();
		pkgUriMap=new HashMap<String,List<String>>();
		tpkgUriMap=new HashMap<String,List<String>>();
		insertHibernate=new HibernateUtil();
		searchHibernate=new HibernateUtil();
	}
	public void Analyse(File file) throws IOException{
		//��ȡ�ļ�file
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr); ;
		try {
			
			//���ж�ȡ�ļ�file����Ϊline
			String line;
			try {
				int count=0;
				LineAnalyse lineAnalyse=new LineAnalyse();
				while ((line = br.readLine()) != null) {
					//����line
					lineAnalyse.Analyse(line);
					//�־û�lineAnalyse��ʵ����
					//lineAnalyse.insert();
					//count++;
					//System.out.println(count);
					Pkg pkg=lineAnalyse.getPkg();
					Uri uri=lineAnalyse.getUri();
					Tpkg tpkg=lineAnalyse.getTpkg();
					//Pkguri pkguri=lineAnalyse.getPkguri();
					//Tpkguri tPkgUri=lineAnalyse.getTpkguri();
					String pkgKey=null;
					String uriKey=null;
					String tpkgKey=null;
					
					if(pkg!=null){
						pkgKey=pkg.getNativeMac()+" "+pkg.getRemoteIp()+" "+pkg.getRemotePort()+" "+pkg.getFlowDirectioin()+" "+pkg.getProtocolType()+" "+pkg.getDateVisit().getTime();
						if(pkgMap.keySet().contains(pkgKey)){
							Pkg oldPkg=pkgMap.get(pkgKey);
							int oldAmount=Integer.parseInt(oldPkg.getFlowAmount());
							int newAmount=oldAmount+Integer.parseInt(pkg.getFlowAmount());
							oldPkg.setFlowAmount(newAmount+"");
							Timestamp fristTime=oldPkg.getFirstVisitTime();
							Timestamp lastTime=oldPkg.getLastVisitTime();
							if(pkg.getFirstVisitTime().getTime()>lastTime.getTime()){
								oldPkg.setLastVisitTime(new Timestamp(pkg.getFirstVisitTime().getTime()));
							}
							if(pkg.getFirstVisitTime().getTime()<fristTime.getTime())
							{
								oldPkg.setFirstVisitTime(new Timestamp(pkg.getFirstVisitTime().getTime()));
							}
							pkgMap.put(pkgKey, oldPkg);
						}else{
							pkgMap.put(pkgKey, pkg);
						}
					}else{
						continue;
					}
					
					if(uri!=null){
						uriKey=uri.getHost()+" "+uri.getPath()+" "+uri.getPort();
						if(!uriMap.keySet().contains(uriKey)){
							uriMap.put(uriKey, uri);
						}
					}
					
					if(tpkg!=null){
						tpkgKey=tpkg.getTnativeMac()+" "+tpkg.getTremoteIp()+" "+tpkg.getTremotePort()+" "+tpkg.getTflowDirection()+" "+tpkg.getTprotocolType();
						if(tpkgMap.keySet().contains(tpkgKey)){
							Tpkg oldTpkg=tpkgMap.get(tpkgKey);
							int oldAmount=Integer.parseInt(oldTpkg.getTflowAmount());
							int newAmount=oldAmount+Integer.parseInt(tpkg.getTflowAmount());
							oldTpkg.setTflowAmount(newAmount+"");
							Timestamp fristTime=oldTpkg.getTfirstVisitTime();
							Timestamp lastTime=oldTpkg.getTlastVisitTime();
							if(tpkg.getTfirstVisitTime().getTime()>lastTime.getTime()){
								oldTpkg.setTlastVisitTime(new Timestamp(tpkg.getTfirstVisitTime().getTime()));
							}
							if(tpkg.getTfirstVisitTime().getTime()<fristTime.getTime())
							{
								oldTpkg.setTfirstVisitTime(new Timestamp(tpkg.getTfirstVisitTime().getTime()));
							}
							//System.out.println("oldAmount="+oldTpkg.getTflowAmount());
							tpkgMap.put(tpkgKey, oldTpkg);
						}else{
							//System.out.println("newAmount="+tpkg.getTflowAmount());
							tpkgMap.put(tpkgKey, tpkg);
						}
					}
					
					if(pkgKey!=null&&uriKey!=null){
						if(pkgUriMap.keySet().contains(pkgKey)){
							List<String> temp=pkgUriMap.get(pkgKey);
							if(!temp.contains(uriKey)){
								temp.add(uriKey);
								pkgUriMap.put(pkgKey, temp);
							}
						}else{
							List<String> temp=new ArrayList<String>();
							temp.add(uriKey);
							pkgUriMap.put(pkgKey, temp);
						}
					}
					
					if(tpkgKey!=null&&uriKey!=null){
						if(tpkgUriMap.keySet().contains(tpkgKey)){
							List<String> temp=tpkgUriMap.get(tpkgKey);
							if(!temp.contains(uriKey)){
								temp.add(uriKey);
								tpkgUriMap.put(tpkgKey, temp);
							}
						}else{
							List<String> temp=new ArrayList<String>();
							temp.add(uriKey);
							tpkgUriMap.put(tpkgKey, temp);
						}
					}
				}
				insert();
				//�ر�����
				insertHibernate.releaseConnection();
				searchHibernate.releaseConnection();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}finally {
			br.close();
        }
        
	}
	/**
	 * �������ݿ�
	 * @author fjm
	 */
	private void insert(){
		Map<String,Integer> pkgM=new HashMap<String,Integer>();
		Map<String,Integer> uriM=new HashMap<String,Integer>();
		Map<String,Integer> tpkgM=new HashMap<String,Integer>();
		//����pkg
		System.out.println("start do sql");
		insertHibernate.beginTransaction();
		for(String pkgKey:pkgMap.keySet()){
			Pkg pkg=pkgMap.get(pkgKey);
			
			//searchHibernate.beginTransaction();
			String pkgHql="from Pkg as model where model.nativeMac='"+pkg.getNativeMac()+"' and model.remoteIp='"+pkg.getRemoteIp()+"' and model.remotePort="+pkg.getRemotePort()+" and model.protocolType="+pkg.getProtocolType()+" and model.flowDirectioin="+pkg.getFlowDirectioin()+" and model.dateVisit='"+pkg.getDateVisit().toString().substring(0, 10)+"'";
			List pkgList=searchHibernate.Search(pkgHql);
			//searchHibernate.endTransaction(false);
			if(pkgList.size()>0){
				Pkg oldPkg=(Pkg)pkgList.get(0);
				pkg.setPackageId(oldPkg.getPackageId());
				int oldAmount=Integer.parseInt(oldPkg.getFlowAmount());
				int newAmount=oldAmount+Integer.parseInt(pkg.getFlowAmount());
				pkg.setFlowAmount(newAmount+"");
				Timestamp fristTime=oldPkg.getFirstVisitTime();
				Timestamp lastTime=oldPkg.getLastVisitTime();
				if(pkg.getFirstVisitTime().getTime()>lastTime.getTime()){
					pkg.setLastVisitTime(new Timestamp(pkg.getFirstVisitTime().getTime()));
				}
				if(pkg.getFirstVisitTime().getTime()<fristTime.getTime())
				{
					pkg.setFirstVisitTime(new Timestamp(pkg.getFirstVisitTime().getTime()));
				}
				insertHibernate.getSession().update(pkg);
			}else{
				insertHibernate.getSession().save(pkg);
			}
			pkgM.put(pkgKey, pkg.getPackageId());
		}
		insertHibernate.endTransaction(true);
		//����uri
		insertHibernate.beginTransaction();
		for(String uriKey:uriMap.keySet()){
			Uri uri=uriMap.get(uriKey);
			String uriHql="from Uri as model where model.host='"+uri.getHost()+"' and model.path='"+uri.getPath()+"' and model.port="+uri.getPort();
			List uriList=searchHibernate.Search(uriHql);
			if(uriList.size()==0)
			{
				insertHibernate.getSession().save(uri);
				uriM.put(uriKey, uri.getUriId());
			}else{
				uri=(Uri)(uriList.get(0));
				uriM.put(uriKey, uri.getUriId());
			}
		}
		insertHibernate.endTransaction(true);
		//����tpkg
		insertHibernate.beginTransaction();
		for(String tpkgKey:tpkgMap.keySet()){
			Tpkg tpkg=tpkgMap.get(tpkgKey);
			
			String tpkgHql="from Tpkg as model where model.tnativeMac='"+tpkg.getTnativeMac()+"' and model.tremoteIp='"+tpkg.getTremoteIp()+"' and model.tremotePort="+tpkg.getTremotePort()+" and model.tprotocolType="+tpkg.getTprotocolType()+" and model.tflowDirection="+tpkg.getTflowDirection();
			List tpkgList=searchHibernate.Search(tpkgHql);
			
			if(tpkgList.size()>0){
				Tpkg oldTpkg=(Tpkg)tpkgList.get(0);
				tpkg.setTpackageId(oldTpkg.getTpackageId());
				int oldAmount=Integer.parseInt(oldTpkg.getTflowAmount());
				int newAmount=oldAmount+Integer.parseInt(tpkg.getTflowAmount());
				tpkg.setTflowAmount(newAmount+"");
				Timestamp fristTime=oldTpkg.getTfirstVisitTime();
				Timestamp lastTime=oldTpkg.getTlastVisitTime();
				if(tpkg.getTfirstVisitTime().getTime()>lastTime.getTime()){
					tpkg.setTlastVisitTime(new Timestamp(tpkg.getTfirstVisitTime().getTime()));
				}
				if(tpkg.getTfirstVisitTime().getTime()<fristTime.getTime())
				{
					tpkg.setTfirstVisitTime(new Timestamp(tpkg.getTfirstVisitTime().getTime()));
				}
				insertHibernate.getSession().update(tpkg);
			}else{
				insertHibernate.getSession().save(tpkg);
			}
			
			//insertHibernate.getSession().save(tpkg);
			tpkgM.put(tpkgKey, tpkg.getTpackageId());
		}
		insertHibernate.endTransaction(true);
		//����pkguri
		insertHibernate.beginTransaction();
		for(String pkguriKey:pkgUriMap.keySet()){
			List<String>uriList=pkgUriMap.get(pkguriKey);
			Pkg pkg=new Pkg();
			pkg.setPackageId(pkgM.get(pkguriKey));
			
			for(String uriKey:uriList){
				Uri uri=new Uri();
				uri.setUriId(uriM.get(uriKey));
				
				String pkguriHql="from Pkguri as model where model.pkg="+pkg.getPackageId()+" and model.uri="+uri.getUriId();
				List pkguriList=searchHibernate.Search(pkguriHql);
				
				if(pkguriList.size()==0){
					Pkguri pkguri=new Pkguri();
					pkguri.setPkg(pkg);
					pkguri.setUri(uri);
					insertHibernate.getSession().save(pkguri);
				}
			}
		}
		insertHibernate.endTransaction(true);
		//����tpkguri
		insertHibernate.beginTransaction();
		for(String tpkguriKey:tpkgUriMap.keySet()){
			List<String>uriList=tpkgUriMap.get(tpkguriKey);
			Tpkg tpkg=new Tpkg();
			tpkg.setTpackageId(tpkgM.get(tpkguriKey));
			
			for(String uriKey:uriList){
				Uri uri=new Uri();
				uri.setUriId(uriM.get(uriKey));
				
				String pkguriHql="from Tpkguri as model where model.tpkg="+tpkg.getTpackageId()+" and model.uri="+uri.getUriId();
				List tpkguriList=searchHibernate.Search(pkguriHql);
				
				if(tpkguriList.size()==0){
					Tpkguri tpkguri=new Tpkguri();
					tpkguri.setTpkg(tpkg);
					tpkguri.setUri(uri);
					insertHibernate.getSession().save(tpkguri);
				}
			}
		}
		insertHibernate.endTransaction(true);
		System.out.println("end do sql");
	}
}
