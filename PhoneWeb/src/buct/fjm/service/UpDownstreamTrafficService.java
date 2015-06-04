package buct.fjm.service;

@SuppressWarnings("unchecked")
public interface UpDownstreamTrafficService {
//	public Long getUpstreamTrafficCount(String nativeMac,String remoteIp);
//	public Long getDownstreamTrafficCount(String nativeMac,String remoteIp);
//	public Long getAllstreamTrafficCount(String nativeMac,String remoteIp);
//	public Long getUpstreamTrafficCount(String nativeMac);
//	public Long getDownstreamTrafficCount(String nativeMac);
//	public Long getAllstreamTrafficCount(String nativeMac);
	public Long getAllUpstreamTrafficCount();
	public Long getAllDownstreamTrafficCount();
	public Long getAllstreamTrafficCount();
//	public Long getTheAllstreamTrafficCount();
}
